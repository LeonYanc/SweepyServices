package com.sweepy.urlService;

import com.sweepy.RedisCache.SequenceIdService;
import com.sweepy.RedisCache.redisService;
import com.sweepy.converter.base62Converter;
import com.sweepy.converter.randomConverter;
import com.sweepy.database.urlTable;
import com.sweepy.exception.emptyEntry;
import com.sweepy.exception.nullRequest;
import com.sweepy.repository.urlRepository;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Service
public class urlServiceImpl implements urlService {

    @Autowired
    private urlRepository urlRepository;
    @Autowired
    private RedisTemplate<String, Long> redisTemplateId;
    private redisService redisService;
    long defaultTime = 5;

    private String protocol = "http://";


    private base62Converter base62Converter = new base62Converter();
    private randomConverter randomConverter = new randomConverter();


    public urlServiceImpl(urlRepository urlRepository, redisService redisService) {
        this.urlRepository = urlRepository;
        this.redisService = redisService;
    }

    @Override
    @Transactional
    public String longToShort(String longUrl, String method) {
        String shortUrl = "";
        urlTable entry = null;

        String shortInRedis = (String) redisService.get(longUrl);
        if (shortInRedis == null) {
            entry = urlRepository.findByLongUrl(longUrl);

            if (!isValid(protocol + longUrl)) {
                return "You must enter a valid long Url.";
            } else if (entry == null || !Objects.equals(entry.getMethod(), method)) {
                if (Objects.equals(method, "base62")) {
                    shortUrl = base62Converter.encode(longUrl);
                } else if (Objects.equals(method, "random")) {
                    shortUrl = randomConverter.encode(longUrl);
                } else {
                    return "Please enter a valid encode method.";
                }

                SequenceIdService ser = new SequenceIdService(redisTemplateId);
                Long Id = ser.getNextSequenceByLua();
                entry = new urlTable(Id,longUrl, shortUrl, method);

                saveToRedis(longUrl, shortUrl, defaultTime);
                urlRepository.save(entry);
                return shortUrl;
            } else {

                shortUrl = entry.getShortUrl();
                saveToRedis(longUrl, shortUrl, defaultTime);
                return shortUrl;
            }
        } else {
            if (isValid(protocol + longUrl)) {
                redisService.expire(longUrl, defaultTime);
                return shortInRedis;
            }
            return "You must enter a valid long Url.";

        }

    }

    @Override
    @Transactional
    public String shortToLong(String shortUrl, HttpServletResponse response) throws IOException {
        urlTable entry;
        String longUrl;

        String longInRedis = (String) redisService.get(shortUrl);

        if (longInRedis == null) {


            try {
                entry = urlRepository.findByShortUrl(shortUrl);
                if (shortUrl == "") {
                    throw new emptyEntry("You must enter a valid short Url.");

                }
                if (entry == null) {
                    throw new nullRequest("The short Url: " + shortUrl + " is invalid");
                }
                longUrl = protocol + entry.getLongUrl();
                if (isValid(longUrl)) {
                    response.sendRedirect(longUrl);
                    saveToRedis(entry.getLongUrl(), shortUrl, defaultTime);
                }

            } catch (nullRequest | emptyEntry | IOException e) {
                return e.getMessage();
            }
        } else {
            longUrl = protocol + longInRedis;
            if (isValid(longUrl)) {
                response.sendRedirect(longUrl);
            } else {
                return "Please enter a valid url";
            }
            redisService.expire(longInRedis, defaultTime);
        }


        return null;
    }



    private void saveToRedis(String longUrl, String shortUrl, long time) {
        System.out.println("outside");
        redisService.setLTS(longUrl, shortUrl, time);
        System.out.println("inlong");
        redisService.setSTL(longUrl, shortUrl, time);
        System.out.println("inshort");
    }

    private String getShortUrl(String longUrl) {
        String shortUrl = (String) redisService.get(longUrl);
        if (shortUrl != null) {
            redisService.expire(longUrl, 5);
        }
        return shortUrl;
    }


    private String getLongUrl(String shortUrl) {
        String longUrl = (String) redisService.get(shortUrl);
        if (longUrl != null) {
            redisService.expire(shortUrl, 5);
        }
        return longUrl;
    }

    private boolean isValid(String url)
    {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}

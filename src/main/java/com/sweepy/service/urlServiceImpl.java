package com.sweepy.service;

import com.sweepy.converter.base62Converter;
import com.sweepy.converter.randomConverter;
import com.sweepy.database.urlTable;
import com.sweepy.exception.emptyEntry;
import com.sweepy.exception.nullRequest;
import com.sweepy.repository.urlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class urlServiceImpl implements urlService {

    @Autowired
    private urlRepository urlRepository;

    private String prefix = "http://www.sweepy.com/";


    private base62Converter base62Converter = new base62Converter();
    private randomConverter randomConverter = new randomConverter();


    public urlServiceImpl(com.sweepy.repository.urlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    @Transactional
    public String longToShort(String longUrl, String method) {
        String shortUrl = "";
        urlTable entry = urlRepository.findByLongUrl(longUrl);
        if (longUrl == "") {
            return "You must enter a valid long Url.";
        } else if (entry == null || !Objects.equals(entry.getMethod(), method)){
            if (Objects.equals(method, "base62")) {
                shortUrl = base62Converter.encode(longUrl);
            } else if (Objects.equals(method, "random")) {
                shortUrl = randomConverter.encode(longUrl);
            } else {
                return "Please enter a valid encode method.";
            }
            entry = new urlTable(longUrl, shortUrl, method);
            urlRepository.save(entry);
            return  prefix + shortUrl;
        } else {
            shortUrl = entry.getShortUrl();
            return prefix + shortUrl;
        }

    }

    @Override
    @Transactional
    public String shortToLong(String shortUrl) {
        urlTable entry;
        String longUrl;
        entry = urlRepository.findByShortUrl(shortUrl);

        try {
            if (shortUrl == "") {
                throw new emptyEntry("You must enter a valid short Url.");

            }
            if (entry == null) {
                throw new nullRequest("The short Url: " + shortUrl + " is invalid");
            }
        } catch (nullRequest | emptyEntry e) {
            return e.getMessage();
        }

        longUrl = entry.getLongUrl();

        return longUrl;
    }


}

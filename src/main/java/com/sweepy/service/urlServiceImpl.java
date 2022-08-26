package com.sweepy.service;

import com.sweepy.converter.base62Converter;
import com.sweepy.converter.randomConverter;
import com.sweepy.database.urlTable;
import com.sweepy.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class urlServiceImpl implements urlService {

    @Autowired
    private UrlRepository urlRepository;
    private base62Converter base62Converter = new base62Converter();
    private randomConverter randomConverter = new randomConverter();


    public urlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    @Transactional
    public String longToShort(String longUrl, String method) {
        String shortUrl = "";

        if (Objects.equals(method, "base62")) {
            shortUrl = base62Converter.encode(longUrl);
        } else {
            shortUrl = randomConverter.encode(longUrl);
        }

        urlTable entry = new urlTable(longUrl, shortUrl, method);
        urlRepository.save(entry);
        return "http://www.sweepy.com/" + shortUrl;
    }

    @Override
    @Transactional
    public String shortToLong(String shortUrl, String method) {
        String longUrl = "";

        if (Objects.equals(method, "base62")) {
            longUrl = base62Converter.decode(shortUrl);
        } else {
            longUrl = randomConverter.decode(shortUrl);
        }

        return longUrl;
    }


}

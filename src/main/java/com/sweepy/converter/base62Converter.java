package com.sweepy.converter;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class base62Converter {

    HashMap<String, String> hm = null;
    char map[] = null;

    public base62Converter() {
        this.hm     = new HashMap<>();
        this.map    = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    }

    public String encode(String longUrl) {



        if (longUrl.equals(""))
            return "";

        StringBuilder sb = new StringBuilder();
        int sum = 0;

        for (char c : longUrl.toCharArray())
            sum += c;

        while (sum > 0) {
            sb.append(map[sum % 62]);
            sum /= 62;
        }

        String shortUrl = sb.toString();
        this.hm.put(shortUrl, longUrl);
        return shortUrl;
    }


    public String decode(String shortUrl) {

        // **** sanity check(s) ****
        if (shortUrl.equals(""))
            return "";

        // **** return longUrl ****
        return this.hm.get(shortUrl);
    }
}


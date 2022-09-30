package com.sweepy.converter;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Base62Converter {

    HashMap<String, String> hm = null;
    char map[] = null;

    public Base62Converter() {
        this.hm     = new HashMap<>();
        this.map    = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    }

    public String encode(Integer Id) {

        StringBuilder sb = new StringBuilder();

        while (Id > 0) {
            sb.append(map[Id % 62]);
            Id /= 62;
        }

        String shortUrl = sb.toString();
        return shortUrl;
    }
}


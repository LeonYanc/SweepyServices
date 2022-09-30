package com.sweepy.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomConverter {

    Map<Integer, String> map = null;
    Random r = null;
    public RandomConverter() {
        this.map= new HashMap<>();
        this.r = new Random();
    }
    public String encode(String longUrl) {


        int key = r.nextInt(Integer.MAX_VALUE);

        while (map.containsKey(key)) {
            key = r.nextInt(Integer.MAX_VALUE);
        }
        map.put(key, longUrl);
        return "" + key;
    }

}

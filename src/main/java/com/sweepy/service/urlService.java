package com.sweepy.service;

import com.sweepy.database.urlTable;

public interface urlService {
    String longToShort (String longUrl, String method);
    String shortToLong (String shortUrl, String method);
}

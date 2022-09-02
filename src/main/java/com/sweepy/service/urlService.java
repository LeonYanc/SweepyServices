package com.sweepy.service;

import com.sweepy.database.urlTable;
import com.sweepy.exception.nullRequest;

public interface urlService {
    String longToShort (String longUrl, String method);
    String shortToLong (String shortUrl);
}

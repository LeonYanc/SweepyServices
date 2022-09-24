package com.sweepy.urlService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface urlService {
    String longToShort (String longUrl, String method);
    String shortToLong (String shortUrl, HttpServletResponse response) throws IOException;
}

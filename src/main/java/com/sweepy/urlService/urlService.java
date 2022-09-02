package com.sweepy.urlService;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

public interface urlService {
    String longToShort (String longUrl, String method);
    String shortToLong (String shortUrl, HttpServletResponse response) throws IOException;
}

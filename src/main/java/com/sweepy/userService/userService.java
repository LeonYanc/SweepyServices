package com.sweepy.userService;

import javax.servlet.http.HttpServletResponse;

public interface userService {
    String register(String username, String password);
    boolean login(String username, String password);
}

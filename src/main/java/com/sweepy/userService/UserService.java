package com.sweepy.userService;

public interface UserService {
    String register(String username, String password);
    boolean login(String username, String password);
}

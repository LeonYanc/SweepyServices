package com.sweepy.userService;

import com.sweepy.database.user;
import com.sweepy.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Override
    public String register(String username, String password) {

        System.out.println("register");
        if (userRepository.findByUsername(username) != null) {
            System.out.println(userRepository.findByUsername(username));
            return "the username has already been taken, please choose another one or login";
        }
        user tmp = new user(username, passwordEncoder.encode(password), "user");

        userRepository.save(tmp);
        return " Registered Successfully";
    }


    @Override
    public boolean login(String username, String password) {

        Authentication auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (auth.isAuthenticated()) {
            return true;
        } else {
            return false;
        }
    }
}

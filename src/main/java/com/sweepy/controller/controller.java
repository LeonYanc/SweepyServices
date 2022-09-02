package com.sweepy.controller;

import com.sweepy.exception.nullRequest;
import com.sweepy.urlService.urlService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class controller {

    private urlService urlService;

    public controller (urlService urlService) {
        this.urlService = urlService;
    }
    @PostMapping("/longToShort")
    public String longtoShort(@Param("method") String method, @Param("url") String url) {
        return  urlService.longToShort(url, method);
    }

    @GetMapping("/goto")
    public String shortToLong(@Param("url") String url, HttpServletResponse response) throws nullRequest, IOException {

        return urlService.shortToLong(url, response);
    }




}

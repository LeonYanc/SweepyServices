package com.sweepy.controller;

import com.sweepy.database.urlTable;
import com.sweepy.service.urlService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    private urlService urlService;

    public controller (urlService urlService) {
        this.urlService = urlService;
    }
    @GetMapping("/longToShort")
    public String longtoShort(@Param("method") String method, @Param("url") String url) {
        return urlService.longToShort(url, method);
    }

    @GetMapping("/shortToLong")
    public String shortToLong(@Param("method") String method, @Param("url") String url) {
        return urlService.shortToLong(url, method);
    }




}

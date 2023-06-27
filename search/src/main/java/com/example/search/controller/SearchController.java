package com.example.search.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.search.entity.SearchRes;
import com.example.search.service.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class SearchController {

    private final Service searchService;
    @Autowired
    public SearchController(Service search) {
        this.searchService = search;
    }

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {
        //TODO
        return new ResponseEntity<>("this is search service", HttpStatus.OK);
    }


    @HystrixCommand(fallbackMethod = "getResultFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
    })

    @GetMapping("/weather/search/result")
    public ResponseEntity<?> getInfoOfStudentAndUniversity (@RequestParam(value = "id") long id, @RequestParam(value = "universityName") String name) {
        SearchRes result = searchService.getStudentUniversity(id, name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

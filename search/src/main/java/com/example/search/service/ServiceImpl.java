package com.example.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.search.entity.SearchRes;
import com.example.search.entity.Student;
import com.example.search.entity.University;

public class ServiceImpl implements Service{


    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    private RestTemplate restTemplate;

    @Autowired
    public ServiceImpl(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @Override
    public SearchRes getStudentUniversity(Long id, String name) {
        
        SearchRes result = new SearchRes();
         CompletableFuture<Void> universityFuture = CompletableFuture.runAsync(()->{
            University response  = restTemplate.getForObject("http://localhost:8200/university?name=" + name, University.class);
            if(response == null){
                System.out.println("Error");
            }
            result.setUniversity(response);
        });

        CompletableFuture<Void> studentFuture = CompletableFuture.runAsync(()->{
            Student response  = restTemplate.getForObject("http://localhost:8200/students/" + id, Student.class);
            if(response == null){
                System.out.println("Error");
            }
            result.setStudent(response);
        });

        CompletableFuture.allOf(universityFuture, studentFuture).join();

        return result;
    }
    
}

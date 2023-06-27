package com.example.search.service;

import com.example.search.entity.SearchRes;

public interface Service {
    
SearchRes getStudentUniversity(Long Id,String country);

}

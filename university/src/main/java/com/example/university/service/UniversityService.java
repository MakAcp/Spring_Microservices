package com.example.university.service;


import java.util.List;


public interface UniversityService {
    
    List<University> searchByName(String name);

    List<University> searchByCountry(String country);
}

package com.example.search.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class University {

    private String country;

    private String alpha_two_code;

    private String name;

    @JsonProperty(value = "state-province")
    private String stateProvince;

    private List<String> domains;

    @JsonProperty(value = "web_pages")
    private List<String> web_Pages;

}

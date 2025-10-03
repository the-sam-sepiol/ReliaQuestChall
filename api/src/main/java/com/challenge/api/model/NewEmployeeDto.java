package com.challenge.api.model;

//use immutable record to capture fields, and to generate standard methods
public record NewEmployeeDto(
        String firstName, String lastName, Integer salary, Integer age, String jobTitle, String email) {}

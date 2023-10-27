package com.example.LibraryManagement.ResponseObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicDetailsStudentResponse {
    private String name;
    private String mobNo;
    private int age;
}

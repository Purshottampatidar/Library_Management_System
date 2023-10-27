package com.example.LibraryManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private Integer studentId;
    private String name;
    private int age;
    private String contactNo;

    private String emailId;

    private String bloodGroup;

    @JsonIgnore
    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
}

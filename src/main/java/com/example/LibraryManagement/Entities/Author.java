package com.example.LibraryManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    @Column(nullable=false)
    private String authorName;
    private int age;
    private double rating;

    @OneToMany(mappedBy="author", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> bookList=new ArrayList<>();


}

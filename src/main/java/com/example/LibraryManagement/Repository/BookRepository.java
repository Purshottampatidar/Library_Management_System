package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Enums.Genre;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> getBooksByGenre(Genre genre);
}

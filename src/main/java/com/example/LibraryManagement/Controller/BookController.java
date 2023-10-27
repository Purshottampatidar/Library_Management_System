package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Enums.Genre;
import com.example.LibraryManagement.Service.BookService;
import org.eclipse.jetty.server.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/addBook")
    public ResponseEntity<String> addBook (@RequestBody Book book , @RequestParam("authorId") Integer authorId) {
        try{
            String result=bookService.addBook(book,authorId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getBookByGenre")
    public List<String> getBookByGenre(@RequestParam("genre")Genre genre) {

          return bookService.getBookByGenre(genre);
    }
}

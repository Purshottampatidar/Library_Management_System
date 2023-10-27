package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entities.Author;
import com.example.LibraryManagement.Service.AuthorService;
import com.example.LibraryManagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;
    @PostMapping("/add-author")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        String request=authorService.addAuthor(author);
        return new ResponseEntity<>(request, HttpStatus.OK);

    }

    @GetMapping("/allAuthorNames")
    public List<String> getAllAuthorNames(){
        return authorService.getAllAuthors();
    }



    @GetMapping("/getListOfBookNameByAuthorId")
    public ResponseEntity<List<String>> getBookByAuthorId(@RequestParam("authorId") Integer authorId){
          List<String> bookList=authorService.getBookList(authorId);
          return new ResponseEntity<>(bookList,HttpStatus.OK);
    }

    @GetMapping("/get")
    public Author getAuthor(@RequestParam("authorId") Integer authorId){
        return authorService.getAuthor(authorId);
    }

}

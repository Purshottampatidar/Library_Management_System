package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entities.Author;
import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Exceptions.AuthorNotFoundException;
import com.example.LibraryManagement.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        //calling save author to save the data into the author database
        authorRepository.save(author);
        return "Author Added";
    }

    public List<String> getAllAuthors() {
        //calling in build method findAll which we return all the authors that are present in the author db
        List<Author> allAuthor= authorRepository.findAll();
        List<String> result=new ArrayList<String>();
        for(Author author : allAuthor){
            result.add(author.getAuthorName());
        }
        return result;

    }

    public Author getAuthor(Integer authorId) {
         Author author =authorRepository.findById(authorId).get();
         return author;
    }
    public List<String> getBookList(Integer authorId)
    {
        List<String> bookName=new ArrayList<String>();
        Author author = authorRepository.findById(authorId).get();
        List<Book> bookList=author.getBookList();
        for(Book book : bookList){
            bookName.add(book.getBookName());
        }
        return bookName;
    }


}

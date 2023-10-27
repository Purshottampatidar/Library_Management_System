package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entities.Author;
import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Enums.Genre;
import com.example.LibraryManagement.Exceptions.AuthorNotFoundException;
import com.example.LibraryManagement.Repository.AuthorRepository;
import com.example.LibraryManagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    public String addBook(Book book, Integer authorId) throws Exception{
        Optional<Author> optionalAuthor=authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent()){
             throw new AuthorNotFoundException("Author Id is invalid");
        }
        Author author=optionalAuthor.get();
        book.setAuthor(author);

        author.getBookList().add(book);


        authorRepository.save(author);
        return "Book Added";
    }

    public List<String> getBookByGenre(Genre genre){
        List<Book> bookList= bookRepository.getBooksByGenre(genre);
        List<String> bookName=new ArrayList<String>();
        for(Book book : bookList){
            bookName.add(book.getBookName());
        }
        return bookName;
    }






}

package com.example.LibraryManagement.Exceptions;

public class BookNotFoundException extends  Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

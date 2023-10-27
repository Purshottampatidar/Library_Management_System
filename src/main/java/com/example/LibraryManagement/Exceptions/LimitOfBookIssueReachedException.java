package com.example.LibraryManagement.Exceptions;

public class LimitOfBookIssueReachedException extends Exception {
    public LimitOfBookIssueReachedException(String message){
        super(message);
    }
}

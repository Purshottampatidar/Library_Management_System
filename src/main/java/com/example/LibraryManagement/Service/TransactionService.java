package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Entities.LibraryCard;
import com.example.LibraryManagement.Entities.Transaction;
import com.example.LibraryManagement.Enums.CardStatus;
import com.example.LibraryManagement.Enums.TransactionStatus;
import com.example.LibraryManagement.Exceptions.*;
import com.example.LibraryManagement.Repository.BookRepository;
import com.example.LibraryManagement.Repository.CardRepository;
import com.example.LibraryManagement.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    private static final Integer MAX_LIMIT_OF_BOOKS=3;
    private static final Integer FINE_PER_DAY=5;



    public String issueBook(Integer bookId, Integer cardId) throws BookNotFoundException, BookNotAvailableException, CardNotFoundException, InvalidCardStatusException, LimitOfBookIssueReachedException {
        Transaction transaction=new Transaction();
        transaction.setStatus(TransactionStatus.PENDING);


        //validate book id
        Optional<Book> optionalBook=bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("book Id is Invalid");
        }
        //check availability
        Book book=optionalBook.get();
        if(!book.isAvailable()){
            throw new BookNotAvailableException("Book is not available");
        }
        //validate card number
        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardId);
        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFoundException("Card Id is Invalid");
        }
        LibraryCard libraryCard=optionalLibraryCard.get();

        //check status of card

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatusException("Invalid card status");
        }

        //check for limit of book
        if(libraryCard.getNoOfBookIssue() == MAX_LIMIT_OF_BOOKS){
            throw new LimitOfBookIssueReachedException("Limit of book reached is "+MAX_LIMIT_OF_BOOKS );
        }

        //create the transaction entities

        transaction.setStatus(TransactionStatus.ISSUED);

        libraryCard.setNoOfBookIssue(libraryCard.getNoOfBookIssue()+1);

        book.setAvailable(false);

        //setting foreign keys
        transaction.setBook(book);
        transaction.setCard(libraryCard);

        //adding transaction to book and library card;

        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);


        //save child

        transactionRepository.save(transaction);
        return "The book with book Id "+bookId+ "has been issued to card Number " +cardId;

    }
    public String returnBook(Integer bookId, Integer cardId){
        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();

        // I need to find the issued transaction
        Transaction transaction  =transactionRepository.findTransactionByBookAndCardAndStatus(book,card,TransactionStatus.ISSUED);

        //getting the issued date
        Date issueDate=transaction.getCreatedOn();

        // preDefined method to calculate time in days;
        long milliSeconds=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long days= TimeUnit.DAYS.convert(milliSeconds, TimeUnit.MILLISECONDS);

        int fineAmount=0;
        if(days>15){
            fineAmount= Math.toIntExact((days - 15) * FINE_PER_DAY);
        }

        Transaction newTransaction=new Transaction();

        newTransaction.setStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        newTransaction.setBook(book);
        newTransaction.setCard(card);

        book.setAvailable(true);
        card.setNoOfBookIssue(card.getNoOfBookIssue()-1);

        book.getTransactionList().add(newTransaction);
        card.getTransactionList().add(newTransaction);


        //saving the transaction
        transactionRepository.save(newTransaction);
        return "Book Has Been Returned";

    }
}

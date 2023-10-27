package com.example.LibraryManagement.Repository;

import com.example.LibraryManagement.Entities.Book;
import com.example.LibraryManagement.Entities.LibraryCard;
import com.example.LibraryManagement.Entities.Transaction;
import com.example.LibraryManagement.Enums.TransactionStatus;
import jdk.jshell.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTransactionByBookAndCardAndStatus(Book book , LibraryCard card, TransactionStatus transactionStatus);

}

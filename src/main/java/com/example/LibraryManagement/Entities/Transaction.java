package com.example.LibraryManagement.Entities;

import com.example.LibraryManagement.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer transactionId;

      @Enumerated(value = EnumType.STRING)
      private TransactionStatus status;


      private Date returnDate;
      private Integer fine;

      @CreationTimestamp
      private Date CreatedOn;

      @UpdateTimestamp
      private Date lastModifiedOn;

      @ManyToOne
      @JoinColumn
      private Book book;


      @ManyToOne
      @JoinColumn
      private LibraryCard card;

}

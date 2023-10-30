**Project: Library Management System using Spring Boot in Java**
- Developed a Library Management System using **Spring Boot**, **Spring Data JPA (Hibernate)**, and **MySQL** database.
- The system allows users to enter book information, including title, author,number of Pages and Price.
- Implemented features for performing searches by title, author, or date.
- The system manages the details of Address, Member, Issues, Books, and Students.
- Set up a Spring Boot project with Maven Project (or Gradle), Java Packaging: Jar, Java version: 17.
- Configured the MySQL database with Hibernate.
- Created JPA Entities for User and Book with a relationship between them to track which user has borrowed a book.
- Created Spring Data JPA Repositories to perform CRUD database operations on Book,Author and User entities.
- Created a Service Layer to handle operations like finding all books, finding a book by ID, saving a book, deleting a book by ID, borrowing a book, and returning a book.

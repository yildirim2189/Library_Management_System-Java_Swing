/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.entity;

import com.yildirimbayrakci.enums_and_constants.BookStatus;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author YILDIRIM
 */

@Entity
@Table(name = "book")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "isbn")
    private String isbn;
    
    @Column(name = "author")
    private String author;
    
    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publish_date")
    private int publishDate;
    
    @Column(name = "number_of_pages")
    private int numberOfPages;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BorrowHistory> borrowHistory;

    @ManyToMany(mappedBy = "reservedBooks", fetch = FetchType.EAGER)
    private Set<Account> usersReservedBy;
    
    
    // constructor and getters/setters

    public Book() {
    }

    public Book(String title, String isbn, String author, String publisher, int publishDate, int numberOfPages, BookStatus bookStatus) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.numberOfPages = numberOfPages;
        this.bookStatus = bookStatus;
        this.isbn = isbn;
    }

    public Set<Account> getUsersReservedBy() {
        return usersReservedBy;
    }

    public void setUsersReservedBy(Set<Account> usersReservedBy) {
        this.usersReservedBy = usersReservedBy;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<BorrowHistory> getBorrowHistory() {
        return borrowHistory;
    }

    public void setBorrowHistory(List<BorrowHistory> borrowHistory) {
        this.borrowHistory = borrowHistory;
    }


    
}

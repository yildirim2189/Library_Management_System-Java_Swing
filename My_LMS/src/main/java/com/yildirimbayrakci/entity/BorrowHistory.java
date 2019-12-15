/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author YILDIRIM
 */

@Entity
@Table(name = "borrow_history")
public class BorrowHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private int borrowId;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "borrow_date")
    private Date borrowDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    private Date dueDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private Date returnDate;

    public BorrowHistory() {
    }

    public BorrowHistory(Date borrowDate, Date dueDate, Date returnDate) {
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
       
    
}

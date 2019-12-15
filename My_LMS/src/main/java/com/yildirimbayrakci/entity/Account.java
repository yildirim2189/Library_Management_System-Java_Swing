/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.entity;

import com.yildirimbayrakci.enums_and_constants.AccountStatus;
import com.yildirimbayrakci.enums_and_constants.AccountType;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author YILDIRIM
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BorrowHistory> borrowHistory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_reservation",
            joinColumns = {
                @JoinColumn(name = "account_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "book_id")})
    private Set<Book> reservedBooks;

    public Account() {
    }

    public Account(String accountId, String password, String firstName, String lastName, String email, String phone, AccountStatus status, AccountType type, Date creationDate) {
        this.accountId = accountId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.type = type;
        this.creationDate = creationDate;
    }

    public Set<Book> getReservedBooks() {
        return reservedBooks;
    }

    public void setReservedBooks(Set<Book> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<BorrowHistory> getBorrowHistory() {
        return borrowHistory;
    }

    public void setBorrowHistory(List<BorrowHistory> borrowHistory) {
        this.borrowHistory = borrowHistory;
    }

    public void addBooktoReserve(Book book) {
        this.reservedBooks.add(book);
        book.getUsersReservedBy().add(this);
    }

    public void removeBookFromReserve(Book book) {
        this.reservedBooks.remove(book);
        book.getUsersReservedBy().remove(this);
    }
}

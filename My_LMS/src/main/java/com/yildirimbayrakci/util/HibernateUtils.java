/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.util;

import com.yildirimbayrakci.entity.Account;
import com.yildirimbayrakci.entity.Book;
import com.yildirimbayrakci.entity.BorrowHistory;
import com.yildirimbayrakci.entity.Category;
import com.yildirimbayrakci.enums_and_constants.AccountStatus;
import com.yildirimbayrakci.enums_and_constants.AccountType;
import com.yildirimbayrakci.enums_and_constants.BookStatus;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author YILDIRIM
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;

    private HibernateUtils() {
    }

    public static synchronized SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(BorrowHistory.class)
                    .buildSessionFactory();
        }

        return sessionFactory;
    }

    public static List<Category> bringCategories() {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            String hql = "FROM Category c";

            List<Category> categories = session.createQuery(hql).getResultList();

            session.getTransaction().commit();

            return categories;
        }
    }

    public static int addBook(String isbn, String title, String author, String publisher, int numberOfPages, int year, String category, String status) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            BookStatus bookStatus = BookStatus.MEVCUT;

            for (BookStatus b : BookStatus.values()) {
                if (b.displayName().equals(status)) {
                    bookStatus = b;
                }
            }

            String hql = "From Category c where c.name = '" + category + "'";
            Category c = session.createQuery(hql, Category.class).getSingleResult();
            Book book = new Book(title, isbn, author, publisher, year, numberOfPages, bookStatus);
            book.setCategory(c);
            int id = (int) session.save(book);

            session.getTransaction().commit();

            return id;
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean deleteBook(int id) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Book book = session.get(Book.class, id);
            session.delete(book);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean editBook(int id, String isbn, String title, String author, String publisher, int numberOfPages, int year, String category, String status) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            BookStatus bookStatus = BookStatus.MEVCUT;

            for (BookStatus b : BookStatus.values()) {
                if (b.displayName().equals(status)) {
                    bookStatus = b;
                }
            }

            String hql = "From Category c where c.name = '" + category + "'";
            Category c = session.createQuery(hql, Category.class).getSingleResult();
            Book book = new Book(title, isbn, author, publisher, year, numberOfPages, bookStatus);
            book.setCategory(c);
            book.setBookId(id);
            session.update(book);
            session.getTransaction().commit();
            return true;

        } catch (Exception exc) {
            return false;
        }
    }

    public static boolean editUser(String id, String firstName, String lastName, String email, String phone, String status, String type) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            AccountStatus accountStatus = AccountStatus.AKTIF;
            AccountType accountType = AccountType.UYE;
            
            for(AccountStatus as : AccountStatus.values()){
                if(as.displayName().equals(status))
                    accountStatus = as;
            }
            
            for(AccountType at: AccountType.values()){
                if(at.displayName().equals(type))
                    accountType = at;
            }
            
            Account account = session.get(Account.class, id);
            
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setEmail(email);
            account.setPhone(phone);
            account.setStatus(accountStatus);
            account.setType(accountType);
            
            session.update(account);
            session.getTransaction().commit();
            return true;

        } catch (Exception exc) {
            return false;
        }
    }

    public static void addUser(String id, String password, String firstName, String lastName, String email, String phone, AccountStatus status, AccountType type) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            String HashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            System.out.println("Password: " + password);
            System.out.println(HashedPassword);

            Account account = new Account(id, HashedPassword, firstName, lastName, email, phone, status, type, new Date());

            session.save(account);

            session.getTransaction().commit();

            JavaMail.sendEmail(id,firstName + " " + lastName, email, password, JavaMail.INITIAL_PASSWORD);
        }
    }

    public static void deleteUser(String id) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Account account = session.get(Account.class, id);
            session.delete(account);
            session.getTransaction().commit();
        }
    }

    public static void lendBook(String accountId, int bookId) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Date borrowdate = new Date();
            Date dueDate = DateUtils.addDays(borrowdate, 15);

            BorrowHistory lending = new BorrowHistory(borrowdate, dueDate, null);
            Account account = session.get(Account.class, accountId);
            Book book = session.get(Book.class, bookId);
            book.setBookStatus(BookStatus.ODUNC_ALINMIS);
            lending.setAccount(account);
            lending.setBook(book);
            session.save(lending);
            session.getTransaction().commit();
        }
    }

    public static void returnBook(int bookId) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            String hql = "SELECT Bh From BorrowHistory Bh"
                    + " WHERE Bh.book=" + bookId + " AND Bh.returnDate IS NULL";

            BorrowHistory bh = session.createQuery(hql, BorrowHistory.class).getSingleResult();

            bh.getBook().setBookStatus(BookStatus.MEVCUT);
            Date returnDate = new Date();
            bh.setReturnDate(returnDate);

            session.update(bh);
            if (returnDate.after(bh.getDueDate())) {
                System.out.println("Punish");
            } else {
                System.out.println("Just in time!");
            }

            session.getTransaction().commit();
            
            // check reservations and if there's send email to reservation owner.
        }
    }

    public static Account updateUserContact(String accountId, String email, String phone) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Account account = session.get(Account.class, accountId);
            account.setEmail(email);
            account.setPhone(phone);

            session.update(account);

            session.getTransaction().commit();

            return account;
        }
    }

    public static boolean authenticateUser(String accountId, String password) {
        boolean testPw;
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Account account = session.get(Account.class, accountId);

            testPw = BCrypt.checkpw(password, account.getPassword());

            session.getTransaction().commit();
        }

        return testPw;
    }

    public static void changePw(String accountId, String newPw) {
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Account account = session.get(Account.class, accountId);

            String hashedPw = BCrypt.hashpw(newPw, BCrypt.gensalt());
            account.setPassword(hashedPw);

            session.update(account);
            session.getTransaction().commit();

            JavaMail.sendEmail(accountId,account.getFirstName() + " " + account.getLastName(),
                    account.getEmail(), newPw, JavaMail.CHANGED_PASSWORD);
        }
    }

    public static boolean isUsernameExist(String accountId) {
        boolean result;
        try ( Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Account account = session.get(Account.class, accountId);

            if (account == null) {
                result = false;
            } else {
                return true;
            }

            session.getTransaction().commit();
        }
        return result;
    }
}

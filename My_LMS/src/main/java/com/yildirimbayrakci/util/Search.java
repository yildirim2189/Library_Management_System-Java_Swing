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
import com.yildirimbayrakci.enums_and_constants.AccountType;
import com.yildirimbayrakci.enums_and_constants.BookStatus;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author YILDIRIM
 */
public class Search {

    public static List<Book> searchBook(String query, String searchCriteria, String sortCriteria, String category) {
        List<Book> books;

        int selectedCategoryId = 0;
        switch (searchCriteria) {
            case "Id":
                searchCriteria = "bookId";
                break;
            case "Başlık":
                searchCriteria = "title";
                break;
            case "Yazar":
                searchCriteria = "author";
                break;
            case "ISBN":
                searchCriteria = "isbn";
            default:
                System.err.println("HATA! SearchBook SearchCriteria");
                break;
        }

        switch (sortCriteria) {
            case "Id":
                sortCriteria = "bookId";
                break;
            case "Başlık":
                sortCriteria = "title";
                break;
            case "Yazar":
                sortCriteria = "author";
                break;
            case "ISBN":
                sortCriteria = "isbn";
            default:
                System.err.println("HATA! SearchBook SortCriteria");
                break;
        }

        String hql = "FROM Book B WHERE B." + searchCriteria + " LIKE '%" + query + "%' "
                + " ORDER BY B." + sortCriteria;

        if (!category.equals("Tümü")) {
            for (Category c : HibernateUtils.bringCategories()) {
                if (category.equals(c.getName())) {
                    selectedCategoryId = c.getCategoryId();

                    hql = "FROM Book B WHERE B." + searchCriteria + " LIKE '%" + query + "%' "
                            + "AND B.category = " + selectedCategoryId
                            + " ORDER BY B." + sortCriteria;

                }
            }
        }

        try (Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            books = session.createQuery(hql).getResultList();
            session.getTransaction().commit();
        }
        return books;
    }

    public static Book searchBook(int bookId) {
        Book book;
        try (Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            book = session.get(Book.class, bookId);
            session.getTransaction().commit();
        }
        return book;
    }

    public static List<Account> searchUser(String query, String searchCriteria, String sortCriteria) {
        List<Account> accounts;
        switch (searchCriteria) {
            case "Id":
                searchCriteria = "accountId";
                break;
            case "Ad":
                searchCriteria = "firstName";
                break;
            case "Soyad":
                searchCriteria = "lastName";
                break;
            default:
                System.err.println("HATA! SearchUser SearchCriteria");
                break;
        }

        switch (sortCriteria) {
            case "Id":
                sortCriteria = "accountId";
                break;
            case "Ad":
                sortCriteria = "firstName";
                break;
            case "Soyad":
                sortCriteria = "lastName";
                break;
            default:
                System.err.println("HATA! SearchUser SearchCriteria");
                break;
        }
        String hql = "FROM Account A WHERE A." + searchCriteria + " LIKE '%" + query + "%' "
                + " ORDER BY A." + sortCriteria;

        try (Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            accounts = session.createQuery(hql).getResultList();
            session.getTransaction().commit();
        }
        return accounts;

    }

    public static Account searchUser(String id) {
        Account account;
        try (Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            account = session.get(Account.class, id);
            session.getTransaction().commit();

        }
        return account;
    }

    public static List<BorrowHistory> searchBorrowHistory(String accountId, boolean bringReturnedBooks) {
        List<BorrowHistory> history;
        try (Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            String hql = "SELECT bh FROM BorrowHistory bh "
                    + "WHERE bh.account = '" + accountId + "'";

            if (!bringReturnedBooks) {
                hql += " AND bh.returnDate IS NULL";
            }

            history = session.createQuery(hql, BorrowHistory.class).getResultList();

            session.getTransaction().commit();

        }
        return history;
    }

    public static List<BorrowHistory> searchBorrowHistory(Integer bookId, Date beginDate, Date endDate) {
        List<BorrowHistory> history;
        try (Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            String hql = "SELECT bh FROM BorrowHistory bh ";

            if (bookId != null) {
                hql += "WHERE bh.book=" + bookId;
            }

            boolean searchWithDate = false;

            if (beginDate != null && endDate != null) {
                if (endDate.after(beginDate) || endDate.equals(beginDate)) {
                    searchWithDate = true;
                    if (bookId != null) {
                        hql += " and bh.borrowDate BETWEEN :begin AND :end";
                    } else {
                        hql += " WHERE bh.borrowDate BETWEEN :begin AND :end";

                    }

                }

            }

            Query query = session.createQuery(hql);

            if (searchWithDate) {
                query.setParameter("begin", beginDate);
                query.setParameter("end", endDate);
            }

            history = query.list();
            // history = session.createQuery(hql, BorrowHistory.class).getResultList();

            session.getTransaction().commit();
        }
        return history;
    }

    public static Long bringCounts(String criteria) {
        Long result = 0L;
        try (Session session = HibernateUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            String hql;

            switch (criteria) {
                case "totalBooks":
                    hql = "select count(*) From Book b";
                    break;
                case "borrowedBooks":
                    hql = "select count(*) From Book b where b.bookStatus ='" + BookStatus.ODUNC_ALINMIS.toString() + "'";
                    break;
                case "availableBooks":
                    hql = "select count(*) From Book b where b.bookStatus ='" + BookStatus.MEVCUT.toString() + "'";
                    break;
                case "totalUsers":
                    hql = "select count(*) From Account";
                    break;
                case "adminUsers":
                    hql = "select count(*) From Account a where a.type='" + AccountType.ADMIN.toString() + "'";
                    break;
                case "normalUsers":
                    hql = "select count(*) From Account a where a.type='" + AccountType.UYE.toString() + "'";
                    break;
                case "academicUsers":
                    hql = "select count(*) From Account a where a.type='" + AccountType.OGRETIM.toString() + "'";
                    break;
                case "reservedBooks":
                    hql = "select count(*) From Book b where b.bookStatus='" + BookStatus.REZERVE.toString() + "'";
                    break;
                case "lostBooks":
                    hql = "select count(*) From Book b where b.bookStatus='" + BookStatus.KAYIP.toString() + "'";
                    break;
                default:
                    hql = "";
                    System.out.println("Hata");
                    break;
            }

            result = (Long) session.createQuery(hql).uniqueResult();
            session.getTransaction().commit();
        }

        return result;
    }

}

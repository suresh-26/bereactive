package com.rakuten.demo.dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

public class ImperativeToReactiveDatabaseAccess {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}

class FunctionalReactive {

    Observable<Book> getBooksAsync(long userId) {
        Observable<Book> observable = Observable.create(o -> {
            try (Connection con = Database.getConnection()) {
                ResultSet rs = con.createStatement().executeQuery("Select id from book where user_id =" + userId);
                while (rs.next() && (!o.isUnsubscribed())) {
                    o.onNext(new Book(rs.getLong(1)));
                }
                rs.close();
                o.onCompleted();
            } catch (Exception e) {
                o.onError(e);
            }
        });
        return observable.subscribeOn(Schedulers.io());
    }

    void handleBooksAsync() {
        Observable<Book> books = getBooksAsync(118);
        // TODO
    }
}

class Imperative {
    List<Book> getBooks(long userId) {
        try (Connection con = Database.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("Select id from book where user_id =" + userId);
            ArrayList<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(new Book(rs.getLong(1)));
            }
            rs.close();
            return books;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void handleBooks() {
        List<Book> books = getBooks(118);
        // TODO
    }
}

class Book {
    long id;

    public Book(long id) {
        super();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

class Database {
    static Connection getConnection() {
        // TODO
        return null;
    }
}
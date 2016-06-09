package com.rakuten.demo.dbaccess;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/*
 * Get all the books from the favorite authors priced less than 100
 */
public class ImperativeToReactiveUsingFlatMap {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

    class FunctionalReactive {
        Observable<Book> getBooksAsync(long userId) {
            Observable<Book> observable =
                    getFavouriteAuthorsAsync(userId)
                            .flatMap(author -> getBooksFromAuthorsAsync(author))
                            .filter(book -> book.getPrice() < 100)
                            .subscribeOn(Schedulers.io());
            return observable;
        }

        void handleBooksAsync() {
            Observable<Book> books = getBooksAsync(118);
            // TODO
        }

        Observable<Author> getFavouriteAuthorsAsync(long userId) {
            Observable<Author> authors = Observable.empty();
            // TODO
            return authors;
        }

        Observable<Book> getBooksFromAuthorsAsync(Author author) {
            Observable<Book> books = Observable.empty();
            // TODO
            return books;
        }
    }

    class Imperative {
        List<Author> getFavouriteAuthors(long userId) {

            ArrayList<Author> authors = new ArrayList<>();
            // TODO
            return authors;

        }

        List<Book> getBooks(List<Author> authors) {

            List<Book> books = new ArrayList<Book>();
            // TODO
            return books;
        }

        List<Book> getBooks(long userId) {

            List<Author> authors = getFavouriteAuthors(userId);
            // get books fromAuthors
            List<Book> books = getBooks(authors);
            // where price < 100;
            // TODO
            return books;
        }

        void handleBooks() {
            List<Book> books = getBooks(118);
            // TODO
        }
    }

    class Book {
        private long id;
        private double price;

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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    class Author {
        private long id;

        public Author(long id) {
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
}

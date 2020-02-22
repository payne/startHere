package com.example.validatingforminput;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Book {
    public Book() {

    }
    public Book(int bookid, String title, String author, String published, int stock) {
        super();
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        setPublished(published);
        this.stock = stock;
    }
    int bookid;
    String title, author;
    Date published;
    int stock;

    public String toString() {
        return String.format("'%s' by %s published %s there are %d in stock.", title, author, published, stock);
    }

    public int getBookid() {
        return bookid;
    }
    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getPublished() {
        return published;
    }
    public void setPublished(String published) {
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("MM-dd-yyyy");
        try {
            this.published = sdfmt1.parse( published );
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}





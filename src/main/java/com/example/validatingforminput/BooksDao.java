package com.example.validatingforminput;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BooksDao {
    private static final Logger log = LoggerFactory.getLogger(BooksDao.class);

    private JdbcTemplate jdbcTemplate;

    public BooksDao(JdbcTemplate jt) {
        this.jdbcTemplate=jt;
    }

    public List<Book> findBooks() {
        log.debug("jdbcTemplate=" + jdbcTemplate);
        // based on https://spring.io/guides/gs/relational-data-access/

        List<Book> lstBooks = jdbcTemplate.query(
                "SELECT  bookid, title, author, published, stock FROM books ",
                (rs, rowNum) -> new Book(rs.getInt("bookid"), rs.getString("title"), rs.getString("author"),
                        rs.getString("published"), rs.getInt("stock")));
        return lstBooks;
    }
}



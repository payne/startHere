package com.example.validatingforminput;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BooksDao {
    private static final Logger log = LoggerFactory.getLogger(BooksDao.class);

    private JdbcTemplate jdbcTemplate;

    public BooksDao(JdbcTemplate jt) {
        this.jdbcTemplate=jt;
    }

    public void addBook(Book book) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        String sql = "INSERT INTO books (bookid, title, author, published, stock) "
        +"VALUES (:bookid, :title, :author, :published, :stock)";

        Map<String, Object> params = new HashMap<>();

        params.put("bookid", book.getBookid());
        params.put("title",book.getTitle());
        params.put("author", book.getAuthor());
        params.put("published",book.getPublished());
        params.put("stock",book.getStock());

        template.update(sql, params);
    }

    public List<Book> findBooks() {
        log.debug("jdbcTemplate=" + jdbcTemplate);
        // based on https://spring.io/guides/gs/relational-data-access/

        List<Book> lstBooks = jdbcTemplate.query(
                "SELECT  bookid, title, author, published, stock FROM books ",
                new BookRowMapper());
        return lstBooks;
    }

    // https://www.baeldung.com/spring-jdbc-jdbctemplate helped
    class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookid(rs.getInt("BOOKID"));
            book.setTitle(rs.getString("TITLE"));
            book.setAuthor(rs.getString("AUTHOR"));
            book.setPublished(rs.getString("PUBLISHED"));
            book.setStock(rs.getInt("STOCK"));
            return book;
        }
    }

}



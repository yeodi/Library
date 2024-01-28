package dao;

import models.Book;
import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT Book(title,author,release_year) VALUES(?,?,?)",book.getTitle(),book.getAuthor(),book.getReleaseYear());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE Book SET title=?,author=?,release_year=? WHERE id=?",updatedBook.getTitle(),updatedBook.getAuthor(),updatedBook.getReleaseYear(),id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
    }
}

package dao;

import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(person_name,year_of_birth) Values(?,?)",person.getName(),person.getYearOfBirth());
    }

    public void update(int id , Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET person_name=?,year_of_birth=? WHERE id=?",updatedPerson.getName(),updatedPerson.getYearOfBirth(),id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }
}

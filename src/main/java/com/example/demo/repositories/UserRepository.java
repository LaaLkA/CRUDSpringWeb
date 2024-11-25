package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    /**
     * Объект подключения к БД
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Конструктор класса
     * @param jdbcTemplate объект подключения к БД
     */
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Метод получения всех пользователей из БД
     * @return список всех пользователей, которые есть в БД.
     */
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable;";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbcTemplate.query(sql, userRowMapper);
    }


    /**
     * Метод сохранения пользователя в БД
     * @param user объект пользователя, который сохраняется в БД.
     * @return объект, сохранённый в БД.
     */
    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName, lastName) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    /**
     * Метод удаления пользователя из БД по id
     * @param id идентификатор пользователя
     */
    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Метод обновления полей пользователя в БД
     * @param user объект пользователя с измененными данными.
     * @return изменённый объект
     */
    public User update(User user) {
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

    /**
     * Метод поиска пользователя по id
     * @param id идентификатор пользователя
     * @return найденный объект пользователя в БД
     */
    public User findById(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        RowMapper<User> userRowMapper = (r,i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }
}

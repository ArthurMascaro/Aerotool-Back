package edu.br.ifsp.applications.persistence.postgres;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.user.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresUserDAOImpl implements UserDAO {

    @Value("${queries.sql.user-dao.insert.user}")
    private String insertUserQuery;

    @Value("${queries.sql.user-dao.select.user-and-login-and-password-from-promptuary-by-promptuary-login}")
    private String findUserByPromptuaryQuery;

    @Value("${queries.sql.user-dao.select.user-by-id}")
    private String findUserByIdQuery;

    @Value("${queries.sql.user-dao.select.user-by-name}")
    private String findUserByNameQuery;

    @Value("${queries.sql.user-dao.select.user-all}")
    private String findAllUsersQuery;

    @Value("${queries.sql.user-dao.update.user-name}")
    private String updateUserNameQuery;

    @Value("${queries.sql.user-dao.delete.user-by-promptuary}")
    private String deleteUserByPromptuary;

    private JdbcTemplate jdbcTemplate;

    public PostgresUserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User type) {
        jdbcTemplate.update(insertUserQuery, type.getId(), type.getNome(), type.getRole().name(),
                type.getPromptuary().getLogin());
        return type;
    }

    @Override
    public Optional<User> findByPromptuary(Promptuary promptuary) {
        try {
            User user = jdbcTemplate.queryForObject(findUserByPromptuaryQuery,
                    this::mapperUserFromRs, promptuary.getLogin());
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUUID(UUID id) {
        try {
            User user = jdbcTemplate.queryForObject(findUserByIdQuery, this::mapperUserFromRs, id);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        try {
            User user = jdbcTemplate.queryForObject(findUserByNameQuery,
                    this::mapperUserFromRs, name);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findOne(Promptuary key) {
        try {
            return findByPromptuary(key);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findALL() {
        List<User> users = jdbcTemplate.query(findAllUsersQuery, this::mapperUserFromRs);
        return users;
    }

    @Override
    public User update(User type) {
        jdbcTemplate.update(updateUserNameQuery, type.getNome(), type.getId());
        return type;
    }

    @Override
    public User deleteByKey(Promptuary key) {
        jdbcTemplate.update(deleteUserByPromptuary, key);
        return new User(key);
    }

    @Override
    public User delete(User type) {
        return deleteByKey(type.getPromptuary());
    }

    private User mapperUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String nome = rs.getString("name");
        Role role = Role.valueOf(rs.getString("role"));
        String login = rs.getString("login");
        String password = rs.getString("password");

        Promptuary promptuary = new Promptuary(login, password);

        return new User(id, nome, role, promptuary);
    }

    private Promptuary mapperPromptuaryFromRs(ResultSet rs, int rowNum) throws SQLException {
        String login = rs.getString("login");
        String password = rs.getString("password");

        return new Promptuary(login, password);
    }

}

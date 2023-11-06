package edu.br.ifsp.applications.persistence.postgres;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.user.UserDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Value("${queries.sql.user-dao.select.user-by-promptuary}")
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
    private final PasswordEncoder passwordEncoder;

    public PostgresUserDAOImpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        UUID userId = UUID.randomUUID();

        jdbcTemplate.update(insertUserQuery, rs -> {
            rs.setObject(1, userId);
            rs.setString(2, user.getName());
            rs.setString(3, user.getRole().toString());
            rs.setString(4, user.getPromptuary());
            rs.setString(5, passwordEncoder.encode(user.getPassword()));
            rs.setBoolean(6, true);
            rs.setBoolean(7, true);
            rs.setBoolean(8, true);
            rs.setBoolean(9, true);
        });

        return user.createWithId(userId);
    }

    @Override
    public Optional<User> findByPromptuary(String promptuary) {
        try {
            User user = jdbcTemplate.queryForObject(findUserByPromptuaryQuery,
                    this::mapperUserFromRs, promptuary);
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
    public Optional<User> findOne(String key) {
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
        type.setId(findByPromptuary(type.getPromptuary()).get().getId());

        jdbcTemplate.update(updateUserNameQuery, type.getName(),
                passwordEncoder.encode(type.getPassword()), type.getId());
        return type;
    }

    @Override
    public User deleteByKey(String key) {
        jdbcTemplate.update(deleteUserByPromptuary, key);
        return new User(key);
    }

    @Override
    public User delete(User type) {
        return deleteByKey(type.getPromptuary());
    }

    private User mapperUserFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String name = rs.getString("name");
        Role role = Role.valueOf(rs.getString("role"));
        String promptuary = rs.getString("promptuary");
        String password = rs.getString("password");
        boolean isAccountNonExpired = rs.getBoolean("is_account_non_expired");
        boolean isAccountNonLocked = rs.getBoolean("is_account_non_locked");
        boolean isCredentialsNonExpired = rs.getBoolean("is_credentials_non_expired");
        boolean isEnabled = rs.getBoolean("is_enabled");

        return new User(id, name, role, promptuary, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled);
    }

    private Promptuary mapperPromptuaryFromRs(ResultSet rs, int rowNum) throws SQLException {
        String login = rs.getString("login");
        String password = rs.getString("password");

        return new Promptuary(login, password);
    }

}

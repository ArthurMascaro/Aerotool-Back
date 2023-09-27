package edu.br.ifsp.applications.persistence.postgres;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.transactions.RequestDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresRequestDAOImpl implements RequestDAO {

    @Value("${queries.sql.request-dao.insert.request}")
    private String insertRequestQuery;

    @Value("${queries.sql.request-dao.select.request-by-id}")
    private String findRequestByUUIDQuery;

    @Value("${queries.sql.request-dao.select.request-all}")
    private String findAllRequestsQuery;

    @Value("${queries.sql.request-dao.delete.request-by-id}")
    private String deleteRequestByUUIDQuery;

    private JdbcTemplate jdbcTemplate;
    private PostgresUserDAOImpl postgresUserDAO;

    public PostgresRequestDAOImpl(JdbcTemplate jdbcTemplate, PostgresUserDAOImpl postgresUserDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.postgresUserDAO = postgresUserDAO;
    }

    @Override
    public Optional<Request> findByUUID(UUID id) {
        Request request = jdbcTemplate.queryForObject(findRequestByUUIDQuery, this::mapperRequestFromRs, id);
        return Optional.of(request);
    }

    @Override
    public Request create(Request type) {
        jdbcTemplate.update(insertRequestQuery, type.getId(), type.getDate(), type.getUser());
        return type;
    }

    @Override
    public Optional<Request> findOne(UUID key) {
        try {
            return findByUUID(key);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Request> findALL() {
        List<Request> requests = jdbcTemplate.query(findAllRequestsQuery, this::mapperRequestFromRs);
        return requests;
    }

    @Override
    public Request update(Request type) {
        return null;
    }

    @Override
    public Request deleteByKey(UUID key) {
        jdbcTemplate.update(deleteRequestByUUIDQuery, key);
        return new Request(key);
    }

    @Override
    public Request delete(Request type) {
        return deleteByKey(type.getId());
    }

    private Request mapperRequestFromRs(ResultSet rs, int i) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        Timestamp requestDate = rs.getTimestamp("request_date");
        UUID userId = UUID.fromString(rs.getString("user_id"));
        User user = postgresUserDAO.findByUUID(userId).get();

        return new Request(id, requestDate, user);
    }

}

package edu.br.ifsp.applications.persistence.postgres;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.event.EventSituation;
import edu.br.ifsp.domain.entities.event.EventType;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.events.EventDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresEventDAOImpl implements EventDAO {

    private JdbcTemplate jdbcTemplate;
    private PostgresUserDAOImpl postgresUserDAO;

    public PostgresEventDAOImpl(JdbcTemplate jdbcTemplate, PostgresUserDAOImpl postgresUserDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.postgresUserDAO = postgresUserDAO;
    }

    @Value("${queries.sql.event-dao.insert.event}")
    private String insertEventQuery;

    @Value("${queries.sql.event-dao.select.event-by-id}")
    private String findEventByUUIDQuery;

    @Value("${queries.sql.event-dao.select.event-by-responsible-id}")
    private String findEventByUserResponsibleIdQuery;

    @Value("${queries.sql.event-dao.select.event-by-subject-id}")
    private String findEventByUserSubjectIdQuery;

    @Value("${queries.sql.event-dao.select.event-all}")
    private String findAllEventsQuery;

    @Value("${queries.sql.event-dao.update.event-situation}")
    private String updateEventSituationQuery;

    @Value("${queries.sql.event-dao.delete.event-by-id}")
    private String deleteEventByUUIDQuery;

    @Override
    public Event create(Event type) {
        jdbcTemplate.update(insertEventQuery, type.getId(), type.getDescription(), type.getDate(),
                type.getType().name(), type.getSituation().name(), type.getResponsible().getId(),
                type.getSubject().getId());
        return findByUUID(type.getId()).get();
    }

    @Override
    public Optional<Event> findByUUID(UUID id) {
        Event event = jdbcTemplate.queryForObject(findEventByUUIDQuery, this::mapperEventFromRs, id);
        return Optional.of(event);
    }

    @Override
    public List<Event> findByUserResponsibleId(UUID id) {
        List<Event> events = jdbcTemplate.query(findEventByUserResponsibleIdQuery, this::mapperEventFromRs, id);
        return events;
    }

    @Override
    public List<Event> findByUserSubjectId(UUID id) {
        List<Event> events = jdbcTemplate.query(findEventByUserSubjectIdQuery, this::mapperEventFromRs, id);
        return events;
    }

    @Override
    public Optional<Event> findOne(UUID key) {
        return findByUUID(key);
    }

    @Override
    public List<Event> findALL() {
        List<Event> events = jdbcTemplate.query(findAllEventsQuery, this::mapperEventFromRs);
        return events;
    }

    @Override
    public Event update(Event type) {
        jdbcTemplate.update(updateEventSituationQuery, type.getSituation().name(), type.getId());
        return findByUUID(type.getId()).get();
    }

    @Override
    public Event deleteByKey(UUID key) {
        jdbcTemplate.update(deleteEventByUUIDQuery, key);
        return new Event(key);
    }

    @Override
    public Event delete(Event type) {
        return deleteByKey(type.getId());
    }

    private Event mapperEventFromRs(ResultSet rs, int i) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String description = rs.getString("description");
        Timestamp eventDate = rs.getTimestamp("event_date");
        EventType eventType = EventType.valueOf(rs.getString("event_type"));
        EventSituation eventSituation = EventSituation.valueOf(rs.getString("event_situation"));
        UUID userResponsibleId = UUID.fromString(rs.getString("user_responsible_id"));
        UUID userSubjectId = UUID.fromString(rs.getString("user_subject_id"));
        User userResponsible = postgresUserDAO.findByUUID(userResponsibleId).get();
        User userSubject = postgresUserDAO.findByUUID(userSubjectId).get();

        return new Event(id,  userResponsible, userSubject, eventDate, eventSituation, eventType, description);
    }

}

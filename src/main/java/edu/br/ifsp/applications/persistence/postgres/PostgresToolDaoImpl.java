package edu.br.ifsp.applications.persistence.postgres;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.tools.ToolDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresToolDaoImpl implements ToolDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.tool-dao.insert.tool}")
    private String insertToolQuery;

    public PostgresToolDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Tool> findByUUID(UUID id) {
        return Optional.empty();
    }

    @Override
    public Tool create(Tool tool) {
        jdbcTemplate.update(insertToolQuery, tool.getId(), tool.getName(), tool.getDescription(), tool.getType().name());
        return tool;
    }

    @Override
    public Optional<Tool> findOne(UUID key) {
        return Optional.empty();
    }

    @Override
    public List<Tool> findALL() {
        return null;
    }

    @Override
    public Tool update(Tool type) {
        return null;
    }

    @Override
    public Tool deleteByKey(UUID key) {
        return null;
    }

    @Override
    public Tool delete(Tool type) {
        return null;
    }
}

package edu.br.ifsp.applications.persistence.postgres;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolType;
import edu.br.ifsp.domain.usecases.tools.ToolDAO;
import edu.br.ifsp.web.exception.GenericResourceException;
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
public class PostgresToolDAOImpl implements ToolDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.tool-dao.insert.tool}")
    private String insertToolQuery;

    @Value("${queries.sql.tool-dao.delete.tool-by-id}")
    private String deleteToolByIdQuery;

    @Value("${queries.sql.tool-dao.update.tool}")
    private String updateToolQuery;

    @Value("${queries.sql.tool-dao.select.tool-by-id}")
    private String findToolByIdQuery;

    @Value("${queries.sql.tool-dao.select.tool-all}")
    private String findAllToolQuery;


    public PostgresToolDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Tool> findByUUID(UUID id) {
        try {
            Tool tool = jdbcTemplate.queryForObject(findToolByIdQuery, this::mapperToolFromRs, id);
            return Optional.of(tool);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Tool create(Tool tool) {
        List<Tool> tools = findALL();

        tools.forEach(t -> {
            if (t.getName().equals(tool.getName()))
                throw new GenericResourceException("Tool with name " + tool.getName() + " already exists", "Resource already exists");
        });

        jdbcTemplate.update(insertToolQuery, tool.getId(), tool.getName(), tool.getDescription(), tool.getType().name());
        return tool;
    }

    @Override
    public Optional<Tool> findOne(UUID key) {
        return findByUUID(key);
    }

    @Override
    public List<Tool> findALL() {
        List<Tool> tools = jdbcTemplate.query(findAllToolQuery, this::mapperToolFromRs);
        return tools;
    }

    @Override
    public Tool update(Tool type) {
        jdbcTemplate.update(updateToolQuery,
                type.getName(), type.getDescription(),
                type.getType().name(), type.getId());
        return type;
    }

    @Override
    public Tool deleteByKey(UUID key) {
        if (jdbcTemplate.update(deleteToolByIdQuery, key) != 1)
            throw new GenericResourceException("Unexpected error when try delete tool with id=" + key, "Exclusion Error");
        return new Tool(key);
    }

    @Override
    public Tool delete(Tool type) {
       return deleteByKey(type.getId());
    }

    private Tool mapperToolFromRs(ResultSet rs, int rowNum) throws SQLException{
        UUID id = (UUID) rs.getObject("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        ToolType type = ToolType.valueOf(rs.getString("type"));
        return new Tool(id, name, description, type);
    }
}

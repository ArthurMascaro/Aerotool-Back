package edu.br.ifsp.applications.persistence.postgres;

import edu.br.ifsp.domain.entities.tools.Locate;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.tools.ToolSituation;
import edu.br.ifsp.domain.usecases.tools.ToolDAO;
import edu.br.ifsp.domain.usecases.tools.ToolItemDAO;
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
public class PostgresToolItemDAOImpl implements ToolItemDAO {

    @Value("${queries.sql.locate-dao.insert.locate}")
    private String insertLocateQuery;

    @Value("${queries.sql.locate-dao.select.find-all}")
    private String findAllLocatesQuery;

    @Value("${queries.sql.tool-item-dao.insert.tool-item}")
    private String insertToolItemQuey;

    @Value("${queries.sql.tool-item-dao.select.tool-item-all}")
    private String findAllToolItemsQuery;

    @Value("${queries.sql.tool-item-dao.select.tool-item-by-id}")
    private String findToolItemByIdQuery;

    @Value("${queries.sql.tool-item-dao.delete.tool-item-by-id}")
    private String deleteToolItemById;

    @Value("${queries.sql.tool-item-dao.update.tool-item}")
    private String updateToolItemById;
    private JdbcTemplate jdbcTemplate;

    private ToolDAO toolDAO;

    public PostgresToolItemDAOImpl(JdbcTemplate jdbcTemplate, ToolDAO toolDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.toolDAO = toolDAO;
    }

    @Override
    public ToolItem create(ToolItem type) {
        jdbcTemplate.update(insertToolItemQuey, type.getId(), type.getPatrimony(), type.getSituation().name(), type.getLocateId(), type.getToolId());
        return type;
    }

    @Override
    public Optional<ToolItem> findOne(UUID key) {
        try {
            ToolItem toolItem = jdbcTemplate.queryForObject(findToolItemByIdQuery, this::mapperToolItemFromRs, key);
            return Optional.of(toolItem);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ToolItem> findALL() {
        List<ToolItem> tools = jdbcTemplate.query(findAllToolItemsQuery, this::mapperToolItemFromRs);
        return tools;
    }

    @Override
    public ToolItem update(ToolItem type) {
        jdbcTemplate.update(updateToolItemById, type.getPatrimony(), type.getSituation().name(), type.getLocateId(), type.getToolId(), type.getId());
        return type;
    }

    @Override
    public ToolItem deleteByKey(UUID key) {
        jdbcTemplate.update(deleteToolItemById, key);
        return new ToolItem(key);
    }

    @Override
    public ToolItem delete(ToolItem type) {
        return deleteByKey(type.getId());
    }

    @Override
    public Locate createLocate(Locate locate) {
        jdbcTemplate.update(insertLocateQuery, locate.getId(), locate.getName(), locate.getDescription());
        return locate;
    }

    @Override
    public List<Locate> findAllLocates() {
        List<Locate> locates = jdbcTemplate.query(findAllLocatesQuery, this::mapperLocateFromRs);
        return locates;
    }

    @Override
    public Optional<Locate> findLocateById(UUID id) {
        try {
            Locate locate = jdbcTemplate.queryForObject(findAllLocatesQuery, this::mapperLocateFromRs, id);
            return Optional.of(locate);
        } catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private ToolItem mapperToolItemFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String patrimony = rs.getString("patrimony");
        ToolSituation situation = ToolSituation.valueOf(rs.getString("situation"));
        UUID locateId = (UUID) rs.getObject("locate_id");
        UUID toolId = (UUID) rs.getObject("tool_id");
        return new ToolItem(id, patrimony, locateId, toolId, situation);
    }

    private Locate mapperLocateFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        return new Locate(id, name, description);
    }
}

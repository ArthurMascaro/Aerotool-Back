package edu.br.ifsp.applications.persistence.inmemory;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.tools.ToolDAO;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

import java.util.*;

public class InMemoryToolDAO implements ToolDAO {

    private static final Map<UUID, Tool> db = new LinkedHashMap<>();

    public Map getDb() {
        return db;
    }

    @Override
    public Optional<Tool> findByUUID(UUID id) {
        return findOne(id);
    }

    @Override
    public Tool create(Tool type) {
        if(db.containsKey(type.getId()))
            throw new EntityAlreadyExistsException("This Tool already exists");
        db.put(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Optional<Tool> findOne(UUID key) {
        return Optional.ofNullable(db.get(key));
    }

    @Override
    public List<Tool> findALL() {
        return new ArrayList<Tool>(db.values());
    }

    @Override
    public Tool update(Tool type) {
        if (!db.containsKey(type.getId()))
            throw new NoSuchElementException("There is no tools with this ID.");
        db.replace(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Tool deleteByKey(UUID key) {
        if (!db.containsKey(key))
            throw new NoSuchElementException("There is no tools with this ID.");
        return db.remove(key);
    }

    @Override
    public Tool delete(Tool type) {
        return deleteByKey(type.getId());
    }
}

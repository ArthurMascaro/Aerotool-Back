package edu.br.ifsp.applications.persistence.inmemory;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.tools.ToolItemDAO;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

import java.util.*;

public class InMemoryToolItemDAO implements ToolItemDAO {

    private static final Map<UUID, ToolItem> db = new LinkedHashMap<>();

    public Map getDb() {
        return db;
    }

    @Override
    public ToolItem create(ToolItem type) {
        if (db.containsKey(type.getId()))
            throw new EntityAlreadyExistsException("This Tool Item already exists.");
        db.put(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Optional<ToolItem> findOne(UUID key) {
        return Optional.ofNullable(db.get(key));
    }

    @Override
    public List<ToolItem> findALL() {
        return new ArrayList<ToolItem>(db.values());
    }

    @Override
    public ToolItem update(ToolItem type) {
        if (!db.containsKey(type.getId()))
            throw new NoSuchElementException("This Tool Item doesn't exists.");
        db.replace(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public ToolItem deleteByKey(UUID key) {
        if (!db.containsKey(key))
            throw new NoSuchElementException("This Tool Item doesn't exists.");
        return db.remove(key);
    }

    @Override
    public ToolItem delete(ToolItem type) {
        return deleteByKey(type.getId());
    }
}

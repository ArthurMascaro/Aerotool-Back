package edu.br.ifsp.applications.persistence.inmemory;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.events.EventDAO;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

import java.util.*;

public class InMemoryEventDAO implements EventDAO {

    private static final Map<UUID, Event> db = new LinkedHashMap<>();

    public Map getDb() {
        return db;
    }

    @Override
    public Optional<Event> findByUUID(UUID id) {
        return findOne(id);
    }

    @Override
    public Event create(Event type) {
        if(db.containsKey(type.getId()))
            throw new EntityAlreadyExistsException("This Event already exists");
        db.put(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Optional<Event> findOne(UUID key) {
        return Optional.ofNullable(db.get(key));
    }

    @Override
    public List<Event> findByUserResponsibleId(UUID id) {
        return Optional.ofNullable(db.get(id)).map(Arrays::asList).orElse(new ArrayList<>());
    }

    @Override
    public List<Event> findByUserSubjectId(UUID id) {
        return Optional.ofNullable(db.get(id)).map(Arrays::asList).orElse(new ArrayList<>());
    }

    @Override
    public List<Event> findALL() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Event update(Event type) {
        if (!db.containsKey(type.getId()))
            throw new NoSuchElementException("Event nonexistent");
        db.replace(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Event deleteByKey(UUID key) {
        if (!db.containsKey(key))
            throw new NoSuchElementException("Event nonexistent");
        return db.remove(key);
    }

    @Override
    public Event delete(Event type) {
        return deleteByKey(type.getId());
    }
}

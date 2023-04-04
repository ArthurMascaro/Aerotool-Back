package edu.br.ifsp.applications.persistence.inmemory;


import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.transactions.RequestDAO;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

import java.util.*;

public class InMemoryRequestDAO implements RequestDAO{

    private static final Map<UUID, Request> db = new LinkedHashMap<>();

    public Map getDb() {
        return db;
    }

    public Optional<Request> findByUUID(UUID id) {
        return findOne(id);
    }


    @Override
    public Request create(Request type) {
        if(type.getId() == null)
            throw new EntityAlreadyExistsException("This Request already exists");
        db.put(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Optional<Request> findOne(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<Request> findALL() {  return new ArrayList<>(db.values());
    }

    @Override
    public Request update(Request type) {
        if (!db.containsKey(type.getId()))
            throw new NoSuchElementException("Request doesn't exist");
        db.replace(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Request deleteByKey(UUID key) {

        if (!db.containsKey(key))
            throw new NoSuchElementException("Request doesn't exist");
        return db.remove(key);
    }

    @Override
    public Request delete(Request type){ return deleteByKey(type.getId()); }
}

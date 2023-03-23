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
        return findOne(String.valueOf(id));
    }


    @Override
    public Request create(Request type) {
        if(type.getId() == null)
            throw new EntityAlreadyExistsException("This Request already exists");
        db.put(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Optional<Request> findOne(String key) {
        return Optional.empty();
    }

    @Override
    public List<Request> findALL() {
        return null;
    }

    @Override
    public Request update(Request type) {
        return null;
    }

    @Override
    public Request deleteByKey(String key) {
        return null;
    }

    @Override
    public Request delete(Request type) {
        return null;
    }
}

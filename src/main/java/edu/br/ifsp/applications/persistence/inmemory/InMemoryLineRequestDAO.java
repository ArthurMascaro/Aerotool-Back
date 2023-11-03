package edu.br.ifsp.applications.persistence.inmemory;

import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.transaction.RequestSituation;
import edu.br.ifsp.domain.usecases.transactions.LineRequestDAO;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

import java.sql.Timestamp;
import java.util.*;

public class InMemoryLineRequestDAO implements LineRequestDAO {

    private static final Map<UUID, LineRequest> db = new LinkedHashMap<>();

    public Map getDb() {
        return db;
    }

    @Override
    public Optional<LineRequest> findByRequest(Request request) {
        return Optional.empty();
    }

    @Override
    public Optional<LineRequest> findByUUID(UUID id) {
        return findOne(id);
    }

    @Override
    public LineRequest create(LineRequest type) {
        if (db.containsKey(type.getId()))
            throw new EntityAlreadyExistsException("This ID is already in use");
        db.put(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public Optional<LineRequest> findOne(UUID key) {
        return Optional.ofNullable(db.get(key));
    }

    @Override
    public List<LineRequest> findALL() {
        return new ArrayList<>(db.values());
    }

    @Override
    public LineRequest update(LineRequest type) {
        if(!db.containsKey(type.getId()))
            throw new NoSuchElementException("This Line Request dont exists");
        db.replace(type.getId(), type);
        return db.get(type.getId());
    }

    @Override
    public LineRequest updateSituation(RequestSituation situation, UUID requestId) {
        return null;
    }

    @Override
    public LineRequest updateExpectedDates(Timestamp expectedReturnDate, Timestamp expectedWithdrawalDate, UUID requestId) {
        return null;
    }

    @Override
    public LineRequest updateRealReturnDate(Timestamp realReturnDate, UUID requestId) {
        return null;
    }

    @Override
    public LineRequest updateRealWithdrawalDate(Timestamp realWithdrawalDate, UUID requestId) {
        return null;
    }

    @Override
    public LineRequest deleteByKey(UUID key) {
        if(!db.containsKey(key))
            throw new NoSuchElementException("This Line Request dont exists");
        return db.remove(key);
    }

    @Override
    public LineRequest delete(LineRequest type) {
        return deleteByKey(type.getId());
    }
}

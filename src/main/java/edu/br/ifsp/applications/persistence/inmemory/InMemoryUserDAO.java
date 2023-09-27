package edu.br.ifsp.applications.persistence.inmemory;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.user.UserDAO;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

import java.util.*;

public class InMemoryUserDAO implements UserDAO {

    private static final Map<Promptuary, User> db = new LinkedHashMap<>();

    public Map getDb() {
        return db;
    }

    @Override
    public Optional<User> findByPromptuary(Promptuary promptuary) {
        return findOne(promptuary);
    }

    @Override
    public User create(User type) {
        if (db.containsKey(type.getPromptuary()))
            throw new EntityAlreadyExistsException("This promptuary is already in use");
        db.put(type.getPromptuary(), type);
        return db.get(type.getPromptuary());
    }

    @Override
    public Optional<User> findOne(Promptuary promptuary) {
        return Optional.ofNullable(db.get(promptuary));
    }

    @Override
    public Optional<User> findByUUID(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(db.get(name));
    }

    @Override
    public List<User> findALL() {
        return new ArrayList<>(db.values());
    }

    @Override
    public User update(User type) {
        if (!db.containsKey(type.getPromptuary()))
            throw new NoSuchElementException("User doesn't exist");
        db.replace(type.getPromptuary(), type);
        return db.get(type.getPromptuary());
    }

    @Override
    public User deleteByKey(Promptuary promptuary) {
        if (!db.containsKey(promptuary))
            throw new NoSuchElementException("User doesn't exist");
        return db.remove(promptuary);
    }

    @Override
    public User delete(User type) {
        return deleteByKey(type.getPromptuary());
    }

}

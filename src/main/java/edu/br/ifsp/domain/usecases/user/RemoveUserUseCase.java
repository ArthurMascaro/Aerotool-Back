package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.event.user.User;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.util.UUID;

public class RemoveUserUseCase {

    private UserDAO userDAO;

    public RemoveUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean remove(UUID id) {
        if (id == null || userDAO.findOne(id.toString()).isEmpty()) {
            throw new EntityNotFoundException("User not found!");
        }
        return userDAO.deleteByKey(id.toString());
    }

    public boolean remove(User user) {
        if (user == null || userDAO.findOne(user.getId().toString()).isEmpty()) {
            throw new EntityNotFoundException("User not found!");
        }
        return userDAO.delete(user);
    }
}

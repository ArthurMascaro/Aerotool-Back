package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.util.UUID;

public class RemoveUserUseCase {

    private UserDAO userDAO;

    public RemoveUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User remove(Promptuary promptuary) {
        if (promptuary == null || userDAO.findOne(promptuary).isEmpty()) {
            throw new EntityNotFoundException("User not found!");
        }
        return userDAO.deleteByKey(promptuary);
    }

    public User remove(User user) {
        if (user == null || userDAO.findOne(user.getPromptuary()).isEmpty()) {
            throw new EntityNotFoundException("User not found!");
        }
        return userDAO.delete(user);
    }
}

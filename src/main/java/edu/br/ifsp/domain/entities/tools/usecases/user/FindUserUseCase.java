package edu.br.ifsp.domain.entities.tools.usecases.user;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindUserUseCase {

    private UserDAO userDAO;

    public FindUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> findOne(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return userDAO.findOne(id.toString());
    }

    public Optional<User> findByPromptuary(Promptuary promptuary) {
        if (Validator.nullOrEmpty(promptuary.toString())) {
            throw new IllegalArgumentException("Promptuary cannot be null or empty!");
        }
        return userDAO.findByPromptuary(promptuary);
    }

    public List<User> findAll() {
        return userDAO.findALL();
    }
}

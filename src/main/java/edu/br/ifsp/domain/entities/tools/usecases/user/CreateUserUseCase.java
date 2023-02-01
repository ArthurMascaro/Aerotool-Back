package edu.br.ifsp.domain.entities.tools.usecases.user;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.entities.tools.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Notification;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Validator;

public class CreateUserUseCase {

    private UserDAO userDAO;

    public CreateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String insert(User user){
        Validator<User> validator = new UserInputRequestValidator();
        Notification notification = validator.validate(user);

        Promptuary promptuary = user.getPromptuary();
        if (userDAO.findByPromptuary(promptuary).isPresent())
            throw new EntityAlreadyExistsException("This promptuary is already in use");

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return userDAO.create(user);
    }
}

package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private UserDAO userDAO;

    public CreateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User insert(User user){
        Validator<User> validator = new UserInputRequestValidator();
        Notification notification = validator.validate(user);

        String promptuary = user.getPromptuary();
        if (userDAO.findByPromptuary(promptuary).isPresent())
            throw new EntityAlreadyExistsException("This promptuary is already in use");

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return userDAO.create(user);
    }
}

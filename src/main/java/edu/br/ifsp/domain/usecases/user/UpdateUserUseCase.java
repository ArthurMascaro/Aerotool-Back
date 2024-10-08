package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {

    private UserDAO userDAO;

    public UpdateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User update(User user) {
        User userDB = userDAO.findOne(user.getPromptuary())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        user.setRole(userDB.getRole());

        Validator<User> validator = new UserInputRequestValidator();
        Notification notification = validator.validate(user);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        if (userDAO.findOne(user.getPromptuary()).isEmpty()) {
            throw new EntityNotFoundException("User not found!");
        }
        return userDAO.update(user);
    }
}

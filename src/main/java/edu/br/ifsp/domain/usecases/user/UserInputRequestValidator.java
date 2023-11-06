package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

public class UserInputRequestValidator extends Validator<User> {
    @Override
    public Notification validate(User user) {
        Notification notification = new Notification();
         if (user == null){
             notification.addError("User is null", new IllegalArgumentException("User is Null"));
             return notification;
         }
         if (nullOrEmpty(user.getName()))
             notification.addError("Name is null or empty", new IllegalArgumentException());
        if (user.getRole() == null)
            notification.addError("Role is null", new IllegalArgumentException());
        if (user.getPromptuary() == null)
            notification.addError("Promptuary is null", new IllegalArgumentException());

        return notification;
    }
}

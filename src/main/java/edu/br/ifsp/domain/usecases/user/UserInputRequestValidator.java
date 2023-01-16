package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

public class UserInputRequestValidator extends Validator<User> {
    @Override
    public Notification validate(User user) {
        Notification notification = new Notification();
         if (user == null){
             notification.addError("User is null");
             return notification;
         }
         if (nullOrEmpty(user.getNome()))
             notification.addError("Name is null or empty");
        if (nullOrEmpty(user.getRole().toString()))
            notification.addError("Role is null or empty");
        if (nullOrEmpty(user.getPromptuary().toString()))
            notification.addError("Promptuary is null or empty");

        return notification;
    }
}

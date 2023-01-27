package edu.br.ifsp.domain.entities.tools.usecases.user;

import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Notification;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Validator;

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
        if (user.getRole() == null)
            notification.addError("Role is null");
        if (user.getPromptuary() == null)
            notification.addError("Promptuary is null");

        return notification;
    }
}

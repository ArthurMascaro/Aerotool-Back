package edu.br.ifsp.domain.entities.tools.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Notification;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Validator;

public class ToolInputRequestValidator extends Validator<Tool> {
    @Override
    public Notification validate(Tool tool) {
        Notification notification = new Notification();
         if (tool == null){
             notification.addError("Tool is null");
             return notification;
         }
         if (nullOrEmpty(tool.getName()))
             notification.addError("Name is null or empty");
        if (nullOrEmpty(tool.getDescription()))
            notification.addError("Description is null or empty");
        if (nullOrEmpty(tool.getType().toString()))
            notification.addError("Type is null or empty");

        return notification;
    }
}

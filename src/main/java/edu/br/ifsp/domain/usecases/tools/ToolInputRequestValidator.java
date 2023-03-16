package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.awt.print.Book;

public class ToolInputRequestValidator extends Validator<Tool> {
    @Override
    public Notification validate(Tool tool) {
        Notification notification = new Notification();
         if (tool == null){
             notification.addError("Tool is null", new IllegalArgumentException("Tool is Null"));
             return notification;
         }
         if (nullOrEmpty(tool.getName()))
             notification.addError("Name is null or empty", new IllegalArgumentException());
        if (nullOrEmpty(tool.getDescription()))
            notification.addError("Description is null or empty", new IllegalArgumentException());
        if (tool.getType() == null)
            notification.addError("Type is null or empty", new IllegalArgumentException());

        return notification;
    }
}

package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

public class ToolItemInputRequestValidator extends Validator<ToolItem> {

    @Override
    public Notification validate(ToolItem toolItem) {
        Notification notification = new Notification();
        if (toolItem == null) {
            notification.addError("Tool Item is null!");
            return notification;
        }

        if (toolItem.getId() == null){
            notification.addError("ID is null!");
        }

        if (nullOrEmpty(toolItem.getPatrimony())) {
            notification.addError("Patrimony is null or empty!");
        }
        if (toolItem.getToolId() == null) {
            notification.addError("Tool Id is null or empty!");
        }
        if (toolItem.getId() == null) {
            notification.addError("ID is null or empty!");
        }
        if (toolItem.getLocateId() == null) {
            notification.addError("Location Id is null or empty!");
        }
        if (toolItem.getSituation() == null) {
            notification.addError("Tool Situation is null or empty!");
        }

        return notification;
    }
}

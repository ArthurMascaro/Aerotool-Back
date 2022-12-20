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

        if (nullOrEmpty(toolItem.getPatrimony())) {
            notification.addError("Patrimony is null or empty!");
        }
        if (nullOrEmpty(toolItem.getTool().toString())) {
            notification.addError("Tool is null or empty!");
        }
        if (nullOrEmpty(toolItem.getId().toString())) {
            notification.addError("ID is null or empty!");
        }
        if (nullOrEmpty(toolItem.getLocate().toString())) {
            notification.addError("Location is null or empty!");
        }
        if (nullOrEmpty(toolItem.getSituation().toString())) {
            notification.addError("Tool Situation is null or empty!");
        }

        return notification;
    }
}

package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

public class EventRequestValidator extends Validator<Event> {

    @Override
    public Notification validate(Event event) {
        Notification notification = new Notification();
        if (event == null) {
            notification.addError("Event is null!");
            return notification;
        }

        if (nullOrEmpty(event.getId())) {
            notification.addError("Id is null or empty!");
        }
        if (nullOrEmpty(event.getResponsible().toString())) {
            notification.addError("Responsible is null or empty!");
        }
        if (nullOrEmpty(event.getSubject().toString())) {
            notification.addError("Subject is null or empty!");
        }
        if (nullOrEmpty(event.getDate().toString())) {
            notification.addError("Date is null or empty!");
        }
        if (nullOrEmpty(event.getSituation().toString())) {
            notification.addError("Event situation is null or empty!");
        }
        if(nullOrEmpty(event.getType().toString())){
            notification.addError("Event type is null or empty");
        }
        if(nullOrEmpty(event.getDescription())){
            notification.addError("Description is null or empty");
        }

        return notification;
    }
}
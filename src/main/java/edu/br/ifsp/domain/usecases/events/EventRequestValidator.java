package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
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

        if (nullOrEmpty(event.getId().toString())) {
            notification.addError("Id is null or empty!");
        }
        if (null == event.getResponsible()) {
            notification.addError("Responsible is null or empty!");
        }
        if (null == event.getSubject()) {
            notification.addError("Subject is null or empty!");
        }
        if (null == event.getDate()) {
            notification.addError("Date is null or empty!");
        }
        if (null == event.getSituation()) {
            notification.addError("Event situation is null or empty!");
        }
        if(null == event.getType()){
            notification.addError("Event type is null or empty");
        }
        if(nullOrEmpty(event.getDescription())){
            notification.addError("Description is null or empty");
        }

        return notification;
    }
}
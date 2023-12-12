package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

public class UpdateEventRequestValidator extends Validator<Event> {

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
        if (null == event.getSituation()) {
            notification.addError("Event situation is null or empty!");
        }

        return notification;
    }
}
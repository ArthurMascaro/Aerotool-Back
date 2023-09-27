package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

@Service
public class CreateEventUseCase{

    private EventDAO eventDAO;

    public CreateEventUseCase(EventDAO eventDAO) { this.eventDAO = eventDAO; }

    public Event insert(Event event){

        Validator<Event> validator = new EventRequestValidator();
        Notification notification = validator.validate(event);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        return eventDAO.create(event);
    }
}
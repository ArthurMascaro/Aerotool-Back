package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

@Service
public class UpdateEventUseCase {
    private EventDAO eventDAO;

    public UpdateEventUseCase(EventDAO eventDAO){ this.eventDAO = eventDAO; }

    public Event update(Event event) {
        Validator<Event> validator = new UpdateEventRequestValidator();
        Notification notification = validator.validate(event);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        if (eventDAO.findOne(event.getId()).isEmpty()) {
            throw new EntityNotFoundException("Event not found!");
        }
        return eventDAO.update(event);
    }

}

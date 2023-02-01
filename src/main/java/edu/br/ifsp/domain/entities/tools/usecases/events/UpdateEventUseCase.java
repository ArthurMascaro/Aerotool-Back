package edu.br.ifsp.domain.entities.tools.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.tools.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Notification;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Validator;

public class UpdateEventUseCase {
    private EventDAO eventDAO;

    public UpdateEventUseCase(EventDAO eventDAO){ this.eventDAO = eventDAO; }

    public boolean update(Event event) {
        Validator<Event> validator = new EventRequestValidator();
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

package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.UUID;

public class CreateEventUseCase{

    private EventDAO eventDAO;

    public CreateEventUseCase(EventDAO eventDAO) { this.eventDAO = eventDAO; }

    public UUID insert(Event event){

        Validator<Event> validator = new EventRequestValidator();
        Notification notification = validator.validate(event);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        UUID eventId = event.getId();

        if(eventDAO.findById(eventId).isPresent()){
            throw new EntityAlreadyExistsException("This id is already in use!");
        }

        return eventDAO.create(event);
    }
}
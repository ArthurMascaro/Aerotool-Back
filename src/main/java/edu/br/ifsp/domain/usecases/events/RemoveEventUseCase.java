package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.util.UUID;

public class RemoveEventUseCase {
    private EventDAO eventDAO;

    public RemoveEventUseCase(EventDAO eventDAO) { this.eventDAO = eventDAO; }

    public boolean remove(Event event) {
        if (event == null || eventDAO.findByUUID(event.getId()).isEmpty()) {
            throw new EntityNotFoundException("Event not found!");
        }

        return eventDAO.delete(event);
    }

    public boolean remove(UUID id) {
        if (id == null || eventDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Event not found!");
        }
        return eventDAO.deleteByKey(id);
    }
}

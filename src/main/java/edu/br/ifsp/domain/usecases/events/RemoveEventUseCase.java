package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveEventUseCase {
    private EventDAO eventDAO;

    public RemoveEventUseCase(EventDAO eventDAO) { this.eventDAO = eventDAO; }

    public Event remove(Event event) {
        if (event == null || eventDAO.findByUUID(event.getId()).isEmpty()) {
            throw new EntityNotFoundException("Event not found!");
        }

        return eventDAO.delete(event);
    }

    public Event remove(UUID id) {
        if (id == null || eventDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Event not found!");
        }
        return eventDAO.deleteByKey(id);
    }
}

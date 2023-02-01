package edu.br.ifsp.domain.entities.tools.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.tools.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindEventUseCase {

    private EventDAO eventDAO;

    public FindEventUseCase(EventDAO eventDAO){ this.eventDAO = eventDAO; }

    public List<Event> findAll() { return eventDAO.findALL(); }

    public Optional<Event> findOne(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return eventDAO.findOne(id);
    }

    public Optional<Event> findByUUID(UUID id) {
        if (Validator.nullOrEmpty(id.toString())) {
            throw new IllegalArgumentException("Id cannot be null or empty!");
        }
        return eventDAO.findByUUID(id);
    }

}

package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null or empty!");
        }
        return eventDAO.findByUUID(id);
    }

}

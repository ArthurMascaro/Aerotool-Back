package edu.br.ifsp.domain.entities.tools.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.tools.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface EventDAO extends DAO<Event, UUID> {

    Optional<Event> findByUUID(UUID id);
}
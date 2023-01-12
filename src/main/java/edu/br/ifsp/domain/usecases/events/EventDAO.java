package edu.br.ifsp.domain.usecases.events;

import edu.br.ifsp.domain.entities.event.Event;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.DAO;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.Optional;
import java.util.UUID;

public interface EventDAO extends DAO<Event, UUID> {

    Optional<Event> findByUUID(UUID id);
}
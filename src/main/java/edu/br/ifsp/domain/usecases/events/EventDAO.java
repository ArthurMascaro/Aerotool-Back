package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.UUID;

public interface EventDAO extends DAO<Event, UUID>{

    Optional<Event> findById(UUID id);
}
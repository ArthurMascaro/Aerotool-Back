package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface RequestDAO extends DAO<Request, UUID> {

    Optional<Request> findByUUID(UUID Id);

}

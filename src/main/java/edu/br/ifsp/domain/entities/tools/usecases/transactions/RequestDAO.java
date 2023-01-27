package edu.br.ifsp.domain.entities.tools.usecases.transactions;

import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.tools.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface RequestDAO extends DAO<Request, String> {

    Optional<Request> findByUUID(UUID Id);

}

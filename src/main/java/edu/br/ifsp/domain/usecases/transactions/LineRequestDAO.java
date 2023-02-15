package edu.br.ifsp.domain.usecases.transactions;


import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface LineRequestDAO extends DAO<LineRequest, UUID> {

    Optional<LineRequest> findByRequest(Request request);

    Optional<LineRequest> findByUUID(UUID id);
}
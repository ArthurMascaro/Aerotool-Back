package edu.br.ifsp.domain.usecases.transactions;


import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.transaction.RequestSituation;
import edu.br.ifsp.domain.usecases.utils.DAO;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public interface LineRequestDAO extends DAO<LineRequest, UUID> {

    Optional<LineRequest> findByRequest(Request request);

    Optional<LineRequest> findByUUID(UUID id);

    LineRequest updateSituation(RequestSituation situation, UUID requestId);

    LineRequest updateExpectedDates(Timestamp expectedReturnDate,
                                    Timestamp expectedWithdrawalDate, UUID requestId);

    LineRequest updateRealReturnDate(Timestamp realReturnDate, UUID requestId);

    LineRequest updateRealWithdrawalDate(Timestamp realWithdrawalDate, UUID requestId);

}
package edu.br.ifsp.web.model.transaction.request;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.transaction.RequestSituation;

import java.sql.Timestamp;
import java.util.UUID;

public record LineRequestDto(UUID requestId, UUID toolItemId, Timestamp expectedReturnDate,
                             Timestamp realReturnDate, Timestamp expectedWithdrawalDate,
                             Timestamp realWithdrawalDate, RequestSituation requestSituation) {

    public LineRequestDto(UUID requestId, UUID toolItemId, Timestamp expectedReturnDate,
                          Timestamp realReturnDate, Timestamp expectedWithdrawalDate,
                          Timestamp realWithdrawalDate, RequestSituation requestSituation) {
        this.requestId = requestId;
        this.toolItemId = toolItemId;
        this.expectedReturnDate = expectedReturnDate;
        this.realReturnDate = realReturnDate;
        this.expectedWithdrawalDate = expectedWithdrawalDate;
        this.realWithdrawalDate = realWithdrawalDate;
        this.requestSituation = requestSituation;
    }

    public LineRequest toLineRequest() {
        return new LineRequest(
                UUID.randomUUID(),
                new Request(requestId),
                new ToolItem(toolItemId),
                expectedReturnDate,
                realReturnDate,
                expectedWithdrawalDate,
                realWithdrawalDate,
                requestSituation
        );
    }
}

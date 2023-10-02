package edu.br.ifsp.web.model.transaction.request;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;

import java.sql.Timestamp;
import java.util.UUID;

public record LineRequestDto(UUID requestId, UUID toolItemId, Timestamp expectedReturnDate, Timestamp expectedWithdrawDate) {

    public LineRequestDto(UUID requestId, UUID toolItemId, Timestamp expectedReturnDate, Timestamp expectedWithdrawDate) {
        this.requestId = requestId;
        this.toolItemId = toolItemId;
        this.expectedReturnDate = expectedReturnDate;
        this.expectedWithdrawDate = expectedWithdrawDate;
    }

    public LineRequest toLineRequest() {
        return new LineRequest(
                UUID.randomUUID(),
                new Request(requestId),
                new ToolItem(toolItemId),
                expectedReturnDate,
                null,
                expectedWithdrawDate,
                null,
                null
        );
    }
}

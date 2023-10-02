package edu.br.ifsp.web.model.transaction.response;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.transaction.RequestSituation;

import java.sql.Timestamp;
import java.util.UUID;

public class LineRequestResponse {

    private UUID id;
    private Request request;
    private ToolItem toolItem;
    private Timestamp expectedReturnDate;
    private Timestamp realReturnDate;
    private Timestamp expectedWithdrawDate;
    private Timestamp realWithdrawDate;
    private RequestSituation situation;

    public LineRequestResponse(UUID id, Request request, ToolItem toolItem, Timestamp expectedReturnDate, Timestamp realReturnDate, Timestamp expectedWithdrawDate, Timestamp realWithdrawDate, RequestSituation situation) {
        this.id = id;
        this.request = request;
        this.toolItem = toolItem;
        this.expectedReturnDate = expectedReturnDate;
        this.realReturnDate = realReturnDate;
        this.expectedWithdrawDate = expectedWithdrawDate;
        this.realWithdrawDate = realWithdrawDate;
        this.situation = situation;
    }

    public static LineRequestResponse fromLineRequest(LineRequest lineRequest){
        return new LineRequestResponse(
                lineRequest.getId(),
                lineRequest.getRequest(),
                lineRequest.getToolItem(),
                lineRequest.getExpectedReturnDate(),
                lineRequest.getRealReturnDate(),
                lineRequest.getExpectedWithdrawDate(),
                lineRequest.getRealWithdrawDate(),
                lineRequest.getSituation()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ToolItem getToolItem() {
        return toolItem;
    }

    public void setToolItem(ToolItem toolItem) {
        this.toolItem = toolItem;
    }

    public Timestamp getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Timestamp expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Timestamp getRealReturnDate() {
        return realReturnDate;
    }

    public void setRealReturnDate(Timestamp realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    public Timestamp getExpectedWithdrawDate() {
        return expectedWithdrawDate;
    }

    public void setExpectedWithdrawDate(Timestamp expectedWithdrawDate) {
        this.expectedWithdrawDate = expectedWithdrawDate;
    }

    public Timestamp getRealWithdrawDate() {
        return realWithdrawDate;
    }

    public void setRealWithdrawDate(Timestamp realWithdrawDate) {
        this.realWithdrawDate = realWithdrawDate;
    }

    public RequestSituation getSituation() {
        return situation;
    }

    public void setSituation(RequestSituation situation) {
        this.situation = situation;
    }
}

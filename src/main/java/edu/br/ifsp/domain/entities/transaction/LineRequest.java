package edu.br.ifsp.domain.entities.transaction;

import edu.br.ifsp.domain.entities.tools.ToolItem;

import java.sql.Timestamp;
import java.util.UUID;

public class LineRequest{

    private UUID id;
    private Request request;
    private ToolItem toolItem;
    private Timestamp expectedReturnDate;
    private Timestamp realReturnDate;
    private Timestamp expectedWithdrawalDate;
    private Timestamp realWithdrawalDate;
    private RequestSituation situation;

    public LineRequest(UUID id) {
        this.id = id;
    }

    public LineRequest() { this.id = UUID.randomUUID(); }

    public LineRequest(UUID id, Request request, ToolItem toolItem, Timestamp expectedReturnDate,
                       Timestamp expectedWithdrawalDate, RequestSituation situation) {
        this.id = id;
        this.request = request;
        this.toolItem = toolItem;
        this.expectedReturnDate = expectedReturnDate;
        this.expectedWithdrawalDate = expectedWithdrawalDate;
        this.situation = situation;
    }

    public LineRequest(UUID id, Request request, ToolItem toolItem, Timestamp expectedReturnDate, Timestamp realReturnDate, Timestamp expectedWithdrawalDate, Timestamp realWithdrawalDate, RequestSituation situation) {
        this.id = id;
        this.request = request;
        this.toolItem = toolItem;
        this.expectedReturnDate = expectedReturnDate;
        this.realReturnDate = realReturnDate;
        this.expectedWithdrawalDate = expectedWithdrawalDate;
        this.realWithdrawalDate = realWithdrawalDate;
        this.situation = situation;
    }

    public UUID getId(){ return id;}

    public void setId(UUID id){this.id = id;}

    public Request getRequest(){return request;}

    public ToolItem getToolItem(){return toolItem;}

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

    public Timestamp getExpectedWithdrawalDate() {
        return expectedWithdrawalDate;
    }

    public void setExpectedWithdrawalDate(Timestamp expectedWithdrawalDate) {
        this.expectedWithdrawalDate = expectedWithdrawalDate;
    }

    public Timestamp getRealWithdrawalDate() {
        return realWithdrawalDate;
    }

    public void setRealWithdrawalDate(Timestamp realWithdrawalDate) {
        this.realWithdrawalDate = realWithdrawalDate;
    }

    public RequestSituation getSituation() {
        return situation;
    }

    public void setSituation(RequestSituation situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "LineRequest{" +
                "expectedReturnDate=" + expectedReturnDate +
                ", realReturnDate=" + realReturnDate +
                ", expectedWithdrawDate=" + expectedWithdrawalDate +
                ", realWithdrawDate=" + realWithdrawalDate +
                ", situation=" + situation +
                '}';
    }
}

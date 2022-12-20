package edu.br.ifsp.domain.entities.transaction;

import edu.br.ifsp.domain.entities.tools.ToolItem;

import java.sql.Timestamp;
import java.util.UUID;

public class LineRequest{

    private Request request;
    private ToolItem toolItem;
    private Timestamp expectedReturnDate;
    private Timestamp realReturnDate;
    private Timestamp expectedWithdrawDate;
    private Timestamp realWithdrawDate;
    private RequestSituation situation;

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

    @Override
    public String toString() {
        return "LineRequest{" +
                "expectedReturnDate=" + expectedReturnDate +
                ", realReturnDate=" + realReturnDate +
                ", expectedWithdrawDate=" + expectedWithdrawDate +
                ", realWithdrawDate=" + realWithdrawDate +
                ", situation=" + situation +
                '}';
    }
}

package edu.br.ifsp.domain.entities.transaction;

import java.sql.Timestamp;
import java.util.UUID;

public class LineRequest extends Request{

    private Timestamp expectedReturnDate;
    private Timestamp realReturnDate;
    private Timestamp expectedWithdrawDate;
    private Timestamp realWithdrawDate;
    private RequestSituation situation;

    public LineRequest(UUID id, Timestamp date) {
        super(id, date);
    }

    public LineRequest(UUID id, Timestamp date, Timestamp expectedReturnDate, Timestamp realReturnDate, Timestamp expectedWithdrawDate, Timestamp realWithdrawDate, RequestSituation situation) {
        super(id, date);
        this.expectedReturnDate = expectedReturnDate;
        this.realReturnDate = realReturnDate;
        this.expectedWithdrawDate = expectedWithdrawDate;
        this.realWithdrawDate = realWithdrawDate;
        this.situation = situation;
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

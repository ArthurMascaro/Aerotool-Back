package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

public class LineRequestInputRequestValidator extends Validator<LineRequest>{

    @Override
    public Notification validate(LineRequest lineRequest) {
        Notification notification = new Notification();
        if (lineRequest == null) {
            notification.addError("Request is null!");
            return notification;
        }

        if (nullOrEmpty(lineRequest.getId().toString())){
            notification.addError("LineRequest ID is null or empty");
        }
        if (nullOrEmpty(lineRequest.getRequest().getId().toString())) {
            notification.addError("LineRequest's Request ID is null or empty!");
        }

        if (nullOrEmpty(lineRequest.getToolItem().getId().toString())){
            notification.addError("Tool Item ID is null!");
        }

        if(nullOrEmpty(lineRequest.getExpectedReturnDate().toString())){
            notification.addError("Expected return date is null!");
        }

        if (nullOrEmpty(lineRequest.getRealReturnDate().toString())){
            notification.addError("Real return date is null!");
        }

        if (nullOrEmpty(lineRequest.getExpectedWithdrawalDate().toString())){
            notification.addError("Expected withdraw date is null!");
        }

        if (nullOrEmpty(lineRequest.getRealWithdrawalDate().toString())){
            notification.addError("Real withdraw date is null!");
        }

        if (nullOrEmpty(lineRequest.getSituation().toString())){
            notification.addError("Situation is null!");
        }

        return notification;
    }

}

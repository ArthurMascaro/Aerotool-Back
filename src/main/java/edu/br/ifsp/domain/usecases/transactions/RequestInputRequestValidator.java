package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

public class RequestInputRequestValidator extends Validator<Request>{

    @Override
    public Notification validate(Request request) {
        Notification notification = new Notification();
        if (request == null) {
            notification.addError("Request is null!");
            return notification;
        }

        if (nullOrEmpty(request.getId().toString())){
            notification.addError("Request ID is null or empty");
        }
        if (nullOrEmpty(request.getUser().toString())) {
            notification.addError("User promptuary is null or empty!");
        }
        if (nullOrEmpty(request.getDate().toString())) {
            notification.addError("Date is null or empty!");
        }

        return notification;
    }

}

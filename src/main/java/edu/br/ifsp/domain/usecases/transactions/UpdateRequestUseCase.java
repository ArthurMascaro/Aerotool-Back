package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

@Service
public class UpdateRequestUseCase {
    private RequestDAO requestDAO;

    public UpdateRequestUseCase(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }

    public Request update(Request request) {
        Validator<Request> validator = new RequestInputRequestValidator();
        Notification notification = validator.validate(request);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        if (requestDAO.findOne(request.getId()).isEmpty()) {
            throw new EntityNotFoundException("Request not found!");
        }

        return requestDAO.update(request);
    }
}

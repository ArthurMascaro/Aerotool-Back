
package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

@Service
public class UpdateLineRequestUseCase {

    private LineRequestDAO lineRequestDAO;

    public UpdateLineRequestUseCase(LineRequestDAO lineRequestDAO) {
        this.lineRequestDAO = lineRequestDAO;
    }

    public LineRequest update(LineRequest lineRequest) {
        Validator<LineRequest> validator = new LineRequestInputRequestValidator();
        Notification notification = validator.validate(lineRequest);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        if (lineRequestDAO.findOne(lineRequest.getId()).isEmpty()) {
            throw new EntityNotFoundException("LineRequest not found!");
        }
        return lineRequestDAO.update(lineRequest);
    }
}

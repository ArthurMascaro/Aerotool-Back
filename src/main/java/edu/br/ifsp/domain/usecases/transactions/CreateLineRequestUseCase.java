package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.tools.ToolSituation;
import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.tools.FindToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.UpdateToolItemUseCase;
import edu.br.ifsp.domain.usecases.user.UserInputRequestValidator;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.util.UUID;

@Service
public class CreateLineRequestUseCase {

    private LineRequestDAO lineRequestDAO;
    private FindToolItemUseCase findToolItemUseCaseUseCase;
    private FindRequestUseCase findRequestUseCase;

    public CreateLineRequestUseCase(LineRequestDAO lineRequestDAO, FindToolItemUseCase findToolItemUseCaseUseCase, FindRequestUseCase findRequestUseCase) {
        this.lineRequestDAO = lineRequestDAO;
        this.findToolItemUseCaseUseCase = findToolItemUseCaseUseCase;
        this.findRequestUseCase = findRequestUseCase;
    }

    public CreateLineRequestUseCase(LineRequestDAO lineRequestDAO) {
        this.lineRequestDAO = lineRequestDAO;
    }

    public LineRequest createLineRequest(LineRequest lineRequest) throws LineRequestNotAllowedException {
        UUID requestID = lineRequest.getRequest().getId();
        UUID toolItemID = lineRequest.getToolItem().getId();

        if (toolItemID == null)
            throw new IllegalArgumentException("Tool Item ID is null.");

        if (requestID == null)
            throw new IllegalArgumentException("Request ID is null.");

        ToolItem toolItem = findToolItemUseCaseUseCase.findOne(toolItemID);

        findRequestUseCase.findOne(requestID).orElseThrow(() ->
                new EntityNotFoundException("Can not find a Request with ID" + requestID));

        if(toolItem.getSituation() == ToolSituation.BUSY)
            throw new LineRequestNotAllowedException("The Tool Item with ID" + toolItemID + "is unavailable.");

        return lineRequestDAO.create(lineRequest);
    }

    public LineRequest insert(LineRequest lineRequest){
        Validator<LineRequest> validator = new LineRequestInputRequestValidator();
        Notification notification = validator.validate(lineRequest);

        UUID id = lineRequest.getId();

        if (lineRequestDAO.findByUUID(id).isPresent())
            throw new EntityAlreadyExistsException("This ID is already in use");
        
        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return lineRequestDAO.create(lineRequest);
    }
}

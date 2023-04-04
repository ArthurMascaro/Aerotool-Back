package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.user.FindUserUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.sql.Timestamp;
import java.util.UUID;

public class CreateRequestUseCase {
    private RequestDAO requestDAO;
    private FindUserUseCase findUserUseCase;


    public CreateRequestUseCase(
            RequestDAO requestDAO,
            FindUserUseCase findUserUseCase){


        this.requestDAO = requestDAO;
        this.findUserUseCase = findUserUseCase;
    }

    public Request createARequest(Request request) throws RequestNotAllowedException {

        Validator<Request> validator = new RequestInputRequestValidator();
        validator.validate(request);
        if (request.getId() == null){
            throw new IllegalArgumentException("Request ID is null.");
        }

        if (request.getUser().getPromptuary() == null){
            throw new IllegalArgumentException("User's promptuary is null.");
        }

        User user = findUserUseCase.findByPromptuary(request.getUser().getPromptuary()).orElseThrow(() ->
                new EntityNotFoundException("Can not find a User with promptuary" + request.getUser().getPromptuary()));


        return requestDAO.create(request);

    }
}

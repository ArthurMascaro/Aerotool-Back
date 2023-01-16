package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.event.user.Promptuary;
import edu.br.ifsp.domain.entities.event.user.User;
import edu.br.ifsp.domain.usecases.user.FindUserUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;

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

    public String createARequest(Promptuary userPromptuary, UUID id) throws RequestNotAllowedException {
        if (id == null){
            throw new IllegalArgumentException("Request ID is null.");
        }

        if (userPromptuary == null){
            throw new IllegalArgumentException("User's promptuary is null.");
        }

        User user = findUserUseCase.findByPromptuary(userPromptuary).orElseThrow(() ->
                new EntityNotFoundException("Can not find a User with promptuary" + userPromptuary));



        Request request = new Request(id, user);
        return requestDAO.create(request);

    }
}

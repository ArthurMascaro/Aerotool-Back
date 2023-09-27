package edu.br.ifsp.web.controller;

import edu.br.ifsp.domain.usecases.transactions.CreateRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.FindRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.RemoveRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.UpdateRequestUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    CreateRequestUseCase createRequestUseCase;
    FindRequestUseCase findRequestUseCase;
    UpdateRequestUseCase updateRequestUseCase;
    RemoveRequestUseCase removeRequestUseCase;

    public RequestController(CreateRequestUseCase createRequestUseCase, FindRequestUseCase findRequestUseCase, UpdateRequestUseCase updateRequestUseCase, RemoveRequestUseCase removeRequestUseCase) {
        this.createRequestUseCase = createRequestUseCase;
        this.findRequestUseCase = findRequestUseCase;
        this.updateRequestUseCase = updateRequestUseCase;
        this.removeRequestUseCase = removeRequestUseCase;
    }
}

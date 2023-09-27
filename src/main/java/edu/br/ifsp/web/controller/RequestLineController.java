package edu.br.ifsp.web.controller;

import edu.br.ifsp.domain.usecases.transactions.CreateLineRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.FindLineRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.RemoveLineRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.UpdateLineRequestUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/request-lines")
public class RequestLineController {

    CreateLineRequestUseCase createLineRequestUseCase;
    FindLineRequestUseCase findLineRequestUseCase;
    UpdateLineRequestUseCase updateLineRequestUseCase;
    RemoveLineRequestUseCase removeLineRequestUseCase;

    public RequestLineController(CreateLineRequestUseCase createLineRequestUseCase, FindLineRequestUseCase findLineRequestUseCase, UpdateLineRequestUseCase updateLineRequestUseCase, RemoveLineRequestUseCase removeLineRequestUseCase) {
        this.createLineRequestUseCase = createLineRequestUseCase;
        this.findLineRequestUseCase = findLineRequestUseCase;
        this.updateLineRequestUseCase = updateLineRequestUseCase;
        this.removeLineRequestUseCase = removeLineRequestUseCase;
    }


}

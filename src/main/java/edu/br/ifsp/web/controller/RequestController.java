package edu.br.ifsp.web.controller;

import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.transactions.CreateRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.FindRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.RemoveRequestUseCase;
import edu.br.ifsp.web.model.transaction.response.RequestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    CreateRequestUseCase createRequestUseCase;
    FindRequestUseCase findRequestUseCase;
    RemoveRequestUseCase removeRequestUseCase;

    public RequestController(CreateRequestUseCase createRequestUseCase, FindRequestUseCase findRequestUseCase, RemoveRequestUseCase removeRequestUseCase) {
        this.createRequestUseCase = createRequestUseCase;
        this.findRequestUseCase = findRequestUseCase;
        this.removeRequestUseCase = removeRequestUseCase;
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<RequestResponse> createRequest(@PathVariable UUID userId) {
        User user = new User().createWithId(userId);
        Request request = createRequestUseCase.createARequest(new Request(user));
        return ResponseEntity.ok(RequestResponse.fromRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponse> findRequest(@PathVariable UUID id) {
        Request request = findRequestUseCase.findOne(id).orElseThrow(
                () -> new RuntimeException("Request not found")
        );
        return ResponseEntity.ok(RequestResponse.fromRequest(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> removeRequest(@PathVariable UUID id) {
        Request request = removeRequestUseCase.remove(id);
        return ResponseEntity.ok(request.getId());
    }
}

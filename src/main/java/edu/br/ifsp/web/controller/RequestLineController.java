package edu.br.ifsp.web.controller;

import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.usecases.transactions.CreateLineRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.FindLineRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.RemoveLineRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.UpdateLineRequestUseCase;
import edu.br.ifsp.web.model.transaction.request.LineRequestDto;
import edu.br.ifsp.web.model.transaction.response.LineRequestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping
    public ResponseEntity<LineRequestResponse> createLineRequest(@RequestBody LineRequestDto lineRequestDto) {
        LineRequest lineRequest = createLineRequestUseCase.createLineRequest(lineRequestDto.toLineRequest());
        return ResponseEntity.ok(LineRequestResponse.fromLineRequest(lineRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineRequestResponse> findOne(@PathVariable UUID id) {
        LineRequest lineRequest = findLineRequestUseCase.findOne(id).orElseThrow(
                () -> new RuntimeException("Line Request not found")
        );
        return ResponseEntity.ok(LineRequestResponse.fromLineRequest(lineRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LineRequestResponse>> findAll() {
        List<LineRequest> lineRequestList = findLineRequestUseCase.findAll();
        return ResponseEntity.ok(lineRequestList.stream()
                .map(LineRequestResponse::fromLineRequest)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineRequestResponse> updateLineRequest(@PathVariable UUID id, @RequestBody LineRequestDto lineRequestDto) {
        LineRequest lineRequest = lineRequestDto.toLineRequest();
        lineRequest.setId(id);
        LineRequest lineRequestUpdated = updateLineRequestUseCase.update(lineRequest);
        return ResponseEntity.ok(LineRequestResponse.fromLineRequest(lineRequestUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LineRequestResponse> deleteLineRequest(@PathVariable UUID id) {
        LineRequest lineRequest = removeLineRequestUseCase.remove(id);
        return ResponseEntity.ok(LineRequestResponse.fromLineRequest(lineRequest));
    }

}

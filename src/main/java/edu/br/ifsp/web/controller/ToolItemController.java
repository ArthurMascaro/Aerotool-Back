package edu.br.ifsp.web.controller;

import edu.br.ifsp.domain.entities.tools.Locate;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.tools.CreateToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.FindToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.RemoveToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.UpdateToolItemUseCase;
import edu.br.ifsp.web.model.tool.request.LocateRequest;
import edu.br.ifsp.web.model.tool.request.ToolItemRequest;
import edu.br.ifsp.web.model.tool.response.LocateResponse;
import edu.br.ifsp.web.model.tool.response.ToolItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tool-items")
public class ToolItemController {

    private CreateToolItemUseCase createToolItemUseCase;
    private UpdateToolItemUseCase updateToolItemUseCase;
    private FindToolItemUseCase findToolItemUseCase;
    private RemoveToolItemUseCase removeToolItemUseCase;

    public ToolItemController(CreateToolItemUseCase createToolItemUseCase, UpdateToolItemUseCase updateToolItemUseCase, FindToolItemUseCase findToolItemUseCase, RemoveToolItemUseCase removeToolItemUseCase) {
        this.createToolItemUseCase = createToolItemUseCase;
        this.updateToolItemUseCase = updateToolItemUseCase;
        this.findToolItemUseCase = findToolItemUseCase;
        this.removeToolItemUseCase = removeToolItemUseCase;
    }

    @PostMapping("/locate/save")
    public ResponseEntity<LocateResponse> createNewLocate(@RequestBody LocateRequest locateRequest) {
        Locate locate = createToolItemUseCase.insertLocate(locateRequest.toLocate());
        return ResponseEntity.ok(LocateResponse.fromLocate(locate));
    }

    @GetMapping("/locate/all")
    public ResponseEntity<List<LocateResponse>> findAllLocate(){
        List<Locate> locates = findToolItemUseCase.findAllLocates();
        return ResponseEntity.ok(locates.stream()
                .map(LocateResponse::fromLocate)
                .collect(Collectors.toList()));
    }

    @PostMapping("/save")
    public ResponseEntity<ToolItemResponse> saveNewToolItem(@RequestBody ToolItemRequest toolItemRequest){
        ToolItem toolItem = createToolItemUseCase.insert(toolItemRequest.toToolItem().newInstanceWithId(UUID.randomUUID()));
        return ResponseEntity.ok(ToolItemResponse.createFromToolItem(toolItem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolItemResponse> findToolItemById(@PathVariable("id") UUID id){
        ToolItem toolItem = findToolItemUseCase.findOne(id);
        return ResponseEntity.ok(ToolItemResponse.createFromToolItem(toolItem));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ToolItemResponse>> findAllToolItem(){
        List<ToolItem> toolItems = findToolItemUseCase.findAll();
        return ResponseEntity.ok(toolItems.stream()
                .map(ToolItemResponse::createFromToolItem)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToolItemResponse> updateToolItem(@PathVariable("id") UUID id, @RequestBody ToolItemRequest toolItemRequest){
        ToolItem toolItem = updateToolItemUseCase.update(toolItemRequest.toToolItem().newInstanceWithId(id));
        return ResponseEntity.ok(ToolItemResponse.createFromToolItem(toolItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteToolItem(@PathVariable("id") UUID id){
        ToolItem toolItem = removeToolItemUseCase.remove(id);
        return ResponseEntity.ok(toolItem.getId());
    }
}

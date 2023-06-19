package edu.br.ifsp.web.controller;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.tools.CreateToolUseCase;
import edu.br.ifsp.domain.usecases.tools.FindToolUseCase;
import edu.br.ifsp.domain.usecases.tools.RemoveToolUseCase;
import edu.br.ifsp.domain.usecases.tools.UpdateToolUseCase;
import edu.br.ifsp.web.model.tool.request.ToolRequest;
import edu.br.ifsp.web.model.tool.response.ToolResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tools")
public class ToolController {

    private CreateToolUseCase createToolUseCase;
    private FindToolUseCase findToolUseCase;
    private RemoveToolUseCase removeToolUseCase;
    private UpdateToolUseCase updateToolUseCase;

    public ToolController(CreateToolUseCase createToolUseCase, FindToolUseCase findToolUseCase, RemoveToolUseCase removeToolUseCase, UpdateToolUseCase updateToolUseCase) {
        this.createToolUseCase = createToolUseCase;
        this.findToolUseCase = findToolUseCase;
        this.removeToolUseCase = removeToolUseCase;
        this.updateToolUseCase = updateToolUseCase;
    }

    @PostMapping("/save")
    public ResponseEntity<ToolResponse> createNewTool(@RequestBody ToolRequest toolRequest) {
        Tool tool = createToolUseCase.insert(toolRequest.toTool());
        return ResponseEntity.ok(ToolResponse.fromTool(tool));
    }
}

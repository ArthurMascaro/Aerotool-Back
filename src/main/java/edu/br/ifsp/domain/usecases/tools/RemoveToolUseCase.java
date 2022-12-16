package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.util.UUID;

public class RemoveToolUseCase {

    private ToolDAO toolDAO;

    public RemoveToolUseCase(ToolDAO toolDAO) {
        this.toolDAO = toolDAO;
    }

    public boolean remove(UUID id) {
        if (id == null || toolDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Tool not found!");
        }
        return toolDAO.deleteByKey(id);
    }

    public boolean remove(Tool tool) {
        if (tool == null || toolDAO.findOne(tool.getId()).isEmpty()) {
            throw new EntityNotFoundException("Tool not found!");
        }
        return toolDAO.delete(tool);
    }
}

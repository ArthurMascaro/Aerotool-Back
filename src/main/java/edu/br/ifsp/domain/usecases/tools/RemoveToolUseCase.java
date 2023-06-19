package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class RemoveToolUseCase {

    private ToolDAO toolDAO;

    public RemoveToolUseCase(ToolDAO toolDAO) {
        this.toolDAO = toolDAO;
    }

    public Tool remove(UUID id) {
        if (id == null || toolDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Tool not found!");
        }
        return toolDAO.deleteByKey(id);
    }

    public Tool remove(Tool tool) {
        if (tool == null || toolDAO.findOne(tool.getId()).isEmpty()) {
            throw new EntityNotFoundException("Tool not found!");
        }
        return toolDAO.delete(tool);
    }
}

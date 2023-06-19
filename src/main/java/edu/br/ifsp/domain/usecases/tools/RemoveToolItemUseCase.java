package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;
public class RemoveToolItemUseCase {

    private ToolItemDAO toolItemDAO;

    public RemoveToolItemUseCase(ToolItemDAO toolItemDAO) {
        this.toolItemDAO = toolItemDAO;
    }

    public ToolItem remove(UUID id) {
        if (id == null) {
            throw new EntityNotFoundException("Tool Item not found!");
        }
        return toolItemDAO.deleteByKey(id);
    }

    public ToolItem remove(ToolItem toolItem) {
        if (toolItem == null) {
            throw new EntityNotFoundException("Tool Item not found!");
        }
        return toolItemDAO.delete(toolItem);
    }
}

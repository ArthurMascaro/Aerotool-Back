package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.util.UUID;

public class RemoveToolItemUseCase {

    private ToolItemDAO toolItemDAO;

    public RemoveToolItemUseCase(ToolItemDAO toolItemDAO) {
        this.toolItemDAO = toolItemDAO;
    }

    public boolean remove(UUID id) {
        if (id == null || toolItemDAO.findOne(id.toString()).isEmpty()) {
            throw new EntityNotFoundException("Tool Item not found!");
        }
        return toolItemDAO.deleteByKey(id.toString());
    }

    public boolean remove(ToolItem toolItem) {
        if (toolItem == null || toolItemDAO.findOne(toolItem.getId().toString()).isEmpty()) {
            throw new EntityNotFoundException("Tool Item not found!");
        }
        return toolItemDAO.delete(toolItem);
    }
}

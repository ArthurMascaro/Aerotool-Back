package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.UUID;

public class UpdateToolItemUseCase {

    private ToolItemDAO toolItemDAO;

    public UpdateToolItemUseCase(ToolItemDAO toolItemDAO) {
        this.toolItemDAO = toolItemDAO;
    }

    public boolean update(ToolItem toolItem) {
        Validator<ToolItem> validator = new ToolItemInputRequestValidator();
        Notification notification = validator.validate(toolItem);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        // turning UUID from database to String to findOne method
        String id = toolItem.getId().toString();
        if (toolItemDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Tool Item not found!");
        }
        return toolItemDAO.update(toolItem);
    }

}
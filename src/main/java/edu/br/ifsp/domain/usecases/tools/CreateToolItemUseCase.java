package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.UUID;

public class CreateToolItemUseCase {

    private ToolItemDAO toolItemDAO;

    public CreateToolItemUseCase(ToolItemDAO toolItemDAO) {
        this.toolItemDAO = toolItemDAO;
    }

    public ToolItem insert(ToolItem toolItem) {
        Validator<ToolItem> validator = new ToolItemInputRequestValidator();
        Notification notification = validator.validate(toolItem);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String toolItemPatrimony = toolItem.getPatrimony();
        if (toolItemDAO.findByPatrimony(toolItemPatrimony).isPresent()) {
            throw new EntityAlreadyExistsException("This patrimony is already in use!");
        }
        return toolItemDAO.create(toolItem);
    }

}

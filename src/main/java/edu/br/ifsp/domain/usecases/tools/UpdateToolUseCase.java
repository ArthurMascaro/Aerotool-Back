package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

public class UpdateToolUseCase {

    private ToolDAO toolDAO;

    public UpdateToolUseCase(ToolDAO toolDAO) {
        this.toolDAO = toolDAO;
    }

    public boolean update(Tool tool) {
        Validator<Tool> validator = new ToolInputRequestValidator();
        Notification notification = validator.validate(tool);

        if (notification.hasErros()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        if (toolDAO.findOne(tool.getId()).isEmpty()) {
            throw new EntityNotFoundException("Tool not found!");
        }
        return toolDAO.update(tool);
    }
}

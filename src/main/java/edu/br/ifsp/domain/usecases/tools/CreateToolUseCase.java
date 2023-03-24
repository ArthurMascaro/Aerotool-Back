package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.UUID;

public class CreateToolUseCase {

    private ToolDAO toolDAO;

    public CreateToolUseCase(ToolDAO toolDAO) {
        this.toolDAO = toolDAO;
    }

    public Tool insert(Tool tool){
        Validator<Tool> validator = new ToolInputRequestValidator();
        Notification notification = validator.validate(tool);

        if (toolDAO.findByUUID(tool.getId()).isPresent())
            throw new EntityAlreadyExistsException("This Tool is already in use.");

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return toolDAO.create(tool);
    }
}

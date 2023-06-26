package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Locate;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.Notification;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
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

        return toolItemDAO.create(toolItem);
    }

    public Locate insertLocate(Locate locate){
        return toolItemDAO.createLocate(locate.instanceWithId(UUID.randomUUID()));
    }

}

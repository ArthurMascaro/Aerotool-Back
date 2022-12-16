package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindToolUseCase {

    private ToolDAO toolDAO;

    public FindToolUseCase(ToolDAO toolDAO) {
        this.toolDAO = toolDAO;
    }

    public Optional<Tool> findOne(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return toolDAO.findOne(id);
    }

    public Optional<Tool> findByUUID(UUID id) {
        if (Validator.nullOrEmpty(id.toString())) {
            throw new IllegalArgumentException("Id cannot be null or empty!");
        }
        return toolDAO.findByUUID(id);
    }

    public List<Tool> findAll() {
        return toolDAO.findALL();
    }
}

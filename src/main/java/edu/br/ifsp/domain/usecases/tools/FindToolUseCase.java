package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class FindToolUseCase {

    private ToolDAO toolDAO;
    public FindToolUseCase(ToolDAO toolDAO) {
        this.toolDAO = toolDAO;
    }
    public Tool findOne(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return toolDAO.findOne(id).orElseThrow(
                () -> new IllegalArgumentException("Tool not found!")
        );
    }
    public Tool findByUUID(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null or empty!");
        return toolDAO.findByUUID(id).orElseThrow(
                () -> new IllegalArgumentException("Tool not found!")
        );
    }
    public List<Tool> findAll() {
        return toolDAO.findALL();
    }
}

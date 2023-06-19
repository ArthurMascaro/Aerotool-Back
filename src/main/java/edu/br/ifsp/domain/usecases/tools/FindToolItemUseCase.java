package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public class FindToolItemUseCase {

    private ToolItemDAO toolItemDAO;

    public FindToolItemUseCase(ToolItemDAO toolItemDAO) {
        this.toolItemDAO = toolItemDAO;
    }

    public Optional<ToolItem> findOne(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return toolItemDAO.findOne(id);
    }

    public List<ToolItem> findAll() {
        return toolItemDAO.findALL();
    }

}

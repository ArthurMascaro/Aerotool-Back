package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.Validator;

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
        return toolItemDAO.findOne(id.toString());
    }

    public Optional<ToolItem> findOneByPatrimony(String patrimony) {
        if (Validator.nullOrEmpty(patrimony)) {
            throw new IllegalArgumentException("Patrimony cannot be null or empty!");
        }
        return toolItemDAO.findByPatrimony(patrimony);
    }

    public List<ToolItem> findAll() {
        return toolItemDAO.findALL();
    }

}

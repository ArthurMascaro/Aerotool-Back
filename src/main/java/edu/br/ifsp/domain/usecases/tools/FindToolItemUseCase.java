package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Locate;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindToolItemUseCase {

    private ToolItemDAO toolItemDAO;

    public FindToolItemUseCase(ToolItemDAO toolItemDAO) {
        this.toolItemDAO = toolItemDAO;
    }

    public ToolItem findOne(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return toolItemDAO.findOne(id).orElseThrow(
                () -> new IllegalArgumentException("ToolItem not found!")
        );
    }

    public List<ToolItem> findAll() {
        return toolItemDAO.findALL();
    }

    public List<Locate> findAllLocates() {
        return toolItemDAO.findAllLocates();
    }

}

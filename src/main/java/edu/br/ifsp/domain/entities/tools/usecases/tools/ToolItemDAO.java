package edu.br.ifsp.domain.entities.tools.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.tools.usecases.utils.DAO;

import java.util.Optional;

public interface ToolItemDAO extends DAO<ToolItem, String> {

    Optional<ToolItem> findByPatrimony(String patrimony);

}

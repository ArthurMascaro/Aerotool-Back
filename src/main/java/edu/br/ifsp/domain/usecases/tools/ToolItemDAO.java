package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface ToolItemDAO extends DAO<ToolItem, UUID> {

    // TODO Implements Patrimony UNIQUE at Database
    // Optional<ToolItem> findByPatrimony(String patrimony);

}

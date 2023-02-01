package edu.br.ifsp.domain.entities.tools.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface ToolDAO extends DAO<Tool, UUID> {

    Optional<Tool> findByUUID(UUID id);

}

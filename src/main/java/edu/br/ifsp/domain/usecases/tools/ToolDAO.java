package edu.br.ifsp.domain.usecases.tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface ToolDAO extends DAO<Tool, UUID> {

    Optional<Tool> findByUUID(UUID id);

}

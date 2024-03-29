package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface UserDAO extends DAO<User, String> {

    Optional<User> findByPromptuary(String promptuary);

    Optional<User> findByUUID(UUID id);

    Optional<User> findByName(String name);
}

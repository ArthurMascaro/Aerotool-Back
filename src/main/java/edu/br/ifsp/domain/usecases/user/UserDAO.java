package edu.br.ifsp.domain.usecases.user;

import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;
import java.util.UUID;

public interface UserDAO extends DAO<User, Promptuary> {

    Optional<User> findByPromptuary(Promptuary promptuary);

}

package edu.br.ifsp.domain.entities.tools.usecases.user;

import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.entities.tools.usecases.utils.DAO;

import java.util.Optional;

public interface UserDAO extends DAO<User, String> {

    Optional<User> findByPromptuary(Promptuary promptuary);

}

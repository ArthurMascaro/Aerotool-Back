package edu.br.ifsp.domain.usecases.transactions;


import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindRequestUseCase {

    private final RequestDAO requestDAO;
    public FindRequestUseCase(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }

    public Optional<Request> findOne(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return requestDAO.findOne(id);
    }

    public Optional<Request> findByUUID(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null or empty!");
        }
        return requestDAO.findByUUID(id);
    }

    public List<Request> findAll() {
        return requestDAO.findALL();
    }
}

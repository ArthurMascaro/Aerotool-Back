package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindLineRequestUseCase {

    private LineRequestDAO lineRequestDAO;
    public FindLineRequestUseCase(LineRequestDAO lineRequestDAO) {
        this.lineRequestDAO = lineRequestDAO;
    }

    public Optional<LineRequest> findOne(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return lineRequestDAO.findOne(id);
    }

    public Optional<LineRequest> findByUUID(UUID id) {
        if (Validator.nullOrEmpty(id.toString())) {
            throw new IllegalArgumentException("Id cannot be null or empty!");
        }
        return lineRequestDAO.findByUUID(id);
    }

    public List<LineRequest> findAll() {
        return lineRequestDAO.findALL();
    }
}

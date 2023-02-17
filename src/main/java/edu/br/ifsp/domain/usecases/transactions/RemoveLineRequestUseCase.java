package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;

import javax.sound.sampled.Line;
import java.util.UUID;

public class RemoveLineRequestUseCase {

    private LineRequestDAO lineRequestDAO;

    public RemoveLineRequestUseCase(LineRequestDAO lineRequestDAO) {
        this.lineRequestDAO = lineRequestDAO;
    }

    public LineRequest remove(UUID id) {
        if (id == null || lineRequestDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Request not found!");
        }
        return lineRequestDAO.deleteByKey(id);
    }

    public LineRequest remove(LineRequest lineRequest) {
        if (lineRequest == null || lineRequestDAO.findOne(lineRequest.getId()).isEmpty()) {
            throw new EntityNotFoundException("Tool Item not found!");
        }
        return lineRequestDAO.delete(lineRequest);
    }
}

package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.tools.ToolItemDAO;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveRequestUseCase {

    private RequestDAO requestDAO;

    public RemoveRequestUseCase(RequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }

    public Request remove(UUID id) {
        if (id == null || requestDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Request not found!");
        }
        return requestDAO.deleteByKey(id);
    }

    public Request remove(Request request) {
        if (request == null || requestDAO.findOne(request.getId()).isEmpty()) {
            throw new EntityNotFoundException("Request not found!");
        }
        return requestDAO.delete(request);
    }
}

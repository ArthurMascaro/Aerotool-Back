package edu.br.ifsp.domain.usecases.transactions;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.tools.ToolSituation;
import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.usecases.tools.FindToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.UpdateToolItemUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;

import javax.sound.sampled.Line;
import java.util.UUID;

public class CreateLineRequestUseCase {

    private LineRequestDAO lineRequestDAO;
    private FindToolItemUseCase findToolItemUseCaseUseCase;
    private UpdateToolItemUseCase updateToolItemUseCase;


    public CreateLineRequestUseCase(
            LineRequestDAO lineRequestDAO,
            FindToolItemUseCase findToolItemUseCaseUseCase,
            UpdateToolItemUseCase updateToolItemUseCase){


        this.lineRequestDAO = lineRequestDAO;
        this.findToolItemUseCaseUseCase = findToolItemUseCaseUseCase;
        this.updateToolItemUseCase = updateToolItemUseCase;
    }

    public UUID createALineRequest(UUID requestID, UUID toolItemID) throws LineRequestNotAllowedException {
        if (toolItemID == null){
            throw new IllegalArgumentException("Tool Item ID is null.");
        }

        if (requestID == null){
            throw new IllegalArgumentException("Request ID is null.");
        }

        ToolItem toolItem = findToolItemUseCaseUseCase.findOne(toolItemID).orElseThrow(() ->
                new EntityNotFoundException("Can not find a Tool Item with ID" + toolItemID));

        if(toolItem.getSituation() == ToolSituation.BUSY) {
            throw new LineRequestNotAllowedException("The Tool Item with ID" + toolItemID + "is unavailable.");
        }

            LineRequest lineRequest = new LineRequest();
            return lineRequestDAO.create(lineRequest);

    }
}

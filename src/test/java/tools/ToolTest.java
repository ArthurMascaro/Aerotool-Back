package tools;

import edu.br.ifsp.applications.persistence.inmemory.InMemoryToolDAO;
import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolType;
import edu.br.ifsp.domain.usecases.tools.CreateToolUseCase;
import edu.br.ifsp.domain.usecases.tools.FindToolUseCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class ToolTest {

    public static InMemoryToolDAO inMemoryToolDAO = new InMemoryToolDAO();

    public CreateToolUseCase createToolUseCase = new CreateToolUseCase(inMemoryToolDAO);

    public FindToolUseCase findToolUseCase = new FindToolUseCase(inMemoryToolDAO);

    @Test
    public void insert_ToolWithoutName_IllegalArgumentException(){
        Tool tool = new Tool(UUID.randomUUID());
        Assertions.assertThrows(IllegalArgumentException.class, () -> createToolUseCase.insert(tool));
    }

    @Test
    public void insert_ToolWithCorrectArguments_ToolClass(){
        Tool tool = new Tool(UUID.randomUUID(), "Teste", "Opa", ToolType.COMMON);
        Assertions.assertEquals(Tool.class, createToolUseCase.insert(tool).getClass());
    }

}

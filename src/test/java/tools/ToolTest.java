package tools;

import edu.br.ifsp.applications.persistence.inmemory.InMemoryToolDAO;
import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolType;
import edu.br.ifsp.domain.usecases.tools.CreateToolUseCase;
import edu.br.ifsp.domain.usecases.tools.FindToolUseCase;
import edu.br.ifsp.domain.usecases.tools.RemoveToolUseCase;
import edu.br.ifsp.domain.usecases.tools.UpdateToolUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.UUID;

public class ToolTest {

    public static InMemoryToolDAO inMemoryToolDAO = new InMemoryToolDAO();

    public CreateToolUseCase createToolUseCase = new CreateToolUseCase(inMemoryToolDAO);
    public FindToolUseCase findToolUseCase = new FindToolUseCase(inMemoryToolDAO);
    public RemoveToolUseCase removeToolUseCase = new RemoveToolUseCase(inMemoryToolDAO);
    public UpdateToolUseCase updateToolUseCase = new UpdateToolUseCase(inMemoryToolDAO);

    @Test
    public void insert_ToolWithoutName_IllegalArgumentException(){
        Tool tool = new Tool(UUID.randomUUID());
        Assertions.assertThrows(IllegalArgumentException.class, () -> createToolUseCase.insert(tool));
    }

    @Test
    public void insert_ToolWithCorrectArguments_ToolClass(){
        Tool tool = new Tool(UUID.randomUUID(), "Test", "Description Test", ToolType.COMMON);
        Assertions.assertEquals(Tool.class, createToolUseCase.insert(tool).getClass());
    }

    @Test
    public void insert_InsertToolAlreadyExists_EntityAlreadyExistsException() {
        Tool tool = new Tool(UUID.randomUUID(), "Test 2", "Description Test", ToolType.COMMON);
        createToolUseCase.insert(tool);
        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> createToolUseCase.insert(tool));
    }

    @Test
    public void findByUUID_ToolWithoutUUID_IllegalArgumentException() {
        Tool tool = new Tool(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> findToolUseCase.findByUUID(tool.getId()));
    }

    @Test
    public void findByUUID_ToolWithCorrectArgs_ToolClass() {
        Tool tool = new Tool(UUID.randomUUID(), "Ruler", "In use", ToolType.COMMON);
        createToolUseCase.insert(tool);
        Assertions.assertEquals(Tool.class, findToolUseCase.findByUUID(tool.getId()).get().getClass());
    }

    @Test
    public void findAllTools_Normal_ArrayOfTools() {
        Assertions.assertEquals(ArrayList.class, findToolUseCase.findAll().getClass());
    }

    @Test
    public void remove_ToolWithoutUUID_EntityNotFoundException() {
        Tool tool = new Tool(null);
        Assertions.assertThrows(EntityNotFoundException.class, () -> removeToolUseCase.remove(tool.getId()));
    }

    @Test
    public void remove_RemoveToolWithCorrectArguments_ToolClass() {
        Tool tool = new Tool(UUID.randomUUID(), "Hammer", "In use", ToolType.PROPERTY);
        createToolUseCase.insert(tool);
        Assertions.assertEquals(Tool.class, removeToolUseCase.remove(tool.getId()).getClass());
    }

    @Test
    public void update_OnlyWithUUID_IllegalArgumentException() {
        Tool tool = new Tool(UUID.randomUUID());
        Assertions.assertThrows(IllegalArgumentException.class, () -> updateToolUseCase.update(tool));
    }

    @Test
    public void update_UpdateWithCorrectArgs_ToolClass() {
        Tool tool = new Tool(UUID.randomUUID(), "Saw", "In use", ToolType.PROPERTY);
        createToolUseCase.insert(tool);
        tool.setName("Chainsaw");
        tool.setDescription("Free to use");
        Assertions.assertEquals(Tool.class, updateToolUseCase.update(tool).getClass());
    }

}

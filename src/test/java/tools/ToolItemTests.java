package tools;

import edu.br.ifsp.applications.persistence.inmemory.InMemoryToolItemDAO;
import edu.br.ifsp.domain.entities.tools.Locate;
import edu.br.ifsp.domain.entities.tools.Tool;
import edu.br.ifsp.domain.entities.tools.ToolItem;
import edu.br.ifsp.domain.entities.tools.ToolSituation;
import edu.br.ifsp.domain.usecases.tools.CreateToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.FindToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.RemoveToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.UpdateToolItemUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

public class ToolItemTests {

    private InMemoryToolItemDAO inMemoryToolItemDAO = new InMemoryToolItemDAO();

    private CreateToolItemUseCase createToolItemUseCase = new CreateToolItemUseCase(inMemoryToolItemDAO);
    private FindToolItemUseCase findToolItemUseCase = new FindToolItemUseCase(inMemoryToolItemDAO);
    private RemoveToolItemUseCase removeToolItemUseCase = new RemoveToolItemUseCase(inMemoryToolItemDAO);
    private UpdateToolItemUseCase updateToolItemUseCase = new UpdateToolItemUseCase(inMemoryToolItemDAO);

    @Test
    public void ToolItemConstructor_WithoutArguments_ObjectUserUUID() {
        assertEquals(UUID.class, new ToolItem().getId().getClass());
    }

    @Test
    public void ToolItemConstructor_WithInvalidArgument_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ToolItem(null));
    }

    @Test
    public void insert_CreateToolItemWithNullUUID_NullPointerException() {
        ToolItem toolItem = new ToolItem();
        assertThrows(IllegalArgumentException.class, () -> createToolItemUseCase.insert(toolItem));
    }

    @Test
    public void insert_CreateToolWithoutCorrectArgs_IllegalArgumentException() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID());
        assertThrows(IllegalArgumentException.class, () -> createToolItemUseCase.insert(toolItem));
    }

    @Test
    public void insert_CreateToolWithCorrectArgs_ToolItemClass() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1", new Locate(UUID.randomUUID(), "Hangar", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), ToolSituation.BUSY);
        assertEquals(ToolItem.class, createToolItemUseCase.insert(toolItem).getClass());
    }

    @Test
    public void findOne_FindToolItemWithCorrectArgs_ToolItemClass() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL123", new Locate(UUID.randomUUID(), "Hangar", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        assertEquals(ToolItem.class, findToolItemUseCase.findOne(toolItem.getId()).getClass());
    }

    @Test
    public void findAll_FindAllToolItem_ArrayList() {
        assertEquals(ArrayList.class, findToolItemUseCase.findAll().getClass());
    }

    @Test
    public void remove_RemoveToolItemByUUID_ToolItemClass() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL12345", new Locate(UUID.randomUUID(), "Hangar", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        assertEquals(ToolItem.class, removeToolItemUseCase.remove(toolItem.getId()).getClass());
    }

    @Test
    public void remove_RemoveToolItemByToolItem_ToolItemClass() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL12345678", new Locate(UUID.randomUUID(), "Hangar", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        assertEquals(ToolItem.class, removeToolItemUseCase.remove(toolItem).getClass());
    }

    @Test
    public void udpate_UpdateWithInvalidArguments_ToolItemClass(){
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1223345", new Locate(UUID.randomUUID(), "Hangar", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        assertThrows(IllegalArgumentException.class, () -> updateToolItemUseCase.update(new ToolItem(UUID.randomUUID(), "SCL1223345", new Locate(UUID.randomUUID(), "Hangar", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), null)));
    }

    @Test
    public void udpate_EntityNotExists_ToolItemClass(){
        assertThrows(EntityNotFoundException.class, () -> updateToolItemUseCase.update(new ToolItem(UUID.randomUUID(), "SCL1223345", new Locate(UUID.randomUUID(), "Hangar", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), ToolSituation.BUSY)));
    }

    @Test
    public void udpate_WithCorrectArguments_ToolItemClass(){
        UUID id = UUID.randomUUID();
        ToolItem toolItem = new ToolItem(id, "SCL1233456", new Locate(UUID.randomUUID(), "Armário 123", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        assertEquals(ToolItem.class, updateToolItemUseCase.update(new ToolItem(id, "SC123456", new Locate(UUID.randomUUID(), "Hangar", "Busy").getId(), new Tool(UUID.randomUUID()).getId(), ToolSituation.FREE)).getClass());
    }
}

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
import org.junit.jupiter.api.Assertions;

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
        Assertions.assertEquals(UUID.class, new ToolItem().getId().getClass());
    }

    @Test
    public void ToolItemConstructor_WithInvalidArgument_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ToolItem(null));
    }

    @Test
    public void insert_CreateToolItemWithNullUUID_NullPointerException() {
        ToolItem toolItem = new ToolItem();
        Assertions.assertThrows(IllegalArgumentException.class, () -> createToolItemUseCase.insert(toolItem));
    }

    @Test
    public void insert_CreateToolWithoutCorrectArgs_IllegalArgumentException() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID());
        Assertions.assertThrows(IllegalArgumentException.class, () -> createToolItemUseCase.insert(toolItem));
    }

    @Test
    public void insert_CreateToolWithCorrectArgs_ToolItemClass() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1", new Locate(UUID.randomUUID(), "Hangar", "Busy"), new Tool(UUID.randomUUID()), ToolSituation.BUSY);
        Assertions.assertEquals(ToolItem.class, createToolItemUseCase.insert(toolItem).getClass());
    }

    @Test
    public void findOne_FindToolItemWithCorrectArgs_ToolItemClass() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL123", new Locate(UUID.randomUUID(), "Hangar", "Busy"), new Tool(UUID.randomUUID()), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        Assertions.assertEquals(ToolItem.class, findToolItemUseCase.findOne(toolItem.getId()).get().getClass());
    }

    @Test
    public void findAll_FindAllToolItem_ArrayList() {
        Assertions.assertEquals(ArrayList.class, findToolItemUseCase.findAll().getClass());
    }

    @Test
    public void remove_RemoveToolItemByUUID_ToolItemClass() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL12345", new Locate(UUID.randomUUID(), "Hangar", "Busy"), new Tool(UUID.randomUUID()), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        Assertions.assertEquals(ToolItem.class, removeToolItemUseCase.remove(toolItem.getId()).getClass());
    }

    @Test
    public void remove_RemoveToolItemByToolItem_ToolItemClass() {
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL12345678", new Locate(UUID.randomUUID(), "Hangar", "Busy"), new Tool(UUID.randomUUID()), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        Assertions.assertEquals(ToolItem.class, removeToolItemUseCase.remove(toolItem).getClass());
    }

    @Test
    public void udpate_UpdateWithInvalidArguments_ToolItemClass(){
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1223345", new Locate(UUID.randomUUID(), "Hangar", "Busy"), new Tool(UUID.randomUUID()), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        Assertions.assertThrows(new IllegalArgumentException().getClass(), () -> updateToolItemUseCase.update(new ToolItem(UUID.randomUUID(), "SCL1223345", new Locate(UUID.randomUUID(), "Hangar", "Busy"), new Tool(UUID.randomUUID()), null)));
    }

    @Test
    public void udpate_EntityNotExists_ToolItemClass(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> updateToolItemUseCase.update(new ToolItem(UUID.randomUUID(), "SCL1223345", new Locate(UUID.randomUUID(), "Hangar", "Busy"), new Tool(UUID.randomUUID()), ToolSituation.BUSY)));
    }

    @Test
    public void udpate_WithCorrectArguments_ToolItemClass(){
        UUID id = UUID.randomUUID();
        ToolItem toolItem = new ToolItem(id, "SCL1233456", new Locate(UUID.randomUUID(), "Armário 123", "Busy"), new Tool(UUID.randomUUID()), ToolSituation.BUSY);
        createToolItemUseCase.insert(toolItem);
        Assertions.assertEquals(ToolItem.class, updateToolItemUseCase.update(new ToolItem(id, "SC123456", new Locate(UUID.randomUUID(), "Hangar", "Busy"), new Tool(UUID.randomUUID()), ToolSituation.FREE)).getClass());
    }
}

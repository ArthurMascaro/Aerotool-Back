package tools;

import edu.br.ifsp.domain.entities.tools.ToolItem;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class ToolItemTests {

    @Test
    public void ToolItemConstructor_WithoutArguments_ObjectUserUUID() {
        Assertions.assertEquals(UUID.class, new ToolItem().getId().getClass());
    }

    @Test
    public void ToolItemConstructor_WithInvalidArgument_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ToolItem(null));
    }
}

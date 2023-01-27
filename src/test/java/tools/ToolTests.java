package tools;

import edu.br.ifsp.domain.entities.tools.Tool;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class ToolTests {
    @Test
    public void ToolConstructor_InvalidUUIDArguments_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Tool(null));
    }

    @Test
    public void ToolConstructor_WithoutArguments_ObjectToolsUUID() {
        Assertions.assertEquals(UUID.class, new Tool().getId().getClass());
    }
}

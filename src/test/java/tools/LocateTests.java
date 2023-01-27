package tools;

import edu.br.ifsp.domain.entities.tools.Locate;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class LocateTests {
    @Test
    public void LocateBuilder_InvalidUUIDArguments_IllegalException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Locate(null));
    }

    @Test
    public void LocateBuilder_WithoutArguments_ObjectLocateUUID() {
        Assertions.assertEquals(UUID.class, new Locate().getId().getClass());
    }
}

package user;

import edu.br.ifsp.domain.entities.user.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class UserClassTests {

    @Test
    public void UserConstructor_WithoutArguments_ObjectUserWithUUID() {
        Assertions.assertEquals(UUID.class, new User().getId().getClass());
    }

    @Test
    public void UserConstructor_WithUUIDArgument_ObjectUserWithUUID() {
        Assertions.assertEquals(UUID.class, new User(UUID.randomUUID()).getId().getClass());
    }

    @Test
    public void UserConstructor_WithInvalidArgument_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(null));
    }

}

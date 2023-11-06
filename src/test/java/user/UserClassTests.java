package user;

import edu.br.ifsp.applications.persistence.inmemory.InMemoryUserDAO;
import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.user.CreateUserUseCase;
import edu.br.ifsp.domain.usecases.user.FindUserUseCase;
import edu.br.ifsp.domain.usecases.user.RemoveUserUseCase;
import edu.br.ifsp.domain.usecases.user.UpdateUserUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

public class UserClassTests {

    public static InMemoryUserDAO inMemoryUserDAO = new InMemoryUserDAO();
    public CreateUserUseCase createUserUseCase = new CreateUserUseCase(inMemoryUserDAO);
    public FindUserUseCase findUserUseCase = new FindUserUseCase(inMemoryUserDAO);
    public RemoveUserUseCase removeUserUseCase = new RemoveUserUseCase(inMemoryUserDAO);
    public UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(inMemoryUserDAO);
    @Test
    public void UserConstructor_WithoutArguments_ObjectUserWithUUID() {
        assertEquals(UUID.class, new User().getId().getClass());
    }

    @Test
    public void UserConstructor_WithUUIDArgument_ObjectUserWithUUID() {
        assertEquals(UUID.class, new User(UUID.randomUUID()).getId().getClass());
    }

    @Test
    public void UserConstructor_WithInvalidArgument_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new User(null));
    }

    @Test
    public void insertUser_AlreadyExists_IllegalStateException(){
        User user = new User(UUID.randomUUID(), "Teste", Role.TEACHER, Promptuary.valueOf("SC123456"));
        createUserUseCase.insert(user);
        assertThrows(EntityAlreadyExistsException.class, () -> createUserUseCase.insert(user));
    }

    @Test
    public void insertUser_OnlyWithUUID_IllegalStateException(){
        User user = new User(UUID.randomUUID());
        assertThrows(new IllegalArgumentException().getClass(), () -> createUserUseCase.insert(user));
    }

    @Test
    public void insertUser_WithCorrectUser_UserClass(){
        User user = new User(UUID.randomUUID(), "Miguel", Role.ADMIN, Promptuary.valueOf("SC1231231"));
        assertEquals(User.class, createUserUseCase.insert(user).getClass());
    }

    @Test
    public void findOneUser_withUserEqualsNull_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> findUserUseCase.findOne(null));
    }

    @Test
    public void findOneUser_withCorrectUser_OptionalUser(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC999999"));
        createUserUseCase.insert(test);
        assertEquals(User.class, findUserUseCase.findOne(test).get().getClass());
    }

    @Test
    public void findByPromptuaryUser_withPromptuaryEqualsNull_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> findUserUseCase.findByPromptuary(null));
    }

    @Test
    public void findByPromptuaryUser_withCorrectPromptuary_OptionalUser(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, "SC999998");
        createUserUseCase.insert(test);
        assertEquals(User.class, findUserUseCase.findByPromptuary("SC999998").get().getClass());
    }

    @Test
    public void FindAllUsers_Normal_ListOfUsers(){
        assertEquals(ArrayList.class, findUserUseCase.findAll().getClass());
    }

    @Test
    public void deleteUserByKey_UserNotExists_EntityNotFoundException(){
        assertThrows(EntityNotFoundException.class, () -> removeUserUseCase.remove(Promptuary.valueOf("SC123111")).getClass());
    }

    @Test
    public void deleteUserByKey_UserExists_UserClass(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987654"));
        createUserUseCase.insert(test);
        assertEquals(User.class, removeUserUseCase.remove(Promptuary.valueOf("SC987654")).getClass());
    }

    @Test
    public void deleteUserByUser_UserNotExists_EntityNotFoundException(){
        assertThrows(EntityNotFoundException.class, () -> removeUserUseCase.remove(new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC981154"))).getClass());
    }

    @Test
    public void deleteUserByUser_UserExists_UserClass(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987687"));
        createUserUseCase.insert(test);
        assertEquals(User.class, removeUserUseCase.remove(test).getClass());
    }

    @Test
    public void updateUser_OnlyWithUUID_IllegalStateException(){
        User user = new User(UUID.randomUUID());
        assertThrows(new IllegalArgumentException().getClass(), () -> updateUserUseCase.update(user));
    }

    @Test
    public void updateUser_UserNotExists_EntityNotFoundException(){
        assertThrows(EntityNotFoundException.class, () -> updateUserUseCase.update(new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987623"))).getClass());
    }

    @Test
    public void updateUser_UserExists_NewUser(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987611"));
        createUserUseCase.insert(test);
        test.setName("TESTE 1");
        test.setRole(Role.ADMIN);
        assertEquals(User.class, updateUserUseCase.update(test).getClass());
    }
}

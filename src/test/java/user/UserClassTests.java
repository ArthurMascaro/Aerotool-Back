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
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.notification.RunListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class UserClassTests {

    public static InMemoryUserDAO inMemoryUserDAO = new InMemoryUserDAO();
    public CreateUserUseCase createUserUseCase = new CreateUserUseCase(inMemoryUserDAO);
    public FindUserUseCase findUserUseCase = new FindUserUseCase(inMemoryUserDAO);
    public RemoveUserUseCase removeUserUseCase = new RemoveUserUseCase(inMemoryUserDAO);
    public UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(inMemoryUserDAO);
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

    @Test
    public void insertUser_AlreadyExists_IllegalStateException(){
        User user = new User(UUID.randomUUID(), "Teste", Role.TEACHER, Promptuary.valueOf("SC123456"));
        createUserUseCase.insert(user);
        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> createUserUseCase.insert(user));
    }

    @Test
    public void insertUser_OnlyWithUUID_IllegalStateException(){
        User user = new User(UUID.randomUUID());
        Assertions.assertThrows(new IllegalArgumentException().getClass(), () -> createUserUseCase.insert(user));
    }

    @Test
    public void insertUser_WithCorrectUser_UserClass(){
        User user = new User(UUID.randomUUID(), "Miguel", Role.ADMIN, Promptuary.valueOf("SC1231231"));
        Assertions.assertEquals(User.class, createUserUseCase.insert(user).getClass());
    }

    @Test
    public void findOneUser_withUserEqualsNull_IllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> findUserUseCase.findOne(null));
    }

    @Test
    public void findOneUser_withCorrectUser_OptionalUser(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC999999"));
        createUserUseCase.insert(test);
        Assertions.assertEquals(User.class, findUserUseCase.findOne(test).get().getClass());
    }

    @Test
    public void findByPromptuaryUser_withPromptuaryEqualsNull_IllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> findUserUseCase.findByPromptuary(null));
    }

    @Test
    public void findByPromptuaryUser_withCorrectPromptuary_OptionalUser(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC999998"));
        createUserUseCase.insert(test);
        Assertions.assertEquals(User.class, findUserUseCase.findByPromptuary(Promptuary.valueOf("SC999998")).get().getClass());
    }

    @Test
    public void FindAllUsers_Normal_ListOfUsers(){
        Assertions.assertEquals(ArrayList.class, findUserUseCase.findAll().getClass());
    }

    @Test
    public void deleteUserByKey_UserNotExists_EntityNotFoundException(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> removeUserUseCase.remove(Promptuary.valueOf("SC123111")).getClass());
    }

    @Test
    public void deleteUserByKey_UserExists_UserClass(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987654"));
        createUserUseCase.insert(test);
        Assertions.assertEquals(User.class, removeUserUseCase.remove(Promptuary.valueOf("SC987654")).getClass());
    }

    @Test
    public void deleteUserByUser_UserNotExists_EntityNotFoundException(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> removeUserUseCase.remove(new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC981154"))).getClass());
    }

    @Test
    public void deleteUserByUser_UserExists_UserClass(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987687"));
        createUserUseCase.insert(test);
        Assertions.assertEquals(User.class, removeUserUseCase.remove(test).getClass());
    }

    @Test
    public void updateUser_OnlyWithUUID_IllegalStateException(){
        User user = new User(UUID.randomUUID());
        Assertions.assertThrows(new IllegalArgumentException().getClass(), () -> updateUserUseCase.update(user));
    }

    @Test
    public void updateUser_UserNotExists_EntityNotFoundException(){
        Assertions.assertThrows(EntityNotFoundException.class, () -> updateUserUseCase.update(new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987623"))).getClass());
    }

    @Test
    public void updateUser_UserExists_NewUser(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987611"));
        createUserUseCase.insert(test);
        test.setNome("TESTE 1");
        test.setRole(Role.ADMIN);
        Assertions.assertEquals(User.class, updateUserUseCase.update(test).getClass());
    }
}

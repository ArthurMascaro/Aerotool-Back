package transactions;

import edu.br.ifsp.applications.persistence.inmemory.InMemoryRequestDAO;
import edu.br.ifsp.applications.persistence.inmemory.InMemoryUserDAO;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.transactions.CreateRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.FindRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.RemoveRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.UpdateRequestUseCase;
import edu.br.ifsp.domain.usecases.user.CreateUserUseCase;
import edu.br.ifsp.domain.usecases.user.FindUserUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

public class RequestClassTest {

    public static InMemoryRequestDAO inMemoryRequestDAO = new InMemoryRequestDAO();
    public static InMemoryUserDAO inMemoryUserDAO = new InMemoryUserDAO();
    public static FindUserUseCase findUserUseCase = new FindUserUseCase(inMemoryUserDAO);

    public static RemoveRequestUseCase removeRequestUseCase = new RemoveRequestUseCase(inMemoryRequestDAO);
    public static FindRequestUseCase findRequestUseCase = new FindRequestUseCase(inMemoryRequestDAO);

    public static CreateUserUseCase createUserUseCase = new CreateUserUseCase(inMemoryUserDAO);
    public static CreateRequestUseCase createRequestUseCase = new CreateRequestUseCase(inMemoryRequestDAO, findUserUseCase);

    public static UpdateRequestUseCase updateRequestUseCase = new UpdateRequestUseCase(inMemoryRequestDAO);
    @Test
    public void RequestConstructor_WithoutArguments_ObjectRequestWithUUID() {
        Assertions.assertEquals(UUID.class, new Request().getId().getClass());
    }

    @Test
    public void RequestConstructor_WithArguments_ObjectRequestWithUUIDAndUser() {
        User user = new User(UUID.randomUUID(), "Renan", Role.TEACHER, Promptuary.valueOf("SC123456"));
        Assertions.assertEquals(UUID.class, new Request(UUID.randomUUID(), user).getId().getClass());
    }

    @Test
    // public void classeSendoTestada_CondiçãoDoTeste_ResultadoEsperado
    public void createARequest_WithCorrectRequest_RequestClass(){
        User user = new User(UUID.randomUUID(), "Renan", Role.TEACHER, Promptuary.valueOf("SC123456"));
        Request request = new Request(UUID.randomUUID(), Timestamp.valueOf("2023-03-13 13:02:21"), user);
        createUserUseCase.insert(user);
        Assertions.assertEquals(Request.class,  createRequestUseCase.createARequest(request).getClass());
    }

    @Test
    public void createARequest_WithUserEqualsNull_IllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> findUserUseCase.findOne(null));
    }

    @Test
    public void createARequest_withRequestIdNull_IllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> findRequestUseCase.findByUUID(null));
    }

    @Test
    public void createARequest_withDateNull_IllegalArgumentException(){
        User user = new User(UUID.randomUUID());
        Request request = new Request(UUID.randomUUID(),null, user);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> createRequestUseCase.createARequest(request));
    }

    @Test
    public void findOneRequest_withCorrectId_OptionalRequest(){
        User user = new User(UUID.randomUUID(), "Renan", Role.TEACHER, Promptuary.valueOf("SC122456"));
        createUserUseCase.insert(user);
        Request request = new Request(UUID.randomUUID(), Timestamp.valueOf("2023-03-13 13:02:21"), user);
        createRequestUseCase.createARequest(request);
        Assertions.assertEquals(Request.class, findRequestUseCase.findOne(request.getId()).get().getClass());
    }

    @Test
    public void findRequestByUUID_withUUIDEqualsNull_IllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> findRequestUseCase.findByUUID(null));
    }

    @Test
    public void FindAllRequests_Normal_ListOfRequests() {
        Assertions.assertEquals(ArrayList.class, findRequestUseCase.findAll().getClass());
    }

    @Test
    public void deleteRequestByKey_RequestExists_RequestClass(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC9876533"));
        createUserUseCase.insert(test);
        Request request = new Request(UUID.randomUUID(), Timestamp.valueOf("2023-03-13 13:02:21"), test);
        createRequestUseCase.createARequest(request);
        Assertions.assertEquals(Request.class, removeRequestUseCase.remove(request.getId()).getClass());
    }

    @Test
    public void deleteRequestByRequest_RequestNotExists_EntityNotFoundException(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987654"));
        createUserUseCase.insert(test);
        Assertions.assertThrows(EntityNotFoundException.class, () -> removeRequestUseCase.remove(new Request(UUID.randomUUID(), Timestamp.valueOf("2023-03-13 13:02:21"), test)).getClass());
    }

    @Test
    public void updateRequest_OnlyWithUUID_IllegalStateException(){
        Request request = new Request();
        Assertions.assertThrows(new IllegalArgumentException().getClass(), () -> updateRequestUseCase.update(request));
    }

    @Test
    public void updateRequest_RequestNotExists_EntityNotFoundException(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987444"));
        createUserUseCase.insert(test);
        Assertions.assertThrows(EntityNotFoundException.class, () -> updateRequestUseCase.update(new Request(UUID.randomUUID(), Timestamp.valueOf("2023-03-13 13:02:21"), test))).getClass();
    }

    @Test
    public void updateRequest_RequestExists_NewRequest(){
        User test = new User(UUID.randomUUID(), "Teste 2", Role.TEACHER, Promptuary.valueOf("SC987611"));
        createUserUseCase.insert(test);
        Request request = new Request(UUID.randomUUID(), Timestamp.valueOf("2023-03-13 13:02:21"), test);
        createRequestUseCase.createARequest(request);
        request.setDate(Timestamp.valueOf("2022-02-11 13:25:32"));
        Assertions.assertEquals(Request.class, updateRequestUseCase.update(request).getClass());
    }
}





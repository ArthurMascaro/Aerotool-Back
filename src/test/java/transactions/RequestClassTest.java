package transactions;

import edu.br.ifsp.applications.persistence.inmemory.InMemoryRequestDAO;
import edu.br.ifsp.applications.persistence.inmemory.InMemoryUserDAO;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.transactions.CreateRequestUseCase;
import edu.br.ifsp.domain.usecases.transactions.FindRequestUseCase;
import edu.br.ifsp.domain.usecases.user.CreateUserUseCase;
import edu.br.ifsp.domain.usecases.user.FindUserUseCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Timestamp;
import java.util.UUID;

public class RequestClassTest {

    public static InMemoryRequestDAO inMemoryRequestDAO = new InMemoryRequestDAO();
    public static InMemoryUserDAO inMemoryUserDAO = new InMemoryUserDAO();
    public static FindUserUseCase findUserUseCase = new FindUserUseCase(inMemoryUserDAO);

    public static FindRequestUseCase findRequestUseCase = new FindRequestUseCase(inMemoryRequestDAO);

    public static CreateUserUseCase createUserUseCase = new CreateUserUseCase(inMemoryUserDAO);
    public static CreateRequestUseCase createRequestUseCase = new CreateRequestUseCase(inMemoryRequestDAO, findUserUseCase);

    @Test
    // public void classeSendoTestada_CondiçãoDoTeste_ResultadoEsperado
    public void createARequest_WithCorrectRequest_RequestClass(){
        User user = new User(UUID.randomUUID(), "Renan", Role.TEACHER, Promptuary.valueOf("SC123456"));
        createUserUseCase.insert(user);
        Assertions.assertEquals(Request.class,  createRequestUseCase.createARequest(user.getPromptuary(), UUID.randomUUID(), Timestamp.valueOf("2023-03-13")).getClass());
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
        Assertions.assertThrows(IllegalArgumentException.class, ()-> createRequestUseCase.createARequest(user.getPromptuary(), UUID.randomUUID(), null));
    }

}





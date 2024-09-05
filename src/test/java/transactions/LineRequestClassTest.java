package transactions;

import edu.br.ifsp.applications.persistence.inmemory.*;
import edu.br.ifsp.domain.entities.tools.*;
import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.transaction.RequestSituation;
import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.tools.CreateToolItemUseCase;
import edu.br.ifsp.domain.usecases.tools.CreateToolUseCase;
import edu.br.ifsp.domain.usecases.tools.FindToolItemUseCase;
import edu.br.ifsp.domain.usecases.transactions.*;
import edu.br.ifsp.domain.usecases.user.CreateUserUseCase;
import edu.br.ifsp.domain.usecases.user.FindUserUseCase;
import edu.br.ifsp.domain.usecases.utils.EntityNotFoundException;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.List;
public class LineRequestClassTest {

    public static InMemoryLineRequestDAO inMemoryLineRequestDAO = new InMemoryLineRequestDAO();
    public static InMemoryRequestDAO inMemoryRequestDAO = new InMemoryRequestDAO();
    public static InMemoryUserDAO inMemoryUserDAO = new InMemoryUserDAO();
    public static InMemoryToolDAO inMemoryToolDAO = new InMemoryToolDAO();
    public static InMemoryToolItemDAO inMemoryToolItemDAO = new InMemoryToolItemDAO();

    public static CreateLineRequestUseCase createLineRequestUseCase = new CreateLineRequestUseCase(inMemoryLineRequestDAO, new FindToolItemUseCase(inMemoryToolItemDAO), new FindRequestUseCase(inMemoryRequestDAO));
    public static CreateUserUseCase createUserUseCase = new CreateUserUseCase(inMemoryUserDAO);
    public static FindUserUseCase findUserUseCase = new FindUserUseCase(inMemoryUserDAO);
    public static CreateRequestUseCase createRequestUseCase = new CreateRequestUseCase(inMemoryRequestDAO, findUserUseCase);
    public static CreateToolUseCase createToolUseCase = new CreateToolUseCase(inMemoryToolDAO);
    public static CreateToolItemUseCase createToolItemUseCase = new CreateToolItemUseCase(inMemoryToolItemDAO);
    public static FindLineRequestUseCase findLineRequestUseCase =  new FindLineRequestUseCase(inMemoryLineRequestDAO);
    public static RemoveLineRequestUseCase removeLineRequestUseCase = new RemoveLineRequestUseCase(inMemoryLineRequestDAO);
    public static UpdateLineRequestUseCase updateLineRequestUseCase = new UpdateLineRequestUseCase(inMemoryLineRequestDAO);


    @Test
    public void LineRequestConstructor_WithoutArguments_ObjectRequestLineWithUUID() {
        assertEquals(UUID.class, new LineRequest().getId().getClass());
    }
    @Test
    public void LineRequestConstructor_WithArguments_ObjectRequestLineWithUUID() {
        assertEquals(UUID.class, new LineRequest(UUID.randomUUID()).getId().getClass());
    }

    @Test
    public void LineRequestGet_WithoutArguments_ObjectRequestLineWithRealReturnDate() {
        Timestamp value = Timestamp.valueOf("2018-11-12 01:02:03");
        LineRequest request = new LineRequest();
        request.setRealReturnDate(value);
        assertEquals(Timestamp.class, request.getRealReturnDate().getClass());
    }

    @Test
    public void insertLineRequest_WithCorrectLineRequest_LineRequestClass(){
        Tool tool = new Tool(UUID.randomUUID(), "Martelo", "Usado para martelar", ToolType.COMMON);
        createToolUseCase.insert(tool);

        Locate locate = new Locate(UUID.randomUUID(), "Armário 1", "Armário da oficina");
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1234", locate.getId(), tool.getId(), ToolSituation.FREE);
        createToolItemUseCase.insert(toolItem);

        User user = new User(UUID.randomUUID(), "Miguel", Role.ADMIN, "SC1231231");
        createUserUseCase.insert(user);

        Timestamp value = Timestamp.valueOf("2018-11-12 01:02:03");
        Request request = new Request(UUID.randomUUID(), value, user);
        createRequestUseCase.createARequest(request);

        LineRequest lineRequest = new LineRequest(UUID.randomUUID(), request, toolItem, value, value, value, value, RequestSituation.ACCEPTED);
        assertEquals(LineRequest.class, createLineRequestUseCase.insert(lineRequest).getClass());
    }

    @Test
    public void findOneLineRequest_WithCorrectLineRequest_LineRequestList(){
        Tool tool = new Tool(UUID.randomUUID(), "Martelo", "Usado para martelar", ToolType.COMMON);
        createToolUseCase.insert(tool);

        Locate locate = new Locate(UUID.randomUUID(), "Armário 2", "Armário da oficina");
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1234", locate.getId(), tool.getId(), ToolSituation.FREE);
        createToolItemUseCase.insert(toolItem);

        User user = new User(UUID.randomUUID(), "Renan", Role.ADMIN, "SC5645667");
        createUserUseCase.insert(user);

        Timestamp value = Timestamp.valueOf("2018-11-12 01:02:03");
        Request request = new Request(UUID.randomUUID(), value, user);
        createRequestUseCase.createARequest(request);

        LineRequest lineRequest = new LineRequest(UUID.randomUUID(), request, toolItem, value, value, value, value, RequestSituation.ACCEPTED);
        UUID id = createLineRequestUseCase.insert(lineRequest).getId();

        assertEquals(lineRequest, findLineRequestUseCase.findOne(id).get());
    }

    @Test
    public void removeLineRequest_WithoutCorrectId_EntityNotFoundException(){
        assertThrows(EntityNotFoundException.class, () -> removeLineRequestUseCase.remove(UUID.randomUUID()));
    }

    @Test
    public void removeLineRequest_WithCorrectId_LineRequestClass(){
        Tool tool = new Tool(UUID.randomUUID(), "Martelo", "Usado para martelar", ToolType.COMMON);
        createToolUseCase.insert(tool);

        Locate locate = new Locate(UUID.randomUUID(), "Armário 3", "Armário da oficina");
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1234", locate.getId(), tool.getId(), ToolSituation.FREE);
        createToolItemUseCase.insert(toolItem);

        User user = new User(UUID.randomUUID(), "Arthur", Role.ADMIN, "SC8901234");
        createUserUseCase.insert(user);

        Timestamp value = Timestamp.valueOf("2018-11-12 01:02:03");
        Request request = new Request(UUID.randomUUID(), value, user);
        createRequestUseCase.createARequest(request);

        LineRequest lineRequest = new LineRequest(UUID.randomUUID(), request, toolItem, value, value, value, value, RequestSituation.ACCEPTED);
        UUID id = createLineRequestUseCase.insert(lineRequest).getId();

        assertEquals(LineRequest.class, removeLineRequestUseCase.remove(id).getClass());
    }

    @Test
    public void updateLineRequest_WithCorrectLineRequest_LineRequestClass(){
        Tool tool = new Tool(UUID.randomUUID(), "Martelo", "Usado para martelar", ToolType.COMMON);
        createToolUseCase.insert(tool);

        Locate locate = new Locate(UUID.randomUUID(), "Armário 4", "Armário do laboratório");
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1234", locate.getId(), tool.getId(), ToolSituation.FREE);
        createToolItemUseCase.insert(toolItem);

        User user = new User(UUID.randomUUID(), "Heitor", Role.ADMIN, "SC6275123");
        createUserUseCase.insert(user);

        Timestamp value = Timestamp.valueOf("2018-11-12 01:02:03");
        Request request = new Request(UUID.randomUUID(), value, user);
        createRequestUseCase.createARequest(request);

        LineRequest lineRequest = new LineRequest(UUID.randomUUID(), request, toolItem, value, value, value, value, RequestSituation.ACCEPTED);
        UUID id = createLineRequestUseCase.insert(lineRequest).getId();

        //New user and new lineRequest
        User user2 = new User(UUID.randomUUID(), "Gustavo", Role.ADMIN, "SC0000000");
        createUserUseCase.insert(user2);

        Request request2 = new Request(UUID.randomUUID(), value, user2);

        LineRequest lineRequest2 = new LineRequest(id, request2, toolItem, value, value, value, value, RequestSituation.ACCEPTED);

        assertEquals(LineRequest.class, updateLineRequestUseCase.update(lineRequest2).getClass());
    }

    @Test
    public void updateLineRequest_WithoutCorrectLineRequest_EntityNotFoundException(){
        Tool tool = new Tool(UUID.randomUUID(), "Martelo", "Usado para martelar", ToolType.COMMON);
        createToolUseCase.insert(tool);

        Locate locate = new Locate(UUID.randomUUID(), "Armário 5", "Armário do laboratório");
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1234", locate.getId(), tool.getId(), ToolSituation.FREE);
        createToolItemUseCase.insert(toolItem);

        User user = new User(UUID.randomUUID(), "Jorge", Role.ADMIN, "SC5374612");
        createUserUseCase.insert(user);

        Timestamp value = Timestamp.valueOf("2018-11-12 01:02:03");
        Request request = new Request(UUID.randomUUID(), value, user);
        createRequestUseCase.createARequest(request);

        LineRequest lineRequest = new LineRequest(UUID.randomUUID(), request, toolItem, value, value, value, value, RequestSituation.ACCEPTED);

        assertThrows(EntityNotFoundException.class, () -> updateLineRequestUseCase.update(lineRequest));
    }

}

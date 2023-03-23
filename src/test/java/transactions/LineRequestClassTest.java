package transactions;

import edu.br.ifsp.applications.persistence.inmemory.InMemoryLineRequestDAO;
import edu.br.ifsp.domain.entities.tools.*;
import edu.br.ifsp.domain.entities.transaction.LineRequest;
import edu.br.ifsp.domain.entities.transaction.Request;
import edu.br.ifsp.domain.entities.user.Promptuary;
import edu.br.ifsp.domain.entities.user.Role;
import edu.br.ifsp.domain.entities.user.User;
import edu.br.ifsp.domain.usecases.transactions.CreateLineRequestUseCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.sound.sampled.Line;
import java.sql.Timestamp;
import java.util.UUID;
public class LineRequestClassTest {

    public static InMemoryLineRequestDAO inMemoryLineRequestDAO = new InMemoryLineRequestDAO();

    public CreateLineRequestUseCase createLineRequestUseCase = new CreateLineRequestUseCase(inMemoryLineRequestDAO);

    @Test
    public void LineRequestConstructor_WithoutArguments_ObjectRequestLineWithUUID() {
        Assertions.assertEquals(UUID.class, new LineRequest().getId().getClass());
    }
    @Test
    public void LineRequestConstructor_WithArguments_ObjectRequestLineWithUUID() {
        Assertions.assertEquals(UUID.class, new LineRequest(UUID.randomUUID()).getId().getClass());
    }

    @Test
    public void LineRequestGet_WithoutArguments_ObjectRequestLineWithRealReturnDate() {
        Timestamp value = Timestamp.valueOf("2018-11-12 01:02:03");
        LineRequest request = new LineRequest();
        request.setRealReturnDate(value);
        Assertions.assertEquals(Timestamp.class, request.getRealReturnDate().getClass());
    }

    @Test
    public void insertLineRequest_WithCorrectLineRequest_LineRequestClass(){
        Tool tool = new Tool(UUID.randomUUID(), "Martelo", "Usado para martelar", ToolType.COMMON);
        Locate locate = new Locate(UUID.randomUUID(), "Armário 1", "Armário da oficina");
        ToolItem toolItem = new ToolItem(UUID.randomUUID(), "SCL1234", locate, tool, ToolSituation.FREE);

        User user = new User(UUID.randomUUID(), "Miguel", Role.ADMIN, Promptuary.valueOf("SC1231231"));
        //Request request = new Request(UUID.randomUUID(), , user);

        //LineRequest lineRequest = new LineRequest(UUID.randomUUID(), );
        //Assertions.assertEquals(LineRequest.class, createLineRequestUseCase.insert(lineRequest).getClass());
    }
}

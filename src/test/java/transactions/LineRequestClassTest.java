package transactions;

import edu.br.ifsp.domain.entities.transaction.LineRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Timestamp;
import java.util.UUID;
public class LineRequestClassTest {

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
}

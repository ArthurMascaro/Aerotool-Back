package user;

import edu.br.ifsp.domain.entities.user.Promptuary;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PromptuaryTests {

    @Test
    public void valueOf_LoginWithLowerCase_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Promptuary.valueOf("sc1234567"));
    }

    @Test
    public void valueOf_LoginWithUpperCase_PromptuaryClass() {
        assertEquals(Promptuary.class, Promptuary.valueOf("SC1234567").getClass());
    }

    @Test
    public void valueOf_LoginWithMoreThan7Digits_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Promptuary.valueOf("SC12345678"));
    }

    @Test
    public void valueOf_LoginWithLessThan6Digits_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Promptuary.valueOf("SC12345"));
    }

    @Test
    public void valueOf_LoginWithoutLetters_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Promptuary.valueOf("1234567"));
    }

    @Test
    public void valueOf_LoginWithoutNumbers_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Promptuary.valueOf("SCabcdefg"));
    }

    @Test
    public void valueOf_LoginWithSymbols_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Promptuary.valueOf("SC1234567!"));
    }

    @Test
    public void valueOf_LoginIsNull_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Promptuary.valueOf(null));
    }

    @Test
    public void valueOf_LoginIsRight_PromptuaryClassWithLogin() {
        var promptuary = Promptuary.valueOf("SC1234567");
        Assert.assertEquals("SC1234567", promptuary.getLogin());
    }
}

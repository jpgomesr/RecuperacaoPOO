import org.example.Brincadeira;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrincadeiraTest {
    Brincadeira brincadeira = new Brincadeira("Jogar bola", 35, 30, 25, 20, 15);

    @Test
    void construtor() {
        assertNotNull(brincadeira);
    }

    @Test
    void getDivertimento() {
        assertEquals(35, brincadeira.getDivertimento());
    }

    @Test
    void getCansaco() {
        assertEquals(30, brincadeira.getCansaco());
    }

    @Test
    void getFome() {
        assertEquals(25, brincadeira.getFome());
    }

    @Test
    void getSede() {
        assertEquals(20, brincadeira.getSede());
    }

    @Test
    void getSujeira() {
        assertEquals(15, brincadeira.getSujeira());
    }

    @Test
    void getCodigo() {
        assertDoesNotThrow(() -> brincadeira.getCodigo());
    }
}
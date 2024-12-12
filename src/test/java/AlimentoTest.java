import org.example.Alimento;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;

import static org.junit.jupiter.api.Assertions.*;

class AlimentoTest {
    static Alimento alimento= new Alimento("Arroz", 30);

    @Test
    void construtor() {
        assertNotNull(alimento);
    }

    @Test
    void getNutricao() {
        assertEquals(30, alimento.getNutricao());
    }

    @Test
    void getCodigo() {
        assertDoesNotThrow(() -> alimento.getCodigo());
    }
}
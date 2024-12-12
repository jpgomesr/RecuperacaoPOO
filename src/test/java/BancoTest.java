import org.example.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BancoTest {

    @Test
    void cadastrarPet() {
        Pet pet = new Pet("Rex");
        Banco.cadastrarPet(pet);
        assertTrue(Banco.getPets().contains(pet));
    }

    @Test
    void cadastrarPessoa() {
        Pessoa pessoa = new Pessoa(123456789, "João", "1234");
        Banco.cadastrarPessoa(pessoa);
        assertNotNull(Banco.procurarPessoa(pessoa.getCpf()));
    }

    @Test
    void removerPet() {
        Pet pet = new Pet("Thor");
        Banco.cadastrarPet(pet);
        if (Banco.getPets().contains(pet)) {
            Banco.removerPet(pet);
            assertFalse(Banco.getPets().contains(pet));
        }
    }

    @Test
    void removerPessoa() {
        Pessoa pessoa = new Pessoa(987654321, "Maria", "4321");
        Banco.cadastrarPessoa(pessoa);
        if (Banco.procurarPessoa(987654321) != null) {
            Banco.removerPessoa(pessoa);
            assertNull(Banco.procurarPessoa(987654321));
        }
    }

    @Test
    void procurarAlimento() {
        Alimento alimento = new Alimento("Arroz", 30);
        Banco.getAlimentos().add(alimento);
        assertEquals(alimento, Banco.procurarAlimento(1));
    }

    @Test
    void procurarBrincadeira() {
        Brincadeira brincadeira = new Brincadeira("Jogar bola", 35, 30, 25, 20, 15);
        Banco.getBrincadeiras().add(brincadeira);
        assertEquals(brincadeira, Banco.procurarBrincadeira(1));
    }

    @Test
    void procurarPet() {
        Pet pet = new Pet("Tobi");
        Banco.cadastrarPet(pet);
        int id = pet.getCodigo();
        assertEquals(pet, Banco.procurarPet(id));
    }

    @Test
    void procurarPessoa() {
        Pessoa pessoa = new Pessoa(111111111, "Jorge", "1111");
        Banco.cadastrarPessoa(pessoa);
        assertEquals(pessoa, Banco.procurarPessoa(111111111));
    }

    @Test
    void getAlimentos() {
        Alimento frango = new Alimento("Frango", 40);
        Alimento feijao = new Alimento("Feijão", 35);
        Alimento farofa = new Alimento("Farofa", 20);
        Banco.getAlimentos().addAll(List.of(frango, feijao, farofa));
        assertTrue(Banco.getAlimentos().size() >= 3);
    }

    @Test
    void getBrincadeiras() {
        Brincadeira bola = new Brincadeira("Jogar bola", 35, 30, 25, 20, 15);
        Brincadeira domino = new Brincadeira("Dominó", 10, 10, 5, 10, 0);
        Brincadeira truco = new Brincadeira("Truco", 20, 15, 10, 15, 5);
        Banco.getBrincadeiras().addAll(List.of(bola, domino, truco));
        assertTrue(Banco.getBrincadeiras().size() >= 3);
    }

    @Test
    void getPets() {
        Pet rex = new Pet("Rex");
        Pet toto = new Pet("Totó");
        Pet lulu = new Pet("Lulu");
        Banco.getPets().addAll(List.of(rex, toto, lulu));
        assertTrue(Banco.getPets().size() >= 3);
    }

    @Test
    void login() {
        Pessoa pessoa = new Pessoa(123456789, "João", "1234");
        Banco.cadastrarPessoa(pessoa);
        assertEquals(pessoa, Banco.login(123456789, "1234"));
    }
}
import org.example.Alimento;
import org.example.Brincadeira;
import org.example.Pessoa;
import org.example.Pet;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;


class PessoaTest {
    @Test
    @Order(1)
    void construtor() {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        assertNotNull(pessoa);
    }

    @Test
    @Order(2)
    void getCpf() {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        assertEquals(123456789, pessoa.getCpf());
    }

    @Test
    @Order(3)
    void getSenha() {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        assertEquals("123", pessoa.getSenha());
    }

    @Test
    @Order(4)
    void botaPetParaDormir() {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        pessoa.botaPetParaDormir();
        assertTrue(pet.toString().contains("Acordado: Não"));
    }

    @Test
    @Order(5)
    void acordarPet() {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        pessoa.acordarPet();
        assertTrue(pet.toString().contains("Acordado: Sim"));
    }

    @Test
    @Order(6)
    void adotarPet() {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        assertFalse(pessoa.avaliarPet().contains("Sem pet"));
    }

    @Test
    @Order(7)
    void darAguaParaPet() throws NoSuchFieldException, IllegalAccessException {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        pessoa.brincarComPet(new Brincadeira("Jogar bola", 35, 30, 25, 20, 15));
        int sedeAntes = getPrivateFieldInteger(pet, "sede");
        pessoa.darAguaParaPet();
        int sedeDepois = getPrivateFieldInteger(pet, "sede");
        assertTrue(sedeDepois > sedeAntes);
    }

    @Test
    @Order(8)
    void brincarComPet() throws NoSuchFieldException, IllegalAccessException {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        pessoa.brincarComPet(new Brincadeira("Jogar bola", -35, 30, 25, 20, 15));
        int cansacoAntes = getPrivateFieldInteger(pet, "energia");
        pessoa.brincarComPet(new Brincadeira("Jogar bola", 30, 30, 25, 20, 15));
        int cansacoDepois = getPrivateFieldInteger(pet, "energia");
        assertEquals(cansacoAntes - 30, cansacoDepois);
    }

    @Test
    @Order(9)
    void alimentarPet() throws NoSuchFieldException, IllegalAccessException {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        pessoa.brincarComPet(new Brincadeira("Jogar bola", 35, 30, 50, 20, 15));
        int fomeAntes = getPrivateFieldInteger(pet, "fome");
        pessoa.alimentarPet(new Alimento("Ração", 30));
        int fomeDepois = getPrivateFieldInteger(pet, "fome");
        assertEquals(fomeAntes + 30, fomeDepois);
    }

    @Test
    @Order(10)
    void levarPetParaFazerNecessidades() throws NoSuchFieldException, IllegalAccessException {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        int higieneAntes = getPrivateFieldInteger(pet, "higiene");
        pessoa.levarPetParaFazerNecessidades();
        int higieneDepois = getPrivateFieldInteger(pet, "higiene");
        assertEquals(higieneAntes - 25, higieneDepois);
    }

    @Test
    @Order(11)
    void limparPet() throws NoSuchFieldException, IllegalAccessException {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        pessoa.levarPetParaFazerNecessidades();
        pessoa.limparPet();
        int higieneDepois = getPrivateFieldInteger(pet, "higiene");
        assertEquals(100, higieneDepois);
    }

    @Test
    @Order(12)
    void avaliarPet() {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        assertTrue(pessoa.avaliarPet().contains("Juca") &&
                   pessoa.avaliarPet().contains("Vivo: Sim") &&
                   pessoa.avaliarPet().contains("Acordado: Sim"));
    }

    @Test
    @Order(13)
    void testToString() throws NoSuchFieldException, IllegalAccessException {
        Pessoa pessoa = new Pessoa(123456789, "João", "123");
        Pet pet = new Pet("Juca");
        pessoa.adotarPet(pet);
        setPrivateField(pet, "vivo", true);
        setPrivateField(pet, "acordado", true);
        setPrivateField(pet, "sede", 100);
        setPrivateField(pet, "fome", 20);
        setPrivateField(pet, "energia", 40);
        setPrivateField(pet, "diversao", 50);
        setPrivateField(pet, "higiene", 70);
        setPrivateField(pet, "vontadeBanheiro", 90);
        String expected = "CPF: 123456789\n"+
                          "Nome: João\n" +
                          "Pet: Nome: Juca\n" +
                                 "Vivo: Sim\n" +
                                 "Acordado: Sim\n" +
                                 "Sede: 100\n" +
                                 "Fome: 20\n" +
                                 "Energia: 40\n" +
                                 "Diversão: 50\n" +
                                 "Higiene: 70\n" +
                                 "Vontade de ir ao banheiro: 90\n";

        assertEquals(expected, pessoa.toString());
//        assertEquals("Nome: João\nCPF: 123456789", pessoa.toString());
    }

    private void setPrivateField(Pet pet, String fieldName, int value) throws NoSuchFieldException, IllegalAccessException {
        Field field = Pet.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.setInt(pet, value);
    }

    private void setPrivateField(Pet pet, String fieldName, boolean value) throws NoSuchFieldException, IllegalAccessException {
        Field field = Pet.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.setBoolean(pet, value);
    }

    private int getPrivateFieldInteger(Pet pet, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        Field field = Pet.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (int) field.get(pet);
    }

    private boolean getPrivateFieldBoolean(Pet pet, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = Pet.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (boolean) field.get(pet);
    }


}


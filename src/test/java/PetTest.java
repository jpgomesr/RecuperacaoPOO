import org.example.Alimento;
import org.example.Brincadeira;
import org.example.Pet;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetTest {

//    Pet(String nome): Construtor que inicializa um objeto Pet com um nome,
//    atribuindo um código único.

    @Test
    @Order(1)
    public void testConstrutor(){
        Pet pet = new Pet("Rex");
        assertNotNull(pet);
    }

//    beberAgua(): Método para o animal beber água, afetando os níveis de sede
//    em +50, vontade de ir ao banheiro em -25 e higiene diminuindo a 0 somente
//    se a vontade de ir ao banheiro atingir 0, a qual passa a ser 100.

    @Test
    @Order(2)
    public void testBeberAguaMelhorandoSede() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "sede", 40);
        setPrivateField(pet, "vontadeBanheiro", 50);
        pet.beberAgua();
        assertEquals(90, getPrivateFieldInteger(pet, "sede"));
        assertEquals(25, getPrivateFieldInteger(pet, "vontadeBanheiro"));
        assertEquals(100, getPrivateFieldInteger(pet, "higiene"));
    }

    @Test
    @Order(3)
    public void testBeberAguaPiorandoHigiene() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "vontadeBanheiro", 20);
        pet.beberAgua();
        assertEquals(0, getPrivateFieldInteger(pet, "higiene"));
    }

    @Test
    @Order(4)
    public void testBeberAguaPiorandoVontadeBanheiro() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "vontadeBanheiro", 20);
        pet.beberAgua();
        assertEquals(100, getPrivateFieldInteger(pet, "vontadeBanheiro"));
    }

    @Test
    @Order(5)
    public void testBeberAguaLimiteDeSedeEmCem() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "sede", 90);
        pet.beberAgua();
        assertEquals(100, getPrivateFieldInteger(pet, "sede"));
    }

//    comer(Alimento alimento): Método para o animal comer, afetando os níveis
//    de fome de acordo com o a nutrição do alimento, vontade de ir ao banheiro
//    diminuindo de acordo com a metade da nutrição do alimento e higiene
//    diminuindo a 0 somente se a vontade de ir ao banheiro atingir 0, a qual passa a
//    ser 100.

    @Test
    @Order(6)
    public void testComerMelhorandoFome() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Alimento alimento = new Alimento("Ração", 30);
        setPrivateField(pet, "fome", 60);
        pet.comer(alimento);
        assertEquals(90, getPrivateFieldInteger(pet, "fome"));
    }

    @Test
    @Order(7)
    public void testComerPiorandoVontadeBanheiro() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Alimento alimento = new Alimento("Ração", 30);
        setPrivateField(pet, "vontadeBanheiro", 50);
        pet.comer(alimento);
        assertEquals(35, getPrivateFieldInteger(pet, "vontadeBanheiro"));
    }

    @Test
    @Order(8)
    public void testComerPiorandoHigiene() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Alimento alimento = new Alimento("Ração", 30);
        setPrivateField(pet, "vontadeBanheiro", 10);
        pet.comer(alimento);
        assertEquals(0, getPrivateFieldInteger(pet, "higiene"));
    }

    @Test
    @Order(9)
    public void testComerLimiteDeFomeEmCem() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Alimento alimento = new Alimento("Ração", 30);
        setPrivateField(pet, "fome", 90);
        pet.comer(alimento);
        assertEquals(100, getPrivateFieldInteger(pet, "fome"));
    }

//    dormir(): Método para fazer o animal dormir, afetando o nível de energia em
//    +25.

    @Test
    @Order(10)
    public void testDormirMelhorandoEnergia() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "energia", 30);
        pet.dormir();
        assertEquals(55, getPrivateFieldInteger(pet, "energia"));
    }

    @Test
    @Order(11)
    public void testDormirAlterandoAcordado() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "acordado", true);
        pet.dormir();
        assertFalse(getPrivateFieldBoolean(pet, "acordado"));
    }

    @Test
    @Order(12)
    public void testDormirLimiteDeEnergiaEmCem() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "energia", 80);
        pet.dormir();
        assertEquals(100, getPrivateFieldInteger(pet, "energia"));
    }

//    acordar(): Método para acordar o animal, afetando o nível de energia em +25.

    @Test
    @Order(13)
    public void testAcordarMelhorandoEnergia() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "acordado", false);
        setPrivateField(pet, "energia", 30);
        pet.acordar();
        assertEquals(55, getPrivateFieldInteger(pet, "energia"));
    }

    @Test
    @Order(14)
    public void testAcordarAlterandoAcordado() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "acordado", false);
        pet.acordar();
        assertTrue(getPrivateFieldBoolean(pet, "acordado"));
    }

//    @Test
//    @Order(15)
//    public void testAcordarLimiteDeEnergiaEmZero() throws NoSuchFieldException, IllegalAccessException {
//        Pet pet = new Pet("Rex");
//        setPrivateField(pet, "acordado", false);
//        setPrivateField(pet, "energia", 20);
//        pet.acordar();
//        assertEquals(45, getPrivateFieldInteger(pet, "energia"));
//    }

//    brincar(Brincadeira brincadeira): Método para o animal brincar, afetando os
//    níveis de diversão aumentando com base no divertimento da brincadeira,
//    energia diminuindo com base no cansaço da brincadeira, fome diminuindo
//    com base na “fome” da brincadeira, sede diminuindo com base na “sede” da
//    brincadeira, higiene diminuindo com base na sujeira da brincadeira, e,
//    potencialmente, levando à morte se os requisitos não forem atendidos.

    @Test
    @Order(16)
    public void testBrincarMelhorandoDiversao() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Brincadeira brincadeira = new Brincadeira("Correr", 40, 30, 20, 10, 5);
        setPrivateField(pet, "diversao", 50);
        pet.brincar(brincadeira);
        assertEquals(90, getPrivateFieldInteger(pet, "diversao"));
    }

    @Test
    @Order(17)
    public void testBrincarPiorandoEnergia() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Brincadeira brincadeira = new Brincadeira("Correr", 40, 30, 20, 10, 5);
        setPrivateField(pet, "energia", 70);
        pet.brincar(brincadeira);
        assertEquals(40, getPrivateFieldInteger(pet, "energia"));
    }

    @Test
    @Order(18)
    public void testBrincarPiorandoFome() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Brincadeira brincadeira = new Brincadeira("Correr", 40, 30, 20, 10, 5);
        setPrivateField(pet, "fome", 50);
        pet.brincar(brincadeira);
        assertEquals(30, getPrivateFieldInteger(pet, "fome"));
    }

    @Test
    @Order(19)
    public void testBrincarPiorandoSede() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Brincadeira brincadeira = new Brincadeira("Correr", 40, 30, 20, 10, 5);
        setPrivateField(pet, "sede", 30);
        pet.brincar(brincadeira);
        assertEquals(20, getPrivateFieldInteger(pet, "sede"));
    }

    @Test
    @Order(20)
    public void testBrincarPiorandoHigiene() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Brincadeira brincadeira = new Brincadeira("Correr", 40, 30, 20, 10, 5);
        setPrivateField(pet, "higiene", 40);
        pet.brincar(brincadeira);
        assertEquals(35, getPrivateFieldInteger(pet, "higiene"));
    }

    @Test
    @Order(21)
    public void testBrincarMorte() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Brincadeira brincadeira = new Brincadeira("Correr", 40, 30, 20, 10, 5);
        setPrivateField(pet, "energia", 25);
        setPrivateField(pet, "fome", 15);
        setPrivateField(pet, "sede", 5);
        setPrivateField(pet, "acordado", true);
        pet.brincar(brincadeira);
        assertFalse(getPrivateFieldBoolean(pet, "vivo"));
    }

    @Test
    @Order(22)
    public void testBrincarLimiteDeDiversaoEmCem() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        Brincadeira brincadeira = new Brincadeira("Correr", 40, 30, 20, 10, 5);
        setPrivateField(pet, "diversao", 90);
        pet.brincar(brincadeira);
        assertEquals(100, getPrivateFieldInteger(pet, "diversao"));
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    @Order(23)
    public void testLimpar() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "higiene", 30);
        pet.limpar();
        assertTrue(getPrivateFieldInteger(pet, "higiene") > 30);
    }

    @Test
    @Order(24)
    public void testFazerNecessidades() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "vontadeBanheiro", 30);
        setPrivateField(pet, "higiene", 60);
        pet.fazerNecessidades();
        assertEquals(100, getPrivateFieldInteger(pet, "vontadeBanheiro"));
        assertEquals(35, getPrivateFieldInteger(pet, "higiene"));
    }

    @Test
    @Order(25)
    public void testMorrerDeSede() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "sede", 0);
        pet.morrer();
        assertFalse(getPrivateFieldBoolean(pet, "vivo"));
    }
    @Test
    @Order(26)
    public void testMorrerDeFome() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "fome", 0);
        pet.morrer();
        assertFalse(getPrivateFieldBoolean(pet, "vivo"));
    }
    @Test
    @Order(27)
    public void testMorrerDeCansaco() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        setPrivateField(pet, "energia", 0);
        pet.morrer();
        assertFalse(getPrivateFieldBoolean(pet, "vivo"));
    }
    @Test
    @Order(28)
    public void testNaoMorrer() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("Rex");
        pet.morrer();
        assertTrue(getPrivateFieldBoolean(pet, "vivo"));
    }

    @Test
    @Order(29)
    public void testToString() throws NoSuchFieldException, IllegalAccessException {
        Pet pet = new Pet("MI72");
        setPrivateField(pet, "vivo", true);
        setPrivateField(pet, "acordado", true);
        setPrivateField(pet, "sede", 100);
        setPrivateField(pet, "fome", 20);
        setPrivateField(pet, "energia", 40);
        setPrivateField(pet, "diversao", 50);
        setPrivateField(pet, "higiene", 70);
        setPrivateField(pet, "vontadeBanheiro", 90);

        String expected = "Nome: MI72\n" +
                          "Vivo: Sim\n" +
                          "Acordado: Sim\n" +
                          "Sede: 100\n" +
                          "Fome: 20\n" +
                          "Energia: 40\n" +
                          "Diversão: 50\n" +
                          "Higiene: 70\n" +
                          "Vontade de ir ao banheiro: 90\n";

        assertEquals(expected, pet.toString());
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
}

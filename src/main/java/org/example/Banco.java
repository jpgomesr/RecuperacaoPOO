package org.example;

import org.example.Modals.Alimento;
import org.example.Modals.Brincadeira;
import org.example.Modals.Pessoa;
import org.example.Modals.Pet;
import org.example.database.CRUDAlimento;
import org.example.database.CRUDPessoa;
import org.example.database.CRUDPet;
import org.example.exceptions.CredenciaisInvalidasException;
import org.example.exceptions.alimento.AlimentoJaExistenteException;
import org.example.exceptions.alimento.AlimentoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private static final CRUDAlimento alimentoCrud = new CRUDAlimento();
    private static final CRUDPessoa pessoaCrud = new CRUDPessoa();
    private static final CRUDPet petCrud = new CRUDPet();

    // ALIMENTO

    public static Alimento salvarAlimento(Alimento alimento) {
        if (alimento.getCodigo() > 0) {
            return alimentoCrud.atualizarAlimento(alimento);
        } else {
            return alimentoCrud.adicionarAlimento(alimento);
        }
    }

    public static ArrayList<Alimento> procurarAlimentos() {
        return alimentoCrud.buscarTodosAlimentos();
    }

    public static Alimento procurarAlimento(int codigo) {
        return alimentoCrud.buscarAlimentoPorCodigo(codigo);
    }

    public static Alimento procurarAlimento(String nome) {
        return alimentoCrud.buscarAlimentoPorNome(nome);
    }

    public static void removerAlimento(Alimento alimento) {
        alimentoCrud.removerAlimento(alimento);
    }

    // PESSOA

    public static Pessoa salvarPessoa(Pessoa pessoa) {
        if (pessoa.getCodigo() > 0) {
            return pessoaCrud.atualizarPessoa(pessoa);
        } else {
            return pessoaCrud.adicionarPessoa(pessoa);
        }
    }

    public static ArrayList<Pessoa> procurarPessoas() {
        return pessoaCrud.buscarTodasPessoas();
    }

    public static Pessoa procurarPessoa(long cpf) {
        return pessoaCrud.buscarPessoaPorCpf(cpf);
    }

    public static void removerPessoa(Pessoa pessoa) {
        pessoaCrud.removerPessoa(pessoa);
    }

    public static Pessoa login(long cpf, String senha) throws CredenciaisInvalidasException {
        Pessoa pessoa = procurarPessoa(cpf);
        if (pessoa.getSenha().equals(senha)) {
            return pessoa;
        }
        throw new CredenciaisInvalidasException("Senha inválida!");
    }

    public static void trocarPet(Pessoa pessoa, Pet pet) {
        pessoa.setPet(pet);
        salvarPessoa(pessoa);
    }

    // PET

    public static Pet salvarPet(Pet pet) {
        if (pet.getCodigo() > 0) {
            return petCrud.atualizarPet(pet);
        } else {
            return petCrud.adicionarPet(pet);
        }
    }

    public static ArrayList<Pet> procurarPets() {
        return petCrud.buscarTodosPets();
    }

    public static Pet procurarPet(int codigo) {
        return petCrud.buscarPetPorCodigo(codigo);
    }

    public static Pet procurarPet(String nome) {
        return petCrud.buscarPetPorNome(nome);
    }

    public static void removerPet(Pet pet) {
        petCrud.removerPet(pet);
    }
}

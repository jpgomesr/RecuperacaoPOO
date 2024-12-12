package org.example;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    public static List<Alimento> alimentos = new ArrayList<>();
    public static List<Brincadeira> brincadeiras = new ArrayList<>();
    public static List<Pessoa> pessoas = new ArrayList<>();
    public static List<Pet> pets = new ArrayList<>();

    public static void cadastrarPet(Pet pet) {
        pets.add(pet);
    }

    public static void cadastrarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public static void removerPet(Pet pet) {
        pets.remove(pet);
    }

    public static void removerPessoa(Pessoa pessoa) {
        pessoas.remove(pessoa);
    }

    public static Alimento procurarAlimento(int codigo) {
        for (Alimento alimento : alimentos) {
            if (codigo == alimento.getCodigo()) {
                return alimento;
            }
        }
        return null;
    }

    public static Brincadeira procurarBrincadeira(int codigo) {
        for (Brincadeira brincadeira : brincadeiras) {
            if (brincadeira.getCodigo() == codigo) {
                return brincadeira;
            }
        }
        return null;
    }

    public static Pet procurarPet(int codigo) {
        for (Pet pet : pets) {
            if (pet.getCodigo() == codigo) {
                return pet;
            }
        }
        return null;
    }

    public static Pessoa procurarPessoa(long cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf() == cpf) {
                return pessoa;
            }
        }
        return null;
    }

    public static List<Alimento> getAlimentos() {
        return alimentos;
    }

    public static List<Brincadeira> getBrincadeiras() {
        return brincadeiras;
    }

    public static List<Pessoa> getPessoas() {
        return pessoas;
    }

    public static List<Pet> getPets() {
        return pets;
    }

    public static Pessoa login(long cpf, String senha) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf() == cpf && pessoa.getSenha().equals(senha)) {
                return pessoa;
            }
        }
        return null;
    }
}

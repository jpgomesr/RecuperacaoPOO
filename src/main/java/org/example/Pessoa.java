package org.example;

public class Pessoa {
    private long cpf;
    private String nome;
    private String senha;
    private Pet pet;

    public Pessoa() {
    }

    public Pessoa(long cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public long getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void botaPetParaDormir() {
        if (this.pet != null) {
            this.pet.dormir();
        }
    }

    public void acordarPet() {
        if (this.pet != null) {
            this.pet.acordar();
        }
    }

    public void darAguaParaPet() {
        if (this.pet != null) {
            this.pet.beberAgua();
        }
    }

    public String avaliarPet() {
        if (pet != null) {
            return pet.toString();
        } else {
            return "Sem pet";
        }
    }

    public void adotarPet(Pet pet) {
        if (this.pet == null) {
            this.pet = pet;
        }
    }

    public void alimentarPet(Alimento alimento) {
        if (this.pet != null) {
            this.pet.comer(alimento);
        }
    }

    public void levarPetParaFazerNecessidades() {
        pet.fazerNecessidades();
    }

    public void brincarComPet(Brincadeira brincadeira) {
        pet.brincar(brincadeira);
    }

    public void limparPet() {
        if (this.pet != null) {
            this.pet.limpar();
        }
    }

    @Override
    public String toString() {
        return "CPF: " + this.cpf + "\n" +
                "Nome: " + this.nome + "\n" +
                "Pet: " + this.pet.toString();
    }
}

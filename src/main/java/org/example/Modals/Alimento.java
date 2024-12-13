package org.example.Modals;

public class Alimento {
    private int codigo;
    private String nome;
    private int nutricao;

    public Alimento() {
    }

    public Alimento(String nome, int nutricao) {
        this.nome = nome;
        this.nutricao = nutricao;
    }

    public Alimento(int id, String nome, int nutricao) {
        this.codigo = id;
        this.nome = nome;
        this.nutricao = nutricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getNutricao() {
        return nutricao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNutricao(int nutricao) {
        this.nutricao = nutricao;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\n" +
                "Nutrição: " + this.nutricao + "\n";
    }
}

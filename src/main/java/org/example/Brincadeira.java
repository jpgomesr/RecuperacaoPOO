package org.example;

public class Brincadeira {
    public static int geradorCodigo = 1;
    private int codigo;
    private String nome;
    private int cansaco;
    private int fome;
    private int sede;
    private int sujeira;
    private int divertimento;

    public Brincadeira(String nome, int divertimento, int cansaco, int fome, int sede, int sujeira) {
        this.codigo = geradorCodigo++;
        this.nome = nome;
        this.divertimento = divertimento;
        this.cansaco = cansaco;
        this.fome = fome;
        this.sede = sede;
        this.sujeira = sujeira;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCansaco() {
        return cansaco;
    }

    public int getFome() {
        return fome;
    }

    public int getSede() {
        return sede;
    }

    public int getSujeira() {
        return sujeira;
    }

    public int getDivertimento() {
        return this.divertimento;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\n" +
                "Cansaço: " + this.cansaco + "\n" +
                "Fome: " + this.fome + "\n" +
                "Sede: " + this.sede + "\n" +
                "Sujeira: " + this.sujeira + "\n" +
                "Diversão: " + this.divertimento + "\n";
    }
}

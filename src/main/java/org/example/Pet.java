package org.example;

public class Pet {
    public static int geradorCodigo = 1;
    private int codigo;
    private String nome;
    private boolean vivo;
    private boolean acordado;
    private int sede;
    private int fome;
    private int energia;
    private int diversao;
    private int higiene;
    private int vontadeBanheiro;

    public Pet(String nome) {
        this.codigo = geradorCodigo++;
        this.nome = nome;
        this.vivo = true;
        this.acordado = true;
        this.sede = 100;
        this.fome = 100;
        this.energia = 100;
        this.diversao = 100;
        this.higiene = 100;
        this.vontadeBanheiro = 100;
    }

    public void beberAgua() {
        if (this.vivo) {
            this.sede += 50;
            if (this.sede > 100) {
                this.sede = 100;
            }
            this.vontadeBanheiro -= 25;
            if (this.vontadeBanheiro <= 0) {
                this.higiene = 0;
                this.vontadeBanheiro = 100;
            }
        }
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void comer(Alimento alimento) {
        if (this.vivo) {
            this.fome += alimento.getNutricao();
            if (this.fome > 100) {
                this.fome = 100;
            }
            this.vontadeBanheiro -= (alimento.getNutricao() / 2);
            if (this.vontadeBanheiro <= 0) {
                this.higiene = 0;
                this.vontadeBanheiro = 100;
            }
        }
    }

    public void dormir() {
        if (this.vivo) {
            this.energia += 25;
            if (this.energia > 100) {
                this.energia = 100;
            }
            this.acordado = false;
        }
    }

    public void acordar() {
        if (this.vivo) {
            this.energia += 25;
            if (this.energia > 100) {
                this.energia = 100;
            }
            this.acordado = true;
        }
    }

    public void brincar(Brincadeira brincadeira) {
        if (this.vivo) {
            this.diversao += brincadeira.getDivertimento();
            if (this.diversao >= 100) {
                this.diversao = 100;
            }
            this.energia -= brincadeira.getCansaco();
            this.fome -= brincadeira.getFome();
            this.sede -= brincadeira.getSede();
            if (this.energia <= 0) {
                this.energia = 0;
            }
            if (this.fome <= 0) {
                this.fome = 0;
            }
            if (this.sede <= 0) {
                this.sede = 0;
            }
            morrer();
            this.higiene -= brincadeira.getSujeira();
        }
    }

    public void limpar() {
        if (this.vivo) {
            this.higiene = 100;
        }
    }

    public void fazerNecessidades() {
        if (this.vivo) {
            this.vontadeBanheiro = 100;
            this.higiene -= 25;
            if (this.higiene < 0) {
                this.higiene = 0;
            }
        }
    }

    public void morrer() {
        if (this.fome == 0 || this.sede == 0 || this.energia == 0) {
            this.vivo = false;
        }
    }

    public String getVivo() {
        if (this.vivo) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    public String getAcordado() {
        if (this.acordado) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\n" +
                "Vivo: " + this.getVivo() + "\n" +
                "Acordado: " + this.getAcordado() + "\n" +
                "Sede: " + this.sede + "\n" +
                "Fome: " + this.fome + "\n" +
                "Energia: " + this.energia + "\n" +
                "Diversão: " + this.diversao + "\n" +
                "Higiene: " + this.higiene + "\n" +
                "Vontade de ir ao banheiro: " + this.vontadeBanheiro + "\n";
    }
}

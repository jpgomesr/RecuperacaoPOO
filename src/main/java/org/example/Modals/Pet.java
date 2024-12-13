package org.example.Modals;

public class Pet {
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

    public Pet() {}

    public Pet(String nome) {
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

    public Pet(int codigo, String nome, boolean vivo, boolean acordado, int sede, int fome, int energia,
               int diversao, int higiene, int vontadeBanheiro) {
        this.codigo = codigo;
        this.nome = nome;
        this.vivo = vivo;
        this.acordado = acordado;
        this.sede = sede;
        this.fome = fome;
        this.energia = energia;
        this.diversao = diversao;
        this.higiene = higiene;
        this.vontadeBanheiro = vontadeBanheiro;
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

    public String getVivoString() {
        if (this.vivo) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    public String getAcordadoString() {
        if (this.acordado) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    public String getNome() {
        return nome;
    }

    public boolean isVivo() {
        return vivo;
    }

    public boolean isAcordado() {
        return acordado;
    }

    public int getSede() {
        return sede;
    }

    public int getFome() {
        return fome;
    }

    public int getEnergia() {
        return energia;
    }

    public int getDiversao() {
        return diversao;
    }

    public int getHigiene() {
        return higiene;
    }

    public int getVontadeBanheiro() {
        return vontadeBanheiro;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setAcordado(boolean acordado) {
        this.acordado = acordado;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }

    public void setFome(int fome) {
        this.fome = fome;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setDiversao(int diversao) {
        this.diversao = diversao;
    }

    public void setHigiene(int higiene) {
        this.higiene = higiene;
    }

    public void setVontadeBanheiro(int vontadeBanheiro) {
        this.vontadeBanheiro = vontadeBanheiro;
    }

    public boolean getVivo() {
        return vivo;
    }

    public boolean getAcordado() {
        return acordado;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\n" +
                "Vivo: " + this.getVivoString() + "\n" +
                "Acordado: " + this.getAcordadoString() + "\n" +
                "Sede: " + this.sede + "\n" +
                "Fome: " + this.fome + "\n" +
                "Energia: " + this.energia + "\n" +
                "Diversão: " + this.diversao + "\n" +
                "Higiene: " + this.higiene + "\n" +
                "Vontade de ir ao banheiro: " + this.vontadeBanheiro + "\n";
    }
}

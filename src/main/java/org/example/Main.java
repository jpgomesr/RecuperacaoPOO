package org.example;

import org.example.Modals.Alimento;
import org.example.Modals.Pessoa;
import org.example.Modals.Pet;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Pessoa usuario = new Pessoa();
    static boolean logado = false;

    public static void main(String[] args) {
        while (true) {
            if (!logado) {
                menuLogin();
            } else {
                menuInicial();
            }
        }
    }

    // INICIO DO FIM
    public static void menuLogin() {
        System.out.print("""
                INICIAR

                1 - Login
                2 - Cadastrar
                0 - Sair

                >\t""");
        opcaoLogin(sc.nextInt());
    }

    public static void opcaoLogin(int opcao) {
        switch (opcao) {
            case 1: {
                boolean acao = false;
                while (!acao) {
                    acao = acaoLogin();
                }
            }
            case 2: {
                boolean acao = false;
                while (!acao) {
                    acao = acaoCadastro();
                }
            }
            case 0: {
                System.out.println("Até mais!");
                System.exit(0);
            }
            default: {
                System.out.println("Opção inválida!");
            }
        }
    }

    public static boolean acaoLogin() {
        System.out.print("Digite seu CPF: ");
        long cpf = sc.nextLong();
        System.out.print("Digite sua senha: ");
        String senha = sc.next();
        usuario = Banco.login(cpf, senha);
        if (usuario != null) {
            System.out.println("Logado com sucesso!");
            logado = true;
            return true;
        }
        System.err.println("Credenciais incorretas!");
        System.out.print("""
                Deseja tentar novamente?

                1 - Sim
                2 - Não

                >\t""");
        return sc.nextInt() != 1;
    }

    public static boolean acaoCadastro() {
        System.out.print("Digite seu CPF: ");
        long cpf = sc.nextLong();
        if (Banco.procurarPessoa(cpf) == null) {
            System.out.print("Digite seu nome: ");
            String nome = sc.next();
            System.out.print("Digite sua senha: ");
            String senha = sc.next();
            usuario = new Pessoa(cpf, nome, senha);
            Banco.salvarPessoa(usuario);
            logado = true;
            return true;
        } else {
            System.err.println("CPF já cadastrado no sistema!");
            return false;
        }
    }

    public static void menuInicial() {
        System.out.print("""
                INICIAL

                1 - Pets
                2 - Alimentos
                3 - Brincadeiras
                0 - Voltar

                >\t""");
        opcaoInicial(sc.nextInt());
    }

    public static void opcaoInicial(int opcao) {
        switch (opcao) {
            case 1: {
                menuPets();
                break;
            }
            case 2: {
                menuAlimentos();
                break;
            }
        }
    }

    public static void menuPets() {
        System.out.print("""
                PETS

                1 - Cadastrar/Atualizar
                2 - Listar
                3 - Remover
                0 - Voltar

                >\t""");
        opcaoPets(sc.nextInt());
    }

    public static void opcaoPets(int opcao) {
        switch (opcao) {
            case 1: {
                Banco.salvarPet(acaoCriarAtualizarPet());
                break;
            }
            case 2: {
                System.out.println(Banco.procurarPets());
                break;
            }
            case 3: {
                System.out.println(Banco.procurarPets());
                System.out.print("Digite o código do pet que deseja remover: ");
                Banco.removerPet(Banco.procurarPet(sc.nextInt()));
                break;
            }
            case 0: {
                System.out.println("Até mais!");
                break;
            }
            default: {
                System.out.println("Opção inválida!");
                break;
            }
        }
    }

    public static Pet acaoCriarAtualizarPet() {
        System.out.print("Digite o nome do pet: ");
        String nomeAntigo = sc.next();
        if (Banco.procurarPet(nomeAntigo) == null) {
            Banco.salvarPet(new Pet(nomeAntigo));
        }
        System.out.println("Digite o novo nome do seu pet: ");
        String nomeNovo = sc.next();
        Pet pet = Banco.procurarPet(nomeAntigo);
        pet.setNome(nomeNovo);
        return Banco.salvarPet(pet);
    }

    public static void menuAlimentos() {
        System.out.print("""
                ALIMENTOS

                1 - Cadastrar/Atualizar
                2 - Listar
                3 - Remover
                0 - Voltar

                >\t""");
        opcaoAlimentos(sc.nextInt());
    }

    public static void opcaoAlimentos(int opcao) {
        switch (opcao) {
            case 1: {
                Banco.salvarAlimento(acaoCriarAtualizarAlimento());
                break;
            }
            case 2: {
                Banco.procurarAlimentos();
                break;
            }
            case 3: {
                System.out.println(Banco.procurarAlimentos());
                System.out.print("Digite o código do alimento que deseja remover: ");
                Banco.removerAlimento(Banco.procurarAlimento(sc.nextInt()));
                break;
            }
            case 0: {
                System.out.println("Até mais!");
                break;
            }
            default: {
                System.out.println("Opção inválida!");
                break;
            }
        }
    }

    public static Alimento acaoCriarAtualizarAlimento() {
        System.out.print("Digite o nome do alimento: ");
        String nome = sc.next();
        Alimento alimento = Banco.procurarAlimento(nome);
        int nutricao = Integer.MAX_VALUE;
        if (alimento == null) {
            do {
                System.out.print("Digite a nutrição fornecida por esse alimento (0-100): ");
                nutricao = sc.nextInt();
            } while (nutricao > 100 && nutricao < 0);
        }
        alimento.setNutricao(nutricao);
        if (alimento != null) {
            System.out.print("Digite o novo nome do alimento: ");
            alimento.setNome(sc.next());
            System.out.println("Valor nutritivo atual: " + alimento.getNutricao());
            do {
                System.out.print("Digite o novo valor nutritivo do alimento (0-100): ");
                alimento.setNutricao(sc.nextInt());
            } while (alimento.getNutricao() > 100 && alimento.getNutricao() < 0);
        }
        return Banco.salvarAlimento(alimento);
    }

    public static void menuBrincadeiras() {
        System.out.println("""
                BRINCADEIRAS
                """);
    }
}
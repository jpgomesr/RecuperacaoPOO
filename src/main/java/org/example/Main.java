package org.example;

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

            }
        }
    }

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
        String senha = sc.nextLine();
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
            String nome = sc.nextLine();
            System.out.print("Digite sua senha: ");
            String senha = sc.nextLine();
            usuario = new Pessoa(cpf, nome, senha);
            Banco.cadastrarPessoa(usuario);
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
            }
        }
    }

    public static void menuPets() {
        System.out.print("""
                PETS
                                
                1 - Cadastrar
                2 - Listar
                0 - Voltar
                                
                >\t""");
    }

    public static void opcaoPets(int opcao) {
        switch (opcao) {

        }
    }

    public static void menuAlimentos() {
        System.out.print("""
                ALIMENTOS
                                
                1 - Cadastrar
                2 - Listar
                0 - Voltar
                                
                >\t""");
    }

    public static void menuBrincadeiras() {
        System.out.println("""
                BRINCADEIRAS
                """);
    }
}
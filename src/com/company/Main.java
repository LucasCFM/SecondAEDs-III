package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        char opcao;

        ArquivoUtil.setDB_ids();

        printMenu();

        opcao = Entrada.getInstance().leChar();

        while( opcao != '0' ) {
            String result = null;
            try {
                result = executaMenu(opcao);
            } catch (Exception e) {
                System.out.println( e.getMessage() );
                printMenu();
            }
            System.out.println(result);

            printMenu();

            opcao = Entrada.getInstance().leChar();
        }

        System.out.println("Saindo do sistema");

    }

    public static String executaMenu(char opcao) throws Exception {
        switch (opcao){
            case '1':
                UsuarioDAO u = new UsuarioDAO();
                return u.novoUsuario();
            default:
                throw new Exception("Opcao nao implementada");
        }

    }

    public static void printMenu() {
        System.out.println(""
            + "0 - Sair \n"
            + "1 - Criar usuario \n"
            + "2 - Logar \n"
            + "");
    }

//    public static void deletaUsuario(short id){
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        usuarioDAO.setAsDeletedUsuarioById(id);
//    }
}

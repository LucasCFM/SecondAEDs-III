package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UsuarioDAO {

//    public UsuarioDTO getUsuarioById(short id) {
//        return DB.getUsuarioById(id);
//    }
//
//    public UsuarioDTO getUsuarioByEmail(String email) {
//        return DB.getUsuarioByEmail(email);
//    }
//
//    public UsuarioDTO getUsuarioByCpf(String cpf) {
//        return DB.getUsuarioByCpf(cpf);
//    }
//
//    public void insertUsuario(UsuarioDTO usuario) {
//        DB.insert( usuario.toByteArray() );
//    }
//
    public String updateUsuario(UsuarioDTO usuario) {
        UsuarioDTO u = getUsuarioFromUser();


        return "Usuario atualizado com sucesso;";
    }

    public UsuarioDTO getUsuarioFromUser() throws Exception {
        System.out.println("Digite o email do usuario: ");
        String email = Entrada.getInstance().leString();

        System.out.println("Digite a senha do usuario: ");
        String senha = Entrada.getInstance().leString();

        return getUsuario(email, senha);

    }

    public UsuarioDTO getUsuarioByEmail(String email) throws Exception {
        email = email.trim();
        int index = 2;
        String arqEmail = "";
        byte[] b = new byte[];
        UsuarioDTO u = new UsuarioDTO();

        b = DB.getInstance().getByteArray( index+2, 40, u.getEntidadeNome());
        arqEmail = b.toString().trim();

        while ( !arqEmail.equals(email) ) {
            index += 16;
            index += 2 + DB.getInstance().getShort();
            index += 37;

            b = DB.getInstance().getByteArray( index, 40, u.getEntidadeNome());

            if ( b.length <= 0 ) {
                throw new Exception("Email nao encontrado");
            }

            arqEmail = b.toString().trim();
        }

        index -= 2;

        return getUsuarioFromIndex(index, u.getEntidadeNome());
    }

    public UsuarioDTO getUsuarioFromIndex(int index, String entidadeNome) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] aux = new byte[];

        aux = DB.getInstance().getByteArray( index, 58, entidadeNome);
        index += 58;

        try {
            outputStream.write(aux);
        } catch (IOException e) {
            e.printStackTrace();
        }

        short nomeTam = DB.getInstance().getShort();
        aux = DB.getInstance().getByteArray( index, 35+nomeTam, entidadeNome);

        try {
            outputStream.write(aux);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsuarioDTO u = new UsuarioDTO( outputStream.toByteArray() );

        return u;
    }

//    public UsuarioDTO logar(String email, String senha) {
//        try {
//            UsuarioDTO u = this.getUsuario(email, senha);
//        } catch (Exception e) {
//            System.out.println( e.getMessage() );
//        }
////        Pegar senha e verificar se é igual, dps loga
//    }

//
//    public void setAsDeletedUsuarioById(short id) {
//        UsuarioDTO usuario = DB.getUsuarioById(id);
//
//        //usuario.setDeletado(true);
//
//        this.updateUsuario( usuario );
//    }

    public String novoUsuario(){
        UsuarioDTO usuario = new UsuarioDTO();

        System.out.println("Digite o nome do usuario:");
        usuario.setNome( Entrada.getInstance().leString() );

        System.out.println("Digite o email do usuario:");
        usuario.setEmail( Entrada.getInstance().leString() );

        String senha;
        String confSenha;
        do {
            System.out.println("Digite a senha do usuario:");
            senha = Entrada.getInstance().leString();

            System.out.println("Confirme a senha do usuario:");
            confSenha = Entrada.getInstance().leString();

        }while (! senha.equals(confSenha) );

        usuario.setSenha(senha);

        System.out.println("Digite o telefone do usuario:");
        usuario.setTelefone( Entrada.getInstance().leString() );

        System.out.println("Digite o rg do usuario:");
        usuario.setRg( Entrada.getInstance().leString() );

        System.out.println("Digite o cpf do usuario:");
        usuario.setCpf( Entrada.getInstance().leString() );

        usuario.setNovoId();
        DB.getInstance().escreveByteArray(usuario);

        return "Usuario cadastrado com sucesso. Email: " + usuario.getEmail();
    }

}

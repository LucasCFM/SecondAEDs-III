package com.company;

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
//    public void updateUsuario(UsuarioDTO usuario) {
//
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

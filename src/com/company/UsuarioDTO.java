package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

public class UsuarioDTO implements DTO {
    private static String ENTIDADE_NOME = "usuario";
    private static short DB_id = -1;

    private short id; // 2 bytes
    private String email;  // MAX 40 bytes
    private String senha; // MAX 16 bytes
    private short nomeTam;
    private String nome; // variavel
    private String telefone; // 11 bytes
    private String rg; // MAX 12 bytes
    private String cpf; // 11 bytes

    private boolean deletado; // 1 byte
    // create attrs for address

    public UsuarioDTO(short id, String email, String senha, String nome, String telefone, String rg, String cpf) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.rg = rg;
        this.cpf = cpf;

        this.nome = nome;
        this.nomeTam = (short) nome.length();
    }

    public UsuarioDTO() {
        this.id = -1;
        this.email = "";
        this.senha = "";
        this.telefone = "";
        this.rg = "";
        this.cpf = "";
    }

    public UsuarioDTO( byte[] usuarioByte ) {
        this.setAttrsByteArray( usuarioByte );
    }

    public void deleta(short id){
        int pos = 0;

            do{
                if(DB.getInstance().getShort(pos) == id) {
                    this.setDeletado(true);
                }else {
                    pos = pos + 58;
                    DB.getInstance().arquivoToPosicao(pos);
                    pos = pos + DB.getInstance().getShort(pos) + 34;
                    DB.getInstance().arquivoToPosicao(pos);
                }
            }while(DB.getInstance().getShort(pos) != id);
    }

    @Override
    public byte[] toByteArray() {
        byte[] deletadoByte = ArquivoUtil.booleanToByteArray(this.deletado);
        byte[] idByte = ArquivoUtil.shortToByteArray(this.id);

        byte[] emailByte = this.email.getBytes();
        byte[] senhaByte = this.senha.getBytes();

        byte[] nomeTamByte = ArquivoUtil.shortToByteArray(this.nomeTam);
        byte[] nomeByte = this.nome.getBytes();

        byte[] rgByte = this.rg.getBytes();
        byte[] cpfByte = this.cpf.getBytes();
        byte[] telefoneByte = this.telefone.getBytes();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            outputStream.write(deletadoByte);
            outputStream.write(idByte);

            outputStream.write(emailByte);
            outputStream.write(senhaByte);

            outputStream.write(nomeTamByte);
            outputStream.write(nomeByte);

            outputStream.write(rgByte);
            outputStream.write(cpfByte);
            outputStream.write(telefoneByte);

        }catch (Exception e) {
            System.err.println("Falha na concatenacao de arrays");
            System.err.println(e.getMessage());
        }

        return outputStream.toByteArray();
    }

    @Override
        public void setAttrsByteArray(byte[] usuarioByteArray) {
        byte[] idBA, emailBA, senhaBA, telefoneBA, rgBA, cpfBA;




        // split usuarioByteArray into atttrs arrays

//        this.setId( ArquivoUtil.byteArrayToShort(idBA) );
//        this.setEmail( ArquivoUtil.byteArrayToString(emailBA) );
//        this.setSenha( ArquivoUtil.byteArrayToString(senhaBA) );
//        this.setTelefone( ArquivoUtil.byteArrayToString(telefoneBA) );
//        this.setRg( ArquivoUtil.byteArrayToString(rgBA) );
//        this.setCpf( ArquivoUtil.byteArrayToString(cpfBA) );

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    @Override
    public void setDB_id() {
        byte[] idByte = new byte[2];
        idByte = DB.getInstance().getUltimoId(this.ENTIDADE_NOME, 2);

        this.DB_id = ArquivoUtil.byteArrayToShort(idByte);
    }

    public short getId() {
        return id;
    }
    public void setId(short id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.nomeTam = (short) nome.length();
    }
    public String getNome() { return this.nome; }

    public void setDeletado(boolean deletado){ this.deletado = deletado; }
    public Boolean getDeletado(){ return this.deletado; }

    @Override
    public String getEntidadeNome() { return this.ENTIDADE_NOME; }

    public void setNovoId() {
        this.DB_id++;

        this.setId(this.DB_id);

        byte[] novoDB_id = ArquivoUtil.shortToByteArray( this.id );

        DB.getInstance().atualizaID(this.ENTIDADE_NOME, novoDB_id);

    }

}

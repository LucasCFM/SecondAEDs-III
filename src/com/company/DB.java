package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DB {
    private String DB_PATH;
    private File f = new File("db_01.aeds");
    private RandomAccessFile arq = null;

    private static DB ourInstance = new DB();
    public static DB getInstance() {
        return ourInstance;
    }

    private DB() {
        try{
            this.f = new File( "db_01.aeds" );
//            this.DB_PATH = f.getParentFile().getAbsoluteFile().getParent();
//            System.out.println(DB_PATH);
//            this.f = new File( this.DB_PATH + "db_01.aeds" );

            this.arq = new RandomAccessFile(f, "rw");
        }catch(IOException e){
            //algo aqui
        }

    }

    public void insertByteArray(){

    }

    public short getShort(int pos){
        short valor = -1;
        try {
            valor = arq.readShort();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valor;
    }

    private void setEntidade(String entidade){
       String dbArquivo = "db_01-" + entidade + ".aeds";

       this.f = new File(dbArquivo);

        try{
            this.arq = new RandomAccessFile(f, "rw");
        }catch(IOException e){
            //algo aqui
        }
    }

    public void escreveByteArray(DTO registro){
        this.setEntidade(registro.getEntidadeNome());

        byte[] registroByteArray = registro.toByteArray();
        try{

            this.arq.seek(arq.length());
            this.arq.write(registroByteArray);

        }catch (IOException e){
            //algo aqui
        }
    }

    public RandomAccessFile getArq() {
        return arq;
    }

    public RandomAccessFile getArq(String entidade) {
        this.setEntidade(entidade);
        return arq;
    }

    public byte[] getUltimoId(String entidade, int id_tam) {
        this.setEntidade(entidade);

        byte[] idByte = new byte[id_tam];

        try {
            this.arq.read(idByte);
        }catch (IOException e) {
            System.err.println("Falha na leitura do id ( " + entidade + ")");
            System.err.println(e.getMessage());
        }

        return idByte;
    }

    public void atualizaID(String entidadeNome, byte[] id) {
        try {
            this.setEntidade( entidadeNome );
            this.arq.seek(0);
            arq.write(id);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println( e.getMessage() );
        }
    }

//    public byte[] getUsuarioById(short id){
//
//    }

    public void arquivoToPosicao(int pos){
        try {
            this.arq.seek(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UsuarioDTO getUsuarioByEmail(String email){
        UsuarioDTO usuario = new UsuarioDTO();
        return usuario;
    }

    public static UsuarioDTO getUsuarioByCpf(String cpf){
        UsuarioDTO usuario = new UsuarioDTO();
        return usuario;
    }

}

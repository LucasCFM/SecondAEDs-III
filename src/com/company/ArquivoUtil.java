package com.company;

import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    public static byte[] shortToByteArray(short num) {
        byte[] result = new byte[2];

        result[1] = (byte) ((num >> 8) & 0xFF);
        result[0] = (byte) (num  & 0xFF);

        return result;
    }

    public static short byteArrayToShort(byte[] numByte) {
        short num = 0;

        num = (short) ((numByte[0] << 8) & num);
        num = (short) ((numByte[1]) | num);

        return num;
    }

    public static byte[] booleanToByteArray(boolean b) {
        byte [] byteArray = new byte[1];
        if ( b )
            byteArray[0] = 1;
        else
            byteArray[0] = 0;

        return byteArray;
    }

    public static void setDB_ids(){
        List<DTO> lista_entidades = getListaEntidades();

        for (DTO entidade : lista_entidades)
            entidade.setDB_id();

    }

    public static List getListaEntidades(){
        List<DTO> list = new ArrayList();

        UsuarioDTO u = new UsuarioDTO(); list.add(u);
//        UsuarioDTO u = new UsuarioDTO(); list.add(u);

        return list;
    }

}

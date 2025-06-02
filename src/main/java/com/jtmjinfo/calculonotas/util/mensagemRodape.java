package com.jtmjinfo.calculonotas.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class mensagemRodape {

    public static String mostrarNome(){
        var formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
        var dataHora = (LocalDateTime.now());

        String data = formatador.format(dataHora);
        String mensagem = "@ CopyRight JTMJ INFO";
        return  mensagem + "    " + data;
    }

}

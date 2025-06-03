package com.jtmjinfo.calculonotas.util;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import org.controlsfx.control.Notifications;

public class Alerta {

    public static void emitirSucesso(){

        Notifications.create()
                .position(Pos.CENTER)
                .darkStyle()
                .text("O aluno(a) foi salvo com sucesso!!!")
                .title("Sucesso")
                .showInformation();
    }
    public static void emitirAtencao(){
        Notifications.create()
                .text("O campo nome não deve ser vazio!!!")
                .darkStyle()
                .title("Atenção")
                .position(Pos.CENTER)
                .showWarning();
    }
    public static void emitirAlertaCamposNumericos(){
        Notifications.create()
                .position(Pos.CENTER)
                .text("O campo deve conter somente números reais!!!")
                .darkStyle()
                .title("Atenção")
                .showWarning();
    }
    public static void emitirAlertaSelecao(){
        Notifications.create()
                .title("Atenção")
                .text("Selecione um aluno(a) para continuar!!!")
                .darkStyle()
                .position(Pos.CENTER)
                .showWarning();
    }
    public static void emitirSucessoExclusao(){
        Notifications.create()
                .title("Sucesso")
                .text("Aluno(a) excluido(a) com sucesso!!!")
                .darkStyle()
                .position(Pos.CENTER)
                .showInformation();
    }
}

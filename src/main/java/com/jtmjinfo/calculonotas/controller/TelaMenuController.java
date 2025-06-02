package com.jtmjinfo.calculonotas.controller;

import com.jtmjinfo.calculonotas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.jtmjinfo.calculonotas.util.mensagemRodape.mostrarNome;

public class TelaMenuController implements Initializable {

    @FXML
    private Button btn_aluno;

    @FXML
    private Button btn_nota;

    @FXML
    private Button btn_relatorio;

    @FXML
    private Label lbl_rodape;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_rodape.setText(mostrarNome());
    }

    @FXML
    void emitirRelatorio(ActionEvent event) throws IOException {
        App.setRoot("views/alunos.fxml");
    }

    @FXML
    void cadastrarNota(ActionEvent event) throws IOException {
        App.setRoot("views/notas.fxml");
    }

    @FXML
    void sairSistema(ActionEvent event) throws IOException {
        System.exit(0);
    }


}

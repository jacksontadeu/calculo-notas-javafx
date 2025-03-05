package com.jtmjinfo.calculonotas.controller;

import com.jtmjinfo.calculonotas.model.Aluno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class NotasController implements Initializable {


    @FXML
    private Button bt_calcularMedia;

    @FXML
    private TextField tf_media;

    @FXML
    private TextField tf_mediaFinal;

    @FXML
    private TextField tf_nomeAluno;

    @FXML
    private TextField tf_notaP1;

    @FXML
    private TextField tf_notaSub;

    @FXML
    private TextField tf_api;


    @FXML
    private TextField tf_notaTrabalho1;

    @FXML
    private TextField tf_notaTrabalho2;

    @FXML
    private TextField tf_pontosExtras;

    @FXML
    private TableColumn<?, ?> tv_api;
    @FXML
    private Label lb_api;


    @FXML
    private TableColumn<?, ?> tv_e1;

    @FXML
    private TableColumn<?, ?> tv_e2;

    @FXML
    private TableColumn<?, ?> tv_extras;

    @FXML
    private TableColumn<?, ?> tv_id;

    @FXML
    private TableColumn<?, ?> tv_mediaFinal;

    @FXML
    private TableColumn<?, ?> tv_nome;

    @FXML
    private TableColumn<?, ?> tv_p1;

    @FXML
    private TableColumn<?, ?> tv_sub;


    Aluno aluno = new Aluno();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void calcular(KeyEvent event) {

        aluno.setNome(tf_nomeAluno.getText().toString());
        aluno.setP1(Double.valueOf(tf_notaP1.getText().toString()));
        aluno.setTrabalho1(Double.valueOf(tf_notaTrabalho1.getText().toString()));
        aluno.setTrabalho2(Double.valueOf(tf_notaTrabalho2.getText().toString()));
        aluno.setMedia(aluno.calcularMedia());

        if (aluno.getMedia() * 2 >= 6){
            aluno.setApi(Double.valueOf(tf_api.getText().toString()));
            aluno.setMediaFinal(aluno.getMedia() + (aluno.getApi() * 0.5));
            this.tf_mediaFinal.setText(String.valueOf(aluno.getMediaFinal()));
           

        }
    }


    @FXML
    void mostrarMediaFinal(KeyEvent event) {
        this.tf_mediaFinal.setText(String.valueOf(aluno.getMediaFinal()));
    }
}
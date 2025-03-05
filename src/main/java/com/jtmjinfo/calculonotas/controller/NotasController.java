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
    void calcularMedia(KeyEvent event) {

        aluno.setNome(tf_nomeAluno.getText().toString());
        aluno.setP1(Double.valueOf(tf_notaP1.getText().toString()));
        aluno.setTrabalho1(Double.valueOf(tf_notaTrabalho1.getText().toString()));
        aluno.setTrabalho2(Double.valueOf(tf_notaTrabalho2.getText().toString()));
        aluno.setMedia(aluno.calcularMedia());

        this.tf_media.setText(String.valueOf(aluno.getMedia()));
        if ((aluno.getMedia() * 2) >= 6) {
            this.tf_notaSub.setVisible(false);
        } else {
            this.tf_api.setVisible(false);
            this.tf_notaSub.setVisible(true);
        }
    }

    @FXML
    void calcularSub(KeyEvent event) {
        if(!this.tf_api.isVisible()){
            this.tf_api.setText("0");
        }else{
            aluno.setApi(Double.valueOf(tf_api.getText().toString()));
        }


        double media = aluno.getMedia() + (aluno.getApi() * 0.5);

        if (media >= 6) {
            this.tf_notaSub.setVisible(false);
            this.tf_notaSub.setText("0");
        } else if (media < 6) {
            this.tf_notaSub.setVisible(true);


        }


    }

    @FXML
    void calcularMediaFinal(KeyEvent event) {
        aluno.setPontosExtras(Double.valueOf(this.tf_pontosExtras.getText().toString()));
        if (this.tf_notaSub.isVisible()){
            aluno.setSub(Double.valueOf(this.tf_notaSub.getText().toString()));
            aluno.setMediaFinal(aluno.calcularMediaFinal());

            this.tf_mediaFinal.setText(String.valueOf(aluno.getMediaFinal()));

        }else{
            aluno.setMediaFinal(aluno.calcularMediaFinal());

            this.tf_mediaFinal.setText(String.valueOf(aluno.getMediaFinal()));

        }





    }


}
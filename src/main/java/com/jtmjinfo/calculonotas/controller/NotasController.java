package com.jtmjinfo.calculonotas.controller;

import com.jtmjinfo.calculonotas.dao.AlunoDao;
import com.jtmjinfo.calculonotas.model.Aluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotasController implements Initializable {


    @FXML
    private Button bt_editar;

    @FXML
    private Button bt_excluir;

    @FXML
    private Button bt_salvar;

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
    private TableColumn tc_api;

    @FXML
    private Label lb_api;

    @FXML
    private TableColumn tc_e1;

    @FXML
    private TableColumn tc_e2;

    @FXML
    private TableColumn tc_extras;

    @FXML
    private TableColumn tc_id;

    @FXML
    private TableColumn tc_mediaFinal;

    @FXML
    private TableColumn tc_nome;

    @FXML
    private TableColumn tc_p1;

    @FXML
    private TableColumn tc_sub;


    @FXML
    private TableView tv_aluno;


    Aluno aluno = new Aluno();
    AlunoDao alunoDao = new AlunoDao();
    ObservableList<Aluno> alunoObservableList;
    List<Aluno> todosAlunos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ocultarBotoes();
        prepararTabela();

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
        if (!this.tf_api.isVisible()) {
            this.tf_api.setText("0");
        } else {
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
        if (this.tf_notaSub.isVisible()) {
            aluno.setSub(Double.valueOf(this.tf_notaSub.getText().toString()));
            aluno.setMediaFinal(aluno.calcularMediaFinal());

            this.tf_mediaFinal.setText(String.valueOf(aluno.getMediaFinal()));

        } else {
            aluno.setMediaFinal(aluno.calcularMediaFinal());

            this.tf_mediaFinal.setText(String.valueOf(aluno.getMediaFinal()));

        }
    }

    void ocultarBotoes() {
        this.bt_editar.setVisible(false);
        this.bt_excluir.setVisible(false);
    }

    @FXML
    public void salvarAluno(ActionEvent event) {

    }

    void prepararTabela() {

        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tc_p1.setCellValueFactory(new PropertyValueFactory<>("p1"));
        tc_e1.setCellValueFactory(new PropertyValueFactory<>("trabalho1"));
        tc_e2.setCellValueFactory(new PropertyValueFactory<>("trabalho2"));
        tc_mediaFinal.setCellValueFactory(new PropertyValueFactory<>("mediaFinal"));
        tc_api.setCellValueFactory(new PropertyValueFactory<>("api"));
        tc_extras.setCellValueFactory(new PropertyValueFactory<>("pontosExtras"));
        tc_sub.setCellValueFactory(new PropertyValueFactory<>("sub"));

        todosAlunos = alunoDao.buscarTodos();
        alunoObservableList = FXCollections.observableList(todosAlunos);

        this.tv_aluno.setItems(alunoObservableList);
    }


}
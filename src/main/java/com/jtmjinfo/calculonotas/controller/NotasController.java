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
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

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
    private TableColumn tc_media;

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
        validadorTextField(tf_api);
        validadorTextField(tf_notaTrabalho1);
        validadorTextField(tf_notaTrabalho2);
        validadorTextField(tf_notaSub);
        validadorTextField(tf_pontosExtras);
        validadorTextField(tf_notaP1);
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

        aluno.setNome(tf_nomeAluno.getText().toString());

        aluno.setP1(Double.parseDouble(tf_notaP1.getText()));
        aluno.setTrabalho1(Double.valueOf(tf_notaTrabalho1.getText().toString()));
        aluno.setTrabalho2(Double.valueOf(tf_notaTrabalho2.getText().toString()));
        aluno.setMedia(Double.valueOf(tf_media.getText().toString()));
        aluno.setApi(Double.valueOf(tf_api.getText().toString()));
        aluno.setPontosExtras(Double.valueOf(tf_pontosExtras.getText().toString()));
        aluno.setSub(Double.valueOf(tf_notaSub.getText().toString()));
        aluno.setMediaFinal(Double.valueOf(tf_mediaFinal.getText().toString()));

        alunoDao.cadastrarAluno(aluno);
        prepararTabela();
        limparCampos();
    }

    void prepararTabela() {
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tc_p1.setCellValueFactory(new PropertyValueFactory<>("p1"));
        tc_e1.setCellValueFactory(new PropertyValueFactory<>("trabalho1"));
        tc_e2.setCellValueFactory(new PropertyValueFactory<>("trabalho2"));
        tc_media.setCellValueFactory(new PropertyValueFactory<>("media"));
        tc_mediaFinal.setCellValueFactory(new PropertyValueFactory<>("mediaFinal"));
        tc_api.setCellValueFactory(new PropertyValueFactory<>("api"));
        tc_extras.setCellValueFactory(new PropertyValueFactory<>("pontosExtras"));
        tc_sub.setCellValueFactory(new PropertyValueFactory<>("sub"));

        todosAlunos = alunoDao.buscarTodos();
        alunoObservableList = FXCollections.observableList(todosAlunos);

        tv_aluno.setItems(alunoObservableList);
    }

    public void limparCampos() {
        tf_nomeAluno.setText("");
        tf_notaP1.setText("");
        tf_notaTrabalho1.setText("");
        tf_notaTrabalho2.setText("");
        tf_media.setText("");
        tf_api.setText("");
        tf_pontosExtras.setText("");
        tf_notaSub.setText("");
        tf_mediaFinal.setText("");
    }

    @FXML
    void mostrarCampos(MouseEvent event) {
        desabilitarEdicao();
        Aluno alunoTv = (Aluno) tv_aluno.getSelectionModel().getSelectedItem();
        tf_nomeAluno.setText(alunoTv.getNome());
        tf_notaP1.setText(String.valueOf(alunoTv.getP1()));
        tf_notaTrabalho1.setText(String.valueOf(alunoTv.getTrabalho1()));
        tf_notaTrabalho2.setText(String.valueOf(alunoTv.getTrabalho2()));
        tf_api.setText(String.valueOf(alunoTv.getApi()));
        tf_notaSub.setText(String.valueOf(alunoTv.getSub()));
        tf_pontosExtras.setText(String.valueOf(alunoTv.getPontosExtras()));
        tf_mediaFinal.setText(String.valueOf(alunoTv.getMediaFinal()));
        tf_media.setText(String.valueOf(alunoTv.getMedia()));

    }

    void validadorTextField(TextField field) {
        inicializarValores();
        field.textProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    try {
                        if (!newValue.isEmpty())
                            parseDouble(newValue);

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Erro");
                        alert.setContentText("O campo deve conter somente números reais!!!");
                        alert.show();
                        field.setText("0");
                    }
                })
        );

    }
    void inicializarValores(){
        tf_notaP1.setText("0");
        tf_notaTrabalho1.setText("0");
        tf_notaTrabalho2.setText("0");
        tf_api.setText("0");
        tf_notaSub.setText("0");
        tf_pontosExtras.setText("0");
    }
    void desabilitarEdicao(){
        tf_nomeAluno.setEditable(false);
        tf_notaP1.setEditable(false);
        tf_notaTrabalho1.setEditable(false);
        tf_notaTrabalho2.setEditable(false);
        tf_notaSub.setEditable(false);
        tf_api.setEditable(false);
        tf_pontosExtras.setEditable(false);
    }


}
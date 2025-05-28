package com.jtmjinfo.calculonotas.controller;

import com.jtmjinfo.calculonotas.dao.AlunoDao;
import com.jtmjinfo.calculonotas.model.Aluno;
import com.jtmjinfo.calculonotas.model.Nota;
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
    Nota nota = new Nota();
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

        nota.setP1(Double.valueOf(tf_notaP1.getText().toString()));
        nota.setTrabalho1(Double.valueOf(tf_notaTrabalho1.getText().toString()));
       nota.setTrabalho2(Double.valueOf(tf_notaTrabalho2.getText().toString()));

        nota.setMedia(nota.calcularMedia());

        this.tf_media.setText(String.valueOf(nota.getMedia()));
        if ((aluno.getNota().getMedia() * 2) >= 6) {
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
            aluno.getNota().setApi(Double.valueOf(tf_api.getText().toString()));
        }
        double media = aluno.getNota().getMedia() + (aluno.getNota().getApi() * 0.5);
        if (media >= 6) {
            this.tf_notaSub.setVisible(false);
            this.tf_notaSub.setText("0");
        } else if (media < 6) {
            this.tf_notaSub.setVisible(true);
        }
    }

    @FXML
    void calcularMediaFinal(KeyEvent event) {
        aluno.getNota().setPontosExtras(Double.valueOf(this.tf_pontosExtras.getText().toString()));
        if (this.tf_notaSub.isVisible()) {
            aluno.getNota().setSub(Double.valueOf(this.tf_notaSub.getText().toString()));
            aluno.getNota().setMediaFinal(aluno.getNota().calcularMediaFinal());

            this.tf_mediaFinal.setText(String.valueOf(aluno.getNota().getMediaFinal()));

        } else {
            aluno.getNota().setMediaFinal(aluno.getNota().calcularMediaFinal());

            this.tf_mediaFinal.setText(String.valueOf(aluno.getNota().getMediaFinal()));

        }
    }

    void ocultarBotoes() {
        this.bt_editar.setVisible(false);
        this.bt_excluir.setVisible(false);
        this.bt_salvar.setVisible(true);
    }

    void mmostrarBotoes() {
        this.bt_editar.setVisible(true);
        this.bt_excluir.setVisible(true);
        this.bt_salvar.setVisible(false);

    }

    @FXML
    public void salvarAluno(ActionEvent event) {
        if (validarNome(tf_nomeAluno) == true) {
            aluno.setNome(tf_nomeAluno.getText().toString());
            aluno.getNota().setP1(Double.parseDouble(tf_notaP1.getText()));
            aluno.getNota().setTrabalho1(Double.valueOf(tf_notaTrabalho1.getText().toString()));
            aluno.getNota().setTrabalho2(Double.valueOf(tf_notaTrabalho2.getText().toString()));
            aluno.getNota().setMedia(Double.valueOf(tf_media.getText().toString()));
            aluno.getNota().setApi(Double.valueOf(tf_api.getText().toString()));
            aluno.getNota().setPontosExtras(Double.valueOf(tf_pontosExtras.getText().toString()));
            aluno.getNota().setSub(Double.valueOf(tf_notaSub.getText().toString()));
            aluno.getNota().setMediaFinal(Double.valueOf(tf_mediaFinal.getText().toString()));

            alunoDao.cadastrarAluno(aluno);
            prepararTabela();
            limparCampos();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setContentText("O campo nome não deve ser vazio!!!");
            alert.show();
            tf_nomeAluno.requestFocus();
            return;
        }

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
    public void deletarAluno(ActionEvent event) {
        Aluno alunoTv = (Aluno) tv_aluno.getSelectionModel().getSelectedItem();

        alunoDao.deletarAluno(alunoTv.getId());
        prepararTabela();
        limparCampos();
        ocultarBotoes();
        habilitarEdicao();
        inicializarValores();

    }

    @FXML
    void mostrarCampos(MouseEvent event) {
        desabilitarEdicao();
        Aluno alunoTv = (Aluno) tv_aluno.getSelectionModel().getSelectedItem();
        tf_nomeAluno.setText(alunoTv.getNome());
        tf_notaP1.setText(String.valueOf(alunoTv.getNota().getP1()));
        tf_notaTrabalho1.setText(String.valueOf(alunoTv.getNota().getTrabalho1()));
        tf_notaTrabalho2.setText(String.valueOf(alunoTv.getNota().getTrabalho2()));
        tf_api.setText(String.valueOf(alunoTv.getNota().getApi()));
        tf_notaSub.setText(String.valueOf(alunoTv.getNota().getSub()));
        tf_pontosExtras.setText(String.valueOf(alunoTv.getNota().getPontosExtras()));
        tf_mediaFinal.setText(String.valueOf(alunoTv.getNota().getMediaFinal()));
        tf_media.setText(String.valueOf(alunoTv.getNota().getMedia()));
        mmostrarBotoes();

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

    void inicializarValores() {
        tf_notaP1.setText("0");
        tf_notaTrabalho1.setText("0");
        tf_notaTrabalho2.setText("0");
        tf_api.setText("0");
        tf_mediaFinal.setText("0");
        tf_media.setText("0");
        tf_notaSub.setText("0");
        tf_pontosExtras.setText("0");
    }

    void desabilitarEdicao() {
        tf_nomeAluno.setEditable(false);
        tf_notaP1.setEditable(false);
        tf_notaTrabalho1.setEditable(false);
        tf_notaTrabalho2.setEditable(false);
        tf_notaSub.setEditable(false);
        tf_api.setEditable(false);
        tf_pontosExtras.setEditable(false);
    }

    void habilitarEdicao() {
        tf_nomeAluno.setEditable(true);
        tf_notaP1.setEditable(true);
        tf_notaTrabalho1.setEditable(true);
        tf_notaTrabalho2.setEditable(true);
        tf_notaSub.setEditable(true);
        tf_api.setEditable(true);
        tf_pontosExtras.setEditable(true);
    }

    boolean validarNome(TextField tf) {
        if (tf.getText().isEmpty() || tf.getText().isBlank())
            return false;
        else return true;
    }


}
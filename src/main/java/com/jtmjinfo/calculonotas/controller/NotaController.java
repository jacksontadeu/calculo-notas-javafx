package com.jtmjinfo.calculonotas.controller;

import com.jtmjinfo.calculonotas.App;

import com.jtmjinfo.calculonotas.dao.NotaDao;

import com.jtmjinfo.calculonotas.model.Nota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.jtmjinfo.calculonotas.util.Alerta.*;
import static com.jtmjinfo.calculonotas.util.mensagemRodape.mostrarNome;
import static java.lang.Double.parseDouble;

public class NotaController implements Initializable {


    @FXML
    private Button btn_editar;

    @FXML
    private Button btn_excluir;

    @FXML
    private Button btn_salvar;

    @FXML
    private Button btn_voltar;

    @FXML
    private Button btn_salvaredicao;


    @FXML
    private Label lbl_rodape;

    @FXML
    private TableColumn<?, ?> tc_api;

    @FXML
    private TableColumn<?, ?> tc_e1;

    @FXML
    private TableColumn<?, ?> tc_e2;

    @FXML
    private TableColumn<?, ?> tc_extras;

    @FXML
    private TableColumn<?, ?> tc_media;

    @FXML
    private TableColumn<?, ?> tc_mediaFinal;

    @FXML
    private TableColumn<?, ?> tc_nome;

    @FXML
    private TableColumn<?, ?> tc_p1;

    @FXML
    private TableColumn<?, ?> tc_sub;

    @FXML
    private TableView tv_aluno;

    @FXML
    private TextField txt_api;

    @FXML
    private TextField txt_media;

    @FXML
    private TextField txt_mediafinal;

    @FXML
    private TextField txt_p1;

    @FXML
    private TextField txt_nome;

    @FXML
    private TextField txt_pontos;


    @FXML
    private TextField txt_sub;

    @FXML
    private TextField txt_trabalho1;

    @FXML
    private TextField txt_trabalho2;



    Nota nota = new Nota();
    NotaDao notaDao = new NotaDao();
    List<Nota> notas;
    ObservableList<Nota> notasObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ocultarBotoes();
        prepararTabela();
        validadorTextField(txt_api);
        validadorTextField(txt_trabalho1);
        validadorTextField(txt_trabalho2);
        validadorTextField(txt_sub);
        validadorTextField(txt_pontos);
        validadorTextField(txt_p1);
        lbl_rodape.setText(mostrarNome());
    }

    @FXML
    void calcularMedia(KeyEvent event) {
        nota.setP1(Double.valueOf(txt_p1.getText().toString()));
        nota.setTrabalho1(Double.valueOf(txt_trabalho1.getText().toString()));
        nota.setTrabalho2(Double.valueOf(txt_trabalho2.getText().toString()));
        nota.setMedia(nota.calcularMedia());
        this.txt_media.setText(String.valueOf(nota.getMedia()));
        if ((nota.getMedia() * 2) >= 6) {
            this.txt_sub.setVisible(false);
        } else {
            this.txt_api.setVisible(false);
            this.txt_sub.setVisible(true);
        }
    }

    @FXML
    void calcularSub(KeyEvent event) {
        if (!this.txt_api.isVisible()) {
            this.txt_api.setText("0");
        } else {
            nota.setApi(Double.valueOf(txt_api.getText().toString()));
        }
        double media = nota.getMedia() + (nota.getApi() * 0.5);
        if (media >= 6) {
            this.txt_sub.setVisible(false);
            this.txt_sub.setText("0");
        } else if (media < 6) {
            this.txt_sub.setVisible(true);
        }
    }

    @FXML
    void voltarMenu(ActionEvent event) throws IOException {
        App.setRoot("views/telamenu.fxml");
    }
    @FXML
    void editarNotas(ActionEvent event) {
        habilitarEdicao();
        btn_salvaredicao.setVisible(true);
        btn_editar.setVisible(false);
        btn_excluir.setVisible(false);
    }
    @FXML
    void salvarEdicao(ActionEvent event) throws IOException {
        if (validarNome(txt_nome) == true) {
            Nota notaTv = (Nota) tv_aluno.getSelectionModel().getSelectedItem();
            nota.setP1(Double.parseDouble(txt_p1.getText()));
            nota.setTrabalho1(Double.valueOf(txt_trabalho1.getText().toString()));
            nota.setTrabalho2(Double.valueOf(txt_trabalho2.getText().toString()));
            nota.setMedia(Double.valueOf(txt_media.getText().toString()));
            nota.setApi(Double.valueOf(txt_api.getText().toString()));
            nota.setPontosExtras(Double.valueOf(txt_pontos.getText().toString()));
            nota.setSub(Double.valueOf(txt_sub.getText().toString()));
            nota.setMediaFinal(Double.valueOf(txt_mediafinal.getText().toString()));
            nota.setNome(txt_nome.getText());
            notaDao.editarNota(nota, notaTv.getId());
            emitirSucesso();
            App.setRoot("views/notas.fxml");

        } else {
            emitirAtencao();
            txt_nome.requestFocus();
            return;
        }
    }

    @FXML
    void calcularMediaFinal(KeyEvent event) {
        nota.setPontosExtras(Double.valueOf(this.txt_pontos.getText().toString()));
        if (this.txt_sub.isVisible()) {
            nota.setSub(Double.valueOf(this.txt_sub.getText().toString()));
            nota.setMediaFinal(nota.calcularMediaFinal());
            this.txt_mediafinal.setText(String.valueOf(nota.getMediaFinal()));
        } else {
            nota.setMediaFinal(nota.calcularMediaFinal());
            this.txt_mediafinal.setText(String.valueOf(nota.getMediaFinal()));
        }
    }

    void ocultarBotoes() {
        this.btn_editar.setVisible(false);
        this.btn_excluir.setVisible(false);
        this.btn_salvar.setVisible(true);
        this.btn_salvaredicao.setVisible(false);
    }

    void mostrarBotoes() {
        this.btn_editar.setVisible(true);
        this.btn_excluir.setVisible(true);
        this.btn_salvar.setVisible(false);
    }

    @FXML
    public void salvarNotas(ActionEvent event) throws IOException {
        if (validarNome(txt_nome) == true) {
            nota.setP1(Double.parseDouble(txt_p1.getText()));
            nota.setTrabalho1(Double.valueOf(txt_trabalho1.getText().toString()));
            nota.setTrabalho2(Double.valueOf(txt_trabalho2.getText().toString()));
            nota.setMedia(Double.valueOf(txt_media.getText().toString()));
            nota.setApi(Double.valueOf(txt_api.getText().toString()));
            nota.setPontosExtras(Double.valueOf(txt_pontos.getText().toString()));
            nota.setSub(Double.valueOf(txt_sub.getText().toString()));
            nota.setMediaFinal(Double.valueOf(txt_mediafinal.getText().toString()));
            nota.setNome(txt_nome.getText());
            notaDao.cadastrarNota(nota);
            emitirSucesso();
            App.setRoot("views/notas.fxml");
        } else {
            emitirAtencao();
            txt_nome.requestFocus();
            return;
        }
    }

    void prepararTabela() {
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tc_p1.setCellValueFactory(new PropertyValueFactory<>("p1"));
        tc_e1.setCellValueFactory(new PropertyValueFactory<>("trabalho1"));
        tc_e2.setCellValueFactory(new PropertyValueFactory<>("trabalho2"));
        tc_media.setCellValueFactory(new PropertyValueFactory<>("media"));
        tc_mediaFinal.setCellValueFactory(new PropertyValueFactory<>("mediaFinal"));
        tc_api.setCellValueFactory(new PropertyValueFactory<>("api"));
        tc_extras.setCellValueFactory(new PropertyValueFactory<>("pontosExtras"));
        tc_sub.setCellValueFactory(new PropertyValueFactory<>("sub"));
        notas = notaDao.buscarTodos();
        notasObservableList = FXCollections.observableList(notas);
        tv_aluno.setItems(notasObservableList);
    }

    @FXML
    public void excluirNotas(ActionEvent event) throws IOException {
       Nota notaTv = (Nota) tv_aluno.getSelectionModel().getSelectedItem();
       notaDao.deletarNota(notaTv.getId());
        emitirSucessoExclusao();
        App.setRoot("views/notas.fxml");
    }

    @FXML
    void mostrarCampos(MouseEvent event) {
        desabilitarEdicao();
        Nota notaTv = (Nota) tv_aluno.getSelectionModel().getSelectedItem();
        txt_nome.setText(String.valueOf(notaTv.getNome()));
        txt_p1.setText(String.valueOf(notaTv.getP1()));
        txt_trabalho1.setText(String.valueOf(notaTv.getTrabalho1()));
        txt_trabalho2.setText(String.valueOf(notaTv.getTrabalho2()));
        txt_api.setText(String.valueOf(notaTv.getApi()));
        txt_sub.setText(String.valueOf(notaTv.getSub()));
        txt_pontos.setText(String.valueOf(notaTv.getPontosExtras()));
        txt_mediafinal.setText(String.valueOf(notaTv.getMediaFinal()));
        txt_media.setText(String.valueOf(notaTv.getMedia()));
        mostrarBotoes();

    }

    void validadorTextField(TextField field) {
        inicializarValores();
        field.textProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    try {
                        if (!newValue.isEmpty())
                            parseDouble(newValue);

                    } catch (Exception e) {
                        emitirAlertaCamposNumericos();
                        field.setText("0");
                    }
                })
        );
    }
    void inicializarValores() {
        txt_nome.setText("");
        txt_p1.setText("0");
        txt_trabalho1.setText("0");
        txt_trabalho2.setText("0");
        txt_api.setText("0");
        txt_mediafinal.setText("0");
        txt_media.setText("0");
        txt_sub.setText("0");
        txt_pontos.setText("0");
    }

    void desabilitarEdicao() {
        txt_nome.setEditable(false);
        txt_p1.setEditable(false);
        txt_trabalho1.setEditable(false);
        txt_trabalho2.setEditable(false);
        txt_sub.setEditable(false);
        txt_api.setEditable(false);
        txt_pontos.setEditable(false);
    }

    void habilitarEdicao() {
        txt_nome.setEditable(true);
        txt_p1.setEditable(true);
        txt_trabalho1.setEditable(true);
        txt_trabalho2.setEditable(true);
        txt_sub.setEditable(true);
        txt_api.setEditable(true);
        txt_pontos.setEditable(true);
    }

    boolean validarNome(TextField txt) {
        if (txt.getText().isEmpty())
            return false;
        else return true;
    }
}
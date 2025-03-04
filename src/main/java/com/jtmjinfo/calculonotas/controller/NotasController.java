package com.jtmjinfo.calculonotas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class NotasController {
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
    private TextField tf_notaTrabalho1;

    @FXML
    private TextField tf_notaTrabalho2;

    @FXML
    private TextField tf_pontosExtras;

    @FXML
    private TableColumn<?, ?> tv_api;

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
}

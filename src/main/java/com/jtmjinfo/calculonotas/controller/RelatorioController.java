package com.jtmjinfo.calculonotas.controller;

import com.jtmjinfo.calculonotas.App;
import com.jtmjinfo.calculonotas.dao.NotaDao;
import com.jtmjinfo.calculonotas.model.Nota;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.jtmjinfo.calculonotas.util.Alerta.emitirAlertaSelecao;
import static com.jtmjinfo.calculonotas.util.mensagemRodape.mostrarNome;

public class RelatorioController implements Initializable {

    @FXML
    private Button btn_aluno;

    @FXML
    private Button btn_geral;

    @FXML
    private Button btn_voltar;

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


    Nota nota = new Nota();
    NotaDao notaDao = new NotaDao();
    List<Nota> notas;
    ObservableList<Nota> notasObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepararTabela();
        lbl_rodape.setText(mostrarNome());
    }

    @FXML
    void emitirAluno(ActionEvent event) throws IOException {
        Nota notaTv = (Nota) tv_aluno.getSelectionModel().getSelectedItem();
        if (notaTv == null) {
            emitirAlertaSelecao();

        } else {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(notaTv.getNome() +".pdf"));
            documento.open();
            documento.setPageSize(PageSize.A4);
            documento.setMargins(0, 0, 3, 3);
            Font fonte = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
            Font fonteCabecalho = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(0, 0, 0));
            Font titulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
            Table table = new Table(9);
            table.setWidths(new float[]{25, 10, 10, 10, 10, 10, 10, 10, 20});
            table.setWidth(100);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Paragraph p = new Paragraph("Relatório de Aluno LP I", titulo);
            p.setAlignment("center");
            documento.add(p);
            Cell nomeAluno = new Cell(new Phrase("Nome do Aluno", fonteCabecalho));
            nomeAluno.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell mediaFinal = new Cell(new Phrase("Média Final", fonteCabecalho));
            mediaFinal.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell p1 = new Cell(new Phrase("P1", fonteCabecalho));
            p1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell trabalho1 = new Cell(new Phrase("E1", fonteCabecalho));
            trabalho1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell trabalho2 = new Cell(new Phrase("E2", fonteCabecalho));
            trabalho2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell media = new Cell(new Phrase("Média", fonteCabecalho));
            media.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell api = new Cell(new Phrase("API", fonteCabecalho));
            api.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell sub = new Cell(new Phrase("SUB", fonteCabecalho));
            sub.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell pontosExtras = new Cell(new Phrase("Pontos", fonteCabecalho));
            pontosExtras.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(nomeAluno);
            table.addCell(p1);
            table.addCell(trabalho1);
            table.addCell(trabalho2);
            table.addCell(media);
            table.addCell(api);
            table.addCell(sub);
            table.addCell(pontosExtras);
            table.addCell(mediaFinal);
            table.setBackgroundColor(ColorUIResource.getHSBColor(255, 200, 50));
            nota = notaDao.buscarPorId(notaTv.getId());
            Cell aluno = new Cell(new Phrase(nota.getNome(), fonte));
            aluno.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell notaP1 = new Cell(new Phrase(String.valueOf(nota.getP1()), fonte));
            notaP1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell e1 = new Cell(new Phrase(String.valueOf(nota.getTrabalho1()), fonte));
            e1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell e2 = new Cell(new Phrase(String.valueOf(nota.getTrabalho2()), fonte));
            e2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell notaMedia = new Cell(new Phrase(String.valueOf(nota.getMedia()), fonte));
            notaMedia.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell notaApi = new Cell(new Phrase(String.valueOf(nota.getApi()), fonte));
            notaApi.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell notaSub = new Cell(new Phrase(String.valueOf(nota.getSub()), fonte));
            notaSub.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell notaPontos = new Cell(new Phrase(String.valueOf(nota.getPontosExtras()), fonte));
            notaPontos.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell notaFinal = new Cell(new Phrase(String.valueOf(nota.getMediaFinal()), fonte));
            notaFinal.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(aluno);
            table.addCell(notaP1);
            table.addCell(e1);
            table.addCell(e2);
            table.addCell(notaMedia);
            table.addCell(notaApi);
            table.addCell(notaSub);
            table.addCell(notaPontos);
            table.addCell(notaFinal);
            documento.add(table);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(notaTv.getNome() +".pdf"));
            documento.close();
        }
    }

    @FXML
    void emitirGeral(ActionEvent event) throws IOException {
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream("relatorio alunos.pdf"));
        documento.open();
        documento.setPageSize(PageSize.A4);
        documento.setMargins(0, 0, 3, 3);

        Font fonte = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        Font fonteCabecalho = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(0, 0, 0));
        Font titulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);

        Table table = new Table(2);
        table.setWidths(new float[]{25, 25});
        table.setWidth(100);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Paragraph p = new Paragraph("Relatório de Alunos LP I", titulo);

        p.setAlignment("center");
        documento.add(p);
        Cell nomeAluno = new Cell(new Phrase("Nome do Aluno", fonteCabecalho));
        Cell mediaFinal = new Cell(new Phrase("Média Final", fonteCabecalho));
        mediaFinal.setHorizontalAlignment(HorizontalAlignment.CENTER);
        nomeAluno.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.addCell(nomeAluno);
        table.addCell(mediaFinal);
        table.setBackgroundColor(ColorUIResource.getHSBColor(255, 200, 50));
        List<Nota> notas = new ArrayList<>();
        notas = notaDao.buscarTodos();
        for (var item : notas) {
            Cell aluno = new Cell(new Phrase(item.getNome(), fonte));
            aluno.setHorizontalAlignment(HorizontalAlignment.CENTER);
            Cell nota = new Cell(new Phrase(String.valueOf(item.getMediaFinal()), fonte));
            nota.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(aluno);
            table.addCell(nota);
        }
        documento.add(table);
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("relatorio alunos.pdf"));
        documento.close();
    }


    @FXML
    void voltarMenu(ActionEvent event) throws IOException {
        App.setRoot("views/telamenu.fxml");
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

}

package com.jtmjinfo.calculonotas.dao;

import com.jtmjinfo.calculonotas.model.Aluno;
import com.jtmjinfo.calculonotas.repository.IAlunoRepository;
import com.jtmjinfo.calculonotas.util.Conexao;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao implements IAlunoRepository {

    private static final String URL = "jdbc:sqlite:c:/banco/notas.db";

    @Override
    public void cadastrarAluno(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno(nome, p1, trabalho1,trabalho2, media,api, pontosExtras,sub, mediaFinal) " +
                    "VALUES('" + aluno.getNome() + "', '" + aluno.getP1() + "','" + aluno.getTrabalho1() + "','" + aluno.getTrabalho2() + "'," +
                    "'" + aluno.getMedia() + "','" + aluno.getApi() + "','" + aluno.getPontosExtras() + "','" + aluno.getSub() + "','" + aluno.getMediaFinal() + "')";
            var conn = DriverManager.getConnection(URL);
            var stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    @Override
    public List<Aluno> buscarTodos() {

        List<Aluno> alunos= new ArrayList<>();

        try {
            ResultSet rs = null;
            String sql = "select * from aluno";
            var conn = DriverManager.getConnection(URL);

            var stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("Id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setP1(rs.getDouble("p1"));
                aluno.setTrabalho1(rs.getDouble("trabalho1"));
                aluno.setTrabalho2(rs.getDouble("trabalho2"));
                aluno.setApi(rs.getDouble("api"));
                aluno.setPontosExtras(rs.getDouble("pontosExtras"));
                aluno.setSub(rs.getDouble("sub"));
                aluno.setMediaFinal(rs.getDouble("mediaFinal"));
                aluno.setMedia(rs.getDouble("media"));

                alunos.add(aluno);
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return alunos;
    }


    @Override
    public Aluno buscarPorId() {


        return null;
    }

    @Override
    public Aluno editarAluno(Aluno aluno, int id) {
        return null;
    }

    @Override
    public void deletarAluno(int id) {
        String sql = "DELETE FROM aluno WHERE id = "+ id +";";

        try{
            var conn = DriverManager.getConnection(URL);
            var stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

}

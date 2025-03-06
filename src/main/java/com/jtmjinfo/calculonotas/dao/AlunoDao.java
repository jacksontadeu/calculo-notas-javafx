package com.jtmjinfo.calculonotas.dao;

import com.jtmjinfo.calculonotas.model.Aluno;
import com.jtmjinfo.calculonotas.repository.IAlunoRepository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AlunoDao implements IAlunoRepository {

    private static final String URL = "jdbc:sqlite:notas.db";

    @Override
    public void cadastrarAluno(Aluno aluno) {
        try{
            String sql = "INSERT INTO aluno(nome, p1, trabalho1,trabalho2, media,api, pontosExtras,sub, mediaFinal) " +
                    "VALUES('"+ aluno.getNome() +"', '"+ aluno.getP1() + "','"+ aluno.getTrabalho1() + "','"+ aluno.getTrabalho2() +"'," +
                    "'"+ aluno.getMedia() +"','"+ aluno.getApi() +"','"+ aluno.getPontosExtras() +"','"+ aluno.getSub() +"','"+aluno.getMediaFinal() +"')";
            var conn = DriverManager.getConnection(URL);
            var stmt = conn.createStatement();
            stmt.execute(sql);



        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    @Override
    public List<Aluno> buscarTodos() {
        return List.of();
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

    }
}

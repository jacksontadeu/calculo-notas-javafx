package com.jtmjinfo.calculonotas.dao;

import com.jtmjinfo.calculonotas.model.Aluno;
import com.jtmjinfo.calculonotas.repository.IAlunoRepository;
import com.jtmjinfo.calculonotas.util.Conexao;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao implements IAlunoRepository {

<<<<<<< HEAD
    private static final String URL = "jdbc:sqlite:c:/banco/notas.db";

=======
>>>>>>> 4866a26edd2ddeef952d18dafe046ae2b1ba9c80
    @Override
    public void cadastrarAluno(Aluno aluno) {
        try{
            String sql= "insert into aluno(nome, email) values(?,?)";

            PreparedStatement ps = Conexao.obterConexao().prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

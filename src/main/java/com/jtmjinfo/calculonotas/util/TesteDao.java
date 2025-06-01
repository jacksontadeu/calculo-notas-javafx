package com.jtmjinfo.calculonotas.util;

import com.jtmjinfo.calculonotas.dao.AlunoDao;
import com.jtmjinfo.calculonotas.model.Aluno;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TesteDao {

    public static void main(String[] args) throws SQLException {


      Conexao.obterConexao();
    }
}

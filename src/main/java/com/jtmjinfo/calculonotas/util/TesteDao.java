package com.jtmjinfo.calculonotas.util;

import com.jtmjinfo.calculonotas.dao.AlunoDao;
import com.jtmjinfo.calculonotas.model.Aluno;

import java.sql.Connection;

public class TesteDao {

    public static void main(String[] args) {


        AlunoDao alunoDao = new AlunoDao();
        Aluno aluno = new Aluno();
        aluno.setNome("Jackson");
        aluno.setP1(8);
        aluno.setTrabalho1(6);
        aluno.setTrabalho2(8);
        aluno.setApi(8);
        aluno.setMedia(3.8);
        aluno.setPontosExtras(0);
        aluno.setSub(0);
        aluno.setMediaFinal(7.8);

        alunoDao.cadastrarAluno(aluno);
    }
}

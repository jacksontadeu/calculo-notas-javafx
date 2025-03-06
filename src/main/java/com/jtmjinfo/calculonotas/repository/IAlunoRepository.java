package com.jtmjinfo.calculonotas.repository;

import com.jtmjinfo.calculonotas.model.Aluno;

import java.util.List;

public interface IAlunoRepository {

    public void cadastrarAluno(Aluno aluno);
    public List<Aluno> buscarTodos();
    public Aluno buscarPorId();
    public Aluno editarAluno(Aluno aluno, int id);
    public void deletarAluno(int id);
}

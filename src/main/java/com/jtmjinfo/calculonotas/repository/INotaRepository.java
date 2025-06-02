package com.jtmjinfo.calculonotas.repository;


import com.jtmjinfo.calculonotas.model.Nota;

import java.util.List;

public interface INotaRepository {
    public void cadastrarNota(Nota nota);
    public List<Nota> buscarTodos();
    public Nota buscarPorId(int id);
    public void editarNota(Nota nota, int id);
    public void deletarNota(int id );
}

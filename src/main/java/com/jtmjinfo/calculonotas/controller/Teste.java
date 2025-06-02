package com.jtmjinfo.calculonotas.controller;


import com.jtmjinfo.calculonotas.dao.NotaDao;

import com.jtmjinfo.calculonotas.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class Teste {

    public static void main(String[] args) {
        Nota nota = new Nota();
        NotaDao notaDao = new NotaDao();

        nota=notaDao.buscarPorId(11);
        System.out.println(nota);




    }
}

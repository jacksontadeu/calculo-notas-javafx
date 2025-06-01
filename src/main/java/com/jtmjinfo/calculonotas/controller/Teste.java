package com.jtmjinfo.calculonotas.controller;

import com.jtmjinfo.calculonotas.model.Aluno;

public class Teste {

    public static void main(String[] args) {
        Aluno aluno = new Aluno();

        aluno.getNota().setP1(8);
        aluno.getNota().setTrabalho1(6);
        aluno.getNota().setTrabalho2(8);

        System.out.println(aluno.getNota().calcularMedia());
        System.out.println(aluno.getNota().getMedia());
    }
}

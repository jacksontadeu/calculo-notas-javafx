package com.jtmjinfo.calculonotas.controller;

import com.jtmjinfo.calculonotas.model.Aluno;

public class Teste {

    public static void main(String[] args) {
        Aluno aluno = new Aluno();

        aluno.setP1(8);
        aluno.setTrabalho1(6);
        aluno.setTrabalho2(8);

        System.out.println(aluno.calcularMedia());
        System.out.println(aluno.getMedia());
    }
}

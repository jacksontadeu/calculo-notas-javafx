package com.jtmjinfo.calculonotas.model;

public class Nota {

    private int id;
    private double p1=0;
    private double trabalho1=0;
    private double trabalho2=0;
    private double media = 0;
    private double mediaFinal = 0;
    private double pontosExtras =0;
    private double api= 0 ;
    private double sub= 0;
    private String nome;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getTrabalho1() {
        return trabalho1;
    }

    public void setTrabalho1(double trabalho1) {
        this.trabalho1 = trabalho1;
    }

    public double getTrabalho2() {
        return trabalho2;
    }

    public void setTrabalho2(double trabalho2) {
        this.trabalho2 = trabalho2;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public double getPontosExtras() {
        return pontosExtras;
    }

    public void setPontosExtras(double pontosExtras) {
        this.pontosExtras = pontosExtras;
    }

    public double getApi() {
        return api;
    }

    public void setApi(double api) {
        this.api = api;
    }

    public double getSub() {
        return sub;
    }

    public void setSub(double sub) {
        this.sub = sub;
    }
    public double calcularMedia(){
        this.media = ((this.p1 * 0.6) + (((this.trabalho1 + this.trabalho2)/2)* 0.4)) * 0.5;
        return this.media;
    }
    public double calcularMediaFinal(){
        this.mediaFinal = media + (this.api * 0.5) + this.pontosExtras + (this.sub * 0.3);
        if (mediaFinal > 10)
            mediaFinal = 10;
        else
            mediaFinal = mediaFinal;
        return mediaFinal;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", p1=" + p1 +
                ", trabalho1=" + trabalho1 +
                ", trabalho2=" + trabalho2 +
                ", media=" + media +
                ", mediaFinal=" + mediaFinal +
                ", pontosExtras=" + pontosExtras +
                ", api=" + api +
                ", sub=" + sub +
                ", nome='" + nome + '\'' +
                '}';
    }
}

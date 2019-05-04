package com.sim.myapplication;

public class TypeClasse {

    Double score ;
    String nom_class ;
    Box box;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getNom_class() {
        return nom_class;
    }

    public void setNom_class(String nom_class) {
        this.nom_class = nom_class;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public TypeClasse(Double score, String nom_class, Box box) {
        this.score = score;
        this.nom_class = nom_class;
        this.box = box;
    }
}

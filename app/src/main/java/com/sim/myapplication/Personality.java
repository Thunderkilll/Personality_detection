package com.sim.myapplication;

public class Personality {

    private String class_type;
    private String personaly_text;
    private float pourcentage;


    public Personality() {
        this.pourcentage = 0 ;
        this.class_type = "";
        this.personaly_text ="";
    }

    public Personality(String class_type) {
        this.pourcentage = 0 ;
        this.class_type = class_type;

        //todo: insert user personalities here per class
        if (class_type.equals("1.0")) {
            System.out.println("personality trait type y_1");
            this.personaly_text = "Loner / Loner with lack of drive";
            System.out.println(this.personaly_text);
        }
        if (class_type.equals("2.0")) {
            System.out.println("personality trait type y_2");
            this.personaly_text = "Physical frustration ";
            System.out.println(this.personaly_text);
        }
        if (class_type.equals("3.0")) {
            System.out.println("personality trait type y_3");
            this.personaly_text = "Clannish / anti_social(lack of trust )";
            System.out.println(this.personaly_text);
        }
        if (class_type.equals("4.0")) {
            System.out.println("personality trait type y_4");
            this.personaly_text = "healthy physical drives / exaggerated physical drives";
            System.out.println(this.personaly_text);
        }
    }

    public String getClass_type() {
        return class_type;
    }

    public void setClass_type(String class_type) {
        this.class_type = class_type;
    }


    public String getPersonaly_text() {
        return personaly_text;
    }

    public void setPersonaly_text(String personaly_text) {
        this.personaly_text = personaly_text;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    @Override
    public String toString() {
        return "Personality{" +
                "class_type='" + class_type + '\'' +
                ", personaly_text='" + personaly_text + '\'' +
                ", pourcentage=" + pourcentage +
                '}';
    }
}
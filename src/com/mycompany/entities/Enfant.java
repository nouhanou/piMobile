/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author HP
 */
public class Enfant {
    int id;
    String name ; 
    String naissance ; 
    String garderie ; 
    String place ; 
    String classe;

    public Enfant() {
    }
    
     public Enfant(int i) {
        this.id=id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public String getGarderie() {
        return garderie;
    }

    public void setGarderie(String garderie) {
        this.garderie = garderie;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    
            
}

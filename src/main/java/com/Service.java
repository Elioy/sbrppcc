/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eliot
 */
public class Service {
    private String nom;
    private List<Etat> etats;
    
    public Service(String nom) {
        this.nom = nom;
        this.etats = new ArrayList<Etat>();
    }

    public String getNom() {
        return nom;
    }

    public List<Etat> getEtats() {
        return etats;
    }
    
    public void ajouterEtat(Etat e){
        this.etats.add(e);
    }
    
}

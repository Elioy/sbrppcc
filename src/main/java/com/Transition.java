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
public class Transition {
    
    private String nom;
    
    private List<Etat> etatsPrecedents;
    
    private List<Etat> etatsSuivants;
    
    private List<Etat> dependances;
    
    private Service Service;

    public Transition(String nom, Service Service) {
        this.nom = nom;
        this.Service = Service;
        this.etatsPrecedents = new ArrayList<Etat>();
        this.etatsSuivants = new ArrayList<Etat>();
        this.dependances = new ArrayList<Etat>();
    }

    public String getNom() {
        return nom;
    }

    public List<Etat> getEtatsPrecedents() {
        return etatsPrecedents;
    }

    public List<Etat> getEtatsSuivants() {
        return etatsSuivants;
    }

    public List<Etat> getDependances() {
        return dependances;
    }

    public Service getService() {
        return Service;
    }
    
    public void ajouterEtatPrecedent(Etat e){
        this.etatsPrecedents.add(e);
    }
    
    public void ajouterEtatSuivant(Etat e){
        this.etatsSuivants.add(e);
    }
    
    public boolean estFranchissable(){
        boolean b = true;
        for(Etat e : this.etatsPrecedents){
            if (e.getNbJetons() < 1){
                b = false;
            }
        }
        return b;
    }
}

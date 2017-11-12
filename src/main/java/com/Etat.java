/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.List;

/**
 * un etat d'un service
 * @author eliot
 */
public class Etat {
    /**
     * le nom de l'etar
     */
    private String nom;
    
    /**
     * le nombre de jeton de l'etat
     */
    private int nbJetons;
    
    private boolean estValide;
    
    /**
     * les transitions de l'etat
     */
    private List<Transition> transitions;

    /**
     * constructor
     * @param nom le nom de l'état
     * @param nbJetons le nombre de jetons de l'etat
     */
    public Etat(String nom, int nbJetons) {
        this.nom = nom;
        this.nbJetons = nbJetons;
        this.transitions = new ArrayList<Transition>();
        if(this.nbJetons > 0){
            this.estValide = true;
        }
        else{
            this.estValide = false;
        }
    }

    /**
     * ajoute une transition à l'etat
     * @param t la transition à ajouter
     */
    public void ajouterTransition(Transition t){
        this.transitions.add(t);
    }

    @Override
    public String toString() {
        String s = "nom : "+nom+"\nnbJetons : "+nbJetons+"\ntransitions :";
        for(Transition t : transitions){
            s+=t.getNom();
        }
        return s;
    }
    
    
    
    //getters & setters
    public String getNom() {
        return nom;
    }

    public int getNbJetons() {
        return nbJetons;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }
    
    
}

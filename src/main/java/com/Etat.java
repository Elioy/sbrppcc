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
    
    private boolean valide;
    
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
            this.valide = true;
        }
        else{
            this.valide = false;
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Etat other = (Etat) obj;
        if (this.nbJetons != other.nbJetons) {
            return false;
        }
        if (this.valide != other.valide) {
            return false;
        }
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        return true;
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
        if(this.nbJetons > 0){
            this.valide = true;
        }
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean b) {
        this.valide = b;
    }
    
    
}

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
    
    /**
     * indique si l'etat est validé ou non
     */
    private boolean valide;
    
    /**
     * les transitions suivantes de l'etat
     */
    private List<Transition> transitionsPrecedentes;

    private List<Transition> transitionsSuivantes;
    
    /**
     * constructor
     * @param nom le nom de l'état
     * @param nbJetons le nombre de jetons de l'etat
     */
    public Etat(String nom, int nbJetons) {
        this.nom = nom;
        this.nbJetons = nbJetons;
        this.transitionsPrecedentes = new ArrayList<Transition>();
        this.transitionsSuivantes = new ArrayList<Transition>();
        if(this.nbJetons > 0){
            this.valide = true;
        }
        else{
            this.valide = false;
        }
    }

    /**
     * ajoute une transition suivante à l'etat
     * @param t la transition à ajouter
     */
    public void ajouterTransitionSuivantes(Transition t){
        this.transitionsSuivantes.add(t);
    }

    public void ajouterTransitionPrecedentes(Transition t){
        this.transitionsPrecedentes.add(t);
    }
    
    @Override
    public String toString() {
        String s = "nom : "+nom+"\nnbJetons : "+nbJetons;
        return s;
    }
    
    //getters & setters
    public String getNom() {
        return nom;
    }

    public int getNbJetons() {
        return nbJetons;
    }

    public List<Transition> getTransitionsSuivantes() {
        return transitionsSuivantes;
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

    public void setTransitionsSuivante(List<Transition> transitions) {
        this.transitionsSuivantes = transitions;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean b) {
        this.valide = b;
    }

    public List<Transition> getTransitionsPrecedentes() {
        return transitionsPrecedentes;
    }
    
    
    
}

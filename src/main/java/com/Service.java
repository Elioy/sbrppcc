/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.List;

/**
 * un service d'une application
 *
 * @author eliot
 */
public class Service {

    /**
     * le nom du service
     */
    private String nom;

    /**
     * les différents états du service
     */
    private List<Etat> etats; //YamlBeans

    /**
     * les différentes transitions du service
     */
    private List<Transition> transitions;

    /**
     * constructor
     *
     * @param nom le nom du service
     */
    public Service(String nom) {
        this.nom = nom;
        this.etats = new ArrayList<Etat>();
        this.transitions = new ArrayList<Transition>();
    }

    /**
     * ajoute un etat au service
     *
     * @param e l'etat à ajouter
     */
    public void ajouterEtat(Etat e) {
        this.etats.add(e);
    }

    /**
     * ajoute une transition au service
     *
     * @param t la transition à ajouter
     */
    public void ajouterTransition(Transition t) {
        this.transitions.add(t);
    }

    /**
     * permet d'obtenir un etat en connaissant son nom
     * @param nom le nom de l'etat
     * @return l'etat correspondant
     */
    public Etat getEtatParNom(String nom) {
        for (Etat e : etats) {
            if (e.getNom() == nom) {
                return e;
            }
        }
        return null;
    }
    
    /**
     * permet d'obtenir une transition en connaissant son nom
     * @param nom le nom de la transition
     * @return la transition correspondante
     */
    public Transition getTransitionParNom(String nom){
        for (Transition t : transitions) {
            if (t.getNom() == nom) {
                return t;
            }
        }
        return null;
    }

    /**
     * ajoute les transitions correspondants aux etats du service
     */
    public void ajouterTransitionsAuxEtats() {
        for (Etat e : etats) {
            for(Transition t :transitions){
                for(Etat e2 : t.getEtatsEntrants()){
                    if(e.equals(e2)){
                        e.ajouterTransition(t);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String s = "nom : " + nom + "\netats :";
        for (Etat e : etats) {
            s += "\n" + e;
        }
        s += "\nTransitions :";
        for (Transition t : transitions) {
            s += "\n" + t;
        }
        return s;
    }

    //getters & setters
    public String getNom() {
        return nom;
    }

    public List<Etat> getEtats() {
        return etats;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEtats(List<Etat> etats) {
        this.etats = etats;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

}

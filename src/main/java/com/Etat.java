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
public class Etat {

    private String nom;
    private boolean estValide;
    private int nbJetons;
    private Service service;

    private List<Transition> transitions;

    public Etat(String nom, int nbJetons, Service service) {
        this.nom = nom;
        this.service = service;
        this.nbJetons = nbJetons;
        estValide = nbJetons > 0;
        this.transitions = new ArrayList<Transition>();
    }

    public String getNom() {
        return nom;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public int getNbJetons() {
        return nbJetons;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    public void ajouterTransition(Transition t) {
        this.transitions.add(t);
    }

    public void franchirTransitions() {
        if (this.aTransition()) {
            this.nbJetons--;
            for (Transition t : this.transitions) {
                if (t.estFranchissable()) {
                    for (Etat e : t.getEtatsSuivants()) {
                        e.setNbJetons(e.getNbJetons() + 1);
                    }
                }
            }
        }
    }

    public boolean aTransition() {
        return transitions.size() != 0;
    }
}

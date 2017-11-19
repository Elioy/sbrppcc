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
     *
     * @param nom le nom de l'etat
     * @return l'etat correspondant
     */
    public Etat getEtatParNom(String nom) {
        for (Etat e : etats) {
            if (e.getNom().equals(nom)) {
                return e;
            }
        }
        return null;
    }

    /**
     * permet d'obtenir une transition en connaissant son nom
     *
     * @param nom le nom de la transition
     * @return la transition correspondante
     */
    public Transition getTransitionParNom(String nom) {
        for (Transition t : transitions) {
            if (t.getNom().equals(nom)) {
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
            for (Transition t : transitions) {
                for (Etat e2 : t.getEtatsEntrants()) {
                    if (e.equals(e2)) {
                        e.ajouterTransition(t);
                    }
                }
            }
        }
    }

    /**
     * franchit une transition, ainsi que les autres transitions qui peuvent
     * être franchie en même temps
     *
     * @param t la transition à franchir
     * @see construireListeEtatsEntrantsEtSortants
     */
    /*public void franchirTransition(Transition t) {
        if (t.estFranchissable()) {
            List<Etat> etatsEntrants = new ArrayList<Etat>();
            List<Etat> etatsSortants = new ArrayList<Etat>();
            construireListeEtatsEntrantsEtSortants(t, new ArrayList<Transition>(), etatsEntrants, etatsSortants);
            for (Etat e : etatsEntrants) {
                e.setNbJetons(e.getNbJetons() - 1);
            }
            for (Etat e : etatsSortants) {
                e.setNbJetons(e.getNbJetons() + 1);
            }
        }
    }*/
    public void franchirTransitions() {
        List<Etat> etatsEntrantsTrans = new ArrayList<Etat>();
        List<Etat> etatsSortantsTrans = new ArrayList<Etat>();
        List<Transition> transitionsAFranchir = new ArrayList<Transition>();
        for (Transition t : this.transitions) {
            if (t.estFranchissable()) {
                transitionsAFranchir.add(t);
            }
        }
        construireListeEtatEntrantsEtSortants(transitionsAFranchir, etatsEntrantsTrans, etatsSortantsTrans);
        for (Etat e : etatsEntrantsTrans) {
            e.setNbJetons(e.getNbJetons() - 1);
        }
        for (Etat e : etatsSortantsTrans) {
            e.setNbJetons(e.getNbJetons() + 1);
        }
    }

    /**
     * construit une liste d'etats entrants et d'etats sortants correspondants
     * aux etats entrants et sortant des transitions qui vont etre franchies
     *
     * @param t la transition à franchir
     * @param transitions les transitions qui vont être franchies simultanément
     * @param etatsEntrants la liste d'etats entrants qui va être remplie
     * @param etatsSortants la liste d'états sortants qui va être remplie
     */
    /*private void construireListeEtatsEntrantsEtSortants(Transition t, List<Transition> transitions, List<Etat> etatsEntrants, List<Etat> etatsSortants) {
        transitions.add(t);
        for (Etat e : t.getEtatsEntrants()) {
            if (!etatsEntrants.contains(e)) {
                etatsEntrants.add(e);
            }
        }
        for (Etat e : t.getEtatsSortant()) {
            etatsSortants.add(e);
        }
        List<Etat> etats = new ArrayList<Etat>();
        etats.addAll(etatsEntrants);
        for (Etat e : etats) {
            for (Transition t1 : e.getTransitions()) {
                if (!transitions.contains(t1) && t1.estFranchissable()) {
                    construireListeEtatsEntrantsEtSortants(t1, transitions, etatsEntrants, etatsSortants);
                }
            }
        }
    }*/
    private void construireListeEtatEntrantsEtSortants(List<Transition> transitionsAFranchir, List<Etat> etatsEntrants, List<Etat> etatsSortants) {
        for (Transition t : transitionsAFranchir) {
            for (Etat e : t.getEtatsEntrants()) {
                if (!etatsEntrants.contains(e)) {
                    etatsEntrants.add(e);
                }
            }
            for (Etat e : t.getEtatsSortant()) {
                etatsSortants.add(e);
            }
        }
    }

    @Override
    public String toString() {
        String s = "####################\nnom : " + nom + "\n####################\netats :";
        for (Etat e : etats) {
            s += "\n" + e;
        }
        /*s += "\nTransitions :";
        for (Transition t : transitions) {
            s += "\n" + t;
        }*/
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

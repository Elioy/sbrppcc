/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * un service d'une application
 *
 * @author eliot
 */
public class Service implements Runnable {

    /**
     * le nom du service
     */
    private String nom;

    /**
     * les différents états du service
     */
    private List<Etat> etats; 

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
                        e.ajouterTransitionSuivantes(t);
                    }
                }
            }
            for (Transition t : transitions) {
                for (Etat e2 : t.getEtatsSortant()) {
                    if (e.equals(e2)) {
                        e.ajouterTransitionPrecedentes(t);
                    }
                }
            }
        }
    }

    /**
     * franchit les transitions qui peuvent être franchies
     *
     */
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
     * @param transitionsAFranchir les transitions qui vont être franchies
     * simultanément
     * @param etatsEntrants la liste d'etats entrants qui va être remplie
     * @param etatsSortants la liste d'états sortants qui va être remplie
     */
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

    /**
     * indique si le service est à l'étar final ou non
     *
     * @return true si le service est à l'état final, false sinon
     */
    public boolean estALEtatFinal() {
        for (Etat e : this.etats) {
            if (e.getNbJetons() > 0 && !e.getTransitionsSuivantes().isEmpty()) {
                for (Transition t : e.getTransitionsSuivantes()) {
                    if (t.pourraEtreFranchie()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * franchit les transitions tant que le service n'est pas à l'état final
     */
    public void run() {
        while (!estALEtatFinal()) {
            this.franchirTransitions();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println(this.getNom() + " transitions franchises");
        }
        System.out.println(this);
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

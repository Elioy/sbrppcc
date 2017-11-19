/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.List;

/**
 * une transition d'un service
 *
 * @author eliot
 */
public class Transition {

    /**
     * le nom de la transition
     */
    private String nom;

    /**
     * les etats entrants de la transition
     */
    private List<Etat> etatsEntrants;

    /**
     * les etats sortants de la transition
     */
    private List<Etat> etatsSortants;

    /**
     * les dépendances de la transition
     */
    private List<Etat> dependances;

    /**
     * constructor
     *
     * @param nom le nom de la transition
     */
    public Transition(String nom) {
        this.nom = nom;
        this.etatsEntrants = new ArrayList<Etat>();
        this.etatsSortants = new ArrayList<Etat>();
        this.dependances = new ArrayList<Etat>();
    }

    /**
     * ajoute un etat entrant à la transition
     *
     * @param e l'etat entrant à ajouter
     */
    public void ajouterEtatEntrant(Etat e) {
        this.etatsEntrants.add(e);
    }

    /**
     * ajouter un etat sortant à la transition
     *
     * @param e l'etat sortant à ajouter
     */
    public void ajouterEtatSortant(Etat e) {
        this.etatsSortants.add(e);
    }

    /**
     * ajouter une dependances à la transition
     *
     * @param e l'etat de la dépendance
     */
    public void ajouterDependances(Etat e) {
        this.dependances.add(e);
    }

    @Override
    public String toString() {
        String s = "nom : " + nom + "\netats entrants : ";
        for (Etat e : etatsEntrants) {
            s += "\n\t" + e.getNom();
        }
        s += "\netats sortants :";
        for (Etat e : etatsSortants) {
            s += "\n\t" + e.getNom();
        }
        s += "\ndependances :";
        for (Etat e : dependances) {
            s += "\n\t" + e.getNom();
        }
        return s;
    }

    /**
     * permet de savoir si la transition est franchissable
     *
     * @return true si la transition et franchissable, false sinon
     */
    public boolean estFranchissable() {
        for (Etat e : this.etatsEntrants) {
            if (e.getNbJetons() < 1) {
                return false;
            }
        }
        for (Etat e : this.dependances) {
            if (!e.isValide()) {
                return false;
            }
        }
        return true;
    }

    //getters & setters
    public String getNom() {
        return nom;
    }

    public List<Etat> getEtatsEntrants() {
        return etatsEntrants;
    }

    public List<Etat> getEtatsSortant() {
        return etatsSortants;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEtatsEntrants(List<Etat> etatsEntrants) {
        this.etatsEntrants = etatsEntrants;
    }

    public void setEtatsSortants(List<Etat> etatsSortants) {
        this.etatsSortants = etatsSortants;
    }

    public List<Etat> getEtatsSortants() {
        return etatsSortants;
    }

    public List<Etat> getDependances() {
        return dependances;
    }

    public void setDependances(List<Etat> dependances) {
        this.dependances = dependances;
    }

}

package com;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Une application composée de différents services
 *
 * @author Eliot
 */
public class App {

    /**
     * les services de l'application
     */
    private List<Service> services;

    private YamlReader parser;

    /**
     * constructor
     */
    public App(String file) throws FileNotFoundException {
        this.services = new ArrayList<Service>();
        this.parser = new YamlReader(new FileReader(file));
        for(Service s : this.services){
            s.ajouterTransitionsAuxEtats();
        }
    }

    /**
     * methode de parsing du fichier yaml
     * @throws YamlException 
     */
    public void parseYAML() throws YamlException {
        Object o1 = parser.read();
        Map m1 = (Map) ((Map) o1).get("application");
        List l1 = (List) m1.get("services"); //liste des services
        for (Object o2 : l1) {
            Map m2 = (Map) ((Map) o2).get("service");//service
            Service s = new Service((String) m2.get("nom"));//nom du service
            List l2 = (List) m2.get("etats"); //liste des états du service
            for (Object o3 : l2) {
                Map m3 = (Map) ((Map) o3).get("etat");//etat
                Etat e = new Etat((String) m3.get("nom"), Integer.parseInt((String) m3.get("nbJetons")));
                s.ajouterEtat(e);
            }
            List l3 = (List) m2.get("transitions");//liste des transitions du service
            for (Object o3 : l3) {
                Map m3 = (Map) ((Map) o3).get("transition");//transition
                Transition t = new Transition((String) m3.get("nom"));
                List l4 = (List) m3.get("etatsEntrants");//liste des etats entrants de la transition
                for (Object o4 : l4) {
                    Map m4 = (Map) ((Map) o4).get("etatEntrant");//etat entrant
                    t.ajouterEtatEntrant(s.getEtatParNom((String) m4.get("nom")));
                }
                List l5 = (List) m3.get("etatsSortants");//liste des etats sortants de la transition
                for (Object o4 : l5) {
                    Map m4 = (Map) ((Map) o4).get("etatSortant");//etat sortant
                    t.ajouterEtatSortant(s.getEtatParNom((String) m4.get("nom")));
                }
                s.ajouterTransition(t);
            }
            ajouterService(s);
        }
        List l2 = (List) m1.get("dependances"); //liste des dependances
        for (Object o2 : l2) {
            Map m2 = (Map)((Map) ((Map) o2).get("dependance")).get("serviceEtat");
            Service s1 = this.getServiceParNom((String) m2.get("nom"));
            Map m3 = (Map)((Map) ((Map) o2).get("dependance")).get("etat");
            Etat e = s1.getEtatParNom((String) m3.get("nom"));
            Map m4 = (Map)((Map) ((Map) o2).get("dependance")).get("serviceTransition");
            Service s2 = this.getServiceParNom((String) m4.get("nom"));
            Map m5 = (Map)((Map) ((Map) o2).get("dependance")).get("transition");
            Transition t = s2.getTransitionParNom((String) m5.get("nom"));
            t.ajouterDependances(e);
        }
        
    }

    /**
     * ajoute un service à l'application
     *
     * @param s le service à ajouter
     */
    public void ajouterService(Service s) {
        this.services.add(s);
    }

    /**
     * permet d'obtenir un service en connaissant son nom
     *
     * @param nom le nom du service
     * @return le service correspondant
     */
    public Service getServiceParNom(String nom) {
        for (Service s : services) {
            if (s.getNom().equals(nom)) {
                return s;
            }
        }
        return null;
    }

    /**
     * ajoute les transitions correspondantes aux différents états des services
     * de l'application
     */
    public void ajouterTransitionsAuxEtats() {
        for (Service s : services) {
            s.ajouterTransitionsAuxEtats();
        }
    }

    /**
     * methode toString
     *
     * @return
     */
    @Override
    public String toString() {
        String s = "";
        s += "Services :";
        for (Service serv : services) {
            s += "\n" + serv;
        }
        return s;
    }

    //getter & setter
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

}

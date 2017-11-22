# Simulation à base de réseau de pétri pour le Cloud Computing
## Consignes 
**Phase 1** - Dans la première phase nous vous demandons d’implémenter les fonctionnalités
demandées, sans interface graphique, c’est-à-dire :
* définir le fichier d’entrée qui permet de décrire les services, leur réseau de pétri interne et
les relations entre les services 
* un parseur de ce fichier d’entrée 
* concevoir les fonctions d’exécution du système en suivant les règles évoquées plus haut 

**Phase 2** - Juste avant la deuxième phase vous allez interchanger vos codes. Le deuxième phase
consiste à implémenter une interface graphique qui permet :
1. de concevoir une application comme celle présentée sur la Figure 1, et écrire le fichier correspondant

2. le simulateur graphique du déploiement de l’application créée.
C’est pour implémenter le simulateur que le temps d’exécution associé aux transitions est utile.
Il faut que l’interface graphique montre en temps réel et dynamiquement le déroulement du dé-
ploiement en suivant les règles évoquées plus haut, donc en utilisant les fonctionnalités codées
dans la phase 1.
Chacun d’entre vous va utiliser le code de la phase 1 d’un autre pour implémenter la phase
2, donc bien évidemment il faut faire en sorte de donner un code propre, bien structuré et bien
commenté à votre camarade de classe.

## Outils
**IDE :** [Netbeans 8.2](https://netbeans.org/downloads/)

**YAML Parser :** [yamlbeans](https://github.com/EsotericSoftware/yamlbeans)

**Gestion des Dépendances :** [Maven](https://maven.apache.org/)

## Diagramme de Classe
![diagramme de classe](https://i.imgur.com/tGMwcSd.png)

## Documentation
La javadoc du projet est accessible [ici](https://elioy.github.io/sbrppcc/)

## Exemple de fichier d'entrée


    application :
        services :
            - service: &s1
                nom : service 1
                etats :
                    - etat : &s1e1
                        nom : e1
                        nbJetons : 1
                    - etat : &s1e2
                        nom : e2
                        nbJetons : 0
                    - etat : &s1e3
                        nom : e3
                        nbJetons : 0
                    - etat : &s1e4
                        nom : e4
                        nbJetons : 0
                    - etat : &s1e5
                        nom : e5
                        nbJetons : 0
                transitions :
                    - transition : &s1t1
                        nom : t1
                        etatsEntrants :
                            - etatEntrant : *s1e1
                        etatsSortants :
                            - etatSortant : *s1e2
                    - transition : &s1t2
                        nom : t2
                        etatsEntrants :
                            - etatEntrant : *s1e2
                        etatsSortants :
                            - etatSortant : *s1e3
                    - transition : &s1t3
                        nom : t3
                        etatsEntrants : 
                            - etatEntrant : *s1e2
                        etatsSortants:
                            - etatSortant : *s1e4
                    - transition : &s1t4
                        nom : t4
                        etatsEntrants : 
                            - etatEntrant : *s1e3
                            - etatEntrant : *s1e4
                        etatsSortants :
                            - etatSortant : *s1e5
            - service: &s2
                nom : service 2
                etats :
                    - etat : &s2e1
                        nom : e1
                        nbJetons : 1
                    - etat : &s2e2
                        nom : e2
                        nbJetons : 0
                    - etat : &s2e3
                        nom : e3
                        nbJetons : 0
                    - etat : &s2e4
                        nom : e4
                        nbJetons : 0
                    - etat : &s2e5
                        nom : e5
                        nbJetons : 0
                transitions :
                    - transition : &s2t1 
                        nom : t1
                        etatsEntrants :
                            - etatEntrant : *s2e1
                        etatsSortants :
                            - etatSortant : *s2e2
                    - transition : &s2t2 
                        nom : t2
                        etatsEntrants :
                            - etatEntrant : *s2e2
                        etatsSortants :
                            - etatSortant : *s2e3
                    - transition : &s2t3 
                        nom : t3
                        etatsEntrants : 
                            - etatEntrant : *s2e2 
                        etatsSortants :
                            - etatSortant : *s2e4
                    - transition : &s2t4 
                        nom : t4
                        etatsEntrants :
                            - etatEntrant : *s2e4
                        etatsSortants :
                            - etatSortant : *s2e5
            - service: &s3
                nom : service 3
                etats :
                    - etat : &s3e1
                        nom : e1
                        nbJetons : 1
                    - etat : &s3e2
                        nom : e2
                        nbJetons : 0
                    - etat : &s3e3
                        nom : e3
                        nbJetons : 0
                    - etat : &s3e4
                        nom : e4
                        nbJetons : 0
                    - etat : &s3e5
                        nom : e5
                        nbJetons : 0
                    - etat : &s3e6
                    nom : e6
                        nbJetons : 0    
                transitions :
                    - transition : &s3t1 
                        nom : t1
                        etatsEntrants :
                            - etatEntrant : *s3e1
                        etatsSortants :
                            - etatSortant : *s3e2
                    - transition : &s3t2 
                        nom : t2
                        etatsEntrants :
                            - etatEntrant : *s3e2
                        etatsSortants :
                            - etatSortant : *s3e3
                    - transition : &s3t3 
                        nom : t3
                        etatsEntrants :
                            - etatEntrant : *s3e2
                        etatsSortants :
                            - etatSortant : *s3e4
                    - transition : &s3t4
                        nom : t4
                        etatsEntrants :
                            - etatEntrant : *s3e2
                        etatsSortants :
                            - etatSortant : *s3e5
                    - transition : &s3t5 
                        nom : t5
                        etatsEntrants :
                            - etatEntrant : *s3e4
                            - etatEntrant : *s3e5
                        etatsSortants :
                            - etatSortant : *s3e6
        dependances :
            - dependance : 
                serviceEtat : *s1
                etat : *s1e4
                serviceTransition : *s2
                transition : *s2t2 
            - dependance : 
                serviceEtat : *s2
                etat : *s2e4
                serviceTransition : *s3
                transition : *s3t1
            - dependance :
                serviceEtat : *s2
                etat : *s2e5
                serviceTransition : *s3
                transition : *s3t5


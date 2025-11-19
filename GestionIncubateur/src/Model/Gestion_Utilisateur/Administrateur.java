/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Utilisateur;

import Model.Gestion_Acteurs.Formateur;
import Model.Gestion_Acteurs.Intervenant;
import Model.Gestion_Evenements.Conference;
import Model.Gestion_Evenements.Formation;
import Model.Gestion_Projets.Projet;
import Model.Gestion_Projets.Ressource;
import Model.Gestion_Utilisateur.Entrepreneur;
import Model.Gestion_Utilisateur.Investisseur;
   import java.util.ArrayList;
import java.util.List;
import gestionincubateur.ErreurEvent;
import java.util.Optional;
import java.util.stream.Collectors;


public final class Administrateur extends Personne {
    private int administrateurID;
    private int motDePasse;

    private List<Conference> tab2;
    private List<Projet> tab1;
    private List<Ressource> tab;

    private List<Intervenant> intervenants;
    private List<Formateur> formatteurs;
    private List<Formation> formations;

    // Nouvelles listes
    private List<Entrepreneur> entrepreneurs;
    private List<Investisseur> investisseurs;


    public Administrateur(int administrateurID, int motDePasse, String nom, String prenom, int age, String adresse) {
        super(nom, prenom, age, adresse);
        this.administrateurID = administrateurID;
        this.motDePasse = motDePasse;
          this.tab2 = new ArrayList<>();
        this.tab1 = new ArrayList<>();
        this.tab = new ArrayList<>();

        this.intervenants = new ArrayList<>();
        this.formatteurs = new ArrayList<>();
        this.formations = new ArrayList<>();

        // Initialisation des nouvelles listes
        this.entrepreneurs = new ArrayList<>();
        this.investisseurs = new ArrayList<>();
        
    }
    
    public int getaministrateurId(){
        return administrateurID;
    }
    public int getMotDePasse() {return motDePasse;}

    public void ajouterProjet(Projet projet) throws ErreurEvent {
         if (tab1.contains(projet)) {
                throw new ErreurEvent("le projet existe déja");
            }
            tab1.add(projet);
        }
    
     public void supprimerProjet(Projet p) {
    try {
        if (tab1.contains(p)) {
            tab1.remove(p);
        } else {
            throw new IllegalArgumentException("Le projet n'existe pas dans la liste.");
        }
    } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
       
    }
}
    
    public Projet modifierProjet( String nouveauNom, String nouveauDomaine, String nouvelleDescription, Entrepreneur nouveauProprietaire) {
        return new Projet(nouveauNom, nouveauDomaine, nouvelleDescription, nouveauProprietaire);
    }
    public List<Entrepreneur> getEntrepreneurs() {
        return entrepreneurs;
    }
        // Méthodes pour Entrepreneurs
    public void ajouterEntrepreneur(Entrepreneur e) {
        entrepreneurs.add(e);
    }


  public void supprimerEntrepreneur(Entrepreneur entrepreneur) {
    try {
        if (entrepreneurs.contains(entrepreneur)) {
            entrepreneurs.remove(entrepreneur);
        } else {
            throw new IllegalArgumentException("L'entrepreneur n'existe pas dans la liste.");
        }
    } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
       
    }
}

   // Méthodes pour Investisseurs
    public void ajouterInvestisseur(Investisseur investisseur) throws ErreurEvent {
        if (investisseurs.contains(investisseur)) {
            throw new ErreurEvent("L'investisseur existe déjà.");
        }
        investisseurs.add(investisseur);
    }

 
   public void supprimerInvestisseur(Investisseur investisseur) {
    try {
        if (investisseurs.contains(investisseur)) {
            investisseurs.remove(investisseur);
        } else {
            throw new IllegalArgumentException("L'investisseur n'existe pas dans la liste.");
        }
    } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
       
    }
}
    public List<Investisseur> getInvestisseurs() {
        return investisseurs;
    }

    // Ajouter une ressource
    public void ajouterRessource(Ressource ressource) throws ErreurEvent {
        if (tab.contains(ressource)) {
            throw new ErreurEvent("La ressource existe déjà.");
        }
        tab.add(ressource);
        System.out.println("Ressource ajoutée avec succès.");
    }

 
  public void supprimerRessource(Ressource r) throws ErreurEvent{
    try {
        if (tab.contains(r)) {
            tab.remove(r);
        } else {
            throw new ErreurEvent("aucun ressource a supprimer .");
        }
    } catch (ErreurEvent e) {
        System.err.println(e.getMessage());
       
    }
}
    // Obtenir la liste des ressources
    public List<Ressource> getRessources() {
        return tab;
    }


    // Méthode pour calculer le total des montants des ressources
    public double calculerTotalMontant() {
        return tab.stream()
                  .mapToDouble(Ressource::getMontant)
                  .sum();
    }

    // Méthode pour trouver une ressource par ID
    public Optional<Ressource> trouverRessourceParID(int ressourceID) {
        return tab.stream()
                  .filter(ressource -> ressource.getRessourceID() == ressourceID)
                  .findFirst();
    }

   
    // Méthodes pour Conference
    public void ajouterconference(Conference conference) throws ErreurEvent {
        if (tab2.contains(conference)) {
            throw new ErreurEvent("La  conference existe déjà.");
        }
        tab2.add(conference); 
    }
  public void supprimerConference(Conference conference) throws ErreurEvent{
    try {
        if (tab2.contains(conference)) {
            tab2.remove(conference);
        } else {
            throw new ErreurEvent("aucun conference a supprimer .");
        }
    } catch (ErreurEvent e) {
        System.err.println(e.getMessage());
       
    }
}
 public List<Conference> getConference() {
        return tab2;
    }     

    // Méthodes pour Formations
    public void ajouterFormation( Formation formation) throws ErreurEvent {
        if (formations.contains(formation)) {
            throw new ErreurEvent("La formation existe déjà.");
        }
        formations.add(formation);
    }
   public void supprimerFormation(Formation formation) throws ErreurEvent{
    try {
        if (formations.contains(formation)) {
            formations.remove(formation);
        } else {
            throw new ErreurEvent("aucun formation a supprimer .");
        }
    } catch (ErreurEvent e) {
        System.err.println(e.getMessage());
       
    }
}
 public List<Formation> getFormation() {
        return formations;
    }   

  
    // Méthodes pour Formatteurs
    public void ajouterFormatteur(Formateur formatteur) throws ErreurEvent {
        if (formatteurs.contains(formatteur)) {
            throw new ErreurEvent("Le formateur existe déjà.");
        }
        formatteurs.add(formatteur);
    }

    public void supprimerFormatteur(int index) throws ErreurEvent{
        if (index < 0 || index >= formatteurs.size()) {
            throw new ErreurEvent("Index invalide pour supprimer un formatteur.");
        }
        formatteurs.remove(index);
        System.out.println("Formatteur supprimé avec succès.");
    }

    public List<Formateur> getFormatteurs() {
        return formatteurs;
    }


public List<String> obtenirNomsFormatteursTries() {
    List<String> noms = formations.stream()
        .map(Formation::getForm) // Utiliser getForm() pour obtenir le formateur
        .map(Formateur::getNom) // Obtenir le nom du formateur
        .distinct() // Éliminer les doublons
        .sorted() // Trier les noms
        .collect(Collectors.toList());

    System.out.println("Noms des formateurs triés : " + noms);
    return noms;
}
    // Méthodes pour Intervenants
    public void ajouterIntervenant(Intervenant intervenant) throws ErreurEvent {
        if (intervenants.contains(intervenant)) {
            throw new ErreurEvent("L'intervenant existe déjà.");
        }
        intervenants.add(intervenant);
    }

    public void supprimerIntervenant(int index) throws ErreurEvent {
        if (index < 0 || index >= intervenants.size()) {
            throw new ErreurEvent("Index invalide pour supprimer un intervenant.");
        }
        intervenants.remove(index);
        System.out.println("Intervenant supprimé avec succès.");
    }

    public List<Intervenant> getIntervenants() {
        return intervenants;
    }
    
}


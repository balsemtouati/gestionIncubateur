/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Evenements;

import Model.Gestion_Acteurs.Intervenant;
public final class Conference implements Evenement {
    private int duree;
    private double budget;
    private Lieu location;
    private int conferenceId;
    private Intervenant inter;  // Ajout de l'attribut intervenant de type Intervenant

    // Constructeur de la classe
    public Conference(int duree, double budget, Lieu location, int conferenceId, Intervenant inter) {
        this.duree = duree;
        this.budget = budget;
        this.location = location;
        this.conferenceId = conferenceId;
        this.inter = inter;
    }

    // Getters
    public int getDuree() {
        return duree;
    }

    public double getBudget() {
        return budget;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public Intervenant getIntervenant() {
        return inter;
    }

    // Setters
    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setLocation(Lieu location) {
        this.location = location;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void setIntervenant(Intervenant inter) {
        this.inter = inter;
    }
 public void modifierLieu(Lieu l) {
    this.location.setAdresse(l.getAdresse()); // Utilisez le setter pour modifier l'adresse
    this.location.setCapacite(l.getCapacite()); // Utilisez le setter pour modifier la capacité
    this.location.setReserve(l.getReserve()); // Utilisez le setter pour modifier l'état de réservation
}


    // Méthode toString pour afficher les informations de la conférence
    @Override
    public String toString() {
        return "Les informations sur la Conférence {" +
                "Durée=" + duree + " heures" +
                ", Budget=" + budget + " €" +
                ", Lieu=" + location.toString() +
                ", ID Conférence=" + conferenceId +
                ", Intervenant=" + inter.toString() +
                '}';
    }

 
@Override
public String genererRapport() {
    StringBuilder rapport = new StringBuilder();
    rapport.append("Rapport de la Conférence:\n");
    rapport.append("Conférence ID: ").append(conferenceId).append("\n");
    rapport.append("Durée: ").append(duree).append(" heures\n");
    rapport.append("Budget: ").append(budget).append(" €\n");
    
    // Informations concernant le lieu
    rapport.append("*** Informations concernant le lieu ***\n")
           .append("Adresse du lieu: ").append(location.getAdresse()).append("\n")
           .append("Capacité du lieu: ").append(location.getCapacite()).append("\n")
           .append("État de réservation du lieu: ").append(location.getReserve()).append("\n");

    // Informations concernant l'intervenant
    rapport.append("*** Informations concernant l'intervenant ***\n")
           .append("Identifiant: ").append(inter.intervenantId()).append("\n")
           .append("Nom: ").append(inter.nom()).append("\n")
           .append("Prénom: ").append(inter.prenom()).append("\n")
           .append("Fonction: ").append(inter.fonction()).append("\n");
    
    // Afficher le rapport dans la console (facultatif)
    System.out.println(rapport.toString());
    
    return rapport.toString();
}
      public Lieu getLocation() {
            return location;
            
        }
    
}




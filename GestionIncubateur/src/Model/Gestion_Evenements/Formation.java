/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Evenements;
import Model.Gestion_Acteurs.Formateur;
public final class Formation implements Evenement {
    private int duree;
    private double budget;
    private Lieu location;
    private int formationId;
    private Formateur form; 

    // Constructeur
    public Formation(int duree, double budget, Lieu location, int formationId, Formateur form) {
        this.duree = duree;
        this.budget = budget;
        this.location = location;
        this.formationId = formationId;
        this.form = form;  
    }

    // Getters
    public int getDuree() {
        return duree;
    }

    public double getBudget() {
        return budget;
    }

    public int getFormationId() {
        return formationId;
    }

    public Formateur getForm() {
        return form;
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

    public void setFormationId(int formationId) {
        this.formationId = formationId;
    }

    public void setForm(Formateur form) {
        this.form = form;
    }
   public void modifierLieu(Lieu l) {
    this.location.setAdresse(l.getAdresse()); // Utilisez le setter pour modifier l'adresse
    this.location.setCapacite(l.getCapacite()); // Utilisez le setter pour modifier la capacité
    this.location.setReserve(l.getReserve()); // Utilisez le setter pour modifier l'état de réservation
}
    @Override
    public String toString() {
        return "Les informations sur la formation {" +
                "ID Formation=" + formationId +
                "Durée=" + duree + " heures" +
                ", Budget=" + budget + " €" +
                ", les informations concernant le lieu =" +"\n"+ "l'adresse du lieu ="+location.getAdresse() +"\n"+"la capacité du lieu :"
                +location.getCapacite()+"\n"+"l'etat de réservation du lieu :"+location.getReserve()+
                ", les informations concernant le formateur=" 
                + "son identifiant : "+form.getFormateurId() 
                + "son nom: "+form.getNom() +
                "son prénom est :"+form.getPrenom()+
               
                '}';
    }

    // Méthode pour générer le rapport de la formation
    @Override
    public String genererRapport() {
        StringBuilder rapport = new StringBuilder();
        rapport.append("Rapport de la Formation:\n");
        rapport.append("Identifiant du formation : ").append(formationId ).append(" heures\n");
        rapport.append("Durée: ").append(duree).append(" heures\n");
        rapport.append("Budget: ").append(budget).append(" €\n");
        rapport.append("*******les informations concernant le lieu *******").append("\n");
        rapport.append("l'adresse du lieu :"+location.getAdresse() +"\n"+"la capacité du lieu :"
                +location.getCapacite()+"\n"+"l'etat de réservation du lieu :"+location.getReserve()).append("\n");

        rapport.append("****les informations concernant le formateur*****" +"\n"+
                 "son identifiant : "+form.getFormateurId() +"\n"+
                "son nom: "+form.getNom() +"\n"+
                "son prénom est :"+form.getPrenom()).append("\n");
// Afficher le rapport dans la console (facultatif)
    System.out.println(rapport.toString());
        
        return rapport.toString();
    }

    

        public Lieu getLocation() {
            return location;
            
        }
    
}
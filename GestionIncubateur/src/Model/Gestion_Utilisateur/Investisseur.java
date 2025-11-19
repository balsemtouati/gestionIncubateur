/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Utilisateur;

public final class Investisseur extends Personne {
    private int investisseurID;
    private int nbInvestissements;
   

    // Constructeur
    public Investisseur(String nom, String prenom, int CIN, String email, int investisseurID, int nbInvestissements) {
        super(nom, prenom, CIN, email);  // Appel au constructeur de la classe parent
        this.investisseurID = investisseurID;
        this.nbInvestissements = nbInvestissements;
       
     
    }

    // Getters
    public int getInvestisseurID() {
        return investisseurID;
    }

    public int getNbInvestissements() {
        return nbInvestissements;
    }

 

    // Setters
    public void setInvestisseurID(int investisseurID) {
        this.investisseurID = investisseurID;
    }

    public void setNbInvestissements(int nbInvestissements) {
        this.nbInvestissements = nbInvestissements;
    }

    
    // Redéfinition de la méthode toString() pour une meilleure représentation de l'investisseur
    @Override
    public String toString() {
        return getNom() + " " + getPrenom() +" "+ getCin()+" "+getEmail()+" "+getInvestisseurID() +" " + getNbInvestissements() ;
    }
}

  

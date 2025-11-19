/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Projets;

import Model.Gestion_Utilisateur.Investisseur;

public class Ressource {
    private int ressourceID;
    private int Montant;
    private Investisseur inv;  // Nouveau champ de type Investisseur

    // Constructeur avec l'attribut Investisseur ajouté
    public Ressource(int ressourceID, int Montant, Investisseur inv) {
        this.ressourceID = ressourceID;
        this.Montant = Montant;
        this.inv = inv;  // Initialisation du champ inv
    }

    // Getters pour tous les champs
    public int getRessourceID() {
        return ressourceID;
    }

    public int getMontant() {
        return Montant;
    }

    public Investisseur getInv() {
        return inv;  // Getter pour l'attribut inv
    }

    // Setters pour tous les champs
    public void setRessourceID(int ressourceID) {
        this.ressourceID = ressourceID;
    }

    public void setMontant(int Montant) {
        this.Montant = Montant;
    }

    public void setInv(Investisseur inv) {
        this.inv = inv;  // Setter pour l'attribut inv
    }}

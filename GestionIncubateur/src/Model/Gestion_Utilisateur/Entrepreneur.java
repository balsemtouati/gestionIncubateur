/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Utilisateur;

import Model.Gestion_Projets.Projet;
import java.util.List;
import java.util.ArrayList;


public final class Entrepreneur extends Personne {
    private int entrepreneurID;
    private int tailleEquipe;

    // Constructeur
    public Entrepreneur(String nom, String prenom, int CIN, String email, int entrepreneurID, int tailleEquipe) {
        super(nom, prenom, CIN, email);  
        this.entrepreneurID = entrepreneurID;
        this.tailleEquipe = tailleEquipe;
    }

    // Getters
    public int getEntrepreneurID() {
        return entrepreneurID;
    }

    public int getTailleEquipe() {
        return tailleEquipe;
    }

    // Setters
    public void setEntrepreneurID(int entrepreneurID) {
        this.entrepreneurID = entrepreneurID;
    }

    public void setTailleEquipe(int tailleEquipe) {
        this.tailleEquipe = tailleEquipe;
    }

    
    // Redéfinition de la méthode toString() pour une meilleure représentation de l'entrepreneur
    @Override
    public String toString() {
        return getNom() + " " + getPrenom() +" " +getCin()+" "+getEmail()+" "+tailleEquipe + " " + entrepreneurID ;
    }
}
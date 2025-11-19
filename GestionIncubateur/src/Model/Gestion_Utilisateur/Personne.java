/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Utilisateur;


public   sealed class Personne permits Entrepreneur, Investisseur, Administrateur {
    private String nom;
    private String prenom;
    private int CIN;
    private String email;
    

    public Personne(String nom, String prenom, int CIN, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.email = email;
        
    }
    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public int getCin(){
        return CIN;
    }
    public String getEmail(){
        return email;
    }
  
 }

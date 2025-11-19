/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Acteurs;
public class Formateur {
private int formateurId;
private String nom;
private String prenom;

public Formateur(int formatteurId,String nom,String prenom){
    this.formateurId=formatteurId;
    this.nom=nom;
    this.prenom=prenom;
    
}
public int getFormateurId(){return formateurId;}
public String getNom(){return nom;}
public String getPrenom(){return prenom;}

@Override
public String toString(){
    return formateurId+ nom+prenom ;
}
}

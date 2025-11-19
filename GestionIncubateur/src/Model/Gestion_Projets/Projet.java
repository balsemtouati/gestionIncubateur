/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Projets;

import Model.Gestion_Utilisateur.Entrepreneur;

public class Projet {
private String nom;
private String domaine;
private String description;
private Entrepreneur proprietaire;

public Projet(String nom, String domaine,String description ,Entrepreneur proprieataire)
{
    this.nom=nom;
    this.domaine=domaine;
    this.description=description;
    this.proprietaire=proprieataire;
}
public String nom(){
    return nom;
}
public String domaine(){
    return domaine;
}
public String description(){
    return description;
}

public Entrepreneur proprietaire(){
    return proprietaire;
}


    @Override
    public String toString() {
        return  " Nom: " + nom + ", Domaine: " + domaine + ", Description: " + description + ", Propriétaire: " + proprietaire;
    }

}
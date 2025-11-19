/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Acteurs;

public record Intervenant(int intervenantId, String nom,String prenom ,String fonction) {
@Override
public String toString(){
    return "l'identifiant de l'intervenant est :"+intervenantId+
            ", son nom :"+nom+
            ", son prenom "+prenom+
            ",sa fonction :"+fonction;
}
}

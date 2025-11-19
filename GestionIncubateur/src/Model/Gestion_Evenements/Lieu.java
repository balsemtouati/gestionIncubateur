/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Gestion_Evenements;

public class Lieu {
 private String adresse;
 private int capacite;
 private boolean reserve;
 public Lieu(String adresse,int capacite,boolean reserve){
     this.adresse=adresse;
     this.capacite=capacite;
     this.reserve=reserve;
 }
 //*********les getters************
public String getAdresse(){
    return adresse;
}
public int getCapacite(){
    return capacite;
} 
public boolean getReserve(){
    return reserve;
}

//**********les setters********
public void setAdresse(String adr){
    adresse=adr;
}
public void setCapacite(int cap){
    capacite=cap;
} 
public void setReserve(boolean res){
    reserve=res;
}

public String toString(){
    return 
       adresse +capacite+reserve;
           
}



}

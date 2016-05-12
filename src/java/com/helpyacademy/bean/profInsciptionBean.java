/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.util.Utils;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author youssefsafi
 */
@ManagedBean
@ViewScoped
public class profInsciptionBean implements Serializable{
    
    private int id;
    private String civilite;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String tel;
    private String email;
    private String mdp;
    private String mdpConfirm;
    private Date date_naissance;
    private String situation_pro;
    private String niv_etude;    
    private int etape = 1;
    
    
    public int getId() {
        return id;
    }

    public int getEtape() {
        return etape;
    }

    public String getMdpConfirm() {
        return mdpConfirm;
    }

    public String getCivilite() {
        return civilite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getSituation_pro() {
        return situation_pro;
    }

    public String getNiv_etude() {
        return niv_etude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setMdpConfirm(String mdpConfirm) {
        this.mdpConfirm = mdpConfirm;
    }

    public void setSituation_pro(String situation_pro) {
        this.situation_pro = situation_pro;
    }

    public void setNiv_etude(String niv_etude) {
        this.niv_etude = niv_etude;
    }

    public void setEtape(int etape) {
        this.etape = etape;
    }
    
    /* ====================================================================== */
    
    public void next(){
        if(etape == 1){
            if(!mdp.equals(mdpConfirm)){
                Utils.addMessage("Confirmation du mot de passe est erroné");
            } else {
                etape ++;
            }            
        } else if(etape == 2){
            etape ++;
        } 
    }
    
    public void previous(){
        if(etape == 2 || etape == 3){
            etape --;
        }
    }
    
    public void finish(){
        
    }
    
    public void reset(){
        
    }
}

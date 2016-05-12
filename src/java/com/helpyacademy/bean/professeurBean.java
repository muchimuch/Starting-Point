/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author youssefsafi
 */
@ManagedBean
@SessionScoped
public class professeurBean implements Serializable{
    
    private int id;
    private String civilite;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String tel;
    private String email;
    private String mdp;
    private Date date_naissance;
    private String situation_pro;
    private String niv_etude;    

    public professeurBean() {
    }

    public int getId() {
        return id;
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

    public void setSituation_pro(String situation_pro) {
        this.situation_pro = situation_pro;
    }

    public void setNiv_etude(String niv_etude) {
        this.niv_etude = niv_etude;
    }
    
    
    /* ====================================================================== */
    
    public void reset(){
        
    }
}

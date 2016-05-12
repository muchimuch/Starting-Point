/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao.model;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author youssefsafi
 */
@Entity
@Table(name="professeur")
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String disponibilite;
    private String token;
    private String compte_active;

    public Professeur() {
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

    public String getDisponibilite() {
        return disponibilite;
    }

    public String getToken() {
        return token;
    }

    public String getCompte_active() {
        return compte_active;
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

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCompte_active(String compte_active) {
        this.compte_active = compte_active;
    }

    public void setNiv_etude(String niv_etude) {
        this.niv_etude = niv_etude;
    }

    public String getNiv_etude() {
        return niv_etude;
    }
    
}

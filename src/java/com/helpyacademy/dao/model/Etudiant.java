/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author youssefsafi
 */
@Entity
@Table(name = "etudiant")
public class Etudiant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")    
    private String prenom;
    @Column(name = "tel")    
    private String tel;
    @Column(name = "adresse")    
    private String adresse;
    @Column(name = "ville")    
    private String ville;
    @Column(name = "genre")    
    private String genre;
    @Column(name = "email",unique = true)    
    private String email;
    @Column(name = "mdp")    
    private String mdp;
    @Column(name = "solde")  
    private float solde;
    @Column(name = "date_inscription")
    private Date date_inscription;
    @Column(name="compte_active")
    private boolean compte_active;
    @Column(name="token")
    private String token;
    
    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String tel, String adresse, String ville, String genre, String email, String mdp, Date date_inscription, boolean compte_active, String token) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.ville = ville;
        this.genre = genre;
        this.email = email;
        this.mdp = mdp;
        this.date_inscription = date_inscription;
        this.compte_active = compte_active;
        this.token = token;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public boolean isCompte_active() {
        return compte_active;
    }

    public void setCompte_active(boolean compte_active) {
        this.compte_active = compte_active;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getGenre() {
        return genre;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public float getSolde() {
        return solde;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}

package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Niveau;
import com.helpyacademy.service.EtudiantService;
import com.helpyacademy.util.Utils;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author youssefsafi
 */
@ManagedBean
@SessionScoped
public class EtudiantBean implements Serializable{
    
    private int id;
    private String nom;    
    private String prenom;    
    private String tel;    
    private String adresse;    
    private String ville;    
    private String genre;    
    private String email;    
    private String mdp;  
    private String mdpConfirm;
    private float solde;
    private Date date_inscription;
    private boolean compte_active;
    private String token;
    private boolean isPwdValid;
    private boolean emailExiste;
    private int niveau;
    
    private EtudiantService etudiantService;
    
    
    public EtudiantBean() {
    }

    public void setEtudiantService(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
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

    public Date getDate_inscription() {
        return date_inscription;
    }

    public boolean isCompte_active() {
        return compte_active;
    }

    public String getToken() {
        return token;
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

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public void setCompte_active(boolean compte_active) {
        this.compte_active = compte_active;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMdpConfirm() {
        return mdpConfirm;
    }

    public void setMdpConfirm(String mdpConfirm) {
        this.mdpConfirm = mdpConfirm;
    }

    public void setIsPwdValid(boolean isPwdValid) {
        this.isPwdValid = isPwdValid;
    }

    public boolean isIsPwdValid() {
        return isPwdValid;
    }

    public void setEmailExiste(boolean emailExiste) {
        this.emailExiste = emailExiste;
    }

    public boolean isEmailExiste() {
        return emailExiste;
    }
    
    public String dateInscriptionStr(){
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRENCH);
        return df.format(date_inscription);
    }
    
    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
    // -------------------------------------------------------------------------

    public String inscrire(){
        isPwdValid = mdp.equals(mdpConfirm); 
        if(isPwdValid){
            emailExiste = etudiantService.emailExiste(email);
            if(emailExiste){
                    Utils.addMessage("L'email existe déjà. Veuillez entrer un autre");
                    return "inscriptionEtudiant.xhtml";
            } else {        
                Niveau niv = new Niveau();
                niv.setId(niveau);
                
                Etudiant etudiant = new Etudiant(nom, prenom, genre, email, mdp, solde, date_inscription,false, niv);
                
                etudiantService.inscrire(etudiant);
            }    
        } else {
            Utils.addMessage("Confirmation du mot de passe est erroné");
        }
        
        return "inscriptionEtudiant.xhtml";
    }
    
    public String login(){
        Etudiant e = etudiantService.login(email,mdp);
        if(e != null){
            
            id = e.getId();
            nom = e.getNom();
            prenom = e.getPrenom();
            email = e.getEmail();
            genre = e.getGenre();
            solde = e.getSolde();
            adresse = e.getAdresse();
            tel = e.getTel();
            date_inscription = e.getDateInscription();
            
            HttpSession session = Utils.getSession();
            session.setAttribute("EtudiantID", id);
            session.setAttribute("EtudiantNom", nom);
            session.setAttribute("EtudiantPrenom", prenom);
            session.setAttribute("theme", "skin-blue");
            
            return "pretty:EspaceE_HOME";
        } else {
            Utils.addMessage("E-mail ou mot de passe incorrect");
            reset();
        }
        return "pretty:connexion_etudiant";
    }
    
    public String logout(){
        reset();
        return etudiantService.logout();
    }
    
    public void reset(){
        id=0;
        nom=null;
        prenom=null;
        email=null;
        mdp=null;
        
    }
    
    public List<Niveau> ListNiveau(){
        return etudiantService.listNiveau();
    }

}

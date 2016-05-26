/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Admin;
import com.helpyacademy.service.AdminService;
import com.helpyacademy.util.Utils;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author youssefsafi
 */

@ManagedBean
@SessionScoped
public class AdminBean implements Serializable{
    
    private String email;
    private String nom;
    private String prenom;
    private String motDePasse;
    private Date dateInscription;
    
    private boolean success;
    private String motDePasseConf;
    private String newMotDePasse;
    
    private String nomM;
    private String prenomM;
    
    private AdminService adminService;

    public AdminBean() {
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMotDePasseConf() {
        return motDePasseConf;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMotDePasseConf(String motDePasseConf) {
        this.motDePasseConf = motDePasseConf;
    }

    public String getNewMotDePasse() {
        return newMotDePasse;
    }

    public void setNewMotDePasse(String newMotDePasse) {
        this.newMotDePasse = newMotDePasse;
    }

    public String getNomM() {
        return nomM;
    }

    public String getPrenomM() {
        return prenomM;
    }

    public void setNomM(String nomM) {
        this.nomM = nomM;
    }

    public void setPrenomM(String prenomM) {
        this.prenomM = prenomM;
    }
    
    /* ------------------------------------------ */
    
    public String modifierInfo(){
        if(nomM.equals(nom) && prenomM.equals(prenom)){
            success = false;
            Utils.addMessage("Vous avez pas modifier vos informations");
        } else {
            if(adminService.modifierInfo(nomM,prenomM)){
                success = true;
                Utils.addMessage("Vos informations ont été bien enregistré");
                nom = nomM;
                prenom = prenomM;
            } else {
                success = false;
                Utils.addMessage("Vos informations n'ont pas été enregistré");
            }
        }
        return "pretty:EspaceA_Profil";
    }
    
    public String modifierMdp(){
        if(newMotDePasse.equals(motDePasseConf)){
            if(newMotDePasse.length() >= 8){
                if(adminService.mdpCorrect(email,motDePasse)){
                    if(adminService.changerMdp(email,newMotDePasse)){
                        Utils.addMessage("Votre mot de passe a été bien modifier");
                        success = true;
                    } else {
                        Utils.addMessage("Votre mot de passe n'a pas été modifier. Essayez une autre fois");
                        success = false;
                    }
                } else {
                    Utils.addMessage("l'ancien mot de passe est incorrect ");
                    success = false;
                }
            } else {
                Utils.addMessage("le mot de passe doit contenir au moin 8 caractéres");
                success = false;
            }
        } else {
            Utils.addMessage("Veuillez confirmer votre nouveau mot de passe");
            success = false;
        }
        return "pretty:EspaceA_Profil";
    }
    
    public String login(){
        Admin admin = adminService.login(email, motDePasse);
        if(admin != null){
            nom = admin.getNom();
            prenom = admin.getPrenom();
            nomM = nom;
            prenomM = prenom;
            dateInscription = admin.getDateInscription();
            email = admin.getEmail();
            motDePasse = null;
            
            HttpSession session = Utils.getSession();
            session.setAttribute("AdmineEmail", email);
            session.setAttribute("theme", "skin-purple-light");
            
            return "pretty:EspaceA_HOME";
        }
        
        Utils.addMessage("E-mail ou mot de passe incorrect");
        return "pretty:connexion_admin";
    }
    
    public String logout(){
        Utils.getSession().invalidate();
        return "pretty:connexion_admin";
    }
    
    public String dateInscriptionStr(){
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRENCH);
        return df.format(dateInscription);
    }
    
}

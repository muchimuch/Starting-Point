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
    
    /* ------------------------------------------ */
    
    public String login(){
        Admin admin = adminService.login(email, motDePasse);
        if(admin != null){
            nom = admin.getNom();
            prenom = admin.getPrenom();
            dateInscription = admin.getDateInscription();
            
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

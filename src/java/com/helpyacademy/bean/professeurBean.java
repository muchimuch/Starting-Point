/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.service.ProfesseurService;
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
public class professeurBean implements Serializable {

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
    private Date dateInscription;

    private ProfesseurService professeurService;

    public professeurBean() {
    }

    public void setProfesseurService(ProfesseurService professeurService) {
        this.professeurService = professeurService;
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

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    /* ====================================================================== */
    public String login() {
        //Etudiant e = etudiantService.login(email,mdp);
        Professeur p = professeurService.login(email, mdp);
        if (p != null) {
            if (p.getCompteActive() == '0') {
                p = null;
                Utils.addMessage("Veuillez confirmer votre E-mail");
            } else if (p.getCompteActive() == '1') {
                p = null;
                Utils.addMessage("Votre compte n'est pas encore activé. Nous allons vous tien au courant par email lorsqu'on traitons votre demande d'inscription");
            } else {
                id = p.getId();
                nom = p.getNom();
                prenom = p.getPrenom();
                dateInscription = p.getDateInscription();

                HttpSession session = Utils.getSession();
                session.setAttribute("ProfesseurID", id);
                session.setAttribute("ProfesseurNom", nom);
                session.setAttribute("ProfesseurPrenom", prenom);
                session.setAttribute("theme", "skin-red-light");

                return "pretty:EspaceP_HOME";
            }
        } else {
            Utils.addMessage("E-mail ou mot de passe incorrect");
            reset();
        }
        return "pretty:connexion_professeur";
    }

    public String logout() {
        Utils.getSession().invalidate();
        return "pretty:connexion_professeur";
    }

    public String dateInscriptionStr() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRENCH);
        return df.format(dateInscription);
    }

    public void reset() {

    }
}

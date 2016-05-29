/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.service.ProfesseurService;
import com.helpyacademy.util.Utils;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Transient;
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
    private String oldPassword;
    private String newPassword;
    private String confPassword;
    private boolean success;
    private String diplome;
    private String diplomeM;
    private int idDiplomeM;
    private Professeur idProf;
    
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

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public void setDiplomeM(String diplomeM) {
        this.diplomeM = diplomeM;
    }

    public String getDiplomeM() {
        return diplomeM;
    }

    /* ====================================================================== */
    public String modifierDiplome() {
        if(professeurService.diplomeExiste(diplomeM)){
            Utils.addMessage("le diplome [ "+diplomeM+" ] existe deja");
            success = false;
        } else if(professeurService.modifierDiplome(idProf,idDiplomeM,diplomeM)){
            Utils.addMessage("Votre diplome a été bien modifier");
            success = true;
        } else {
            Utils.addMessage("Votre diplome n'a pas été modifier");
            success = false;
        }
        return "pretty:EspaceP_Profil";
    }

    public void initUpdateDiplome(Diplome d) {
        diplomeM = d.getDiplome();
        idDiplomeM = d.getId();
        idProf = d.getIdProf();
    }

    public String deleteDiplome(Diplome d) {
        if (professeurService.deleteDiplome(d)) {
            Utils.addMessage("Votre diplome a été bien supprimé");
            success = true;
        } else {
            Utils.addMessage("Votre diplome n'a pas été supprimé");
            success = false;
        }
        return "pretty:EspaceP_Profil";
    }

    public List<Diplome> getDiplomes() {
        return professeurService.getDiplomes();
    }

    public String ajoutDiplome() {
        if(professeurService.diplomeExiste(diplome)){
            Utils.addMessage("le diplome [ "+diplome+" ] existe deja");
            success = false;
        } else if (professeurService.ajoutDiplome(diplome)) {
            Utils.addMessage("Votre diplome a été bien ajouté");
            success = true;
            diplome = null;
        } else {
            Utils.addMessage("Votre diplome n'a pas été ajouté");
            success = false;
        }
        return "pretty:EspaceP_Profil";
    }

    public String modifierMdp() {
        if (newPassword.equals(confPassword)) {
            if (newPassword.length() >= 8) {
                if (professeurService.mdpCorrect(email, oldPassword)) {
                    if (professeurService.changerMdp(email, newPassword)) {
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
        return "pretty:EspaceP_Profil";
    }

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

                civilite = p.getCivilite();
                adresse = p.getAdresse();
                ville = p.getVille();
                tel = p.getVille();
                date_naissance = p.getDateNaissance();
                situation_pro = p.getSituationPro();
                niv_etude = p.getNivEtude();

                HttpSession session = Utils.getSession();
                session.setAttribute("IDP", id);
                session.setAttribute("NomP", nom);
                session.setAttribute("PrenomP", prenom);
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

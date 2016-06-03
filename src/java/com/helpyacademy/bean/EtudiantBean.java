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
public class EtudiantBean implements Serializable {

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

    private boolean success;
    private String newMotDePasse;
    private String motDePasseConf;
    private String OldMotDePasse;

    private String nomM;
    private String prenomM;
    private String villeM;
    private String adresseM;
    private String telM;
    private int nivM;

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

    public String dateInscriptionStr() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRENCH);
        return df.format(date_inscription);
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getNewMotDePasse() {
        return newMotDePasse;
    }

    public String getMotDePasseConf() {
        return motDePasseConf;
    }

    public String getOldMotDePasse() {
        return OldMotDePasse;
    }

    public void setNewMotDePasse(String newMotDePasse) {
        this.newMotDePasse = newMotDePasse;
    }

    public void setMotDePasseConf(String motDePasseConf) {
        this.motDePasseConf = motDePasseConf;
    }

    public void setOldMotDePasse(String OldMotDePasse) {
        this.OldMotDePasse = OldMotDePasse;
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

    public String getVilleM() {
        return villeM;
    }

    public String getAdresseM() {
        return adresseM;
    }

    public String getTelM() {
        return telM;
    }

    public int getNivM() {
        return nivM;
    }

    public void setVilleM(String villeM) {
        this.villeM = villeM;
    }

    public void setAdresseM(String adresseM) {
        this.adresseM = adresseM;
    }

    public void setTelM(String telM) {
        this.telM = telM;
    }

    public void setNivM(int nivM) {
        this.nivM = nivM;
    }

    // -------------------------------------------------------------------------
    
    public String modifierInfo() {
        if (nomM.equals(nom) && prenomM.equals(prenom) && villeM.equals(ville) && adresse.equals(adresseM) && telM.equals(tel) && nivM == niveau) {
            success = false;
            Utils.addMessage("Vous avez pas modifier vos informations");
        } else if (etudiantService.changerInfo(nomM, prenomM, villeM, adresseM, telM, nivM)) {
            success = true;
            Utils.addMessage("Vos informations ont été bien enregistré");
            nom = nomM;
            prenom = prenomM;
            ville = villeM;
            adresse = adresseM;
            tel = telM;
            niveau = nivM;
        } else {
            success = false;
            Utils.addMessage("Vos informations n'ont pas été enregistré");
        }
        return "pretty:EspaceE_Profil";
    }

    public void initUpdate() {
        nomM = nom;
        prenomM = prenom;
        villeM = ville;
        adresseM = adresse;
        telM = tel;
        nivM = niveau;
    }

    public String modifierMdp() {
        if (newMotDePasse.equals(motDePasseConf)) {
            if (newMotDePasse.length() >= 8) {
                if (etudiantService.mdpCorrect(email, OldMotDePasse)) {
                    if (etudiantService.changerMdp(email, newMotDePasse)) {
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
        return "pretty:EspaceE_Profil";
    }

    public String getNiveauEtude() {
        return etudiantService.getNiveauEtude();
    }

    public String inscrire() {
        isPwdValid = mdp.equals(mdpConfirm);
        if (isPwdValid) {
            emailExiste = etudiantService.emailExiste(email);
            if (emailExiste) {
                Utils.addMessage("L'email existe déjà. Veuillez entrer un autre");
            } else {
                Niveau niv = new Niveau();
                niv.setId(niveau);
                Etudiant etudiant = new Etudiant(nom, prenom, genre, email, mdp, solde, date_inscription, false, niv);
                if (etudiantService.inscrire(etudiant)) {
                    return "pretty:inscription_faite";
                } else {
                    Utils.addMessage("Veuillez renseigner correctement les champs");
                }
            }
        } else {
            Utils.addMessage("Confirmation du mot de passe est erroné");
        }

        return "pretty:inscriptionE";
    }

    public String login() {
        Etudiant e = etudiantService.login(email, mdp);
        if (e != null) {
            if (e.getCompteActive()) {
                id = e.getId();
                nom = e.getNom();
                prenom = e.getPrenom();
                email = e.getEmail();
                genre = e.getGenre();
                solde = e.getSolde();
                adresse = e.getAdresse();
                tel = e.getTel();
                date_inscription = e.getDateInscription();
                ville = e.getVille();
                nivM = e.getNiveau().getId();
                
                HttpSession session = Utils.getSession();
                session.setAttribute("EtudiantID", id);
                session.setAttribute("EtudiantNom", nom);
                session.setAttribute("EtudiantPrenom", prenom);
                session.setAttribute("theme", "skin-blue");

                return "pretty:EspaceE_HOME";
            } else {
                e = null;
                Utils.addMessage("Veuillez confirmer votre E-mail !");
            }
        } else {
            Utils.addMessage("E-mail ou mot de passe incorrect");
            reset();
        }
        return "pretty:connexion_etudiant";
    }

    public String logout() {
        reset();
        return etudiantService.logout();
    }

    public void reset() {
        id = 0;
        nom = null;
        prenom = null;
        email = null;
        mdp = null;
    }

    public List<Niveau> ListNiveau() {
        return etudiantService.listNiveau();
    }

}

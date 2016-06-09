/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.service.TrouverSoutienService;
import com.helpyacademy.util.Utils;
import com.sun.faces.facelets.tag.jsf.core.AjaxHandler;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author youssefsafi
 */
@ManagedBean
@RequestScoped
public class TrouverSoutienBean {

    private int idM;
    private int idNiv;
    private boolean success;

    private TrouverSoutienService trouverSoutienService;

    private boolean Ccadeau20min;
    private int CidP;
    private int CidM;
    private float Cprix;
    private String matiere;

    private boolean cadeau20min;
    private int duree;
    private float prixTotal;
    private float prix;
    private String heureDebut;
    private String dateDebut;
    private String titre;
    private String description;
    private String prof;
    private String niveau;
    private String nivMatiere;

    private String nivEtudiant;
    private String etudiant;

    private List<Conference> listCommandes;

    public void setTrouverSoutienService(TrouverSoutienService trouverSoutienService) {
        this.trouverSoutienService = trouverSoutienService;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public void setIdNiv(int idNiv) {
        this.idNiv = idNiv;
    }

    public int getIdM() {
        return idM;
    }

    public int getIdNiv() {
        return idNiv;
    }

    public String getMatiere() {
        return matiere;
    }

    public boolean isCadeau20min() {
        return cadeau20min;
    }

    public int getDuree() {
        return duree;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setCadeau20min(boolean cadeau20min) {
        this.cadeau20min = cadeau20min;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCcadeau20min() {
        return Ccadeau20min;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Conference> getListCommandes() {
        return listCommandes;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getProf() {
        return prof;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getNivEtudiant() {
        return nivEtudiant;
    }

    public String getEtudiant() {
        return etudiant;
    }

    public void setNivEtudiant(String nivEtudiant) {
        this.nivEtudiant = nivEtudiant;
    }

    public void setEtudiant(String etudiant) {
        this.etudiant = etudiant;
    }

    public String getNivMatiere() {
        return nivMatiere;
    }

    // ----------------------------------------------------------------------
    public String annulerP(Conference conf) {
        if (conf.getStatut() == 0) {
            if (trouverSoutienService.updateStatut(conf, 3,1)) {
                success = true;
                Utils.addMessage("la commande a été annulé. ");
            } else {
                success = true;
                Utils.addMessage("la commande n'a pas été annulé. ");
            }
        }
        return "pretty:EspaceP_GestionCMD";
    }

    public String accepter(Conference conf) {
        if (conf.getStatut() == 0) {
            if (trouverSoutienService.updateStatut(conf, 1,1)) {
                success = true;
                Utils.addMessage("la commande a été bien accepté. ");
            } else {
                success = true;
                Utils.addMessage("la commande a pas été accepté. ");
            }
        }
        return "pretty:EspaceP_GestionCMD";
    }

    public boolean emptyListCommandesP() {
        listCommandes = trouverSoutienService.listCommandesP();
        return listCommandes.isEmpty();
    }

    public void plusInfoP(Conference conf) {
        Ccadeau20min = conf.getCadeau20Min();
        titre = conf.getTitre();
        description = conf.getDescription();
        duree = conf.getDuree();
        prixTotal = conf.getPrix();
        dateDebut = conf.getDateDebut().toString();
        heureDebut = conf.getHeureDebutString();
        matiere = conf.getIdMatiere().getMatiere();
        niveau = conf.getIdMatiere().getIdNiveau().getNiveau();
        etudiant = conf.getIdEleve().getNom().toUpperCase() + " " + conf.getIdEleve().getPrenom().toUpperCase();
        nivEtudiant = conf.getIdEleve().getNiveau().getNiveau();
        nivMatiere = conf.getIdMatiere().getIdNiveau().getNiveau();
    }

    public void plusInfo(Conference conf) {
        Ccadeau20min = conf.getCadeau20Min();
        titre = conf.getTitre();
        description = conf.getDescription();
        duree = conf.getDuree();
        prixTotal = conf.getPrix();
        dateDebut = conf.getDateDebut().toString();
        heureDebut = conf.getHeureDebutString();
        matiere = conf.getIdMatiere().getMatiere();
        niveau = conf.getIdMatiere().getIdNiveau().getNiveau();
        prof = conf.getIdProf().getNom().toUpperCase() + " " + conf.getIdProf().getPrenom().toUpperCase();
    }

    public void initUpdate(Conference conf) {

    }

    public String dureeTotal() {
        int h = duree / 60;
        int m = duree % 60;
        String d = h + "h ";
        if (m != 0) {
            d += m + "min";
        }
        if (Ccadeau20min) {
            d += " + 20 min Cadeau";
        }
        return d;
    }
    
    public String annuler(Conference conf) {
        if (conf.getStatut() == 0) {
            if (trouverSoutienService.updateStatut(conf, 3,0)) {
                success = true;
                Utils.addMessage("Votre commande a été annulé. ");
            } else {
                success = true;
                Utils.addMessage("Votre commande n'a pas été annulé. ");
            }
        }
        return "pretty:EspaceE_MesCommandes";
    }

    public boolean emptyListCommandes() {
        listCommandes = trouverSoutienService.listCommandes();
        return listCommandes.isEmpty();
    }

    public String validerCMD() {
        if (trouverSoutienService.ajouterCMD(CidM, CidP, titre, description, dateDebut, heureDebut, duree, prixTotal, Ccadeau20min, cadeau20min)) {
            success = true;
            Utils.addMessage("Votre commande a été bien enregistré. ");

        } else {
            success = false;
            Utils.addMessage("Votre commande n'a pas été enregistré. Veuillez saisir correctement les informations du formulaire");
        }
        return "pretty:EspaceE_TrouvezSoutien";
    }

    public void actualisePrix(ValueChangeEvent e) {
        prixTotal = ((int) e.getNewValue() * prix) / 60;
    }

    public void initCommande(int idM, int idP, boolean cadeau, float prix) {
        CidM = idM;
        CidP = idP;
        Ccadeau20min = cadeau;
        Cprix = prix;
        cadeau20min = false;
        matiere = trouverSoutienService.getMatiereNomByID(idM);
        duree = 60;
        prixTotal = prix;
        this.prix = prix;
        success = false;
        titre = null;
        description = "";
        dateDebut = null;
        heureDebut = null;
    }

    public Professeur getProfByID(int idp) {
        return trouverSoutienService.getProfByID(idp);
    }

    public List<Enseigner> listMatiereEns() {
        return trouverSoutienService.listMatiereEns(idM);
    }

    public String TrouverSoutien() {
        description = "";
        return "pretty:EspaceE_TrouvezSoutien";
    }

}

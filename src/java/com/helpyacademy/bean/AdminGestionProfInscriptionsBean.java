/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.service.AdminService;
import com.helpyacademy.util.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mkerm
 */
@ManagedBean
@RequestScoped
public class AdminGestionProfInscriptionsBean {

    private AdminService adminService;
    private List<Professeur> inscriptions = new ArrayList<>();
    private List<Diplome> diplomes = new ArrayList<>();
    private String nom;
    private String email;
    private boolean success;

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public List<Professeur> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Professeur> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Diplome> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(List<Diplome> diplomes) {
        this.diplomes = diplomes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //-- useful methods
    public boolean emptyInscriptions() {
        inscriptions = adminService.getNewInscriptions();
        return inscriptions.isEmpty();
    }

    public void getProfDiplomes(Professeur p) {
        diplomes = (ArrayList<Diplome>) adminService.getProfDiplomes(p);
        nom = p.getNom() + " " + p.getPrenom();
    }

    public String Accepter(Professeur p) {
        nom = p.getNom() + " " + p.getPrenom();
        if (adminService.accepterProfInscription(p)) {
            success = true;
            Utils.addMessage("Professeur " + nom + " est Accepté.");
        } else {
            success = false;
            Utils.addMessage("Erreur lors de l'acciptation du professeur " + nom);
        }
        return "pretty:GestionProfInscription";
    }

    public String Refuser(Professeur p) {
        nom = p.getNom() + " " + p.getPrenom();
        if (adminService.refuserProfInscription(p)) {
            success = true;
            Utils.addMessage("Professeur " + nom + " est Refusé.");
        } else {
            success = false;
            Utils.addMessage("Erreur lors de la suppression du professeur " + nom);
        }
        return "pretty:GestionProfInscription";
    }
}

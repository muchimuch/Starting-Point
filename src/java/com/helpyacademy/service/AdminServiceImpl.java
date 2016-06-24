/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.AdminDAO;
import com.helpyacademy.dao.ConferenceDAO;
import com.helpyacademy.dao.EnseignerDAO;
import com.helpyacademy.dao.EtudiantDAO;
import com.helpyacademy.dao.ProfesseurDAO;
import com.helpyacademy.dao.model.Admin;
import com.helpyacademy.util.Utils;

/**
 *
 * @author youssefsafi
 */
public class AdminServiceImpl implements AdminService{

    private AdminDAO adminDAO;
    private EtudiantDAO etudiantDAO;
    private ConferenceDAO conferenceDAO;
    private EnseignerDAO enseignerDAO;
    private ProfesseurDAO professeurDAO;

    public void setProfesseurDAO(ProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }
    
    public void setConferenceDAO(ConferenceDAO conferenceDAO) {
        this.conferenceDAO = conferenceDAO;
    }

    public void setEnseignerDAO(EnseignerDAO enseignerDAO) {
        this.enseignerDAO = enseignerDAO;
    }
    
    public void setEtudiantDAO(EtudiantDAO etudiantDAO) {
        this.etudiantDAO = etudiantDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }
    
    @Override
    public Admin login(String email, String mdp) {
        return adminDAO.login(email, mdp);
    }

    @Override
    public boolean changerMdp(String email, String newMotDePasse) {
        Admin a = adminDAO.getAdmin(email);
        a.setMotDePasse(newMotDePasse);
        adminDAO.update(a);
        return true;
    }

    @Override
    public boolean mdpCorrect(String email, String oldMotDePasse) {
        Admin a = adminDAO.login(email, oldMotDePasse);
        return a != null;
    }

    @Override
    public boolean modifierInfo(String nom, String prenom) {
        String email = (String) Utils.getSession().getAttribute("AdmineEmail");
        Admin a = adminDAO.getAdmin(email);
        a.setNom(nom);
        a.setPrenom(prenom);
        adminDAO.update(a);
        return true;
    }

    @Override
    public int nbrEtudiants() {
        return etudiantDAO.nbrEtudiants();
    }

    @Override
    public int nbrNCmd() {
        return conferenceDAO.nbrNCmd();
    }

    @Override
    public int nbrCoursProgramme() {
        return conferenceDAO.nbrCoursProgramme();
    }

    @Override
    public int nbrProfesseurs() {
        return professeurDAO.nbrProfesseurs();
    }

    @Override
    public int nbrOffres() {
        return enseignerDAO.nbrOffres();
    }
    
}

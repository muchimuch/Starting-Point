/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.ConferenceDAO;
import com.helpyacademy.dao.EnseignerDAO;
import com.helpyacademy.dao.MatiereDAO;
import com.helpyacademy.dao.ProfesseurDAO;
import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.util.Utils;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssefsafi
 */
public class TrouverSoutienServiceImpl implements TrouverSoutienService{

    private EnseignerDAO enseignerDAO;
    private ProfesseurDAO professeurDAO;
    private MatiereDAO matiereDAO;
    private ConferenceDAO conferenceDAO;

    public void setConferenceDAO(ConferenceDAO conferenceDAO) {
        this.conferenceDAO = conferenceDAO;
    }

    public void setEnseignerDAO(EnseignerDAO enseignerDAO) {
        this.enseignerDAO = enseignerDAO;
    }

    public void setProfesseurDAO(ProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    public void setMatiereDAO(MatiereDAO matiereDAO) {
        this.matiereDAO = matiereDAO;
    }
    
    @Override
    public List<Enseigner> listMatiereEns(int idM) {
        return enseignerDAO.getMatiere(idM);
    }

    @Override
    public Professeur getProfByID(int idp) {
        return professeurDAO.getProfByID(idp);
    }

    @Override
    public String getMatiereNomByID(int idM) {
        return matiereDAO.getMatiere(idM).getMatiere();
    }

    @Override
    public boolean ajouterCMD(int CidM, int CidP, String titre, String description, String dateDebut, String heureDebut, int duree, float prixTotal, boolean Ccadeau20min, boolean cadeau20min) {
        Conference c = new Conference();
        
        int idE = (int) Utils.getSession().getAttribute("EtudiantID");
        Etudiant e = new Etudiant(idE);
        c.setIdEleve(e);
        Professeur p = new Professeur(CidP);
        c.setIdProf(p);
        Matiere m = new Matiere(CidM);
        c.setIdMatiere(m);
        if(Ccadeau20min)
            c.setCadeau20Min(cadeau20min);
        else
            c.setCadeau20Min(false);
        c.setTitre(titre);
        c.setDescription(description);
        c.setDuree(duree);
        c.setPrix(prixTotal);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date_debut = null;
        try {
            date_debut = formatter.parse(dateDebut);
        } catch (ParseException ex) {
            Logger.getLogger(TrouverSoutienServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(date_debut == null)
            return false;
        c.setDateDebut(date_debut);
        
        Date heure_debut = new Date();
        Time t = new Time(Integer.parseInt(heureDebut.substring(0, 2)), Integer.parseInt(heureDebut.substring(3, 5)), 0);
        heure_debut.setTime(t.getTime());
        c.setHeureDebut(heure_debut);
        c.setStatut(0);
        
        Integer id = conferenceDAO.add(c);
        
        return id != null;
    }

    @Override
    public List<Conference> listCommandes() {
        int idE = (int) Utils.getSession().getAttribute("EtudiantID");
        return conferenceDAO.listCommandes(idE);
    }

    @Override
    public boolean deleteConf(Conference conf) {
        conferenceDAO.delete(conf);
        return true;
    }
    
}

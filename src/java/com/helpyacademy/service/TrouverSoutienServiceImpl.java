/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.ConferenceDAO;
import com.helpyacademy.dao.EnseignerDAO;
import com.helpyacademy.dao.MatiereDAO;
import com.helpyacademy.dao.NotificationDAO;
import com.helpyacademy.dao.ProfesseurDAO;
import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Notification;
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
    private NotificationDAO notificationDAO;

    public void setNotificationDAO(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

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
        if(id != null){
            Conference conf = conferenceDAO.getConference(id);
            String Etud = conf.getIdEleve().getNom().toUpperCase()+" "+conf.getIdEleve().getPrenom().toUpperCase();
          
            Notification notification = new Notification();
            notification.setTitre("Vous avez recu une commande de l'"+conf.getIdEleve().getGenre()+" <b>"+Etud+"</b>");
            notification.setDateNotification(new Date());
            notification.setIdUtilisateur(CidP);
            notification.setIdPage(id);
            notification.setEspace('p');
            notification.setType("cmd");
            notification.setVu('0');
            
            notificationDAO.addNotification(notification);
        }
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

    @Override
    public boolean updateStatut(Conference conf, int i,int c) {
        conf.setStatut(i);
        conferenceDAO.update(conf);
        if(i == 3 && c == 0){
            
            String Etud = conf.getIdEleve().getNom().toUpperCase()+" "+conf.getIdEleve().getPrenom().toUpperCase();
            Notification notification = new Notification();
            notification.setTitre("l'"+conf.getIdEleve().getGenre()+" <b>"+Etud+"</b> a annulé sa commande");
            notification.setDateNotification(new Date());
            notification.setIdUtilisateur(conf.getIdProf().getId());
            notification.setIdPage(conf.getId());
            notification.setEspace('p');
            notification.setType("cmdAnnuler");
            notification.setVu('0');
            
            notificationDAO.addNotification(notification);
        } else if(i == 3 && c == 1){
            
            String Prof = conf.getIdProf().getNom().toUpperCase()+" "+conf.getIdProf().getPrenom().toUpperCase();
            Notification notification = new Notification();
            notification.setTitre("le professeur <b>"+Prof+"</b> a annulé votre commande");
            notification.setDateNotification(new Date());
            notification.setIdUtilisateur(conf.getIdEleve().getId());
            notification.setIdPage(conf.getId());
            notification.setEspace('e');
            notification.setType("cmdAnnuler");
            notification.setVu('0');
            
            notificationDAO.addNotification(notification);
        } else if(i == 1 && c == 1){
            
            String Prof = conf.getIdProf().getNom().toUpperCase()+" "+conf.getIdProf().getPrenom().toUpperCase();
            Notification notification = new Notification();
            notification.setTitre("le professeur <b>"+Prof+"</b> a accepté votre commande");
            notification.setDateNotification(new Date());
            notification.setIdUtilisateur(conf.getIdEleve().getId());
            notification.setIdPage(conf.getId());
            notification.setEspace('e');
            notification.setType("cmd");
            notification.setVu('0');
            
            notificationDAO.addNotification(notification);
        }
        return true;
    }

    @Override
    public List<Conference> listCommandesP() {
        int idP = (int) Utils.getSession().getAttribute("IDP");
        return conferenceDAO.listCommandesP(idP);
    }
    
}

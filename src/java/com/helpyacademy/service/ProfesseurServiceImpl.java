/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.ConferenceDAO;
import com.helpyacademy.dao.ConfigDAO;
import com.helpyacademy.dao.DiplomeDAO;
import com.helpyacademy.dao.EnseignerDAO;
import com.helpyacademy.dao.NotificationDAO;
import com.helpyacademy.dao.ProfesseurDAO;
import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Config;
import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Notification;
import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.util.Utils;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author youssefsafi
 */
public class ProfesseurServiceImpl implements ProfesseurService {

    private ProfesseurDAO professeurDAO;
    private DiplomeDAO diplomeDAO;
    private NotificationDAO notificationDAO;
    private ConferenceDAO conferenceDAO;
    private EnseignerDAO enseignerDAO;
    private ConfigDAO configDAO;

    public void setConfigDAO(ConfigDAO configDAO) {
        this.configDAO = configDAO;
    }

    public void setEnseignerDAO(EnseignerDAO enseignerDAO) {
        this.enseignerDAO = enseignerDAO;
    }

    public void setConferenceDAO(ConferenceDAO conferenceDAO) {
        this.conferenceDAO = conferenceDAO;
    }

    public void setNotificationDAO(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    public void setProfesseurDAO(ProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    public void setDiplomeDAO(DiplomeDAO diplomeDAO) {
        this.diplomeDAO = diplomeDAO;
    }

    @Override
    public Professeur login(String email, String mdp) {
        return professeurDAO.login(email, mdp);
    }

    @Override
    public boolean emailExiste(String email) {
        return professeurDAO.emailExiste(email);
    }

    @Override
    public boolean inscrire(String email, String mdp, String civilite, String nom, String prenom, String tel, List<String> Diplomes,Date dateNaiss) {
        String token = Utils.token();
        Professeur p = new Professeur(civilite, nom, prenom, tel, email, mdp);
        p.setToken(token);
        p.setDateNaissance(dateNaiss);
        Integer idP = professeurDAO.ajouter(p);
        if (idP != null) {
            p.setId(idP);

            for (String diplome : Diplomes) {
                Diplome d = new Diplome();
                d.setDiplome(diplome);
                d.setIdProf(p);
                diplomeDAO.ajouter(d);
            }
            try {

                Config conf = configDAO.getConf(1);
                
                String url = Utils.urlVerification(p.getEmail(), token,2,conf.getUrlSite());
                String msg = Utils.VerrificationCompteProfMessage(p.getNom().toUpperCase(), p.getPrenom().toUpperCase(), url);
                
                MailService m = new MailService(conf.getMailHost(), conf.getMailPort(), conf.getEmail(), conf.getMdpEmail(), conf.getMailFrom());
                m.sendMessage(p.getEmail(), "Vérifiez votre compte HelpyAcademy", msg);
                
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            
        }
        return false;
    }

    @Override
    public String verifier(String email,String token) {
        if(!email.isEmpty() && !token.isEmpty()){
            if(professeurDAO.isPresent(email, token)){
                professeurDAO.activerCompte(email);
                return "";
            }
        }
        return "pretty:home";
    }

    @Override
    public boolean mdpCorrect(String email, String oldPassword) {
        Professeur p = professeurDAO.login(email, oldPassword);
        return p != null;
    }

    @Override
    public boolean changerMdp(String email, String newPassword) {
        Professeur p = professeurDAO.getProf(email);
        p.setMdp(newPassword);
        professeurDAO.update(p);
        return true;
    }

    @Override
    public boolean ajoutDiplome(String diplome) {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        Professeur p = new Professeur(idp);
        Diplome d = new Diplome();
        d.setIdProf(p);
        d.setDiplome(diplome);
        Integer id = diplomeDAO.ajouter(d);
        return id != null;
    }

    @Override
    public List<Diplome> getDiplomes() {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        Professeur p = new Professeur(idp);
        return diplomeDAO.getDiplomes(p);
    }

    @Override
    public boolean deleteDiplome(Diplome d) {
        diplomeDAO.delete(d);
        return true;
    }

    @Override
    public boolean diplomeExiste(String diplome) {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        Diplome d = diplomeDAO.getDiplomeByName(diplome,idp);
        return d != null;
    }

    @Override
    public boolean modifierDiplome(Professeur idProf, int idDiplomeM, String diplomeM) {
        Diplome d = new Diplome(idDiplomeM);
        d.setIdProf(idProf);
        d.setDiplome(diplomeM);
        diplomeDAO.update(d);
        return true;
    }

    @Override
    public boolean modifierInfoP(Date date_naissance, String ville, String adresse, String tel, String situation_pro, String niv_etude) {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        Professeur p = professeurDAO.getProfByID(idp);
        p.setDateNaissance(date_naissance);
        p.setVille(ville);
        p.setAdresse(adresse);
        p.setTel(tel);
        p.setSituationPro(situation_pro);
        p.setNivEtude(niv_etude);
        
        professeurDAO.update(p);
        return true;
    }

    @Override
    public List<Notification> listNotification(int id, char c) {
        return notificationDAO.getNotification(id, c);
    }

    @Override
    public void notificationVu(int notifID) {
        notificationDAO.notificationVu(notifID);
    }

    @Override
    public Conference getConference(int idConf) {
        return conferenceDAO.getConference(idConf);
    }

    @Override
    public int nbrMesCours() {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        return enseignerDAO.nbrMesCours(idp);
    }

    @Override
    public int nbrCoursPrevenu() {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        return conferenceDAO.nbrCoursPrevenu(idp);
    }

    @Override
    public int nbrNouvelleCmd() {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        return conferenceDAO.nbrNouvelleCmd(idp);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.ConfigDAO;
import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Config;
import com.helpyacademy.service.BBBService;
import com.helpyacademy.service.ConferenceService;
import com.helpyacademy.util.Utils;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author youssefsafi
 */
@ManagedBean(name = "conferanceBean")
@ViewScoped
public class ConferanceBean {

    private boolean success;
    private List<Conference> listCours;
    private int indexCC;
    private int nbrCours;
    private Conference cours;
    private boolean coursTerminer;

    private ConferenceService conferenceService;
    private ConfigDAO configDAO;

    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    public void setConfigDAO(ConfigDAO configDAO) {
        this.configDAO = configDAO;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Conference> getListCours() {
        return listCours;
    }

    public Conference getCours() {
        return cours;
    }

    public boolean isCoursTerminer() {
        return coursTerminer;
    }

    /* ---------------------------------------- */
    
    public void confirmerFinCours() {
        if (conferenceService.confirmerFinCours(cours.getId())) {
            success = true;
            Utils.addMessage("le cours est marqué comme étant terminé");
            
            listCours = conferenceService.listCoursP();
            nbrCours = listCours.size();
            indexCC = 0;
            
            Config config = configDAO.getConf(1);
            BBBService bbb = new BBBService(config.getUrl(), config.getSalt());
            String meetingID = "Cours de Soutien en " + cours.getIdMatiere().getMatiere() + " | ID : " + cours.getId() + "-" + cours.getIdEleve().getId() + "-" + cours.getIdProf().getId();
            bbb.endMeeting(meetingID, "mp");
            
        } else {
            success = false;
            Utils.addMessage("le cours n'est pas marqué comme étant terminé");
        }
    }

    public boolean coursTermine() {
        if (countDown() > 0) {
            coursTerminer = false;
        } else if (countDownFinCours() <= 0) {
            coursTerminer = true;
        }
        return coursTerminer;
    }

    public int countDownFinCours() {
        int duree = 0;
        if (cours.getCadeau20Min()) {
            duree = 20 * 60;
        }
        duree = duree + (cours.getDuree() * 60);
        Date dNow = new Date();
        Long diff = (cours.getDateDebut().getTime() + cours.getHeureDebut().getTime()) / 1000 - dNow.getTime() / 1000;
        return diff.intValue() + duree;
    }

    public String startConfEtudiant() throws IOException {
        String username = cours.getIdEleve().getNom().toUpperCase() + " " + cours.getIdEleve().getPrenom().toUpperCase();
        String meetingID = "Cours de Soutien en " + cours.getIdMatiere().getMatiere() + " | ID : " + cours.getId() + "-" + cours.getIdEleve().getId() + "-" + cours.getIdProf().getId();
        //
        // This is the URL for to join the meeting as moderator
        //
        Config config = configDAO.getConf(1);
        BBBService bbb = new BBBService(config.getUrl(), config.getSalt());
        String joinURL = bbb.getJoinURL(username, meetingID, "true", "<br>Bienvenu au %%CONFNAME%%.<br>", null, null, 1);
        return joinURL;
    }

    public String startConfProf() throws IOException {
        String username = cours.getIdProf().getNom().toUpperCase() + " " + cours.getIdProf().getPrenom().toUpperCase();
        String meetingID = "Cours de Soutien en " + cours.getIdMatiere().getMatiere() + " | ID : " + cours.getId() + "-" + cours.getIdEleve().getId() + "-" + cours.getIdProf().getId();
        //
        // This is the URL for to join the meeting as moderator
        //
        Config config = configDAO.getConf(1);
        BBBService bbb = new BBBService(config.getUrl(), config.getSalt());
        String joinURL = bbb.getJoinURL(username, meetingID, "true", "<br>Bienvenu au %%CONFNAME%%.<br>", null, null, 1);
        return joinURL;
    }
    
    public String dureeTotal() {
        int h = cours.getDuree() / 60;
        int m = cours.getDuree() % 60;
        String d = h + "h ";
        if (m != 0) {
            d += m + "min";
        }
        if (cours.getCadeau20Min()) {
            d += " + 20 min Cadeau";
        }
        return d;
    }

    public int diffCoursSuivant() {
        return nbrCours - (indexCC + 1);
    }

    public int diffCoursPrecedent() {
        return indexCC;
    }

    public void coursSuivant() {
        indexCC++;
        cours = listCours.get(indexCC);
    }

    public void coursPrecedent() {
        indexCC--;
        cours = listCours.get(indexCC);
    }

    public boolean SuivantTest() {
        return (indexCC + 1) < nbrCours;
    }

    public boolean PrecedentTest() {
        return (indexCC - 1) >= 0;
    }

    public int countDown() {
        if (cours == null) {
            return 0;
        }
        Date dNow = new Date();
        Long diff = (cours.getDateDebut().getTime() + cours.getHeureDebut().getTime()) / 1000 - dNow.getTime() / 1000;
        if (diff.intValue() <= 0) {
            return 0;
        }
        return diff.intValue();
    }

    public String dureeTotal(Integer duree, boolean cadeau20Min) {
        int h = duree / 60;
        int m = duree % 60;
        String d = h + "h ";
        if (m != 0) {
            d += m + "min";
        }
        if (cadeau20Min) {
            d += " + 20 min Cadeau";
        }
        return d;
    }

    public boolean emptyListCours() {
        listCours = conferenceService.listCours();
        if (nbrCours == 0) {
            nbrCours = listCours.size();
        }
        if (cours == null && !listCours.isEmpty()) {
            indexCC = 0;
            cours = listCours.get(indexCC);
        }
        cours = listCours.get(indexCC);
        
        return listCours.isEmpty();
    }

    public boolean emptyListCoursP() {
        listCours = conferenceService.listCoursP();
        if (nbrCours == 0) {
            nbrCours = listCours.size();
        }
        if (cours == null && !listCours.isEmpty()) {
            indexCC = 0;
        } 
        cours = listCours.get(indexCC);
        
        return listCours.isEmpty();
    }
}

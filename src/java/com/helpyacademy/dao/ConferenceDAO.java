/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Etudiant;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface ConferenceDAO {
    public Integer add(Conference conference);
    public Conference getConference(int idConf);
    public List<Conference> listCommandes(int idE);

    public void delete(Conference conf);

    public void update(Conference conf);

    public List<Conference> listCommandesP(int idP);

    public List<Conference> listCoursE(int idE);

    public List<Conference> listCoursP(int idP);

    public Integer confirmerFinCours(int idConf);

    public int nbrEtudiantCours(int idE);

    public int nbrEtudiantNewCMD(int idE);

    public int nbrCoursPrevenu(int idp);

    public int nbrNouvelleCmd(int idp);

    public int nbrNCmd();

    public int nbrCoursProgramme();
}
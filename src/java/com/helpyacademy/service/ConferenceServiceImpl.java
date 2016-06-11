/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.ConferenceDAO;
import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.util.Utils;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public class ConferenceServiceImpl implements ConferenceService{
    
    private ConferenceDAO conferenceDAO;

    public void setConferenceDAO(ConferenceDAO conferenceDAO) {
        this.conferenceDAO = conferenceDAO;
    }

    @Override
    public List<Conference> listCours() {
        int idE = (int) Utils.getSession().getAttribute("EtudiantID");
        return conferenceDAO.listCoursE(idE);
    }
    
}

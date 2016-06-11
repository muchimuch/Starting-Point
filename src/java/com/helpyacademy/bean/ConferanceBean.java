/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.service.ConferenceService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author youssefsafi
 */
@ManagedBean(name = "conferanceBean")
@RequestScoped
public class ConferanceBean {
    
    private boolean success;
    private List<Conference> listCours;
    
    private ConferenceService conferenceService;

    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Conference> getListCours() {
        return listCours;
    }
    
    /* ---------------------------------------- */
    
    public String dureeTotal(Integer duree,boolean cadeau20Min){
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
    
    public boolean emptyListCours(){
        listCours = conferenceService.listCours();
        return listCours.isEmpty();
    }
    
}

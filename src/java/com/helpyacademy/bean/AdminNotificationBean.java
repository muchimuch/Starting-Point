/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.service.AdminService;
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
public class AdminNotificationBean {
    private AdminService adminService;
    private List<Professeur> inscNotifcations = new ArrayList<>();
    
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public List<Professeur> getInscNotifcations() {
        return inscNotifcations;
    }

    public void setInscNotifcations(List<Professeur> inscNotifcations) {
        this.inscNotifcations = inscNotifcations;
    }
    
    // -- Useful methods
    
    public boolean emptyInscNotifcations(){
        inscNotifcations = adminService.getNewInscriptions();
        return inscNotifcations.isEmpty();
    }
    
}

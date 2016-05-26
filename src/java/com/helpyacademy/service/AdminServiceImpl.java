/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.AdminDAO;
import com.helpyacademy.dao.model.Admin;
import com.helpyacademy.util.Utils;

/**
 *
 * @author youssefsafi
 */
public class AdminServiceImpl implements AdminService{

    public AdminDAO adminDAO;

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
    
}

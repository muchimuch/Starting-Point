/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.AdminDAO;
import com.helpyacademy.dao.DiplomeDAO;
import com.helpyacademy.dao.ProfesseurDAO;
import com.helpyacademy.dao.model.Admin;
import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Professeur;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public class AdminServiceImpl implements AdminService{

    public AdminDAO adminDAO;
    public ProfesseurDAO professeurDAO;
    public DiplomeDAO diplomeDAO;

    public void setDiplomeDAO(DiplomeDAO diplomeDAO) {
        this.diplomeDAO = diplomeDAO;
    }
    
    public void setProfesseurDAO(ProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }
    
    @Override
    public Admin login(String email, String mdp) {
        return adminDAO.login(email, mdp);
    }

    @Override
    public List<Professeur> getNewInscriptions() {
        ArrayList<Professeur> profs = (ArrayList<Professeur>) professeurDAO.getProfByCompte('1');
        return profs;
    }

    @Override
    public List<Diplome> getProfDiplomes(Professeur p) {
        return diplomeDAO.getProfDiplomes(p);
    }

    @Override
    public boolean accepterProfInscription(Professeur p) {
        return professeurDAO.accepterProfInscription(p);
    }

    @Override
    public boolean refuserProfInscription(Professeur p) {
        return professeurDAO.refuserProfInscription(p);
    }
    
    
}

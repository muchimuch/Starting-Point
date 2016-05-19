/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.ProfesseurDAO;
import com.helpyacademy.dao.model.Professeur;

/**
 *
 * @author youssefsafi
 */
public class ProfesseurServiceImpl implements ProfesseurService{

    private ProfesseurDAO professeurDAO;

    public void setProfesseurDAO(ProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }
    
    @Override
    public Professeur login(String email, String mdp) {
        return professeurDAO.login(email, mdp);
    }
    
}

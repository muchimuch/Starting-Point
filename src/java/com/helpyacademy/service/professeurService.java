/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.dao.professeurDAO;

/**
 *
 * @author mkerm
 */
public class professeurService implements service<Professeur>{
    
    private professeurDAO profDAO ;

    public void setProfDAO(professeurDAO profDAO) {
        this.profDAO = profDAO;
    }
    
    @Override
    public Professeur insert(Professeur o) {
        return profDAO.insert(o);
    }

    @Override
    public Professeur update(Professeur o) {
        return profDAO.update(o);
    }

    @Override
    public boolean delete(Integer id) {
        return profDAO.delete(id);
    }

    @Override
    public Professeur find(Integer id) {
        return profDAO.find(id);
    }
    
    public boolean emailExiste(String email){
        return profDAO.emailExiste(email);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.NiveauDAO;
import com.helpyacademy.dao.model.Niveau;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public class NiveauServiceImpl implements NiveauService{

    private NiveauDAO niveauDAO;

    public void setNiveauDAO(NiveauDAO niveauDAO) {
        this.niveauDAO = niveauDAO;
    }
    
    @Override
    public Integer ajouter(String niv) {
        Niveau niveau = new Niveau();
        niveau.setNiveau(niv);
        
        return niveauDAO.ajouter(niveau);
    }

    @Override
    public List<Niveau> listNiveau() {
        return niveauDAO.listNiveau();
    }

    @Override
    public void delete(Niveau niveau) {
        niveauDAO.delete(niveau);
    }

    @Override
    public boolean update(Integer IdUpdate, String NivUpdate) {
        Niveau niveau = new Niveau();
        niveau.setId(IdUpdate);
        niveau.setNiveau(NivUpdate);
        return niveauDAO.update(niveau);
    }
    
}

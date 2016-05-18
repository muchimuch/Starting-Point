/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.MatiereDAO;
import com.helpyacademy.dao.NiveauDAO;
import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Niveau;
import java.util.List;

/**
 *
 * @author youssefsafi
 */

public class MatiereServiceImpl implements MatiereService{

    private MatiereDAO matiereDAO;
    private NiveauDAO niveauDAO;

    public void setMatiereDAO(MatiereDAO matiereDAO) {
        this.matiereDAO = matiereDAO;
    }

    public void setNiveauDAO(NiveauDAO niveauDAO) {
        this.niveauDAO = niveauDAO;
    }

    @Override
    public void delete(int matiereID) {
        Matiere matiere = new Matiere();
        matiere.setId(matiereID);
        
        matiereDAO.delete(matiere);
    }

    @Override
    public List<Niveau> listNiveau() {
        return niveauDAO.listNiveau();
    }

    @Override
    public boolean ajouter(String matiere, int idNiveau) {
        Niveau niveau = new Niveau();
        niveau.setId(idNiveau);
        
        Matiere m = new Matiere();
        m.setIdNiveau(niveau);
        m.setMatiere(matiere);
        
        Integer id = matiereDAO.ajouter(m);
        
        return id != null;
    }

    @Override
    public List<Matiere> MatiereParNiv(int IDniveau) {
        return matiereDAO.MatiereParNiv(IDniveau);
    }

    @Override
    public boolean emptyMatiere() {
        return matiereDAO.nbrMatiere() == 0;
    }

    @Override
    public boolean update(int id, int idNiv, String matiere) {
        Niveau niveau = new Niveau();
        niveau.setId(idNiv);
        
        Matiere m = new Matiere();
        m.setIdNiveau(niveau);
        m.setId(id);
        m.setMatiere(matiere);
        
        return matiereDAO.update(m);
    }
    
    
    
}

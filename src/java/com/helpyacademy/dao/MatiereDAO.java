/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Niveau;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface MatiereDAO {
    public Long nbrMatiere();
    public Integer ajouter(Matiere matiere);
    public void delete(Matiere matiere);    
    public List<Matiere> MatiereParNiv(int IDniveau);
    public boolean update(Matiere matiere);
    public int getIdNiv(int idMatiereM);

    public Matiere getMatiere(int idM);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Niveau;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface MatiereService {
    public void delete(int matiereID);
    public List<Niveau> listNiveau();
    public boolean ajouter(String matiere,int idNiveau);
    public List<Matiere> MatiereParNiv(int IDniveau);
    public boolean emptyMatiere();
    public boolean update(int id,int idNiv,String matiere);
}

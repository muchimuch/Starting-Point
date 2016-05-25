/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Niveau;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface EnseignerService {
    public List<Niveau> listNiveau();
    public List<Matiere> MatiereParNiv(int IDniveau);
    public List<Enseigner> MatiereEnseigner();

    public boolean add(int idMatiere, String description, float prixHeure, boolean cadeau20Min);

    public boolean delete(Enseigner enseigner);

    public int getIdNiv(int idMatiereM);

    public boolean update(int OldidMatiere,int idMatiereM, String descriptionM, float prixHeureM, boolean cadeau20MinM);

    public String getMatiereNom(int idM);

    public String getNiveauNom(int idMatiere);

    public boolean matierExiste(int idMatiere);
}

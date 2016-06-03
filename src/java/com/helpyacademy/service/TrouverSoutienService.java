/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.Professeur;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface TrouverSoutienService {

    public List<Enseigner> listMatiereEns(int idM);
    public Professeur getProfByID(int idp);

    public String getMatiereNomByID(int idM);

    public boolean ajouterCMD(int CidM, int CidP, String titre, String description, String dateDebut, String heureDebut, int duree, float prixTotal, boolean Ccadeau20min, boolean cadeau20min);

    public List<Conference> listCommandes();

    public boolean deleteConf(Conference conf);
    
}

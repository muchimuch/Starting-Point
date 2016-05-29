/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Professeur;
import java.util.Date;
import java.util.List;

/**
 *
 * @author youssefsafi
 */

public interface ProfesseurService {
    public Professeur login(String email,String mdp);
    public boolean emailExiste(String email);
    public boolean inscrire(String email, String mdp, String civilite, String nom, String prenom, String tel, List<String> Diplomes);
    public String verifier(String email,String token);
    public boolean mdpCorrect(String email, String oldPassword);
    public boolean changerMdp(String email, String newPassword);
    public boolean ajoutDiplome(String diplome);
    public List<Diplome> getDiplomes();
    public boolean deleteDiplome(Diplome d);
    public boolean diplomeExiste(String diplome);
    public boolean modifierDiplome(Professeur idProf, int idDiplomeM, String diplomeM);
    public boolean modifierInfoP(Date date_naissance, String ville, String adresse, String tel, String situation_pro, String niv_etude);
}

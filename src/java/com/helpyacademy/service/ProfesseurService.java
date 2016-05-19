/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Professeur;
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
}

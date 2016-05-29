/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Professeur;
import java.util.Date;

/**
 *
 * @author youssefsafi
 */
public interface ProfesseurDAO {
    public Professeur login(String email,String mdp);
    public boolean emailExiste(String email);
    public Integer ajouter(Professeur p);
    public boolean isPresent(String email, String token);
    public Integer activerCompte(String email);
    public Professeur getProf(String email);
    public Professeur getProfByID(int idp);
    public void update(Professeur p);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Etudiant;

/**
 *
 * @author youssefsafi
 */
public interface EtudiantDAO {
    public Integer add(Etudiant etudiant);
    public boolean emailExiste(String email);
    public Integer activerCompte(String email);
    public boolean isPresent(String email, String token);
    public Etudiant login(String email,String mdp);
}

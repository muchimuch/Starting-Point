/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Niveau;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface EtudiantService {
    public String inscrire(Etudiant etudiant);
    public String verifier(String email,String token);
    public Etudiant login(String email,String mdp);
    public String logout();
    public boolean emailExiste(String email);
    public List<Niveau> listNiveau();
}

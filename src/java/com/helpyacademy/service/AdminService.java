/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Admin;
import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Professeur;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface AdminService {
    public Admin login(String email,String mdp);
    public List<Professeur> getNewInscriptions();
    public List<Diplome> getProfDiplomes(Professeur p);
    public boolean accepterProfInscription(Professeur p);
    public boolean refuserProfInscription(Professeur p);
}

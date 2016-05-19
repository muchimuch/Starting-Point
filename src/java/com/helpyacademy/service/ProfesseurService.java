/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Professeur;

/**
 *
 * @author youssefsafi
 */

public interface ProfesseurService {
    public Professeur login(String email,String mdp);
}

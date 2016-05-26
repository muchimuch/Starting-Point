/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Admin;

/**
 *
 * @author youssefsafi
 */
public interface AdminService {
    public Admin login(String email,String mdp);

    public boolean changerMdp(String email, String newMotDePasse);

    public boolean mdpCorrect(String email, String oldMotDePasse);

    public boolean modifierInfo(String nom, String prenom);
}

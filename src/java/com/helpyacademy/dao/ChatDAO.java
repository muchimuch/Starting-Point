/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Message;
import com.helpyacademy.dao.model.Professeur;
import java.util.List;

/**
 *
 * @author mkerm
 */
public interface ChatDAO {
    public List<Etudiant> getProfesseurContacts(int profId);
    public List<Professeur> getEtudiantContacts(int etudId);
    public void insert(Message msg);
}

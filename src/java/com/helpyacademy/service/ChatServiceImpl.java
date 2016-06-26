package com.helpyacademy.service;

import com.helpyacademy.dao.EtudiantDAO;
import com.helpyacademy.dao.ChatDAO;
import com.helpyacademy.dao.ProfesseurDAO;
import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Message;
import com.helpyacademy.dao.model.Professeur;
import java.util.List;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mkerm
 */
public class ChatServiceImpl implements ChatService{
    
    private ChatDAO chatDAO;
    private EtudiantDAO etudiantDAO;
    private ProfesseurDAO professeurDAO;

    public void setChatDAO(ChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    public void setEtudiantDAO(EtudiantDAO etudiantDAO) {
        this.etudiantDAO = etudiantDAO;
    }

    public void setProfesseurDAO(ProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    @Override
    public Etudiant getEtudiantById(int id) {
        return etudiantDAO.getEtudiant(id);
    }

    @Override
    public Professeur getProfesseurById(int id) {
        return professeurDAO.getProfByID(id);
    }

    @Override
    public List<Etudiant> getProfesseurContacts(int id) {
        return chatDAO.getProfesseurContacts(id);
    }

    @Override
    public void insert(Message msg) {
        chatDAO.insert(msg);
    }
    
    
    
}

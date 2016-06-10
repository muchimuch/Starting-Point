/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.service.ChatService;
import com.helpyacademy.util.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mkerm
 */
@ManagedBean
@SessionScoped
public class EtudiantChatBean {

    private List<Professeur> contacts = new ArrayList<>();
    private Etudiant current;
    private Professeur prof;

    private String userParam;

    private ChatService chatService;

    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    public List<Professeur> getContacts() {
        return contacts;
    }

    public void setContacts(List<Professeur> contacts) {
        this.contacts = contacts;
    }

    public void setContacts() {
        HttpSession session = Utils.getSession();
        Integer id = Integer.parseInt(session.getAttribute("EtudiantID").toString());
        //this.contacts = chatService.getProfesseurContacts(id);
        boolean exist = false;
        for (Professeur p : contacts) {
            if (p.getId() == prof.getId()) {
                exist = true;
            }
        }
        if (!exist) {
            contacts.add(prof);
        }
    }

    public Etudiant getCurrent() {
        return current;
    }

    public void setCurrent(Etudiant current) {
        this.current = current;
    }


    public void setCurrent() {
        HttpSession session = Utils.getSession();
        Integer id = Integer.parseInt(session.getAttribute("EtudiantID").toString());
        this.current = chatService.getEtudiantById(id);
    }

    public Professeur getProf() {
        return prof;
    }

    public void setProf(Professeur prof) {
        this.prof = prof;
    }

    public String getUserParam() {
        return userParam;
    }

    public String setUserParam(String userParam) {

        Pattern pattern = Pattern.compile("^prof-[0-9]+$");
        Matcher matcher = pattern.matcher(userParam);
        if (matcher.matches()) {
            int id = Integer.parseInt(userParam.split("-")[1]);
            this.prof = chatService.getProfesseurById(id);
            if (prof == null) {
                return "pretty:EspaceE_HOME";
            }
            setCurrent();
            setContacts();
            return null;
        } else {
            return "pretty:EspaceE_HOME";
        }
    }

}

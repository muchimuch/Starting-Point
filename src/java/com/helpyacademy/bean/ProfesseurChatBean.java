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
public class ProfesseurChatBean {

    private List<Etudiant> contacts = new ArrayList<>();
    private Professeur current;
    private Etudiant etude;

    private String userParam;

    private ChatService chatService;

    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    public List<Etudiant> getContacts() {
        return contacts;
    }

    public void setContacts(List<Etudiant> contacts) {
        this.contacts = contacts;
    }

    public void setContacts() {
        HttpSession session = Utils.getSession();
        Integer id = Integer.parseInt(session.getAttribute("IDP").toString());
        //this.contacts = chatService.getProfesseurContacts(id);
        boolean exist = false;
        for (Etudiant e : contacts) {
            if (e.getId() == etude.getId()) {
                exist = true;
            }
        }
        if (!exist) {
            contacts.add(etude);
        }
    }

    public Professeur getCurrent() {
        return current;
    }

    public void setCurrent(Professeur current) {
        this.current = current;
    }

    public void setCurrent() {
        HttpSession session = Utils.getSession();
        Integer id = Integer.parseInt(session.getAttribute("IDP").toString());
        this.current = chatService.getProfesseurById(id);
    }

    public Etudiant getEtude() {
        return etude;
    }

    public void setEtude(Etudiant etude) {
        this.etude = etude;
    }

    public String getUserParam() {
        return userParam;
    }

    public String setUserParam(String userParam) {

        Pattern pattern = Pattern.compile("^etude-[0-9]+$");
        Matcher matcher = pattern.matcher(userParam);
        if (matcher.matches()) {
            int id = Integer.parseInt(userParam.split("-")[1]);
            this.etude = chatService.getEtudiantById(id);
            if (etude == null) {
                return "pretty:EspaceP_HOME";
            }
            setCurrent();
            setContacts();
            return null;
        } else {
            return "pretty:EspaceP_HOME";
        }
    }

}

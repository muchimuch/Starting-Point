/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.DiplomeDAO;
import com.helpyacademy.dao.ProfesseurDAO;
import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.util.Utils;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author youssefsafi
 */
public class ProfesseurServiceImpl implements ProfesseurService {

    private ProfesseurDAO professeurDAO;
    private DiplomeDAO diplomeDAO;

    public void setProfesseurDAO(ProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    public void setDiplomeDAO(DiplomeDAO diplomeDAO) {
        this.diplomeDAO = diplomeDAO;
    }

    @Override
    public Professeur login(String email, String mdp) {
        return professeurDAO.login(email, mdp);
    }

    @Override
    public boolean emailExiste(String email) {
        return professeurDAO.emailExiste(email);
    }

    @Override
    public boolean inscrire(String email, String mdp, String civilite, String nom, String prenom, String tel, List<String> Diplomes) {
        String token = Utils.token();
        Professeur p = new Professeur(civilite, nom, prenom, tel, email, mdp);
        p.setToken(token);
        Integer idP = professeurDAO.ajouter(p);
        if (idP != null) {
            p.setId(idP);

            for (String diplome : Diplomes) {
                Diplome d = new Diplome();
                d.setDiplome(diplome);
                d.setIdProf(p);
                diplomeDAO.ajouter(d);
            }
            try {

                String url = Utils.urlVerification(p.getEmail(), token,2);
                String msg = Utils.VerrificationCompteProfMessage(p.getNom().toUpperCase(), p.getPrenom().toUpperCase(), url);
                MailService.sendMessage(p.getEmail(), "Vérifiez votre compte HelpyAcademy", msg);
                
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            
        }
        return false;
    }

    @Override
    public String verifier(String email,String token) {
        if(!email.isEmpty() && !token.isEmpty()){
            if(professeurDAO.isPresent(email, token)){
                professeurDAO.activerCompte(email);
                return "";
            }
        }
        return "pretty:home";
    }

    @Override
    public boolean mdpCorrect(String email, String oldPassword) {
        Professeur p = professeurDAO.login(email, oldPassword);
        return p != null;
    }

    @Override
    public boolean changerMdp(String email, String newPassword) {
        Professeur p = professeurDAO.getProf(email);
        p.setMdp(newPassword);
        professeurDAO.update(p);
        return true;
    }
}

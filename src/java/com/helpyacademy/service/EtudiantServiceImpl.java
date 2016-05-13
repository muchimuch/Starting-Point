/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.EtudiantDAO;
import com.helpyacademy.dao.NiveauDAO;
import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Niveau;
import com.helpyacademy.util.Utils;import java.util.List;
;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author youssefsafi
 */
public class EtudiantServiceImpl implements EtudiantService{

    private EtudiantDAO etudiantDAO;
    private NiveauDAO niveauDAO;

    public void setEtudiantDAO(EtudiantDAO etudiantDAO) {
        this.etudiantDAO = etudiantDAO;
    }

    public void setNiveauDAO(NiveauDAO niveauDAO) {
        this.niveauDAO = niveauDAO;
    }
    
    @Override
    public String inscrire(Etudiant etudiant) {
        
        
        
                String token = Utils.token();
                etudiant.setToken(token);
                Integer id = etudiantDAO.add(etudiant);
                if(id == null){
                     Utils.addMessage("Veuillez renseigner correctement les champs");
                     return "inscriptionEtudiant.xhtml";
                } else {
                    try{
                        
                        String url = Utils.urlVerification(etudiant.getEmail(),token);
                        String msg = Utils.VerrificationCompteMessage(etudiant.getNom().toUpperCase(), etudiant.getPrenom().toUpperCase(), url);
                        MailService.sendMessage(etudiant.getEmail(), "Vérifiez votre compte HelpyAcademy", msg);
                        
                        return "pretty:inscription_faite";

                    } catch(MessagingException e){
                        e.printStackTrace();
                    }
                }
        
        return "inscriptionEtudiant.xhtml?faces-redirect=true";
    }
    
    @Override
    public String verifier(String email,String token) {
        if(!email.isEmpty() && !token.isEmpty()){
            if(etudiantDAO.isPresent(email, token)){
                etudiantDAO.activerCompte(email);
                return "";
            }
        }
        return "pretty:home";
    }
    
    @Override
    public Etudiant login(String email,String mdp) {
        return etudiantDAO.login(email, mdp);
    }

    @Override
    public String logout() {
        HttpSession session = Utils.getSession();
        session.invalidate();
        return "pretty:connexion_etudiant";
    }

    @Override
    public boolean emailExiste(String email) {
        return etudiantDAO.emailExiste(email);
    }

    @Override
    public List<Niveau> listNiveau() {
        return niveauDAO.listNiveau();
    }

}

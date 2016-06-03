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
import com.helpyacademy.util.Utils;
import java.util.List;
import javax.mail.MessagingException;
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
    public boolean inscrire(Etudiant etudiant) {
        
                String token = Utils.token();
                etudiant.setToken(token);
                Integer id = etudiantDAO.add(etudiant);
                if(id == null){
                    return false;
                } else {
                    try{
                        
                        String url = Utils.urlVerification(etudiant.getEmail(),token,1);
                        String msg = Utils.VerrificationCompteMessage(etudiant.getNom().toUpperCase(), etudiant.getPrenom().toUpperCase(), url);
                        MailService.sendMessage(etudiant.getEmail(), "Vérifiez votre compte HelpyAcademy", msg);
        
                    } catch(MessagingException e){
                        e.printStackTrace();
                    }
                }
        
        return true;
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

    @Override
    public String getNiveauEtude() {
        int idE = (int) Utils.getSession().getAttribute("EtudiantID");
        Etudiant e = etudiantDAO.getEtudiant(idE);
        return e.getNiveau().getNiveau();
    }

    @Override
    public boolean mdpCorrect(String email, String OldMotDePasse) {
        Etudiant e = etudiantDAO.login(email, OldMotDePasse);
        return e != null;
    }

    @Override
    public boolean changerMdp(String email, String newMotDePasse) {
        int idE = (int) Utils.getSession().getAttribute("EtudiantID");
        Etudiant e = etudiantDAO.getEtudiant(idE);
        e.setMdp(newMotDePasse);
        etudiantDAO.update(e);
        return true;
    }

    @Override
    public boolean changerInfo(String nomM, String prenomM, String villeM, String adresseM, String telM, int nivM) {
        int idE = (int) Utils.getSession().getAttribute("EtudiantID");
        Etudiant e = etudiantDAO.getEtudiant(idE);
        e.setNom(nomM);
        e.setPrenom(prenomM);
        e.setVille(villeM);
        e.setAdresse(adresseM);
        e.setTel(telM);
        Niveau niv = new Niveau(nivM);
        e.setNiveau(niv);
        etudiantDAO.update(e);
        
        return true;
    }

}

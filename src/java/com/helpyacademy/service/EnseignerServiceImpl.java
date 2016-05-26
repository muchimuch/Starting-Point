/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.EnseignerDAO;
import com.helpyacademy.dao.MatiereDAO;
import com.helpyacademy.dao.NiveauDAO;
import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.EnseignerPK;
import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Niveau;
import com.helpyacademy.util.Utils;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author youssefsafi
 */
public class EnseignerServiceImpl implements EnseignerService {

    private EnseignerDAO enseignerDAO;
    private MatiereDAO matiereDAO;
    private NiveauDAO niveauDAO;

    public void setEnseignerDAO(EnseignerDAO enseignerDAO) {
        this.enseignerDAO = enseignerDAO;
    }

    public void setMatiereDAO(MatiereDAO matiereDAO) {
        this.matiereDAO = matiereDAO;
    }

    public void setNiveauDAO(NiveauDAO niveauDAO) {
        this.niveauDAO = niveauDAO;
    }

    /* ------------------------------- */
    @Override
    public List<Niveau> listNiveau() {
        return niveauDAO.listNiveau();
    }

    @Override
    public List<Matiere> MatiereParNiv(int IDniveau) {
        return matiereDAO.MatiereParNiv(IDniveau);
    }

    @Override
    public List<Enseigner> MatiereEnseigner() {
        HttpSession session = Utils.getSession();
        int IDP = (int) session.getAttribute("IDP");
        int idp = (int) Utils.getSession().getAttribute("IDP");
        return enseignerDAO.MatiereEnseigner(idp);
    }

    @Override
    public boolean add(int idMatiere, String description, float prixHeure, boolean cadeau20Min) {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        EnseignerPK epk = new EnseignerPK(idMatiere, idp);
        Enseigner enseigner = new Enseigner(epk, prixHeure, cadeau20Min, description);
        EnseignerPK id = enseignerDAO.add(enseigner);

        return id != null;
    }

    @Override
    public boolean delete(Enseigner enseigner) {
        enseignerDAO.delete(enseigner);
        return true;
    }

    @Override
    public int getIdNiv(int idMatiereM) {
        return matiereDAO.getIdNiv(idMatiereM);
    }

    @Override
    public boolean update(int OldidMatiere,int idMatiereM, String descriptionM, float prixHeureM, boolean cadeau20MinM) {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        EnseignerPK epk = new EnseignerPK(idMatiereM, idp);
        Enseigner enseigner = new Enseigner(epk, prixHeureM, cadeau20MinM, descriptionM);
        if(OldidMatiere == idMatiereM)
            enseignerDAO.update(enseigner);
        else {
            enseigner.setEnseignerPK(new EnseignerPK(OldidMatiere, idp));
            enseignerDAO.delete(enseigner);
            enseigner.setEnseignerPK(new EnseignerPK(idMatiereM, idp));
            return enseignerDAO.add(enseigner) != null;
        }
        return true;
    }

    @Override
    public String getMatiereNom(int idM) {
        return matiereDAO.getMatiere(idM).getMatiere();
    }

    @Override
    public String getNiveauNom(int idMatiere) {
        return matiereDAO.getMatiere(idMatiere).getIdNiveau().getNiveau();
    }

    @Override
    public boolean matierExiste(int idMatiere) {
        int idp = (int) Utils.getSession().getAttribute("IDP");
        Enseigner m = enseignerDAO.getMatiere(idMatiere,idp);
        return m != null;
    }

}

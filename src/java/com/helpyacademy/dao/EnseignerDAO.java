/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.EnseignerPK;
import com.helpyacademy.dao.model.Matiere;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface EnseignerDAO {
    public void delete(Enseigner enseigner);    
    public List<Enseigner> MatiereParNiv(int IDniv);
    public List<Enseigner> MatiereEnseigner(int IDP);
    public EnseignerPK add(Enseigner enseigner);
    public void update(Enseigner enseigner);

    public Enseigner getMatiere(int idMatiere, int idp);

    public List<Enseigner> getMatiere(int idM);

    public int nbrCoursDisponible();

    public int nbrMesCours(int idp);

    public int nbrOffres();
}

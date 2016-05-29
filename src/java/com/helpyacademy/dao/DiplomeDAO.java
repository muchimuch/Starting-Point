/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Professeur;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface DiplomeDAO {
    public Integer ajouter(Diplome diplome);
    public List<Diplome> getDiplomes(Professeur idProf);

    public void delete(Diplome d);

    public Diplome getDiplomeByName(String diplome);

    public void update(Diplome d);
}

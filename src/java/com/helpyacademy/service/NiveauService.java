/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Niveau;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface NiveauService {
    public Integer ajouter(String niveau);
    public List<Niveau> listNiveau();
    public boolean update(Integer IdUpdate,String NivUpdate);
    public void delete(Niveau niveau);
}

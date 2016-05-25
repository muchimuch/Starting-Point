/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Niveau;
import com.helpyacademy.service.NiveauService;
import com.helpyacademy.util.Utils;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author youssefsafi
 */

@ManagedBean
@RequestScoped
public class NiveauBean implements Serializable{
    
    private String niveau;
    private boolean success;
    private List<Niveau> listDesNiveau;
    private String NivUpdate;
    private Integer IdUpdate;
    
    private NiveauService niveauService;
    
    public NiveauBean() {
    }

    public void setNivUpdate(String NivUpdate) {
        this.NivUpdate = NivUpdate;
    }

    public void setIdUpdate(Integer IdUpdate) {
        this.IdUpdate = IdUpdate;
    }

    public String getNivUpdate() {
        return NivUpdate;
    }

    public Integer getIdUpdate() {
        return IdUpdate;
    }
    
    public void setNiv(Niveau niv) {
        this.IdUpdate = niv.getId();
        this.NivUpdate = niv.getNiveau();
    }

    public List<Niveau> getListDesNiveau() {
        return listDesNiveau;
    }

    public void setNiveauService(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getNiveau() {
        return niveau;
    }
    
    /* -------------------------------------------- */
    
    public String update(){
        if(niveauService.update(IdUpdate,NivUpdate)){
            success = true;
            Utils.addMessage("Le niveau a été bien modifié");
        } else {
            success = false;
            Utils.addMessage("Le niveau n'a pas été modifié");
        }
        return "pretty:EspaceA_LNiveau";
    }
    
    public String delete(Niveau niveau){
        niveauService.delete(niveau);
        success = true;
        Utils.addMessage("Le niveau a été bien supprimé");
        return "pretty:EspaceA_LNiveau";
    }
    
    public boolean emptyNiveau(){
        listDesNiveau = niveauService.listNiveau();
        if(listDesNiveau.size() == 0)
            return true;
        return false;
    }
    
    public String save(){
        if(niveauService.ajouter(niveau) != null){
            success = true;
            Utils.addMessage("le Niveau a été bien enregistré");
        }else{
            success = false;
            Utils.addMessage("le Niveau n'a pas été enregistré");
        }
        reset();
        return "pretty:EspaceA_LNiveau";
    }
    
    public void reset(){
        niveau = null;
    }
    
}

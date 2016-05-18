/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Niveau;
import com.helpyacademy.service.MatiereService;
import com.helpyacademy.util.Utils;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author youssefsafi
 */
@ManagedBean
@RequestScoped
public class MatiereBean implements Serializable{
    
    private Integer id;
    private int idNiveau;
    private String matiere;
    private List<Niveau> listNiveau;
    private List<Matiere> listMatiere;
    private boolean success;
    
    private int MidMatiere;
    private int MidNieau;
    private String Mmatiere;
            
    private MatiereService matiereService;

    public MatiereBean() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setMatiereService(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    public Integer getId() {
        return id;
    }

    public String getMatiere() {
        return matiere;
    }

    public List<Matiere> getListMatiere() {
        if(idNiveau == 0){
            listMatiere = matiereService.MatiereParNiv(listNiveau.get(0).getId());
        }
        return listMatiere;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Niveau> getListNiveau() {
        return listNiveau;
    }

    public void setMidMatiere(int MidMatiere) {
        this.MidMatiere = MidMatiere;
    }

    public void setMidNieau(int MidNieau) {
        this.MidNieau = MidNieau;
    }

    public void setMmatiere(String Mmatiere) {
        this.Mmatiere = Mmatiere;
    }

    public int getMidMatiere() {
        return MidMatiere;
    }

    public int getMidNieau() {
        return MidNieau;
    }

    public String getMmatiere() {
        return Mmatiere;
    }
    
    /* -------------------------------------------- */
    
    public String update(){
        if(matiereService.update(MidMatiere, MidNieau, Mmatiere)){
            success = true;
            Utils.addMessage("La Matière a été bien modifié");
            listMatiere = matiereService.MatiereParNiv(idNiveau);
        } else {
            success = false;
            Utils.addMessage("La Matière n'a pas été modifié");
        }
        return "pretty:EspaceA_LMatieres";
    }
    
    public void initUpdate(Matiere matiere){
        this.Mmatiere = matiere.getMatiere();
        this.MidNieau = matiere.getIdNiveau().getId();
        this.MidMatiere = matiere.getId();
    }
    
    public void MatiereParNiv(ValueChangeEvent e){
        idNiveau = (int) e.getNewValue();
        listMatiere = matiereService.MatiereParNiv(idNiveau);
    }
    
    public String save(){
        if(matiereService.ajouter(matiere, idNiveau)){
            success = true;
            Utils.addMessage("La Matière a été bien ajouté");
        } else {
            success = true;
            Utils.addMessage("La Matière n'a pas été ajouté");
        }
        reset();
        return "pretty:EspaceA_LMatieres";
    }
    public String delete(int matiereID){
        matiereService.delete(matiereID);
        success = true;
        Utils.addMessage("La Matière a été bien supprimé");
        return "pretty:EspaceA_LMatieres";
    }
    
    public boolean emptyNiveau(){
        listNiveau = matiereService.listNiveau();
        return listNiveau.isEmpty();
    }
    
    public boolean emptyMatiere(){
        return matiereService.emptyMatiere();
    }
    
    public void reset(){
        matiere = null;
    }
    
}

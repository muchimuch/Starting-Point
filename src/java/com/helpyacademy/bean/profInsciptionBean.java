/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.util.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author youssefsafi
 */
@ManagedBean
@ViewScoped
public class profInsciptionBean implements Serializable{
    
    private int id;
    private String civilite;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String tel;
    private String email;
    private String mdp;
    private String mdpConfirm;
    private Date date_naissance;
    private String situation_pro;
    private String niv_etude;   
    private String Diplome;
    private List<String> Diplomes = new ArrayList<String>();
    private int etape = 1;
    private boolean success;
    
    @PostConstruct
    public void init(){
    }
    
    public int getId() {
        return id;
    }

    public int getEtape() {
        return etape;
    }

    public String getMdpConfirm() {
        return mdpConfirm;
    }

    public String getCivilite() {
        return civilite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getSituation_pro() {
        return situation_pro;
    }

    public String getNiv_etude() {
        return niv_etude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setMdpConfirm(String mdpConfirm) {
        this.mdpConfirm = mdpConfirm;
    }

    public void setSituation_pro(String situation_pro) {
        this.situation_pro = situation_pro;
    }

    public void setNiv_etude(String niv_etude) {
        this.niv_etude = niv_etude;
    }

    public void setEtape(int etape) {
        this.etape = etape;
    }

    public List<String> getDiplomes() {
        return Diplomes;
    }

    public void setDiplomes(List<String> Diplomes) {
        this.Diplomes = Diplomes;
    }

    public String getDiplome() {
        return Diplome;
    }

    public void setDiplome(String Diplome) {
        this.Diplome = Diplome;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /* ====================================================================== */
    
    public void addDiplome(){
        if(Diplome != null && !Diplome.isEmpty()){
            Diplomes.add(Diplome);
            Diplome = "";
        }else{
            Utils.addMessage("Insérer un diplome pour l'ajouter à vous Diplomes");
        }
    }
    
    public void next(){
        if(etape == 1){
            if(!mdp.equals(mdpConfirm)){
                Utils.addMessage("Confirmation du mot de passe est erroné");
                success=false;
            } else {
                etape ++;
            }            
        } else if(etape == 2){
            boolean t1=true,t2=true,t3=true;
            if(civilite == null || civilite.isEmpty()){
                Utils.addMessage("Veuillez indiquez votre Civilité");
                t1=false;
            }
            if(nom == null || nom.isEmpty()){
                Utils.addMessage("Veuillez indiquez votre nom");
                t2=false;
            }
            if(prenom == null || prenom.isEmpty()){
                Utils.addMessage("Veuillez indiquez votre prenom");
                t3=false;
            }
            if(t1 && t2 && t3){
                etape++;
            }else{
                success=false;
            }
        } 
    }
    
    public void previous(){
        if(etape == 2 || etape == 3){
            etape --;
        }
    }
    
    public void finish(){
        if(Diplomes.size()>0){
            //-- #Inscription Code
            
            //-------------------
            success=true;
            Utils.addMessage("Inscription Complète.\nMerci de vérifier votre Email!");
            reset();
        }else{
            Utils.addMessage("Veuillez insérez vos Diplomes.");
            success=false;
        }
    }
    
    public void reset(){
        civilite = "";
        nom = "";
        prenom = "";
        tel="";
        email = "";
        mdp="";
        mdpConfirm="";
        Diplome="";
        Diplomes.clear();
        etape = 1;
    }
}

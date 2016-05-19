/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author youssefsafi
 */
@Entity
@Table(name = "professeur")
public class Professeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "civilite")
    private String civilite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "ville")
    private String ville;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tel")
    private String tel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mdp")
    private String mdp;
    @Basic(optional = false)
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "situation_pro")
    private String situationPro;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "niv_etude")
    private String nivEtude;
    @Basic(optional = false)
    @Column(name = "disponibilite")
    private int disponibilite;
    @Size(max = 100)
    @Column(name = "token")
    private String token;
    @Column(name = "compte_active",columnDefinition = "enum('0', '1', '2')")
    private char compteActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProf")
    private List<Note> noteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProf")
    private List<Conference> conferenceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProf")
    private List<Message> messageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProf")
    private List<Diplome> diplomeList;
    @NotNull
    @Column(name = "date_inscription")
    @Temporal(TemporalType.DATE)
    private Date dateInscription;
    
    public Professeur() {
    }

    public Professeur(Integer id) {
        this.id = id;
    }

    public Professeur(String civilite, String nom, String prenom, String tel, String email, String mdp) {
        
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.mdp = mdp;
        this.dateInscription = new Date();
        this.compteActive = '0';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSituationPro() {
        return situationPro;
    }

    public void setSituationPro(String situationPro) {
        this.situationPro = situationPro;
    }

    public String getNivEtude() {
        return nivEtude;
    }

    public void setNivEtude(String nivEtude) {
        this.nivEtude = nivEtude;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public char getCompteActive() {
        return compteActive;
    }

    public void setCompteActive(char compteActive) {
        this.compteActive = compteActive;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public List<Conference> getConferenceList() {
        return conferenceList;
    }

    public void setConferenceList(List<Conference> conferenceList) {
        this.conferenceList = conferenceList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Diplome> getDiplomeList() {
        return diplomeList;
    }

    public void setDiplomeList(List<Diplome> diplomeList) {
        this.diplomeList = diplomeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professeur)) {
            return false;
        }
        Professeur other = (Professeur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helpyacademy.dao.model.Professeur[ id=" + id + " ]";
    }
    
}

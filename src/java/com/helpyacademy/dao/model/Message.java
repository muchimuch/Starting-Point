/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "message")
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_env")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnv;
    @Column(name = "lu")
    private Boolean lu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "emetteur")
    private int emetteur;
    @JoinColumn(name = "idEleve", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Etudiant idEleve;
    @JoinColumn(name = "idProf", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Professeur idProf;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message(Integer id, String message, Date dateEnv, int emetteur) {
        this.id = id;
        this.message = message;
        this.dateEnv = dateEnv;
        this.emetteur = emetteur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateEnv() {
        return dateEnv;
    }

    public void setDateEnv(Date dateEnv) {
        this.dateEnv = dateEnv;
    }

    public Boolean getLu() {
        return lu;
    }

    public void setLu(Boolean lu) {
        this.lu = lu;
    }

    public int getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(int emetteur) {
        this.emetteur = emetteur;
    }

    public Etudiant getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(Etudiant idEleve) {
        this.idEleve = idEleve;
    }

    public Professeur getIdProf() {
        return idProf;
    }

    public void setIdProf(Professeur idProf) {
        this.idProf = idProf;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helpyacademy.dao.model.Message[ id=" + id + " ]";
    }
    
}

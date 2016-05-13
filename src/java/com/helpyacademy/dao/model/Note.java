/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author youssefsafi
 */
@Entity
@Table(name = "note")
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "note")
    private Integer note;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @JoinColumn(name = "idEleve", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Etudiant idEleve;
    @JoinColumn(name = "idProf", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Professeur idProf;
    @JoinColumn(name = "idConf", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conference idConf;

    public Note() {
    }

    public Note(Integer id) {
        this.id = id;
    }

    public Note(Integer id, String commentaire) {
        this.id = id;
        this.commentaire = commentaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    public Conference getIdConf() {
        return idConf;
    }

    public void setIdConf(Conference idConf) {
        this.idConf = idConf;
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
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helpyacademy.dao.model.Note[ id=" + id + " ]";
    }
    
}

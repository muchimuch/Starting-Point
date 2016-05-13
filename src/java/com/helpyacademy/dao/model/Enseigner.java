/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author youssefsafi
 */
@Entity
@Table(name = "enseigner")
public class Enseigner implements Serializable {

    private static final long serialVersionUID = 1L; 
    @EmbeddedId
    protected EnseignerPK enseignerPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prixHeure")
    private float prixHeure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cadeau20Min")
    private short cadeau20Min;

    public Enseigner() {
    }

    public Enseigner(EnseignerPK enseignerPK) {
        this.enseignerPK = enseignerPK;
    }

    public Enseigner(EnseignerPK enseignerPK, float prixHeure, short cadeau20Min) {
        this.enseignerPK = enseignerPK;
        this.prixHeure = prixHeure;
        this.cadeau20Min = cadeau20Min;
    }

    public Enseigner(int idMatiere, int idProf) {
        this.enseignerPK = new EnseignerPK(idMatiere, idProf);
    }

    public EnseignerPK getEnseignerPK() {
        return enseignerPK;
    }

    public void setEnseignerPK(EnseignerPK enseignerPK) {
        this.enseignerPK = enseignerPK;
    }

    public float getPrixHeure() {
        return prixHeure;
    }

    public void setPrixHeure(float prixHeure) {
        this.prixHeure = prixHeure;
    }

    public short getCadeau20Min() {
        return cadeau20Min;
    }

    public void setCadeau20Min(short cadeau20Min) {
        this.cadeau20Min = cadeau20Min;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enseignerPK != null ? enseignerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enseigner)) {
            return false;
        }
        Enseigner other = (Enseigner) object;
        if ((this.enseignerPK == null && other.enseignerPK != null) || (this.enseignerPK != null && !this.enseignerPK.equals(other.enseignerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helpyacademy.dao.model.Enseigner[ enseignerPK=" + enseignerPK + " ]";
    }
    
}

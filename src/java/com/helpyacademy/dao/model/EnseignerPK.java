/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author youssefsafi
 */
@Embeddable
public class EnseignerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idMatiere")
    private int idMatiere;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProf")
    private int idProf;
   
    public EnseignerPK() {
    }

    public EnseignerPK(int idMatiere, int idProf) {
        this.idMatiere = idMatiere;
        this.idProf = idProf;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMatiere;
        hash += (int) idProf;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnseignerPK)) {
            return false;
        }
        EnseignerPK other = (EnseignerPK) object;
        if (this.idMatiere != other.idMatiere) {
            return false;
        }
        if (this.idProf != other.idProf) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helpyacademy.dao.model.EnseignerPK[ idMatiere=" + idMatiere + ", idProf=" + idProf + " ]";
    }
    
}

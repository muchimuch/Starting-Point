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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author youssefsafi
 */
@Entity
@Table(name = "config")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salt")
    private String salt;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "mdpEmail")
    private String mdpEmail;
    @NotNull
    @Column(name = "mailFrom")
    private String mailFrom;
    @NotNull
    @Column(name = "mailHost")
    private String mailHost;
    @NotNull
    @Column(name = "mailPort")
    private int mailPort;

    public Config() {
    }

    public void setMailPort(int mailPort) {
        this.mailPort = mailPort;
    }

    public int getMailPort() {
        return mailPort;
    }

    public Config(Integer id) {
        this.id = id;
    }

    public Config(Integer id, String url, String salt) {
        this.id = id;
        this.url = url;
        this.salt = salt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdpEmail(String mdpEmail) {
        this.mdpEmail = mdpEmail;
    }

    public String getEmail() {
        return email;
    }

    public String getMdpEmail() {
        return mdpEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public String getMailHost() {
        return mailHost;
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
        if (!(object instanceof Config)) {
            return false;
        }
        Config other = (Config) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.helpyacademy.dao.model.Config[ id=" + id + " ]";
    }
    
}

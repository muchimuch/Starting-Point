/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.bean;

import com.helpyacademy.dao.model.Config;
import com.helpyacademy.service.BBBService;
import com.helpyacademy.service.ConfigService;
import com.helpyacademy.util.Utils;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author youssefsafi
 */
@ManagedBean
@RequestScoped
public class ConfigBean {
    
    private boolean success;
    private String url;
    private String salt;
    private String email;
    private String mdp;
    private String hostMail;
    private String from;
    private int mailPort;
    private String Serverlink;
    private boolean serverWorking;
    
    private String urlM;
    private String saltM;
    private String emailM;
    private String mdpM;
    private String hostMailM;
    private String fromM;
    private int mailPortM;
    
    private ConfigService configService;

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    public String getUrl() {
        return url;
    }

    public String getSalt() {
        return salt;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getUrlM() {
        return urlM;
    }

    public String getSaltM() {
        return saltM;
    }

    public void setUrlM(String urlM) {
        this.urlM = urlM;
    }

    public void setSaltM(String saltM) {
        this.saltM = saltM;
    }

    public String getServerlink() {
        return Serverlink;
    }

    public boolean isServerWorking() {
        return serverWorking;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public String getEmailM() {
        return emailM;
    }

    public String getMdpM() {
        return mdpM;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEmailM(String emailM) {
        this.emailM = emailM;
    }

    public void setMdpM(String mdpM) {
        this.mdpM = mdpM;
    }

    public String getHostMail() {
        return hostMail;
    }

    public String getFrom() {
        return from;
    }

    public int getMailPort() {
        return mailPort;
    }

    public String getHostMailM() {
        return hostMailM;
    }

    public String getFromM() {
        return fromM;
    }

    public int getMailPortM() {
        return mailPortM;
    }

    public void setHostMail(String hostMail) {
        this.hostMail = hostMail;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMailPort(int mailPort) {
        this.mailPort = mailPort;
    }

    public void setHostMailM(String hostMailM) {
        this.hostMailM = hostMailM;
    }

    public void setFromM(String fromM) {
        this.fromM = fromM;
    }

    public void setMailPortM(int mailPortM) {
        this.mailPortM = mailPortM;
    }
    
    /* --------------------------------------------- */
    
    public String updateServiceEmail(){
        if(email.equals(emailM) && mdp.equals(mdpM) && mailPort == mailPortM && hostMail.equals(hostMailM) && from.equals(fromM)){ 
            success = false;
            Utils.addMessage("Vous avez pas modifier vos informations");
        } else if(configService.updateServiceEmailConf(emailM,mdpM,mailPortM,hostMailM,fromM)){
            email = emailM;
            mdp = mdpM;
            mailPort = mailPortM;
            hostMail = hostMailM;
            from = fromM;
            success = true;
            Utils.addMessage("Les informations du Service Mail ont été bien enregister");
        } else {
            success = false;
            Utils.addMessage("Les informations du Service Mail n'ont pas été enregister");
        }
        return "pretty:EspaceA_Config";
    }
    
    public void initServiceEmailUpdate(){
        emailM = email;
        mdpM = mdp;
        mailPortM = mailPort;
        hostMailM = hostMail;
        fromM = from;
    }
    
    public void testBBBServeur(){
        serverWorking = false;
        String username = "User";
        String meetingID = "Test BigBlueButton";
        //
        // This is the URL for to join the meeting as moderator
        //
        Config config = configService.getConf();
        BBBService bbb = new BBBService(config.getUrl(), config.getSalt());
        String joinURL = bbb.getJoinURL(username, meetingID, "true", "<br>Bienvenu au %%CONFNAME%%.<br>", null, null, 1);
        Serverlink = joinURL;
        if(!joinURL.isEmpty())
            serverWorking = true;
    }
    
    public String updateBBB(){
        if(salt.equals(saltM) && url.equals(urlM)){
            success = false;
            Utils.addMessage("Vous avez pas modifier vos informations");
        } else if(configService.updateBBBServerConf(urlM,saltM)){
            url = urlM;
            salt = saltM;
            success = true;
            Utils.addMessage("Les informations du Serveur BigBlueButton ont été bien enregister");
        } else {
            success = false;
            Utils.addMessage("Les informations du Serveur BigBlueButton n'ont pas été enregister");
        }
        return "pretty:EspaceA_Config";
    }
    
    public void initBBBUpdate(){
        urlM = url;
        saltM = salt;
    }
    
    public void init() {
        Config config = configService.getConf();
        url = config.getUrl();
        salt = config.getSalt();
        email = config.getEmail();
        mdp = config.getMdpEmail();
        mailPort = config.getMailPort();
        hostMail = config.getMailHost();
        from = config.getMailFrom();
    }
}

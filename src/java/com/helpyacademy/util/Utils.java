/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.util;

import java.security.SecureRandom;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author youssefsafi
 */
public class Utils {
    
    public static void addMessage(String msg){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);        
    }
    
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
 
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
    
    public static String VerrificationCompteMessage(String nom,String prenom,String url){
        String msg = "<div style=\"font-size:1.3em\"><span>Salutation <b>"+nom+" "+prenom+"</b><span><br/><br/>";

        msg += "<div style=\"padding-left:30px;padding-top:40px;padding-bottom:40px\">Merci pour votre inscription sur la plateforme <b>HelpY Academy</b><br/><br/>";
        msg += "S'il vous plaît cliquer sur le lien ci-dessous pour activer votre compte :<br/><br/>";
        msg += "<a style=\"color:#2B865D\" target=\"_blank\" href=\""+url+"\">Vérifiez votre compte</a><br/><br/></div>";
        msg += "Cordialement,<br/>L'équipe <b>HelpyAcademy</b></div>";
        
        return msg;
    }
    
    public static String token(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }
    
    public static String urlVerification(String email,String token){
        return "http://localhost:8080/HelpyAcademy/VerificationCompte/"+token+"/"+email+"/";
    }
    
}

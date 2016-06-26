/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.ConfigDAO;
import com.helpyacademy.dao.ConfigDAOImpl;
import com.helpyacademy.dao.model.Config;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author youssefsafi
 */
public class MailService {
    private Session mailSession;
    
    private String HOST ;
    private int PORT ;
    private String USER ;     
    private String PASSWORD ; 
    private String FROM ;
    
    public void sendMessage(String recipient, String subject, String message) throws MessagingException {

        MimeMessage mimeMessage = new MimeMessage(mailSession);
	mimeMessage.setFrom(new InternetAddress(FROM));
	mimeMessage.setSender(new InternetAddress(FROM));
	mimeMessage.setSubject(subject);
        mimeMessage.setContent(message, "text/html");

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        Transport transport = mailSession.getTransport("smtps");
        transport.connect(HOST, PORT, USER, PASSWORD);

        transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
        transport.close();

    }

    public MailService(String host,int port,String user,String mdp,String from) {
        
        HOST = host;
        PORT = port;
        USER = user;
        PASSWORD = mdp;
        FROM = from;
        
        Properties props = new Properties();
        
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", HOST);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.from", FROM);
        props.put("mail.smtps.quitwait", "false");

        mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
    }
}

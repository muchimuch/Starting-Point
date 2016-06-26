/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.websocket;

import com.helpyacademy.util.Utils;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author mkerm
 */
@ServerEndpoint(value = "/admin/serverEndPoint")
public class ServerSide {

    private static Set<Session> openSessions = Collections.synchronizedSet(new HashSet<>());

    @OnMessage
    public void onMessage(String message, Session session) throws ParseException {
        HashMap<String, String> map;
        map = Utils.JSONToMap(message);
        switch (map.get("type")) {
            case "message":
                //- envoi du message
                // E : Emetteur - R : Recepteur
                String Etype = session.getUserProperties().get("user").toString();
                Boolean sended = false;
                for (Session s : openSessions) {
                    String Rtype = (String) s.getUserProperties().get("user");
                    // professeur peut envoyer un message à un etudiant et l'inverse
                    if (!Rtype.equalsIgnoreCase(Etype)) {
                        //System.out.println(Rtype + " " + Etype);
                        int RuserId = Integer.parseInt(map.get("recepteur"));
                        int userId = Integer.parseInt(s.getUserProperties().get("userId").toString());
                        //System.out.println(userId + " " + RuserId);
                        if (userId == RuserId) {
                            sended = true;
                            sendMessage(s, message);
                        }
                    }
                }
                //- send to user that (recepteur) is not connected
                if(!sended){
                    JsonObject msg = Json.createObjectBuilder().add("type", "horsLigne").build();
                    //System.out.println(msg.toString());
                    sendMessage(session, msg.toString());
                }
                for (Session s : openSessions) {
                    if (s.getUserProperties().get("user").toString().equals(session.getUserProperties().get("user").toString()) && s.getUserProperties().get("userId").toString().equals(session.getUserProperties().get("userId").toString())) {
                        message = message.replace("message", "synchronise");
                        sendMessage(s, message);
                    }
                }
                break;
            case "defineUser":
                session.getUserProperties().put("user", map.get("user"));
                session.getUserProperties().put("userId", map.get("userId"));
                break;
            case "online":
                Set<Integer> online = new HashSet<>();
                for(Session s: openSessions){
                    if(s.getUserProperties().get("user").toString().equalsIgnoreCase(map.get("users"))){
                        Integer id = Integer.parseInt(s.getUserProperties().get("userId").toString());
                        if(!online.contains(id)){
                           online.add(id);
                        }
                    }
                }
        }
        //sendMessage(session, message);
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        openSessions.add(session);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        openSessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }

    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Notification;
import java.util.List;

/**
 *
 * @author youssefsafi
 */
public interface NotificationDAO {

    public Integer addNotification(Notification notification);
    public List<Notification> getNotification(int idUser,char espace);
    public Integer notificationVu(int notifID);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Notification;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author youssefsafi
 */
public class NotificationDAOImpl implements NotificationDAO{
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer addNotification(Notification notification) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try{
           tx = session.beginTransaction();
           ID = (Integer) session.save(notification); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        return ID;
    }

    @Override
    public List<Notification> getNotification(int idUser, char espace) {
        String hql = "FROM Notification WHERE idUtilisateur=:idUser AND espace='"+espace+"' AND vu='0'";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idUser", idUser);
        List<Notification> n = q.list();
        session.close();
        
        return n;
    }

    @Override
    public Integer notificationVu(int notifID) {
        String hql = "UPDATE Notification SET vu='1' WHERE id=:idNotif";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idNotif", notifID);
        Integer id = q.executeUpdate();
        session.close();
        
        return id;
    }
    
}

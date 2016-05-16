/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Admin;
import com.helpyacademy.dao.model.Niveau;
import java.util.Date;
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
public class NiveauDAOImpl implements NiveauDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Niveau> listNiveau() {
        Session session = sessionFactory.openSession();
        List<Niveau> list = session.createQuery("FROM Niveau").list();
        session.close();
        return list;
    }

    @Override
    public Integer ajouter(Niveau niveau) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try {
            tx = session.beginTransaction();
            ID = (Integer) session.save(niveau);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ID;
    }

    @Override
    public void delete(Niveau niveau) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(niveau);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    @Override
    public boolean update(Niveau niveau){
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         session.update(niveau); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null)
             tx.rollback();
         e.printStackTrace(); 
         return false;
      }finally {
         session.close(); 
      }
      return true;
   }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.EnseignerPK;
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
public class EnseignerDAOImpl implements EnseignerDAO{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Enseigner enseigner) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(enseigner);
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
    public List<Enseigner> MatiereParNiv(int IDniv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Enseigner enseigner) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        EnseignerPK ID = null;
        try{
           tx = session.beginTransaction();
           session.update(enseigner); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }

    @Override
    public List<Enseigner> MatiereEnseigner(int IDP) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Enseigner WHERE enseignerPK.idProf=:idp");
        q.setParameter("idp", IDP);
        
        List<Enseigner> list = q.list();
        session.close();
        return list;
    }

    @Override
    public EnseignerPK add(Enseigner enseigner) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        EnseignerPK ID = null;
        try{
           tx = session.beginTransaction();
           ID = (EnseignerPK) session.save(enseigner); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        return ID;
    }
    
}

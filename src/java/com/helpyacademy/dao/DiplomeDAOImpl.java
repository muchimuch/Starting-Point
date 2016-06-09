/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.Professeur;
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
public class DiplomeDAOImpl implements DiplomeDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer ajouter(Diplome diplome) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try {
            tx = session.beginTransaction();
            ID = (Integer) session.save(diplome);
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
    public List<Diplome> getDiplomes(Professeur idProf) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Diplome WHERE idProf=:idProf");
        q.setParameter("idProf", idProf);
        List<Diplome> list = q.list();
        session.close();
        return list;
    }

    @Override
    public void delete(Diplome d) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try {
            tx = session.beginTransaction();
            session.delete(d);
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
    public Diplome getDiplomeByName(String diplome,int idp) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Diplome WHERE diplome=:diplome AND idProf.id=:idP");
        q.setParameter("diplome", diplome);
        q.setParameter("idP", idp);
        Diplome d = (Diplome) q.uniqueResult();
        session.close();
        return d;
    }

    @Override
    public void update(Diplome d) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try {
            tx = session.beginTransaction();
            session.update(d);
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

}

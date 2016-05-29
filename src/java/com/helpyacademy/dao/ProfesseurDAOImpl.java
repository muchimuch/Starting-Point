/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Professeur;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author youssefsafi
 */
public class ProfesseurDAOImpl implements ProfesseurDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Professeur login(String email, String mdp) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Professeur WHERE email=:email AND mdp=:mdp";
        Query q = session.createQuery(hql);
        q.setParameter("email", email);
        q.setParameter("mdp", mdp);
        Professeur professeur = (Professeur) q.uniqueResult();
        session.close();

        return professeur;
    }

    @Override
    public boolean emailExiste(String email) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Professeur WHERE email=:email");
        q.setParameter("email", email);
        Professeur p = (Professeur) q.uniqueResult();
        session.close();
        return p != null;
    }

    @Override
    public Integer ajouter(Professeur p) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try {
            tx = session.beginTransaction();
            ID = (Integer) session.save(p);
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
    public boolean isPresent(String email, String token) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Professeur WHERE email=:email AND token=:token");
        q.setParameter("email", email);
        q.setParameter("token", token);

        Professeur p = (Professeur) q.uniqueResult();
        session.close();
        return p != null;
    }

    @Override
    public Integer activerCompte(String email) {
        String hql = "UPDATE Professeur set compteActive = '1',token = null WHERE email=:email";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        Integer n = query.executeUpdate();
        session.close();
        
        return n;
    }

    @Override
    public Professeur getProf(String email) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Professeur WHERE email=:email";
        Query q = session.createQuery(hql);
        q.setParameter("email", email);
        Professeur professeur = (Professeur) q.uniqueResult();
        session.close();

        return professeur;
    }

    @Override
    public Professeur getProfByID(int idp) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Professeur WHERE id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", idp);
        Professeur professeur = (Professeur) q.uniqueResult();
        session.close();

        return professeur;
    }

    @Override
    public void update(Professeur p) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try {
            tx = session.beginTransaction();
            session.update(p);
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

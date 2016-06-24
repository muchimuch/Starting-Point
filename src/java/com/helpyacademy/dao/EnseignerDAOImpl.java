/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Enseigner;
import com.helpyacademy.dao.model.EnseignerPK;
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

    @Override
    public Enseigner getMatiere(int idMatiere, int idp) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Enseigner WHERE enseignerPK.idProf=:idp AND enseignerPK.idMatiere=:idMatiere");
        q.setParameter("idp", idp);
        q.setParameter("idMatiere", idMatiere);
        Enseigner e = (Enseigner) q.uniqueResult();
        session.close();
        return e;
    }

    @Override
    public List<Enseigner> getMatiere(int idM) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Enseigner WHERE enseignerPK.idMatiere=:idMatiere");
        q.setParameter("idMatiere", idM);
        List<Enseigner> Liste = q.list();
        session.close();
        return Liste;
    }

    @Override
    public int nbrCoursDisponible() {
        Session session = sessionFactory.openSession();
        Long nbr = (Long) session.createQuery("SELECT COUNT(enseignerPK.idMatiere) FROM Enseigner").uniqueResult();
        session.close();
        return nbr.intValue();
    }

    @Override
    public int nbrMesCours(int idp) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("SELECT COUNT(enseignerPK.idMatiere) FROM Enseigner WHERE enseignerPK.idProf=:idProf");
        q.setParameter("idProf", idp);
        Long nbr = (Long) q.uniqueResult();
        session.close();
        return nbr.intValue();
    }

    @Override
    public int nbrOffres() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("SELECT COUNT(enseignerPK.idMatiere) FROM Enseigner");
        Long nbr = (Long) q.uniqueResult();
        session.close();
        return nbr.intValue();
    }
    
}

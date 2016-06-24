/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Conference;
import com.helpyacademy.dao.model.Etudiant;
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
public class ConferenceDAOImpl implements ConferenceDAO{
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Integer add(Conference conference) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try{
           tx = session.beginTransaction();
           ID = (Integer) session.save(conference); 
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
    public List<Conference> listCommandes(int idE) {
        String hql = "FROM Conference WHERE idEleve.id=:idE";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idE", idE);
        List<Conference> e = q.list();
        session.close();
        
        return e;
    }

    @Override
    public void delete(Conference conf) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try{
           tx = session.beginTransaction();
           session.delete(conf); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }

    @Override
    public void update(Conference conf) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try{
           tx = session.beginTransaction();
           session.update(conf); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }

    @Override
    public List<Conference> listCommandesP(int idP) {
        String hql = "FROM Conference WHERE idProf.id=:idP";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idP", idP);
        List<Conference> e = q.list();
        session.close();
        
        return e;
    }

    @Override
    public Conference getConference(int idConf) {
        String hql = "FROM Conference WHERE id=:idConf";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idConf", idConf);
        Conference c = (Conference) q.uniqueResult();
        session.close();
        
        return c;
    }

    @Override
    public List<Conference> listCoursE(int idE) {
        String hql = "FROM Conference WHERE idEleve.id=:idE AND statut=1";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idE", idE);
        List<Conference> e = q.list();
        session.close();
        
        return e;
    }

    @Override
    public List<Conference> listCoursP(int idP) {
        String hql = "FROM Conference WHERE idProf.id=:idP AND statut=1";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idP", idP);
        List<Conference> e = q.list();
        session.close();
        
        return e;
    }

    @Override
    public Integer confirmerFinCours(int idConf) {
        String hql = "UPDATE Conference set statut = 2 WHERE id=:id";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("id", idConf);
        Integer n = query.executeUpdate();
        session.close();
        
        return n;
    }

    @Override
    public int nbrEtudiantCours(int idE) {
        String hql = "SELECT COUNT(id) FROM Conference WHERE idEleve.id=:idE AND statut=1";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idE", idE);
        Long e = (Long) q.uniqueResult();
        session.close();
        
        return e.intValue();
    }

    @Override
    public int nbrEtudiantNewCMD(int idE) {
        String hql = "SELECT COUNT(id) FROM Conference WHERE idEleve.id=:idE AND statut=0";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idE", idE);
        Long e = (Long) q.uniqueResult();
        session.close();
        
        return e.intValue();
    }

    @Override
    public int nbrCoursPrevenu(int idp) {
        String hql = "SELECT COUNT(id) FROM Conference WHERE idProf.id=:idp AND statut=1";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idp", idp);
        Long e = (Long) q.uniqueResult();
        session.close();
        
        return e.intValue();
    }

    @Override
    public int nbrNouvelleCmd(int idp) {
        String hql = "SELECT COUNT(id) FROM Conference WHERE idProf.id=:idp AND statut=0";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("idp", idp);
        Long e = (Long) q.uniqueResult();
        session.close();
        
        return e.intValue();
    }

    @Override
    public int nbrNCmd() {
        String hql = "SELECT COUNT(id) FROM Conference WHERE statut=0";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        Long e = (Long) q.uniqueResult();
        session.close();
        
        return e.intValue();
    }

    @Override
    public int nbrCoursProgramme() {
        String hql = "SELECT COUNT(id) FROM Conference WHERE statut=1";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        Long e = (Long) q.uniqueResult();
        session.close();
        
        return e.intValue();
    }
}

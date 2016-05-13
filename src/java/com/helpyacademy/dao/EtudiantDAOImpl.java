/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Etudiant;
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
public class EtudiantDAOImpl implements EtudiantDAO{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Integer add(Etudiant etudiant) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try{
           tx = session.beginTransaction();
           etudiant.setDateInscription(new Date());
           ID = (Integer) session.save(etudiant); 
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
    public boolean emailExiste(String email) {
        Session session = sessionFactory.openSession();
        int nbr = session.createQuery("FROM Etudiant e WHERE e.email='"+email+"'").list().size();
        session.close();
        if(nbr == 0)
            return false;
        return true;
    }

    @Override
    public Integer activerCompte(String email) {
        String hql = "UPDATE Etudiant set compte_active = true,token = null WHERE email=:email";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        
        return query.executeUpdate();
    }

    @Override
    public boolean isPresent(String email, String token) {
        String hql = "FROM Etudiant WHERE email=:email AND token=:token";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("email", email);
        q.setParameter("token", token);
        int n = q.list().size();
        if(n == 0)
            return false;
        return true;
    }

    @Override
    public Etudiant login(String email, String mdp) {
        String hql = "FROM Etudiant WHERE email=:email AND mdp=:mdp AND compte_active=true";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("email", email);
        q.setParameter("mdp", mdp);
        return (Etudiant) q.uniqueResult();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Professeur;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author youssefsafi
 */
public class ProfesseurDAOImpl implements ProfesseurDAO{

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
    
}
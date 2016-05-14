/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Admin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author youssefsafi
 */
public class AdminDAOImpl implements AdminDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Admin login(String email, String mdp) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Admin WHERE email=:email AND motDePasse=:mdp";
        Query q = session.createQuery(hql);
        q.setParameter("email", email);
        q.setParameter("mdp", mdp);
        Admin admin = (Admin) q.uniqueResult();
        session.close();
        
        return admin;
    }

}

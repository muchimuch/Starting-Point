/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Admin;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

    @Override
    public void update(Admin admin) {
        Session session = sessionFactory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         session.update(admin); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null)
             tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
    }

    @Override
    public Admin getAdmin(String email) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Admin WHERE email=:email";
        Query q = session.createQuery(hql);
        q.setParameter("email", email);
        Admin admin = (Admin) q.uniqueResult();
        session.close();
        
        return admin;
    }

}

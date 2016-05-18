/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Professeur;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author mkerm
 */
public class professeurDAO implements DAO<Professeur>{
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    
    @Override
    public Professeur insert(Professeur o) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Professeur insertedProf = null;
        Integer ID = 0;
        try{
           tx = session.beginTransaction();
           //Professeur p = new Professeur(civilite, nom, prenom, adresse, ville, tel, email, mdp, dateNaissance, situationPro, nivEtude, 0, compteActive);
           ID = (int) session.save(o);
           if( ID != 0 )
               insertedProf = find(ID);
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace();
        }finally {
           session.close(); 
        }
        return insertedProf;
    }

    @Override
    public Professeur update(Professeur o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professeur find(Integer id) {
        Session session = sessionFactory.openSession();
        return (Professeur) session.get(Professeur.class, id);
    }
    
    public boolean emailExiste(String email) {
        Session session = sessionFactory.openSession();
        int nbr = session.createQuery("FROM Professeur e WHERE e.email='"+email+"'").list().size();
        session.close();
        return nbr != 0;
    }
    
}

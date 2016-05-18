/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Matiere;
import com.helpyacademy.dao.model.Niveau;
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
public class MatiereDAOImpl implements MatiereDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer ajouter(Matiere matiere) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer ID = null;
        try{
           tx = session.beginTransaction();
           ID = (Integer) session.save(matiere); 
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
    public void delete(Matiere matiere) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(matiere);
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
    public List<Matiere> MatiereParNiv(int IDniveau) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Matiere WHERE idNiveau=:idNiv");
        Niveau niveau = new Niveau();
        niveau.setId(IDniveau);
        q.setParameter("idNiv", niveau);
        List<Matiere> list = q.list();
        session.close();
        
        return list;
    }

    @Override
    public Long nbrMatiere() {
        Session session = sessionFactory.openSession();
        Long nbr = (Long) session.createQuery("SELECT COUNT(id) FROM Matiere").uniqueResult();
        session.close();
        return nbr;
    }
    
    @Override
    public boolean update(Matiere matiere){
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         session.update(matiere); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null)
             tx.rollback();
         e.printStackTrace(); 
         return false;
      }finally {
         session.close(); 
      }
      return true;
   }

}

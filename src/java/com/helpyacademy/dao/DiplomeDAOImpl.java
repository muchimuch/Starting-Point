/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Diplome;
import com.helpyacademy.dao.model.Professeur;
import java.util.ArrayList;
import java.util.Collection;
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
    public List<Diplome> getProfDiplomes(Professeur p) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Diplome WHERE idProf.id=:id");
        q.setParameter("id", p.getId());
        List result = q.list();
        session.close();
        return castList(Diplome.class, result);
    }

    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<>(c.size());
        for (Object o : c) {
            r.add(clazz.cast(o));
        }
        return r;
    }
    
}

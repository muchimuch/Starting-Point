/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

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
    public List<Professeur> getProfByCompte(char etat) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Professeur WHERE compte_active=:comte_active");
        q.setParameter("comte_active", '1');
        List result = q.list();
        session.close();
        return castList(Professeur.class, result);
    }

    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<>(c.size());
        for (Object o : c) {
            r.add(clazz.cast(o));
        }
        return r;
    }

    @Override
    public boolean accepterProfInscription(Professeur p) {
        String hql = "UPDATE Professeur set compteActive = '2' WHERE email=:email";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("email", p.getEmail());
        Integer n = query.executeUpdate();
        session.close();
        return n > 0;
    }

    @Override
    public boolean refuserProfInscription(Professeur p) {
        String hql = "DELETE FROM Professeur WHERE email=:email";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("email", p.getEmail());
        int result = query.executeUpdate();
        return result > 0;
    }

    @Override
    public Professeur getProfesseurById(int id) {
        Professeur p = null;
        String hql = "FROM Professeur WHERE id=:id";
        Session session = sessionFactory.openSession();
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        p = (Professeur) q.uniqueResult();
        session.close();
        return p;
    }
}

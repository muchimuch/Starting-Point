/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Etudiant;
import com.helpyacademy.dao.model.Message;
import com.helpyacademy.dao.model.Professeur;
import com.helpyacademy.util.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author mkerm
 */
public class ChatDAOImpl implements ChatDAO{
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Etudiant> getProfesseurContacts(int profId) {
        List<Etudiant> contacts = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM message WHERE idProf=:idProf");
        q.setParameter("idProf", profId);
        List<Message> messageList = Utils.castList(Message.class, q.list());
        for(Message m : messageList){
            contacts.add(m.getIdEleve());
        }
        session.close();
        return contacts;
    }

    @Override
    public List<Professeur> getEtudiantContacts(int etudId) {
        Set<Professeur> contacts = Collections.synchronizedSet(new HashSet<>());
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM message WHERE idEleve=:idEleve");
        q.setParameter("idEleve", etudId);
        List<Message> messageList = Utils.castList(Message.class, q.list());
        for(Message m : messageList){
            contacts.add(m.getIdProf());
        }
        session.close();
        return null;
    }

    @Override
    public void insert(Message msg) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(msg);
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
    
}

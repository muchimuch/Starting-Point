/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Niveau;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author youssefsafi
 */
public class NiveauDAOImpl implements NiveauDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Niveau> listNiveau() {
        Session session = sessionFactory.openSession();
        List<Niveau> list = session.createQuery("FROM Niveau").list();
        session.close();
        return list;
    }

}

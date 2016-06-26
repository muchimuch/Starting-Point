/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Config;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author youssefsafi
 */
public class ConfigDAOImpl implements ConfigDAO{
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Config getConf(int i) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Config WHERE id=:id");
        q.setParameter("id", i);
        Config config = (Config) q.uniqueResult();
        session.close();
        return config;
    }

    @Override
    public Integer updateBBBServerConf(String url, String salt, int i) {
        String hql = "UPDATE Config set salt=:salt ,url=:url WHERE id=:id";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("id", i);
        query.setParameter("salt", salt);
        query.setParameter("url", url);
        Integer n = query.executeUpdate();
        session.close();
        return n;
    }

    @Override
    public Integer updateServiceEmailConf(String emailM, String mdpM, int mailPort, String hostMailM, String fromM, int i) {
        String hql = "UPDATE Config set email=:email ,mdpEmail=:mdp,mailPort=:port,mailHost=:host,mailFrom=:from WHERE id=:id";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("id", i);
        query.setParameter("email", emailM);
        query.setParameter("mdp", mdpM);
        query.setParameter("port", mailPort);
        query.setParameter("host", hostMailM);
        query.setParameter("from", fromM);
        Integer n = query.executeUpdate();
        session.close();
        return n;
    }

    @Override
    public Integer updateWebSiteConf(String urlSiteM) {
        String hql = "UPDATE Config set urlSite=:url WHERE id=1";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("url", urlSiteM);
        Integer n = query.executeUpdate();
        session.close();
        return n;
    }
    
}
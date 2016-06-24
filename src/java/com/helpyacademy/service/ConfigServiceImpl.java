/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.ConfigDAO;
import com.helpyacademy.dao.model.Config;

/**
 *
 * @author youssefsafi
 */
public class ConfigServiceImpl implements ConfigService{
    
    private ConfigDAO configDAO;

    public void setConfigDAO(ConfigDAO configDAO) {
        this.configDAO = configDAO;
    }

    @Override
    public Config getConf() {
        return configDAO.getConf(1);
    }

    @Override
    public boolean updateBBBServerConf(String url, String salt) {
        Integer id = configDAO.updateBBBServerConf(url,salt,1);
        return id != null;
    }
    
    
}

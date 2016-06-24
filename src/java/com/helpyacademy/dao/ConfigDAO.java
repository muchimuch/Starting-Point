/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Config;

/**
 *
 * @author youssefsafi
 */
public interface ConfigDAO {

    public Config getConf(int i);

    public Integer updateBBBServerConf(String url, String salt, int i);
    
}

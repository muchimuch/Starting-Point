/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

import com.helpyacademy.dao.model.Config;

/**
 *
 * @author youssefsafi
 */
public interface ConfigService {

    public Config getConf();
    public boolean updateBBBServerConf(String url, String salt);
    public boolean updateServiceEmailConf(String emailM, String mdpM, int mailPort, String hostMailM, String fromM);

    public boolean webSiteSettingUpdate(String urlSiteM);
    
}

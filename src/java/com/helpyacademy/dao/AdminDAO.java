/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.dao;

import com.helpyacademy.dao.model.Admin;

/**
 *
 * @author youssefsafi
 */
public interface AdminDAO {
    public Admin login(String email,String mdp);
}

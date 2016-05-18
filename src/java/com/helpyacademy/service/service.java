/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.service;

/**
 *
 * @author mkerm
 */

public interface service<T> {
    public T insert(T o);
    public T update(T o);
    public boolean delete(Integer id);
    public T find(Integer id);
}

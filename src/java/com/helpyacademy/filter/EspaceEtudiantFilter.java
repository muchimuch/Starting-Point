/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.filter;

import com.helpyacademy.bean.EtudiantBean;
import java.io.IOException;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author youssefsafi
 */
@WebFilter(filterName = "EspaceEtudiantFilter", urlPatterns = {"/WEB-INF/espaces/*"},dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.ERROR})
public class EspaceEtudiantFilter implements Filter {
    
    public EspaceEtudiantFilter() {
    }    

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession ses = req.getSession(false);
    
        if(ses == null || ses.getAttribute("EtudiantNom") == null){
            res.sendRedirect(req.getContextPath()+"/Auth/Etudiant");
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
        
    }

 
}

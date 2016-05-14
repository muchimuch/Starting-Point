/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpyacademy.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
@WebFilter(filterName = "EspaceEtudiantFilter", urlPatterns = {"/WEB-INF/espaces/admin/*"},dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.ERROR})
public class EspaceAdminFilter implements Filter {
    
    public EspaceAdminFilter() {
    }    
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession ses = req.getSession(false);
    
        if(ses == null || ses.getAttribute("AdmineEmail") == null){
            res.sendRedirect(req.getContextPath()+"/Auth/Admin");
        }else{
            chain.doFilter(request, response);
        }
       
    }

    

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        
    }
    
}

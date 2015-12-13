/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.mvc.security;


import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.facade.EmployeeFacade;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Severin Simko
 */

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter{
    
    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        
        
        
        HttpSession http = request.getSession(true);
        Long employeeId=  (Long)http.getAttribute("id");
      
        if(employeeId == null){
            response401(response);
        
        }        
        
        EmployeeFacade empFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(EmployeeFacade.class);;
        EmployeeDTO employeeDTO = empFacade.findEmployeeById(employeeId);
        if(employeeDTO ==null){
            response401(response);
        }        
     
        chain.doFilter(request, response);
    }   
    
    
     @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    
    private void response401(HttpServletResponse response) throws IOException {
        response.sendRedirect("/auth/login");
    }
    
    @Override
    public void destroy() {

    }
    
    
}

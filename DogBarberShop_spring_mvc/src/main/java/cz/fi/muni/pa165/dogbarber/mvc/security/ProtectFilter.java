package cz.fi.muni.pa165.dogbarber.mvc.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Severin Simko
 */


@WebFilter(urlPatterns = {"/shopping/*"})
public class ProtectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
   
        HttpSession session = request.getSession(true);
        
        EmployeeDTO employee = (EmployeeDTO)session.getAttribute("admin");
        if(employee ==null){
            CustomerDTO customer = (CustomerDTO)session.getAttribute("authUser");
            request.setAttribute("authUser", session.getAttribute("authUser"));
            if(customer==null){ response401(response);}          
            
        
        }else{
        request.setAttribute("admin", session.getAttribute("admin"));
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

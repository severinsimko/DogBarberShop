package cz.fi.muni.pa165.dogbarber.mvc.controllers;

import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.facade.EmployeeFacade;
import cz.fi.muni.pa165.dogbarber.mvc.utils.Utils;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Severin Simko
 */

//this class is accessable only for employee who is root
@Controller
@RequestMapping("/employee")
public class EmployeeController {
  
  @Autowired
  private  EmployeeFacade emp;
 
    @RequestMapping(value = {"", "/", "/"}, method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest req){
        if(!Utils.isAdmin(req.getSession()))
                    return "redirect:/auth/login";
        Collection<EmployeeDTO> allEmployees = emp.getAllEmployees();
       
        model.addAttribute("employees", allEmployees);
        
        
        return "/employee/home";
    }
  
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model, HttpServletRequest req){
        if(!Utils.isAdmin(req.getSession()))
                    return "redirect:/auth/login";
        model.addAttribute("employee", emp.findEmployeeById(id));
        model.addAttribute("employeeService", emp.findEmployeeById(id).getServices());
        return "employee/view";
    }
    
  
}

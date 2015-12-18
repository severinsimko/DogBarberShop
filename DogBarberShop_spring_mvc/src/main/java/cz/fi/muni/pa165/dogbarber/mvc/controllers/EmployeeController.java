package cz.fi.muni.pa165.dogbarber.mvc.controllers;

import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.facade.EmployeeFacade;
import static cz.fi.muni.pa165.dogbarber.mvc.controllers.AuthController.log;
import static cz.fi.muni.pa165.dogbarber.mvc.controllers.ServiceController.log;
import java.util.Collection;
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
    public String index(Model model){
        Collection<EmployeeDTO> allEmployees = emp.getAllEmployees();
       
        model.addAttribute("employees", allEmployees);
        
        
        return "/employee/home";
    }
  
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model){
        model.addAttribute("employee", emp.findEmployeeById(id));
        model.addAttribute("employeeService", emp.findEmployeeById(id).getServices());
        return "employee/view";
    }
    
  
}

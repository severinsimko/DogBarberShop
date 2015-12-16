package cz.fi.muni.pa165.dogbarber.mvc.controllers;

import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.facade.ServiceFacade;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping("/service")
public class ServiceController {
    
    @Autowired
    private ServiceFacade serviceFacade;
    
    
    final static Logger log = LoggerFactory.getLogger(ServiceController.class);
    
     @RequestMapping(value = {"", "/", "/shopping"}, method = RequestMethod.GET)
    public String index(Model model){
        Collection<ServiceDTO> allServices = serviceFacade.getAllServices();
        log.error("All the services",allServices);
        model.addAttribute("services", allServices);
        
        Collection<ServiceDTO> sortedServices =serviceFacade.getSortedServices();
        model.addAttribute("sorted", sortedServices);
        
        return "service/shopping";        
        
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model){
        model.addAttribute("serviceView", serviceFacade.getServiceById(id));
        log.error("EROOOOR",serviceFacade.getServiceById(id));
        return "service/view";
    }
    
    
}

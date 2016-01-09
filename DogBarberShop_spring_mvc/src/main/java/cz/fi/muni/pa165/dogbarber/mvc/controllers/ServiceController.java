package cz.fi.muni.pa165.dogbarber.mvc.controllers;

import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.facade.ServiceFacade;
//import cz.fi.muni.pa165.dogbarber.mvc.forms.CreateServiceValidator;
import java.util.Collection;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
        log.info("All the services",allServices);
        model.addAttribute("services", allServices);
        
        Collection<ServiceDTO> sortedServices = serviceFacade.getSortedServices();
        model.addAttribute("sorted", sortedServices);
        
        return "service/shopping";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model){
        model.addAttribute("serviceView", serviceFacade.getServiceById(id));
        return "service/view";
    }
    
    
    
    

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("serviceCreate", new ServiceDTO());
        return "service/create";
    }
    
    
   /* @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof ServiceDTO) {
            binder.addValidators(new CreateServiceValidator());
        }
    }*/
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("serviceCreate") ServiceDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){
        log.error("Create service(formBean={})", formBean);
        
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "/service/create";
        }
        
       
        
        Long id = serviceFacade.createService(formBean);
        
        return "redirect:" + uriBuilder.path("/service").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        serviceFacade.deleteService(id);
       return "redirect:" + uriBuilder.path("/service").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/order/{id}", method = RequestMethod.POST)
    public String order(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        serviceFacade.addEmployee(id, id);
       return "redirect:" + uriBuilder.path("/service").buildAndExpand(id).encode().toUriString();
    }
    
}

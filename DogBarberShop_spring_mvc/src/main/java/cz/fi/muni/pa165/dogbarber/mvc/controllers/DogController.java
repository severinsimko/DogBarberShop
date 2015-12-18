package cz.fi.muni.pa165.dogbarber.mvc.controllers;

import java.util.EnumSet;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import cz.fi.muni.pa165.dogbarber.dto.DogCreateDTO;
import cz.fi.muni.pa165.dogbarber.dto.DogDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import cz.fi.muni.pa165.dogbarber.facade.CustomerFacade;
import cz.fi.muni.pa165.dogbarber.facade.DogFacade;
import cz.fi.muni.pa165.dogbarber.facade.ServiceFacade;

/**
*
* @author Pavel Drobek
*/
@Controller
@RequestMapping("/dog")
public class DogController {
    final static Logger LOGGER = LoggerFactory.getLogger(DogController.class);

    @Autowired
    private DogFacade dogFacade;
    
    @Autowired
    private ServiceFacade serviceFacade;

    @Autowired
    private CustomerFacade customerFacade;
    
    @RequestMapping(value = {"", "/", "/list"}, method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("dogs", dogFacade.getAllDogs());
        return "dog/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
    	model.addAttribute("dog", dogFacade.getDogByID(id));
    	model.addAttribute("services", serviceFacade.getAllServices());
    	return "dog/view";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        DogDTO dog = dogFacade.getDogByID(id);
        dogFacade.deleteDog(dog);
        
        redirectAttributes.addFlashAttribute("alert_success", "Dog \"" + dog.getName() + "\" was removed from database.");
        return "redirect:" + uriBuilder.path("/dog/list").build().toUriString();
    }
    
    @RequestMapping(value = "/subscribe/{id}/", method = RequestMethod.POST)
    public String subscribe(@PathVariable long id, HttpServletRequest request, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
    	Long serviceId = Long.valueOf(request.getParameter("services"));
        DogDTO dog = dogFacade.getDogByID(id);
        ServiceDTO service = serviceFacade.getServiceById(serviceId);
        
        try {
        	dogFacade.subscribeDogForAService(dog, service);
        } catch ( DogBarberException e) {
        	LOGGER.info(e.getMessage());
        	redirectAttributes.addFlashAttribute("alert_warning", "Dog \"" + dog.getName() + "\" is already subscribed for " + service.getServiceName() + " service");
            return "redirect:" + uriBuilder.path("/dog/view/{id}").build().toUriString();
        }
        redirectAttributes.addFlashAttribute("alert_success", "Dog \"" + dog.getName() + "\" was succesfully subscribed for " + service.getServiceName() + " service");
        return "redirect:" + uriBuilder.path("/dog/view/{id}").build().toUriString();
    }
    
    @RequestMapping(value = "/unsubscribe/{id}/{serviceName}", method = RequestMethod.GET)
    public String unsubscribe(@PathVariable Long id, @PathVariable String serviceName, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
    	ServiceDTO service = serviceFacade.getServicesByName(serviceName).get(0);
    	DogDTO dog = dogFacade.getDogByID(id);
        //ServiceDTO service = serviceFacade.getServiceById(Long.valueOf(idds[1]));
        
        dogFacade.unsubscribeDogForAService(dog, service);
        
        redirectAttributes.addFlashAttribute("alert_success", "Dog \"" + dog.getName() + "\" was succesfully unsubscribed from " + service.getServiceName() + " service");
        return "redirect:" + uriBuilder.path("/dog/view/{id}").build().toUriString();
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDog(Model model) {
        model.addAttribute("dogCreate", new DogCreateDTO());
        model.addAttribute("customers", customerFacade.getAllCustomers());
        model.addAttribute("colors", EnumSet.allOf( Color.class));
        return "dog/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("dogCreate") DogCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("colors", EnumSet.allOf( Color.class));
        	model.addAttribute("customers", customerFacade.getAllCustomers());
        	
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "dog/new";
        }
        
        dogFacade.createDog(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Dog " + formBean.getName() + " was added to database");
        return "redirect:" + uriBuilder.path("/dog/list").build().toUriString();
    }
}
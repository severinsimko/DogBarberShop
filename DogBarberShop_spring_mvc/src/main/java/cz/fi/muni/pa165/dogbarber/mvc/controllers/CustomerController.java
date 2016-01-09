/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.mvc.controllers;
import cz.fi.muni.pa165.dogbarber.dto.CustomerCreateDTO;
import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import cz.fi.muni.pa165.dogbarber.facade.CustomerFacade;
import java.util.Collection;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
/**
 *
 * @author Martin Penaz
 */

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerFacade customerFacade;
    
    final static Logger log = LoggerFactory.getLogger(CustomerController.class);
    
    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String index(Model model){
        Collection<CustomerDTO> customers = customerFacade.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/home";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        customerFacade.deleteCustomer(id);
        return "redirect:" + uriBuilder.path("/customer").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model){
        model.addAttribute("customer", customerFacade.getCustomerById(id));        
        return "customer/view";
    }
       
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("customerCreate", new CustomerCreateDTO());
        return "customer/create";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("customerCreate") CustomerCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder){
        log.error("create customer(formBean={})", formBean);
        
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "/customer/create";
        }
        
       
        try{
            Long id = customerFacade.registerCustomer(formBean, formBean.getPassword());
           }catch(PersistenceException e){
               redirectAttributes.addFlashAttribute("alert_danger", "!This email is already in use!");
               redirectAttributes.addFlashAttribute("alert_info", "Please use the other email for registration!");
                return "redirect:/customer/create";
            }
        
        return "redirect:" + uriBuilder.path("/customer").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model){
        CustomerDTO customer = customerFacade.getCustomerById(id);
        CustomerDTO update = new CustomerDTO();
        update.setId(id);
        update.setAllDogs(customer.getAllDogs());
        model.addAttribute("customerUpdate", update);
        return "customer/update";
    }
}

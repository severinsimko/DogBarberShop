/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarbershop_rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import cz.fi.muni.pa165.dogbarber.facade.CustomerFacade;
import cz.fi.muni.pa165.dogbarbershop_rest.ApiUris;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Martin Penaz
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_SERVICES)
public class CustomerController {
    final static Logger logger = LoggerFactory.getLogger(ServicesController.class);
    
    @Autowired
    private CustomerFacade customerFacade;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CustomerDTO> getAllCustomers() throws JsonProcessingException{
        return customerFacade.getAllCustomers();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomer(@PathVariable("id") long id) throws Exception {
        CustomerDTO customerDTO = customerFacade.getCustomerById(id);
        if(customerDTO == null){
            throw new Exception();
        }
        return customerDTO;
    }
    
    @RequestMapping(value = "/findbyemail/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomerByEmail(@PathVariable("email") String email) throws Exception {
        CustomerDTO customerDTO = customerFacade.getCustomerByEmail(email);
        if(customerDTO == null){
            throw new Exception();
        }
        return customerDTO;
    }
}

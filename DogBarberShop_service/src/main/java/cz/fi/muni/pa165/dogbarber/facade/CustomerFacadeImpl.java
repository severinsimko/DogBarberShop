/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.CustomerAuthenticateDTO;
import cz.fi.muni.pa165.dogbarber.dto.CustomerCreateDTO;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import cz.fi.muni.pa165.dogbarber.facade.CustomerFacade;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.CustomerService;
import cz.fi.muni.pa165.dogbarber.service.DogService;
import java.util.Collection;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
/**
 *
 * @author Martin Penaz
 */
@Transactional
@Service
public class CustomerFacadeImpl implements CustomerFacade {

    @Inject
    private CustomerService customerService;

    @Inject
    private DogService dogService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public Collection<CustomerDTO> getAllCustomers() {
        return beanMappingService.mapTo(customerService.findAll(), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerById(Long Id) {
        Customer c = customerService.findById(Id);
        return (c == null) ? null : beanMappingService.mapTo(c, CustomerDTO.class);
    }

    @Override
    public void addDog(Long CustomerId, Long DogId) {
        customerService.addDog(dogService.getDogByID(DogId), customerService.findById(CustomerId));
    }

    @Override
    public void removeDog(Long CustomerId, Long DogId) {
        customerService.removeDog(dogService.getDogByID(DogId), customerService.findById(CustomerId));
    }

    @Override
    public void deleteCustomer(Long CustomerId) {
        customerService.deleteCustomer(new Customer(CustomerId));
    }

    @Override
    public void registerCustomer(CustomerCreateDTO c, String password) {
        Customer customer = beanMappingService.mapTo(c, Customer.class);
        customerService.registerCustomer(customer, password);
        c.setId(customer.getId());
    }

    @Override
    public boolean authenticate(CustomerAuthenticateDTO c) {
        return customerService.authenticate(customerService.findById(c.getCustomerId()), c.getPassword());
    }    

}

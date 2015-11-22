/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 *
 * @author Martin Penaz
 */
@Service
public interface CustomerService {
    public void registerCustomer(Customer c, String password);
    public Customer findById(Long Id);
    public List<Customer> findAll();
    public void addDog(Dog dog, Customer customer);
    public void removeDog(Dog dog, Customer customer);
    public void deleteCustomer(Customer c);
    boolean authenticate(Customer c, String password);
}

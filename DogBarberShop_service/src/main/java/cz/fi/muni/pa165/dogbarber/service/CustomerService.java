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
    public Customer findByEmail(String email);
    public List<Customer> findAll();
    public void addDog(Dog dog, Customer customer);
    public void removeDog(Dog dog, Customer customer);
    public void deleteCustomer(Customer c);
    boolean authenticate(Customer c, String password);
    public double getTotalPrice(Customer customer);
    Customer update(Customer cust);
}

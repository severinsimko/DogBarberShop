package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Customer;
import java.util.List;

/**
 *
 * @author Martin Penaz
 */
public interface CustomerDao {
    public Customer findById(Long id);
    public Customer findByEmail(String email);
    public void createCustomer(Customer customer);
    public void deleteCustomer(Customer customer) throws IllegalArgumentException;
    public Customer updateCustomer(Customer customer);
    public List<Customer> getAllCustomers();
}

package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.CustomerAuthenticateDTO;
import cz.fi.muni.pa165.dogbarber.dto.CustomerCreateDTO;
import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import java.util.Collection;

/**
 *
 * @author Martin Penaz
 */
public interface CustomerFacade {
    Collection<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long Id);    
    CustomerDTO getCustomerByEmail(String email);
    public Long registerCustomer(CustomerCreateDTO c, String password);
    public void addDog(Long CustomerId, Long DogId);
    public void removeDog(Long CustomerId, Long DogId);
    public void deleteCustomer(Long CustomerId);    
    boolean authenticate(CustomerAuthenticateDTO c);
    double getTotalPrice(Long Id);
    void updateCustomer(CustomerDTO c);
}

package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Penaz
 */
@Transactional
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public void createCustomer(Customer customer) {
      
           em.persist(customer);
      
    }

    @Override
    public void deleteCustomer(Customer customer) {
        if(customer == null)
            throw new IllegalArgumentException("Tried to delete null entity.");
        em.remove(findById(customer.getId()));
    }
    
    @Override
    public Customer updateCustomer(Customer customer){
        return em.merge(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer findByEmail(String email) {
        
       try{ 
           Customer cust= em.createQuery("SELECT c FROM Customer c where email=:email", Customer.class).setParameter("email", email).getSingleResult();
           return cust;
       }catch(NoResultException e){          
           return null;
       }
    }
    
}

package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.List;
import java.util.Set;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Severin Simko
 */
public interface ServiceDao {
    
    Service findbyId(Long id);
    void createService(Service service)throws ConstraintViolationException;
    Service updateService(Service service)throws ConstraintViolationException;
    void removeService(Service service);
    List<Service> getAllServices();
    List<Service> findByName(String name);
    
}

package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.List;

/**
 *
 * @author MichalBrath
 */
@org.springframework.stereotype.Service
public interface EmployeeService {

    void registerEmployee(Employee employee, String password_hash);

    boolean authenticate(Employee emp, String pass);

    boolean isRoot(Employee emp);

    Employee findEmployeeById(Long id);
    
    Employee findEmployeeByEmail(String email);

    List<Employee> findEmployeeByName(String name);

    List<Employee> getAllEmployees();
    
    Employee update(Employee emp);
    
   
    void removeEmployee(Employee emp);
        
    void addService(Employee emp, Service s);

    void removeService(Employee emp, Service s);
    

}


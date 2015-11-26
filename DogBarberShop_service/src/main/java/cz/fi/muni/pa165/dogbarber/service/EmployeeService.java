package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author MichalBrath
 */
@Service
public interface EmployeeService {

    void registerEmployee(Employee employee, String password_hash);

    boolean authenticate(Employee emp, String pass);

    boolean isRoot(Employee emp);

    Employee findEmployeeById(Long id);

    List<Employee> findEmployeeByName(String name);

    List<Employee> getAllEmployees();
    
    Employee update(Employee emp);
}


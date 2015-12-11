package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import java.util.Collection;
import java.util.List;
/**
 * 
 * @author MichalBrath
 */
public interface EmployeeFacade {

    void registerEmployee(EmployeeDTO employeeDTO, String passwordHash);

    boolean authenticate(EmployeeDTO employee, String password);

    boolean isRoot(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployeeById(Long id);
    
    List<EmployeeDTO> findEmployeeByName(String name);

    Collection<EmployeeDTO> getAllEmployees();
    
    EmployeeDTO update(EmployeeDTO e);
    
    void deleteEmployee(EmployeeDTO emp);

    void addService(EmployeeDTO emp, ServiceDTO s);
    
    void removeService(EmployeeDTO emp, ServiceDTO s);
}

package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import java.util.Collection;
import java.util.List;

public interface EmployeeFacade {

    void registerEmployee(EmployeeDTO employeeDTO, String passwordHash);

    boolean authenticate(EmployeeDTO employee, String password);

    boolean isRoot(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployeeById(Long id);
    
    List<EmployeeDTO> findEmployeeByName(String name);

    Collection<EmployeeDTO> getAllEmployees();
    
    EmployeeDTO update(EmployeeDTO e);
   
}

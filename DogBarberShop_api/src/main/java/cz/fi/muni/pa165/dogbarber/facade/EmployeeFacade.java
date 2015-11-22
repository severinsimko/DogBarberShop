package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import java.util.Collection;

public interface EmployeeFacade {

    void registerEmployee(EmployeeDTO employeeDTO, String passwordHash);

    boolean authenticate(EmployeeDTO employee, String password);

    boolean isRoot(EmployeeDTO userDTO);

    EmployeeDTO findUserById(Long id);

    EmployeeDTO findEmployeeByName(String name);

    Collection<EmployeeDTO> getAllEmployees();
   
}

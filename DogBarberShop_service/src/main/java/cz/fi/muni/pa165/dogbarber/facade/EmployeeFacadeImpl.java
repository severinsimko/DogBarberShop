package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.facade.EmployeeFacade;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import java.util.Collection;
import javax.inject.Inject;


public class EmployeeFacadeImpl implements EmployeeFacade {
    @Inject
    private EmployeeService employeeService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void registerEmployee(EmployeeDTO employeeDTO, String encryptedPass) {
        Employee employee = beanMappingService.mapTo(employeeDTO, Employee.class);
        employeeService.registerEmployee(employee, encryptedPass);
        employeeDTO.setId(employee.getId());
    }


    @Override
    public boolean authenticate(EmployeeDTO emp, String pass) {
        return employeeService.authenticate(
            employeeService.findEmployeeById(emp.getId()), pass);
    }

    @Override
    public boolean isRoot(EmployeeDTO userDTO) {
        return employeeService.isRoot(beanMappingService.mapTo(userDTO, Employee.class));
    }

    @Override
    public EmployeeDTO findUserById(Long userId) {
        Employee emp = employeeService.findEmployeeById(userId);
        return beanMappingService.mapTo(emp, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findEmployeeByName(String name) {
        Employee user = employeeService.findEmployeeByName(name);
        return beanMappingService.mapTo(user, EmployeeDTO.class);
    }

    @Override
    public Collection<EmployeeDTO> getAllEmployees() {
        return beanMappingService.mapTo(employeeService.getAllEmployees(), EmployeeDTO.class);
    }
}

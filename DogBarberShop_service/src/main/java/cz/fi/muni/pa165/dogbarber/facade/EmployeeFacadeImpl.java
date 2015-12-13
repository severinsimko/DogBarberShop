package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
/**
 * 
 * @author MichalBrath
 */

@Transactional
@org.springframework.stereotype.Service
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
    public void deleteEmployee(EmployeeDTO emp) {
        employeeService.removeEmployee(beanMappingService.mapTo(emp, Employee.class));
    }
    
    @Override
    public boolean authenticate(EmployeeDTO emp, String pass) {
        return employeeService.authenticate(
            employeeService.findEmployeeById(emp.getId()), pass);
    }

    @Override
    public boolean isRoot(EmployeeDTO employeeDTO) {
        return employeeService.isRoot(beanMappingService.mapTo(employeeDTO, Employee.class));
    }

    @Override
    public EmployeeDTO findEmployeeById(Long userId) {
       
        Employee emp = employeeService.findEmployeeById(userId);
        
         if(emp==null){
            return null;
        
        }
            return beanMappingService.mapTo(emp, EmployeeDTO.class);
    
    }

    @Override
    public List<EmployeeDTO> findEmployeeByName(String name) {
        List<Employee> users = employeeService.findEmployeeByName(name);
        return beanMappingService.mapTo(users, EmployeeDTO.class);
    }

    @Override
    public Collection<EmployeeDTO> getAllEmployees() {
        return beanMappingService.mapTo(employeeService.getAllEmployees(), EmployeeDTO.class);
    }
    
    @Override
    public EmployeeDTO update(EmployeeDTO e) {
        return beanMappingService.mapTo(employeeService.update(beanMappingService.mapTo(e, Employee.class)),EmployeeDTO.class);
    }
    
    @Override
    public void addService(EmployeeDTO emp, ServiceDTO s) {
        employeeService.addService(beanMappingService.mapTo(emp,Employee.class), beanMappingService.mapTo(s, Service.class));
    }
    
    @Override
    public void removeService(EmployeeDTO emp, ServiceDTO s) {
        employeeService.removeService(beanMappingService.mapTo(emp,Employee.class), beanMappingService.mapTo(s, Service.class));
    }

    
}

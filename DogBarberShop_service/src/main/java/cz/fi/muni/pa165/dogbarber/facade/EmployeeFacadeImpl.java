package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author MichalBrath
 */

@Transactional
@org.springframework.stereotype.Service
public class EmployeeFacadeImpl implements EmployeeFacade {
	
	@Autowired
    private EmployeeService employeeService;

    @Autowired
    private BeanMappingService beanMappingService;

    public void registerEmployee(EmployeeDTO employeeDTO, String encryptedPass) {
        Employee employee = beanMappingService.mapTo(employeeDTO, Employee.class);
        employeeService.registerEmployee(employee, encryptedPass);
        employeeDTO.setId(employee.getId());
    }

    @Override
    public void deleteEmployee(EmployeeDTO emp) {
         if(emp==null){
            throw new DogBarberException("Employee does not exist!");
        }
        
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
            throw new DogBarberException("Employee does not exist!");
       }
       return beanMappingService.mapTo(emp, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> findEmployeeByName(String name) {
        List<Employee> users = employeeService.findEmployeeByName(name);
        
        if(users==null){
            throw new DogBarberException("Employees does not exist!");
        }
        return beanMappingService.mapTo(users, EmployeeDTO.class);
    }

    @Override
    public Collection<EmployeeDTO> getAllEmployees() {
        return beanMappingService.mapTo(employeeService.getAllEmployees(), EmployeeDTO.class);
    }
    
    @Override
    public EmployeeDTO update(EmployeeDTO e) {
         if(e==null){
            throw new DogBarberException("Employee does not exist!");
        }
        
        return beanMappingService.mapTo(employeeService.update(beanMappingService.mapTo(e, Employee.class)),EmployeeDTO.class);
    }
    
    @Override
    public void addService(EmployeeDTO emp, ServiceDTO s) {
         if(emp==null){
            throw new DogBarberException("Employee does not exist!");
        }
          if(s==null){
            throw new DogBarberException("Service does not exist!");
        }
        employeeService.addService(beanMappingService.mapTo(emp,Employee.class), beanMappingService.mapTo(s, Service.class));
    }
    
    @Override
    public void removeService(EmployeeDTO emp, ServiceDTO s) {
         if(emp==null){
            throw new DogBarberException("Employee does not exist!");
        }
          if(s==null){
            throw new DogBarberException("Service does not exist!");
        }
        employeeService.removeService(beanMappingService.mapTo(emp,Employee.class), beanMappingService.mapTo(s, Service.class));
    }

    @Override
    public EmployeeDTO findEmployeeByEmail(String email) {
        Employee emp = employeeService.findEmployeeByEmail(email);
        
        if(emp==null){
            throw new DogBarberException("Employee does not exist!");
        }
            return beanMappingService.mapTo(emp, EmployeeDTO.class);
    }
}

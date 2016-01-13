/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import java.util.ArrayList;
import java.util.List;
//import javax.inject.Inject;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import javax.inject.Inject;

/**
 * @author MichalBrath
 */
@org.springframework.stereotype.Service
//@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;
    
    
    @Override
    public void removeEmployee(Employee emp){
        employeeDao.removeEmployee(emp);
    }
    
    @Override
    public void registerEmployee(Employee employee, String password_hash) {
        employee.setPassword_hash(password_hash);
        employeeDao.addEmployee(employee);
    }

    @Override
    public boolean authenticate(Employee employee, String pass) {
        return validatePassword(pass, employee.getPassword_hash());
    }

    @Override
    public boolean isRoot(Employee employee) {
        return employeeDao.getEmployeeByID(employee.getId()).getRoot();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeDao.getEmployeeByID(id);
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeDao.findByName(name);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeDao.getAllEmployees());
    }
    
    @Override
    public Employee update(Employee emp){
        return employeeDao.updateEmployee(emp);
    }

    public static boolean validatePassword(String password, String hash) {
        return password.equals(hash);
    }
    
    
    
    
    @Override
    public void addService(Employee emp, Service s) {
        if(emp.getServices().contains(s)){
            throw new DogBarberException("Employee already has this service. Employee: " + emp.getId() + ", service: " + s.getId());
        }
        else{ 
            
            emp.addService(s);
            employeeDao.updateEmployee(emp);
        }
    }

    @Override
    public void removeService(Employee emp, Service s) {
        if(emp.getServices().contains(s)){
            emp.removeService(s);
            employeeDao.updateEmployee(emp);
        }
        else{
            throw new DogBarberException("Employee doesnt has this service. Employee: " + emp.getName() + ", service: " + s.getServiceName());
        }        
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeDao.getEmployeeByEmail(email);
    }
}


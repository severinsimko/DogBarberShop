/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author artur
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

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
    public Employee findEmployeeByName(String name) {
        return null;// employeeDao.findByName(name);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<Employee>(employeeDao.getAllEmployees());
    }

    public static boolean validatePassword(String password, String hash) {
        return password.equals(hash);
    }
}


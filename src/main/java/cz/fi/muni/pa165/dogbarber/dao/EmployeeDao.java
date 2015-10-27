/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dao;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import java.util.Set;

/**
 *
 * @author artur
 */
public interface EmployeeDao {
    void addEmployee(Employee employee);
    void removeEmployee(Employee employee);
    Employee getEmployeeByID(Long Id);
    Set<Employee> getAllEmployees();
}

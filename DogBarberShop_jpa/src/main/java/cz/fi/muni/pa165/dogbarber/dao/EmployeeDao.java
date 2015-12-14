/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dao;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import java.util.List;
import java.util.Set;

/**
 *
 * @author artur
 */
public interface EmployeeDao {
    void addEmployee(Employee employee);
    void removeEmployee(Employee employee);
    Employee getEmployeeByEmail(String email);
    Employee getEmployeeByID(Long Id);
    public List<Employee> findByName(String name);
    Set<Employee> getAllEmployees();
    Employee updateEmployee(Employee employee);
}

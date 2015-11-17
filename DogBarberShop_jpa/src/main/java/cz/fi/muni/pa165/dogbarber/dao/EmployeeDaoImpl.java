/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dao;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
/**
 *
 * @author artur
 */
@Transactional
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
   
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void addEmployee(Employee employee){
        em.persist(employee);
    };
    
    @Override
    public void removeEmployee(Employee employee){
        em.remove(employee);
    };
    
    @Override
    public Employee getEmployeeByID(Long Id){
        return em.find(Employee.class, Id);
    };
    
    @Override
    public Set<Employee> getAllEmployees(){
        return new HashSet<Employee>(em.createQuery("select c from Employee c", Employee.class)
                .getResultList());
    };
    
    @Override
    public Employee updateEmployee(Employee employee){
        return em.merge(employee);
    }
}
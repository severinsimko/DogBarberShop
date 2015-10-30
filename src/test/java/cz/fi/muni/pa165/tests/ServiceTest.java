
package cz.fi.muni.pa165.tests;

import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;


import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Severin Simko
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
public class ServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    public EmployeeDao employeeDao;
    @Autowired
    public ServiceDao serviceDao;
    
    Employee setEmployee(Employee em, String name, String surname,
                         String address,String phonenumber,BigDecimal salary){
        em.setName(name);
        em.setSurname(surname);
        em.setAddress(address);
        em.setPhone_number(phonenumber);
        em.setSalary(salary);
        return em;
    }
    Service setService(Service se,int lenght, BigDecimal price, String name){
        se.setLengthInMinutes(lenght);
        se.setPrice(price);
        se.setServiceName(name);
        return se;
    }
    
    @Test
    public void categoryTest() {
        Employee emp1 = 
          setEmployee(new Employee(),"Miso","Brath","Pohranice","3636569",BigDecimal.valueOf(10000));

        Service service1 = 
            setService(new Service(), 20, BigDecimal.valueOf(200), "Washing");        
        employeeDao.addEmployee(emp1);
        service1.addEmployee(emp1);
        serviceDao.createService(service1);


        Service tmpService=serviceDao.findbyId(service1.getId());
        assertTrue(tmpService.getId().equals(service1.getId()));

        //tests if employee retrieved from service retrieved from database equals to original
        assertTrue(tmpService.getAllEmployees().iterator().next().equals(emp1));
    }  
}
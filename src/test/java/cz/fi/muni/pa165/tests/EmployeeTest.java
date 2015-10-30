
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
 * @author Michal Brath
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
public class EmployeeTest extends AbstractTestNGSpringContextTests {
    
    
    @Autowired
    public EmployeeDao employeeDao;
    @Autowired
    public ServiceDao serviceDao;
    
    @Test
	public void categoryTest() {

            Employee emp1 = 
              setEmployee(new Employee(),"Miso","Brath","Pohranice","3636569",BigDecimal.valueOf(10000));
           
            Employee emp2 =   
              setEmployee(new Employee(),"Lukas","Korec","Brno","5556569",BigDecimal.valueOf(30000));
           


            
            Service service1 = 
                setService(new Service(), 20, BigDecimal.valueOf(200), "Washing");        
            Service service2 = 
                setService(new Service(), 10, BigDecimal.valueOf(100), "Fur brushing");
            serviceDao.createService(service1);
            serviceDao.createService(service2);
            
            Service tmpService=serviceDao.findbyId(service1.getId());
            assertTrue(tmpService.getId().equals(service1.getId()));
            
            tmpService=serviceDao.findbyId(service2.getId());
            assertTrue(tmpService.getServiceName().equals(service2.getServiceName()));
            assertTrue(tmpService.getLengthInMinutes()==(service2.getLengthInMinutes()));
            emp1.addService(service1);
            employeeDao.addEmployee(emp1);
            employeeDao.addEmployee(emp2);
            
            Employee tmp=employeeDao.getEmployeeByID(emp1.getId());
            
            assertTrue(tmp.equals(emp1));
            assertTrue(tmp.getServices().size()==1); 

            tmp=employeeDao.getEmployeeByID(emp2.getId());
            assertTrue(emp2.equals(tmp));
            

        }
      
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
}

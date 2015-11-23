
package cz.fi.muni.pa165.dogbarber.tests;

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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Brath
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EmployeeTest extends AbstractTestNGSpringContextTests {
    
    
    @Autowired
    public EmployeeDao employeeDao;

    @Autowired
    public ServiceDao serviceDao;
    
    @Test
	public void create_update_addService_test() {

            Employee emp1 = 
              setEmployee(new Employee(),"Miso","Brath","Pohranice","3636569",BigDecimal.valueOf(10000));
           
            Employee emp2 =   
              setEmployee(new Employee(),"Lukas","Korec","Brno","5556569",BigDecimal.valueOf(30000));
           


            
            Service service1 = 
                setService(new Service(), 20, new BigDecimal("200"), "Washing");        
            Service service2 = 
                setService(new Service(), 10, new BigDecimal("100"), "Fur brushing");
            
          
            employeeDao.addEmployee(emp1);
            employeeDao.addEmployee(emp2);
            
            serviceDao.createService(service1);
            serviceDao.createService(service2);
            
            emp1.addService(service1);
            emp1.addService(service2);
            emp1.setName("Miso2");
            
            employeeDao.updateEmployee(emp1);
            
            Employee tmp=employeeDao.getEmployeeByID(emp1.getId());
            assertTrue(emp1.getServices().equals(tmp.getServices()));
            
            assertTrue(tmp.equals(emp1));

            tmp=employeeDao.getEmployeeByID(emp2.getId());
            assertTrue(emp2.equals(tmp));
            

        }
      
        @Test
        public void add_find_test() {

            Employee emp1 = 
              setEmployee(new Employee(),"Miso","Brath","Pohranice","3636569",BigDecimal.valueOf(10000));
           
            Employee emp2 =   
              setEmployee(new Employee(),"Lukas","Korec","Brno","5556569",BigDecimal.valueOf(30000));
         
            employeeDao.addEmployee(emp1);
            employeeDao.addEmployee(emp2);
            
            Employee tmp=employeeDao.getEmployeeByID(emp1.getId());
            
            assertTrue(tmp.equals(emp1));

            tmp=employeeDao.getEmployeeByID(emp2.getId());
            assertTrue(emp2.equals(tmp));
        }
        
        @Test
        public void remove_test() {

            Employee emp1 = 
              setEmployee(new Employee(),"Miso","Brath","Pohranice","3636569",BigDecimal.valueOf(10000));
           
            Employee emp2 =   
              setEmployee(new Employee(),"Lukas","Korec","Brno","5556569",BigDecimal.valueOf(30000));
         
            employeeDao.addEmployee(emp1);
            employeeDao.addEmployee(emp2);
        
            Employee tmp=employeeDao.getEmployeeByID(emp1.getId());
            assertTrue(tmp.equals(emp1));
            
            employeeDao.removeEmployee(emp1);
            tmp=employeeDao.getEmployeeByID(emp1.getId());
            assertTrue(tmp==null);
            
            tmp=employeeDao.getEmployeeByID(emp2.getId());
            assertTrue(emp2.equals(tmp));
            
            employeeDao.removeEmployee(emp2);
            tmp=employeeDao.getEmployeeByID(emp2.getId());
            assertTrue(tmp==null);
            
        }
        
        
        Employee setEmployee(Employee em, String name, String surname,
                             String address,String phonenumber,BigDecimal salary){
            em.setName(name);
            em.setSurname(surname);
            em.setAddress(address);
            em.setPhone_number(phonenumber);
            em.setSalary(salary);
            em.setPassword_hash("");
            em.setRoot(false);
            return em;
        }
        Service setService(Service se,int lenght, BigDecimal price, String name){
            se.setLengthInMinutes(lenght);
            se.setPrice(price);
            se.setServiceName(name);
            
            return se;
        }
}

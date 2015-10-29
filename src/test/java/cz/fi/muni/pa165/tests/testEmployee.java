
package cz.fi.muni.pa165.tests;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Severin Simko
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
public class testEmployee extends AbstractTestNGSpringContextTests {
    
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Test
	public void categoryTest() {
        
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Employee emp = new Employee();
            emp.setAddress("Brno");
            emp.setName("Peter");
            emp.setSalary(new BigDecimal(1100.52));
            emp.setPhone_number("0902111111");
            emp.setSurname("test");
            
             Employee empExpected = new Employee();
            empExpected.setAddress("Praha");
            empExpected.setName("Test");
            empExpected.setSalary(new BigDecimal(1000.52));
            empExpected.setPhone_number("09021111111");
            empExpected.setSurname("test1");
            em.persist(emp);
            em.persist(empExpected);
            
            /*Employee emp2 = new Employee();
                 em2 =em.find(Employee.class,emp.getId())
                    
            Assert.assertSame(emp2, emp);*/
            
            Service service = new Service();
            service.setLengthInMinutes(90);
            service.setPrice(new BigDecimal(1000));
            service.setServiceName("test");
            
            
            Service service2 = new Service();
            service.setLengthInMinutes(120);
            service.setPrice(new BigDecimal(10700));
            service.setServiceName("serviceNameTest");
            
            em.persist(service);
            em.persist(service2);
            
            Set <Service> services = new HashSet<>();
            
            emp.addService(service);
            
           services= emp.getServices();           
           assertFalse(services.isEmpty());
            
           emp.removeService(service);           
           assertTrue(emp.getServices().isEmpty());
    
                    
            em.getTransaction().commit();
            em.close(); 
        }
      
        
}

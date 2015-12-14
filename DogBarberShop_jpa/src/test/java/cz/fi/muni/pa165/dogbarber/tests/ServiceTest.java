package cz.fi.muni.pa165.dogbarber.tests;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;

import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Severin Simko
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ServiceTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private ServiceDao serviceDao;

    private Service service1;
    private Service service2;
    private Employee employee1;
    private Employee employee2;

    @BeforeMethod
    public void initTest() {

        employee1 = new Employee();
        employee1.setAddress("Test Address");
        employee1.setName("First Employee");
        employee1.setPhone_number("0123456");
        employee1.setSalary(new BigDecimal(222.22));
        employee1.setSurname("Employee1 surname");
        employee1.setEmail("mail@mail.cz");

        
        employee2 = new Employee();
        employee2.setAddress("Address employee2");
        employee2.setName("Second Employee");
        employee2.setPhone_number("654321");
        employee2.setSalary(new BigDecimal(111.111));
        employee2.setSurname("Employee2 surname");
        employee2.setEmail("mail@mail.cz");
        
        service1 = new Service();
        service1.setLengthInMinutes(90);
        service1.setPrice(new BigDecimal(222.222));
        service1.setServiceName("Service1");

        service2 = new Service();
        service2.setLengthInMinutes(120);
        service2.setPrice(new BigDecimal(11.99));
        service2.setServiceName("Service2");

    }

    @Test
    public void testfindAll() {
        serviceDao.createService(service1);
        serviceDao.createService(service2);
        Assert.assertTrue(serviceDao.getAllServices().size() == 2);
    }

    @Test
    public void testFindbyId() {
        serviceDao.createService(service1);
        Service testService = new Service();
        testService = serviceDao.findbyId(service1.getId());

        Assert.assertSame(service1, testService);

    }

    @Test
    public void testUpdate() {
        serviceDao.createService(service1);

        Service testService = serviceDao.findbyId(service1.getId());
        testService.setServiceName("Name Changed");
        serviceDao.updateService(testService);

        Service updatedService = serviceDao.findbyId(testService.getId());
        assertSame(updatedService.getServiceName(), "Name Changed");

    }

    @Test
    public void testDelete() {
        serviceDao.createService(service1);
        serviceDao.createService(service2);
        Assert.assertTrue(serviceDao.getAllServices().size() == 2);

        serviceDao.removeService(service1);
        //Assert.assertTrue(serviceDao.getAllServices().size()==1);
        assertTrue(!serviceDao.getAllServices().contains(service1));

        serviceDao.removeService(service2);
        assertTrue(serviceDao.getAllServices().isEmpty());
    }

//    @Test(expectedExceptions = PersistenceException.class)
    public void testMissingServiceName() {

        /*Service serviceMissing = new Service();
        serviceMissing.setLengthInMinutes(90);
        serviceMissing.setPrice(null);
        serviceMissing.setServiceName("Test");

        serviceDao.createService(serviceMissing);*/

    }

    @Test
    public void testEmptyDB() {
        Service s = serviceDao.findbyId(Long.MIN_VALUE);
        assertNull(s);
    }
    
    @Test
    public void testPriceEquals(){
        serviceDao.createService(service1);
    Service s = serviceDao.findbyId(service1.getId());
    
        assertSame(s.getPrice(), service1.getPrice());
    
    }

}

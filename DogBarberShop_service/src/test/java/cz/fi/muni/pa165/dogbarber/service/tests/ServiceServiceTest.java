package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Severin Simko
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    final static Logger logger = LoggerFactory.getLogger(ServiceServiceTest.class);
    
    @Autowired
    ServiceDao serviceDao;

    @Autowired
    @InjectMocks
    private ServiceService serviceServ;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Service service1;

    @BeforeMethod
    public void setUpMethod() throws Exception {

        service1 = new Service();
        service1.setLengthInMinutes(90);
        service1.setPrice(new BigDecimal("120"));
        service1.setServiceName("TestName1");

    }

    @Test
    public void testUpdateExcusion() {

        serviceDao.createService(service1);
        service1.setServiceName("Name changed");
        service1.setLengthInMinutes(150);
        serviceServ.update(service1);
        assertEquals(service1, serviceDao.findbyId(service1.getId()));
    }

    @Test
    public void testFindByIdNotExisting() {
        assertNull(serviceServ.findById(Long.MIN_VALUE));
    }

    @Test
    public void testFindById() {

        serviceDao.createService(service1);
        assertEquals(service1, serviceServ.findById(service1.getId()));
    }

    @Test
    public void testFindAll() {

        assertEquals(serviceServ.getAllServices().size(), 0);
        serviceDao.createService(service1);
        assertTrue(serviceServ.getAllServices().size() == 1);
        Service s = new Service();
        s.setLengthInMinutes(120);
        s.setPrice(new BigDecimal("1270"));
        s.setServiceName("TestName2");

        serviceDao.createService(s);

        assertTrue(serviceServ.getAllServices().size()==2);
    }

   

    @Test
    public void testFindByName() {
        serviceDao.createService(service1);
        assertTrue(serviceServ.getAllServices().size()==1);       
        assertTrue( serviceServ.getServicesByName(service1.getServiceName()).contains(service1));
    }

    @Test
    public void getSortedServicestest() {
        //Sorting by prices testing
        
        
        Service service2 = new Service();
        service2.setLengthInMinutes(900);
        service2.setPrice(new BigDecimal("12"));
        service2.setServiceName("TestName2");
        
         Service service3 = new Service();
        service3.setLengthInMinutes(900);
        service3.setPrice(new BigDecimal("1"));
        service3.setServiceName("TestName2");
        
        //service 3 : 1 , service2: 12 ,service 1 : 120
        
        List<Service> list = new ArrayList<>();
        serviceDao.createService(service1);
        serviceDao.createService(service2);
        serviceDao.createService(service3);
        list = serviceDao.getAllServices();
        assertTrue(list.size()==3);
        assertEquals(service1.getId(), list.get(0).getId());
        assertEquals(service2.getId(), list.get(1).getId());
        
       
        
        logger.info(list.get(0).getPrice().toString());
        logger.info(list.get(1).getPrice().toString());
        logger.info(list.get(2).getPrice().toString());
        
        
        
       List<Service> sortedList = new ArrayList<>();
        
      sortedList =  serviceServ.sortedServicesByPrice();
      
      
      logger.info(sortedList.get(0).getPrice().toString());
        logger.info(sortedList.get(1).getPrice().toString());
        logger.info(sortedList.get(2).getPrice().toString());
        
       assertEquals(service3.getId(), sortedList.get(0).getId());
       assertEquals(service2.getId(), sortedList.get(1).getId());
       assertEquals(service1.getId(), sortedList.get(2).getId());
        
    }
}
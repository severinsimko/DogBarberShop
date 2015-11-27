package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    
    @Mock
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

       
        serviceServ.update(service1);
        verify(serviceDao).updateService(service1);
    }

    @Test
    public void testFindByIdNotExisting() {
         when(serviceDao.findbyId(Long.MIN_VALUE)).thenReturn(null);
        assertNull(serviceServ.findById(Long.MIN_VALUE));
    }

    @Test
    public void testFindById() {
        service1.setId(new Long("11"));
        when(serviceDao.findbyId(service1.getId())).thenReturn(service1);
       
        assertEquals(service1, serviceServ.findById(service1.getId()));
    }

    @Test
    public void testFindAll() {

        when(serviceDao.getAllServices()).thenReturn(new ArrayList<Service>());
        assertEquals(serviceServ.getAllServices().size(), 0);
        when(serviceDao.getAllServices()).thenReturn(Collections.singletonList(service1));
        assertEquals(serviceServ.getAllServices().size(), 1);
        
        Service s = new Service();
        s.setLengthInMinutes(120);
        s.setPrice(new BigDecimal("1270"));
        s.setServiceName("TestName2");

        when(serviceDao.getAllServices()).thenReturn(Arrays.asList(service1, s));

        assertTrue(serviceServ.getAllServices().size()==2);
    }

   

    @Test
    public void testFindByName() {
        when(serviceDao.findByName(service1.getServiceName())).thenReturn(Arrays.asList(service1));
               
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
        
        when(serviceDao.getAllServices()).thenReturn(Arrays.asList(service1,service3,service2));
          
   
        
        list = serviceServ.sortedServicesByPrice();
       
         assertTrue(list.size()==3);
         
         
       assertEquals(service3.getId(), list.get(0).getId());
       assertEquals(service2.getId(), list.get(1).getId());
       assertEquals(service1.getId(), list.get(2).getId());
        
    }
}

package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.math.BigDecimal;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
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

    @Autowired
    private ServiceDao serviceDao;

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

        serviceDao.createService(service1);

        assertTrue(serviceServ.getAllServices().size() == 2);
    }

   

    @Test
    public void testFindByName() {
        serviceDao.createService(service1);
        assertTrue(serviceServ.getAllServices().size()==1);
        assertEquals(service1, serviceServ.getServicesByName(service1.getServiceName()));
    }

}

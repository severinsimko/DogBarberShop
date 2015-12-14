package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Severin Simko
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {
    
    
final static Logger LOGGER = LoggerFactory.getLogger(BeanMappingServiceTest.class);
    @Autowired
    BeanMappingService beanMappingService;

    private Service service1;
    private Service service2;
    private ServiceDTO serviceDTO1;
    private ServiceDTO serviceDTO2;
    private List<Service> servicesList;
    private List<ServiceDTO> serviceDTOList;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        
    service1 = new Service();
    service1.setId(new Long("1"));
    service1.setLengthInMinutes(90);
    service1.setServiceName("Test1");
    service1.setPrice(new BigDecimal("120"));
    
    serviceDTO1 = new ServiceDTO();
    serviceDTO1.setId(new Long("1"));
    serviceDTO1.setLengthInMinutes(90);
    serviceDTO1.setServiceName("Test1");
    serviceDTO1.setPrice(new BigDecimal("120"));
    
    
    service2 = new Service();
    service2.setId(new Long("2"));
    service2.setLengthInMinutes(120);
    service2.setServiceName("Test2");
    service2.setPrice(new BigDecimal("1200"));

    serviceDTO2 = new ServiceDTO();
    serviceDTO2.setId(new Long("2"));
    serviceDTO2.setLengthInMinutes(120);
    serviceDTO2.setPrice(new BigDecimal("1200"));
    serviceDTO2.setServiceName("Test2");
        
        servicesList = new ArrayList<>();
        servicesList.add(service1);
        servicesList.add(service2);
        
        
        
        
        
        serviceDTOList = new ArrayList<>();
        serviceDTOList.add(serviceDTO1);
        serviceDTOList.add(serviceDTO2);
    }

    @Test
    public void testMapToCollectionServiceDTOToEntity() {
        LOGGER.debug("testMapToCollectionServiceDTOToEntity");
        List<Service> eList = beanMappingService.mapTo(
                serviceDTOList, Service.class
        );
        LOGGER.info("eList Simko:" + eList.get(0).toString());
        LOGGER.info("Service2 Simko" + service2.toString());
        
        assertDeepEquals(eList.get(0),service1);
        assertDeepEquals(eList.get(1), service2);
    }

    @Test
    public void testMapToCollectionServiceEntityToDTO() {
        List<ServiceDTO> eList = beanMappingService.mapTo(
                servicesList, ServiceDTO.class
        );
        
        assertDeepEquals(eList.get(0),serviceDTO1);
        Assert.assertTrue(eList.size()==2);
        
    }

    @Test
    public void testMapToServiceDTOToEntity() {
        Service s = beanMappingService.mapTo(serviceDTO1, Service.class);
        assertDeepEquals(service1,s);
    }

    @Test
    public void testMapToServiceEntityToDTO() {
        ServiceDTO s = beanMappingService.mapTo(service1, ServiceDTO.class);
        assertDeepEquals(serviceDTO1,s);
        
    }
    
     private void assertDeepEquals(Service serv1, Service serv2) {
       
        assertEquals(serv1.getId(), serv2.getId());
        assertEquals(serv2.getLengthInMinutes(), serv1.getLengthInMinutes());
        assertEquals(serv1.getPrice(), serv2.getPrice());
        assertEquals(serv1.getServiceName(), serv2.getServiceName());
    }

    private void assertDeepEquals(ServiceDTO serv1, ServiceDTO serv2) {
      
        assertEquals(serv1.getId(), serv2.getId());
        assertEquals(serv1.getLengthInMinutes(), serv2.getLengthInMinutes());
        assertEquals(serv1.getPrice(), serv2.getPrice());
        assertEquals(serv1.getServiceName(), serv1.getServiceName());
    }

    
}
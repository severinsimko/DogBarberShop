package cz.fi.muni.pa165.dogbarber.facade.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.facade.ServiceFacade;
import cz.fi.muni.pa165.dogbarber.facade.ServiceFacadeImpl;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Severin Simko
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceFacadeImplTest {
    
    @Mock
    private ServiceService service;
    
    @Mock
    private EmployeeService employeeService;
    
    @Mock
    private BeanMappingService beanMappingService;
    
    private Service service1;
    
    @InjectMocks
    private ServiceFacade facade = new ServiceFacadeImpl();
    
    
    private ServiceDTO serviceDTO;
    private List<Service> services = new ArrayList<>();
    private List<ServiceDTO> listServiceDTO = new ArrayList<ServiceDTO>();
   // private ServiceDTO serviceDTO = new TroopDTO();
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        service1 = new Service();
        service1.setLengthInMinutes(90);
        service1.setPrice(new BigDecimal(90));
        service1.setServiceName("Test1");
        services.add(service1);
        
        serviceDTO = new ServiceDTO();
        serviceDTO.setLengthInMinutes(90);
        serviceDTO.setPrice(new BigDecimal(90));
        serviceDTO.setServiceName("Test1");
        listServiceDTO.add(serviceDTO);
    }
    
    @BeforeMethod
    public void setUpMethod() throws Exception {
        service1 = new Service();
        service1.setLengthInMinutes(120);
        service1.setPrice(new BigDecimal("90"));
        service1.setServiceName("TestName2");
        
        mockServiceService();
        mockMappingService();
    }
    
    @Test
    public void testFindAllTest(){
        List<ServiceDTO> list = facade.getAllServices();
        assertTrue(list.contains(serviceDTO));
        verify(service).getAllServices();
    }
    
   
    @Test
    public void getServiceByIdTest() {
        ServiceDTO returnedDTO = facade.getServiceById(1l);

        Assert.assertEquals(returnedDTO, serviceDTO);
        verify(service, times(2)).findById(1l);
    }
    
    @Test
    public void deleteService() {
        facade.deleteService(Long.MIN_VALUE);
        verify(service).remove(Matchers.eq(service1));
    }

    private void mockServiceService() {
        Mockito.when(service.getAllServices())
                .thenReturn(services);

        Mockito.when(service.findById(Matchers.anyLong()))
                .thenReturn(service1); 
    }

    private void mockMappingService() {
        Mockito.when(beanMappingService.mapTo(Matchers.any(), Matchers.eq(ServiceDTO.class)))
                .thenReturn(serviceDTO);

        Mockito.when(beanMappingService.mapTo(Matchers.anyCollection(), Matchers.eq(ServiceDTO.class)))
                .thenReturn(listServiceDTO);
    }
}

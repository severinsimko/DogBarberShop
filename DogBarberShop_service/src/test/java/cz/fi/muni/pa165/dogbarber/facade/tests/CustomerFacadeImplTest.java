package cz.fi.muni.pa165.dogbarber.facade.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dto.CustomerCreateDTO;
import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.facade.CustomerFacade;
import cz.fi.muni.pa165.dogbarber.facade.CustomerFacadeImpl;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.CustomerService;
import cz.fi.muni.pa165.dogbarber.service.DogService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Collection;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author Martin Penaz
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CustomerFacadeImplTest {
    @Mock
    private BeanMappingService beanMappingService;
    
    @Mock 
    private CustomerService customerService;
    
    @Mock
    private DogService dogService;
            
    private Customer c1;
    private Customer c2;
    private Dog d1;
    
    @InjectMocks
    private CustomerFacade customerFacade = new CustomerFacadeImpl();
    
    private CustomerDTO customerDTO;
    private CustomerCreateDTO createDTO;
    private List<Customer> customers = new ArrayList<>();
    private List<CustomerDTO> customersDTO = new ArrayList<>();    
    
    @BeforeClass
    public void setup() throws ServiceException{
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void setUpMethod() throws Exception {
        c1 = new Customer();
        c1.setName("Petr");
        c1.setSurname("Hruska");
        c1.setAdress("Brno");
        c1.setPhoneNumber("123456789");
        c1.setId(new Long(1));
        
        c2 = new Customer();
        c2.setName("Adam");
        c2.setSurname("Svestka");
        c2.setAdress("Brno");
        c2.setPhoneNumber("123456789");
        c2.setPassword("password");
        customers.add(c2);
        
        customerDTO = new CustomerDTO();
        customerDTO.setName("Petr");
        customerDTO.setSurname("Hruska");
        customerDTO.setAdress("Brno");
        customerDTO.setphoneNumber("123456789");
        customersDTO.add(customerDTO);        
        
        createDTO = new CustomerCreateDTO();
        createDTO.setName("Petr");
        createDTO.setSurname("Hruska");
        createDTO.setAdress("Brno");
        createDTO.setphoneNumber("123456789");
        createDTO.setId(new Long(5));
        
        d1 = new Dog("Rex", "Vlcak", new GregorianCalendar(2013, Calendar.JANUARY, 10), Color.BLACK); 
        d1.setId(2l);
        
        mockCustomerService();
        mockMappingService();
    }
    
    @Test
    public void findAllTest(){
        Collection<CustomerDTO> result = customerFacade.getAllCustomers();
        assertTrue(result.contains(customerDTO));
        verify(customerService).findAll();
    }
    
    @Test
    public void findByIdTest(){
        CustomerDTO result = customerFacade.getCustomerById(1L);
        assertEquals(result, customerDTO);
    }
    
    @Test
    public void addDogTest(){
        customerFacade.addDog(c1.getId(), d1.getId());
        verify(customerService).addDog(d1, c1);
    }
    
    @Test
    public void removeDogTest(){
        customerFacade.removeDog(c1.getId(), d1.getId());
        verify(customerService).removeDog(d1, c1);
    }
    
    private void mockCustomerService() {
        Mockito.when(customerService.findAll())
                .thenReturn(customers);

        Mockito.when(customerService.findById(Matchers.anyLong()))
                .thenReturn(c1);
        
        Mockito.when(dogService.getDogByID(Matchers.anyLong()))
                .thenReturn(d1);
    }

    private void mockMappingService() {
        Mockito.when(beanMappingService.mapTo(Matchers.any(), Matchers.eq(CustomerDTO.class)))
                .thenReturn(customerDTO);

        Mockito.when(beanMappingService.mapTo(Matchers.anyCollection(), Matchers.eq(CustomerDTO.class)))
                .thenReturn(customersDTO);
    }
}


package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dao.CustomerDao;
import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import cz.fi.muni.pa165.dogbarber.service.CustomerServiceImpl;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author MichalBrath
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeDao employeeDao;
    
    @Mock
    ServiceDao serviceDao;
    
    @Autowired
    @InjectMocks
    EmployeeServiceImpl employeeService;
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Employee empA;
    private Employee empB;
    private Employee empC;
    
    private Service sA;
    private Service sB;
    
    @BeforeMethod
    public void initEmployee(){
        empA=setEmployee(new Employee(), "name1", "surname1", "address xxxxxxx", "02135645569", BigDecimal.ONE);
        empA.setPassword_hash("aaa");
        
        empB=setEmployee(new Employee(), "name2", "surname2", "address yyyyyyy", "02145444569", BigDecimal.TEN);
        empC=setEmployee(new Employee(), "name3", "surname3", "address yyyyyyy", "02145444569", BigDecimal.TEN);
        
        sA = setService(new Service(), 10, BigDecimal.ZERO, "brushing");
        empA.addService(sA);
        sB = setService(new Service(), 15, BigDecimal.TEN, "washing");
    }
    
    @Test
    public void createEmployeeTest(){
        employeeService.registerEmployee(empA, "aaa");
        verify(employeeDao, times(1)).addEmployee(empA);
    }
    
    @Test
    public void deteleEmployeeTest(){
        employeeService.removeEmployee(empA);
        verify(employeeDao, times(1)).removeEmployee(empA);
    }
    
    @Test
    public void findEmployeeByIdTest(){
        when(employeeDao.getEmployeeByID(empA.getId())).thenReturn(empA);
        assertEquals(employeeService.findEmployeeById(empA.getId()), empA);
    }
    
    @Test
    public void findAllEmployeesTest(){
        Set<Employee> tmp = new HashSet<>();
        tmp.add(empA);
        tmp.add(empB);
        
        when(employeeDao.getAllEmployees()).thenReturn(tmp);
        assertEquals(employeeService.getAllEmployees(), tmp);
        verify(employeeDao, times(1)).getAllEmployees();
    }
    
    @Test
    public void addServiceTest(){
        employeeService.addService(empA,sB);
        verify(employeeDao, times(1)).updateEmployee(empA); 
    }
    
    @Test
    public void removeServiceTest(){
        employeeService.registerEmployee(empB, "xx");
        employeeService.addService(empB, sA);
        employeeService.removeService(empB, sA);
        verify(employeeDao, times(2)).updateEmployee(empB); 
    }

    @Test
    public void autenticate(){
        employeeService.registerEmployee(empC, "yy");
        assertTrue(employeeService.authenticate(empC, "yy"));
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

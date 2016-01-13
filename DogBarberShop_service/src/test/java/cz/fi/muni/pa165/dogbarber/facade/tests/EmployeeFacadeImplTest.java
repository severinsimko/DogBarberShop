/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.facade.tests;

import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.facade.EmployeeFacade;
import cz.fi.muni.pa165.dogbarber.facade.EmployeeFacadeImpl;
import cz.fi.muni.pa165.dogbarber.service.BeanMappingService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author artur
 */
public class EmployeeFacadeImplTest {
 
    
    @Mock
    private ServiceService serviceService;
    
    @Mock
    private EmployeeService employeeService;
    
    @Mock
    private BeanMappingService beanMappingService;
    
    @Mock
    EmployeeDao employeeDao;
    
    private Employee empA;
    private Employee empB;
    
    @InjectMocks
    private EmployeeFacade facade = new EmployeeFacadeImpl();

    private EmployeeDTO empDTOA;
    private EmployeeDTO empDTOB;
    
    private List<Employee> listEmp;
    private List<EmployeeDTO> listEmpDTOs;
       
    
    @BeforeClass
    public void setup() throws ServiceException{
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void setupMethod() throws ServiceException {
        listEmp = new ArrayList<>();
        listEmpDTOs = new ArrayList<>();
    
        empA=setEmployee(new Employee(), "name1", "surname1", "address xxxxxxx", "02135645569", BigDecimal.ONE);
        empA.setPassword_hash("aaa");
        empB=setEmployee(new Employee(), "name2", "surname2", "address yyyyyyy", "02145444569", BigDecimal.TEN);
        
        empDTOA=setEmployee(new EmployeeDTO(), "name3", "surname3", "address yyyyyyy", "02145444569", BigDecimal.TEN);
        empDTOB=setEmployee(new EmployeeDTO(), "name4", "surname5", "address yyyyyyy", "02145444569", BigDecimal.TEN);
        
        
        listEmp.add(empA);
        listEmp.add(empB);
        listEmpDTOs.add(empDTOA);
        listEmpDTOs.add(empDTOB);
        
        Mockito.when(beanMappingService.mapTo(Matchers.any(), Matchers.eq(EmployeeDTO.class)))
                .thenReturn(empDTOA);

        Mockito.when(beanMappingService.mapTo(Matchers.any(), Matchers.eq(Employee.class)))
                .thenReturn(empA);
        
        Mockito.when(beanMappingService.mapTo(Matchers.anyCollection(), Matchers.eq(EmployeeDTO.class)))
                .thenReturn(listEmpDTOs);
        
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(listEmp);

        Mockito.when(employeeService.findEmployeeById(Matchers.anyLong()))
                .thenReturn(empA);
        
    }
    
    
    @Test
    public void testFindAllEmpTest(){
        Collection<EmployeeDTO> col = facade.getAllEmployees();
        Assert.assertTrue(col.contains(listEmpDTOs.get(0)));
        Assert.assertTrue(col.contains(listEmpDTOs.get(1)));
        verify(employeeService).getAllEmployees();    
    }
    
   
    @Test
    public void getEmpByIdTest() {
        EmployeeDTO tmp = facade.findEmployeeById(1l);
        Assert.assertEquals(tmp, listEmpDTOs.get(0));
        verify(employeeService).findEmployeeById(1l);
    }
    
    @Test
    public void getEmpByNameTest() {
        List<EmployeeDTO> tmp = facade.findEmployeeByName("name1");
        Assert.assertEquals(tmp.get(0), listEmpDTOs.get(0));
        verify(employeeService).findEmployeeByName("name1");
    }
   
    @Test
    public void deleteEmp() {
        facade.deleteEmployee(empDTOA);
        verify(employeeService).removeEmployee(Matchers.eq(beanMappingService.mapTo(empA,Employee.class)));
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
    
    EmployeeDTO setEmployee(EmployeeDTO em, String name, String surname,
                         String address,String phonenumber,BigDecimal salary){
        em.setName(name);
        em.setSurname(surname);
        em.setAddress(address);
        em.setPhone_number(phonenumber);
        em.setSalary(salary);
        return em;
    }
    
    Service setService(Service se,int lenght, BigDecimal price, String name){
        se.setLengthInMinutes(lenght);
        se.setPrice(price);
        se.setServiceName(name);

        return se;
    }
}

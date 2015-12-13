package cz.fi.muni.pa165.dogbarbershop_sampledata;


import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.CustomerService;
import cz.fi.muni.pa165.dogbarber.service.DogService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Severin Simko
 */

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    
    final static Logger logger = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);
    
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DogService dogService;
    @Autowired
    private EmployeeService employeeService;
   
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException{
        
       Service service= service(90,new BigDecimal(BigInteger.ONE),"Test");
       Service service1= service(120,new BigDecimal(BigInteger.TEN),"Test1");
       Service service2= service(180,new BigDecimal("120"),"Test2");
    
       Set<Service> services = new HashSet<>();
       services.add(service);
       Employee employee1= employee("Admin","TestPriezvisko",true,"admin","address","1921654",new BigDecimal("120"),services);
       Employee employee2= employee("Meno","Priezvisko",false,"meno","addressa","1921654546",new BigDecimal("1200"),services);
    
    }
    
     private Service service(int lengthInMinutes, BigDecimal price, String name) {
        Service ser = new Service();
        ser.setLengthInMinutes(lengthInMinutes);
        ser.setPrice(price);
        ser.setServiceName(name);
        serviceService.create(ser);
        return ser;
    }
     
     
     private Employee employee(String name, String surname,boolean root,String pass, String address,String phoneNum,BigDecimal salary,Set<Service> services) {
        Employee employee = new Employee();
        employee.setAddress(address);
        employee.setName(name);
        employee.setPassword_hash(pass);
        employee.setPhone_number(phoneNum);
        employee.setRoot(root);
        employee.setSurname(surname);
        employee.setSalary(salary);       
        employee.setServices(services);
        employeeService.registerEmployee(employee, pass);
        return employee;    
    }
     
     
     
     }
    
    


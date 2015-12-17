package cz.fi.muni.pa165.dogbarbershop_sampledata;

import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.enums.Color;
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
import java.util.Calendar;
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
        
       Employee employee112 = employeeNoService("Example","TestPriezvisko",true,"example","example@email.cz","address","1921654",new BigDecimal("120")); 
        
       Service service = service(90, new BigDecimal("1000"), "Washing", employee112);
       Service service1 = service(120, new BigDecimal("150"), "Cutting", employee112);
       Service service2 = service(180, new BigDecimal("120"), "Massage", employee112);
    
       Set<Service> services = new HashSet<>();
       services.add(service);
       Employee employee1 = employee("Admin","TestPriezvisko",true,"admin","admin@admin.cz","address","1921654",new BigDecimal("120"),services);
       Employee employee2 = employee("Meno","Priezvisko",false,"meno","email@email.cz","addressa","1921654546",new BigDecimal("1200"),services);
       
       Customer customer1 = customer("Pepa","Novak", "pepa", "pepa@pepa.cz", "adresa123", "123456789");
       Customer customer2 = customer("Uzivatel", "Prijmeni", "heslo", "mail@mail.cz", "address", "987654321");
       
       Dog dog1 = dog("test","test",Calendar.getInstance(),Color.BLACK,customer1,true);
    }
    
     private Service service(int lengthInMinutes, BigDecimal price, String name, Employee emp) {
        Service ser = new Service();
        ser.setLengthInMinutes(lengthInMinutes);
        ser.setPrice(price);
        ser.setServiceName(name);
        ser.addEmployee(emp);
        serviceService.create(ser);
        return ser;
    }
     
     
     private Employee employee(String name, String surname,boolean root,String pass,String email, String address,String phoneNum,BigDecimal salary,Set<Service> services) {
        Employee employee = new Employee();
        employee.setAddress(address);
        employee.setName(name);
        employee.setPassword_hash(pass);
        employee.setPhone_number(phoneNum);
        employee.setRoot(root);
        employee.setSurname(surname);
        employee.setSalary(salary);       
        employee.setServices(services);
        employee.setEmail(email);
        employeeService.registerEmployee(employee, pass);
        return employee;    
    }
     
     private Employee employeeNoService(String name, String surname,boolean root,String pass,String email, String address,String phoneNum,BigDecimal salary) {
        Employee employee = new Employee();
        employee.setAddress(address);
        employee.setName(name);
        employee.setPassword_hash(pass);
        employee.setPhone_number(phoneNum);
        employee.setRoot(root);
        employee.setSurname(surname);
        employee.setSalary(salary); 
        employee.setEmail(email);
        employeeService.registerEmployee(employee, pass);
        return employee;    
    } 
     
     
     private Customer customer(String name, String surname, String pass, String email, String address, String phoneNum){
         Customer customer = new Customer();
         customer.setName(name);
         customer.setSurname(surname);
         customer.setEmail(email);
         customer.setPhoneNumber(phoneNum);
         customer.setAdress(address);
         customerService.registerCustomer(customer, pass);
         return customer;
     }
     
    private Dog dog(String name,String breed,Calendar bornDate, Color color,Customer customer, boolean takenByShop){
    	Dog dog = new Dog();
        dog.setBreed(breed);
        dog.setBornDate(bornDate);
        dog.setColor(color);
        dog.setName(name);
        dog.setCustomer(customer);
        dog.setTaken_by_shop(takenByShop);
        dogService.createDog(dog);
        return dog;
    }
}
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
       
       Service service = service(90, new BigDecimal("1000"), "Washing");
       Service service1 = service(120, new BigDecimal("150"), "Cutting");
       Service service2 = service(180, new BigDecimal("120"), "Massage");
    
       employee112.addService(service);
       employee112.addService(service1);
       employee112.addService(service2);
       
       Set<Service> services = new HashSet<>();
       services.add(service);
       Employee employee1 = employee("Admin", "Admin", true, "admin", "admin@dogbarber.cz", "Úzka 216, Brno", "+420 773 261 333", new BigDecimal("28000"), services);
       Employee employee2 = employee("Martin", "Novotný", false, "heslo", "email@email.cz", "Údolní 13, Brno", "+420 605 213 222", new BigDecimal("30000"), services);
       
       Customer customer1 = customer("Pepa", "Novák", "heslo", "pepa@email.cz", "T.G.Masaryka 28, Brno", "+420 608 810 313");
       Customer customer2 = customer("Osvald", "Starý", "heslo", "osvald@email.cz", "Úvozní 225, Praha", "+420 604 404 500");
       
       Dog dog1 = dog("Nemo", "Husky", Calendar.getInstance(), Color.BLACK, customer1, true);
       Dog dog2 = dog("Alfred", "Buldog", Calendar.getInstance(), Color.BROWN, customer2, true);
       
       customer1.addDog(dog1);
       customer2.addDog(dog2);
       
       dog1.addService(service2);
       dog1.addService(service1);
       
       dog2.addService(service2);
       dog2.addService(service);
    }
    
     private Service service(int lengthInMinutes, BigDecimal price, String name) {
        Service ser = new Service();
        ser.setLengthInMinutes(lengthInMinutes);
        ser.setPrice(price);
        ser.setServiceName(name);
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
        dog.setTakenByShop(takenByShop);
        dogService.createDog(dog);
        return dog;
    }
}
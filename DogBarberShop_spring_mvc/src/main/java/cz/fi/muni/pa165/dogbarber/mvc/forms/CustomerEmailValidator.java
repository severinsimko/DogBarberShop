package cz.fi.muni.pa165.dogbarber.mvc.forms;

import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

/**
 *
 * @author Severin Simko
 */
public class CustomerEmailValidator implements Validator{
   
    
    @Override
    public boolean supports(Class<?> aClass) {
        return ServiceDTO.class.isAssignableFrom(aClass);
    }
    
    @Override
    public void validate(Object target, Errors errors){
        
        
      /*  
        CustomerService serviceC;
        CustomerDTO customer = (CustomerDTO) target;
        List<Customer> allCustomers = serviceC.findAll();
        
       for(Customer cust: allCustomers){}
    }
    
    */
    }
}

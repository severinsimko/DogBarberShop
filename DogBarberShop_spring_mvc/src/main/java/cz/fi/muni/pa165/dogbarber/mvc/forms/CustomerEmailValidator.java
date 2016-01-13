package cz.fi.muni.pa165.dogbarber.mvc.forms;

import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
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

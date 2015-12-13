package cz.fi.muni.pa165.dogbarber.dto;

/**
 *
 * @author  Severin Simko
 */
public class EmployeeAuthenticateDTO {
    
    private Long employeeId;
    private String password;
    
    public Long getCustomerId(){
        return employeeId;
    }
    
    public void setCustomerId(Long customerId){
        this.employeeId = customerId;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}
    
    
    


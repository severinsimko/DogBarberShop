package cz.fi.muni.pa165.dogbarber.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MichalBrath
 */
public class EmployeeDTO {
    
    final static Logger log = LoggerFactory.getLogger(EmployeeDTO.class);
    
    private Long id;
    
    private boolean root;
    
    private String name;
    
    private String surname;
    
    private String address;
    
    private String phone_number;
    
    private BigDecimal salary;

    private Set <ServiceDTO> services = new HashSet<>();
    
    private String email;
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    // root user can be created just by system
    public boolean isRoot(){
        return root;
    }
    
    public void setId(Long id){
        this.id=id;
    }
    
    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setServices(Set<ServiceDTO> services) {
        this.services = services;
    }

    public Set<ServiceDTO> getServices(){
       return services;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.surname);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + Objects.hashCode(this.phone_number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeDTO other = (EmployeeDTO) obj;

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone_number, other.phone_number)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + 
               ", name=" + name + 
               ", surname=" + surname + 
               ", address=" + address + 
               ", phone_number=" + phone_number + 
               ", salary=" + salary +  '}';
    }
}

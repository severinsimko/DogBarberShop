
package cz.fi.muni.pa165.dogbarber.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


/**
 *
 * Severin Simko
 */

@Entity
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false)
    private String serviceName;
    
    @NotNull
    private int lengthInMinutes;
    
    @NotNull
    @DecimalMin("00.00")
    @Column(nullable=false)
    private BigDecimal price;

    @ManyToMany(mappedBy = "services")
    private Set<Employee> employees = new HashSet<>();

    
    //Constructors
    
    public Service(Long id) {
        this.id = id;
    }   

    public Service (){
    
    }
    
    //Getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    //methods that add, remove and show all the employees assigned to a service
    
    public void addEmployee(Employee employee){
        employees.add(employee);
        employee.addService(this);
    
    }
    
    public void removeEmployee(Employee employee){
        employees.remove(employee);
        
    }
    
    public Set<Employee> getAllEmployees(){
       return Collections.unmodifiableSet(employees);
    
    }
    
    
    
    //hashCode and equals methods 
    
    @Override
    public int hashCode(){
    
        int hash = 31;
        return this.getServiceName().hashCode()* hash;
    }
    
    //do not equal on attribute id, but on name
    @Override
    public boolean equals(Object o){
    
        if(o == null) return false;
        if(this == o) return true;
        if(!(o instanceof Service)) return false;
        
        Service ser = (Service) o;
        if(this.serviceName != ser.serviceName) return false;
     
        if (this.lengthInMinutes != ser.lengthInMinutes) {
            return false;
        }
        if (this.price != ser.price) {
            return false;
        }

        if (this.serviceName == null) {
            if (ser.serviceName != null) {
                return false;
            }
        } else if (!this.serviceName.equals(ser.serviceName)) {
            return false;
        }
        
        if(this.price == null){
            if(ser.serviceName !=null){
                return false;
            }
        }else if(!this.price.equals(ser.price)){
            return false;
        }
        return true;
    }
}

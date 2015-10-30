
package cz.fi.muni.pa165.dogbarber.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

    
    //Constructors
    
    public Service(Long id) {
        this.id = id;
    }   

    public Service (){
    
    }
    
    //Getters and setters

    public void setId(Long id) {
        this.id = id;
    }
    
         
    public Long getId() {
        return id;
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
    
    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", serviceName=" + serviceName + ", lengthInMinutes=" + lengthInMinutes + ", price=" + price + ", employees=" + employees + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Service other = (Service) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.serviceName, other.serviceName)) {
            return false;
        }
        if (this.lengthInMinutes != other.lengthInMinutes) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }
    
    
}

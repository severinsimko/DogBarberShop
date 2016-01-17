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
 * @author Severin Simko
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

    @ManyToMany(mappedBy = "services",fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

    @ManyToMany(mappedBy = "services",fetch = FetchType.EAGER)
    private Set<Dog> dogs = new HashSet<>();
    
    
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
    public void setId(Long id){
        this.id=id;
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
        this.employees.add(employee);
    
    }
    
    public void removeEmployee(Employee employee){
        this.employees.remove(employee);
        
    }
    
    public Set<Employee> getAllEmployees(){
       return Collections.unmodifiableSet(employees);
    
    }
    
    //hashCode and equals methods 
    
    @Override
    public int hashCode(){
        int hash = 5;
        hash = 17 * hash + ((serviceName == null) ? 0 : serviceName.hashCode());
        return hash;
    }
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Service)) {
            return false;
        }
        final Service other = (Service) obj;

        if (serviceName == null) {
            if (other.getServiceName() != null) {
                return false;
            }
        }else if(!serviceName.equals(other.getServiceName())){
            return false;
        }
        return true;
    }


}

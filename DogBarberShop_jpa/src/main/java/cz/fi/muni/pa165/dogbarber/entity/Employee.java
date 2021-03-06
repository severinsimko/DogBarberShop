package cz.fi.muni.pa165.dogbarber.entity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false)
    private String name;
    
    @NotNull
    private String password_hash;
    
    // root user is created by system, juts root user can add another users
    @Type(type="yes_no")
    private boolean root;
    
    @NotNull
    @Column(nullable=false)
    private String surname;
    
    @NotNull
    @Column(nullable=false)
    private String address;
    
    @NotNull
    @Column(nullable=false)
    private String phone_number;
    
    @NotNull
    @DecimalMin("00.00")
    @Column(nullable=false)
    private BigDecimal salary;

    @NotNull
    @Column(nullable=false, unique=true)
    private String email;
    
    //set of services, on which the employee is working    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set <Service> services = new HashSet<>();
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void addService(Service service){
        services.add(service);
    }
    
    public Long getId(){
        return id;
    }
    
    public void removeService(Service service){
        services.remove(service);
    }
    
    public Set<Service> getServices(){
        return Collections.unmodifiableSet(services);
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

    public String getPassword_hash(){
        return password_hash;
    }
    
    public BigDecimal getSalary() {
        return salary;
    }

    public void setRoot(boolean root){
        this.root=root;
    }
    
    public boolean getRoot(){
        return root;
    }
    
    public void setPassword_hash(String hash){
        this.password_hash=hash;
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

    public void setServices(Set<Service> services) {
        this.services = services;
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
        if (obj instanceof Employee) {
	        final Employee other = (Employee) obj;
	
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
        return false;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + 
               ", name=" + name + 
               ", surname=" + surname + 
               ", address=" + address + 
               ", phone_number=" + phone_number + 
               ", salary=" + salary + 
               ", services=" + services + '}';
    }
}

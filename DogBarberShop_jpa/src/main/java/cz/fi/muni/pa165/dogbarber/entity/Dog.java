package cz.fi.muni.pa165.dogbarber.entity;

import cz.fi.muni.pa165.dogbarber.enums.Color;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

/**
 *
 * @author Pavel Drobek
 */
@Entity
public class Dog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String name;
    
    @NotNull
    @Column(nullable = false)
    private String breed;
    
    @NotNull
    private Calendar bornDate;
    
    @NotNull
    private Color color;
    
    @ManyToOne
    private Customer customer;
    
    @ManyToMany
    private Set<Service> services;
    
    @Type(type = "yes_no")
    private boolean takenByShop;
    
    public Dog() {
    	services = new HashSet<>();
    }
    
    public Dog(String name, String breed, Calendar bornDate, Color color) {
        this.name = name;
        this.breed = breed;
        this.bornDate = bornDate;
        this.color = color;
        
        services = new HashSet<>();
    }
    
    public boolean getTakenByShop() {
        return takenByShop;
    }
    
    public void setTakenByShop(boolean t) {
        takenByShop = t;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void addService(Service service) {
        services.add(service);
    }
    
    public void removeService(Service service) {
        services.remove(service);
    }
    
    public Set<Service> getServices(){
        return services;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long Id){
        this.id = Id;
    }
    
    public String getName() {
        return name;
    }
    
    public Calendar getBornDate() {
        return bornDate;
    }
    
    public int getAge() {
        Calendar cal = Calendar.getInstance();
        
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        
        int bornYear = bornDate.get(Calendar.YEAR);
        int bornMonth = bornDate.get(Calendar.MONTH);
        int bornDay = bornDate.get(Calendar.DAY_OF_MONTH);
        
        int result = currentYear - bornYear;
        
        if (currentMonth < bornMonth) {
            result--;
        } else if (currentMonth == bornMonth) {
            if (currentDay < bornDay) {
                result--;
            }
        }
        return result;
    }
    
    public Color getColor() {
        return color;
    }
    
    public String getBreed() {
        return breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setBornDate(Calendar bornDate) {
        this.bornDate = bornDate;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = hash * 29 + name.hashCode();
        hash = hash * 11 + bornDate.hashCode();
        hash = hash * 7 + breed.hashCode();
        hash = hash * 17 + color.hashCode();
        
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Dog) {
            Dog other = (Dog) obj;
            
            return name.equals(other.name) 
                    && bornDate.equals(other.bornDate) 
                    && breed.equals(other.breed) 
                    && color.equals(other.color);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Id: " + id
                + "Name: " + name
                + "Breed: " + breed
                + "Age: " + bornDate.toString()
                + "Color " + color;        
    }
}

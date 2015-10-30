package cz.fi.muni.pa165.dogbarber.entity;

import cz.fi.muni.pa165.dogbarber.enums.Color;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Digits;
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
    @Column(nullable=false)
    private String name;
    
    @NotNull
    @Column(nullable=false)
    private String breed;
    
    @Digits(integer=2, fraction=0)
    private int age;
    
    @NotNull
    private Color color;
    
    @ManyToOne
    private Customer customer;
    
    @OneToMany
    private Set<Service> services;

    @Type(type="yes_no")
    private boolean taken_by_shop;
    
    boolean getTakenByShop(){
        return taken_by_shop;
    }
    
    void setTakenByShop(boolean t){
        taken_by_shop=t;
    }
    
    protected Dog() {}
    
    public Dog(String name, String breed, int age, Color color){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
        
        services = new HashSet<>();
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public void addService(Service service){
        services.add(service);
    }
    
    public void removeService(Service service){
        services.remove(service);
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public Long getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return age;
    }
    
    public Color getColor(){
        return color;
    }
    
    public String getBreed(){
        return breed;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Dog){
            Dog other = (Dog)obj;
            return Objects.equals(this.id, other.id);
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Id: " + id +
                "Name: " + name +
                "Breed: " + breed +
                "Age: " + age +
                "Color " + color;    
    }
}


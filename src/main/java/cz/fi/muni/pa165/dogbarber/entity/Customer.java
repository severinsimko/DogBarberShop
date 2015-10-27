/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
/**
 *
 * @author Martin Penaz
 */

@Entity
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false)
    private String name;
    
    @NotNull
    @Column(nullable=false)
    private String surname;
    
    @NotNull
    @Column(nullable=false)
    private String adress;
    
    @NotNull
    @Column(nullable=false)
    private String phoneNumber;
    
    @OneToMany(mappedBy = "customer")
    private Set<Dog> dogs = new HashSet<Dog>();
    
    public Customer(Long customerId){
        this.id = customerId;
    }
    
    public Customer(){
        
    }
    
    public void setId(Long customerId){
        this.id = customerId;
    }
    
    public long getId(){
        return this.id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setSurName(String surname){
        this.surname = surname;
    }
    
    public String getSurName(){
        return this.surname;
    }
    
    public void setAdress(String adress){
        this.adress = adress;
    }
    
    public String getAdress(){
        return this.adress;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    public void addDog(Dog dog){
        dogs.add(dog);
    }
    
    public void removeDog(Dog dog){
        dogs.remove(dog);
    }
    
    public Set<Dog> getAllDogs(){
        return Collections.unmodifiableSet(dogs);
    }
    
    @Override
    public int hashCode(){    
        int hash = 31;
        return this.name.hashCode()* hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        return true;
    }

    
}

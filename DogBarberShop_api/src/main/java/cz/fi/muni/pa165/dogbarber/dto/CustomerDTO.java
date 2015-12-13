/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Martin Penaz
 */
public class CustomerDTO {
    
    private Long Id;
    
    private String name;
    
    private String surname;

    private String adress;
    
    private String phoneNumber;
    
    private Set<DogDTO> dogs = new HashSet<>();
 
    
    public Long getId(){
        return Id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getSurname(){
        return surname;
    }
    
    public String getAdress(){
        return adress;
    }
    
    public String getphoneNumber(){
        return phoneNumber;
    }
    
    public Set<DogDTO> getDogs(){
        return Collections.unmodifiableSet(dogs);
    }
    
     public void setId(Long Id){
        this.Id = Id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setSurname(String surname){
        this.surname = surname;
    }
    
    public void setAdress(String adress){
        this.adress = adress;
    }
    
    public void setphoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setDogs(Set <DogDTO> dogs){
        this.dogs = dogs;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerDTO other = (CustomerDTO) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
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
        if (!Objects.equals(this.dogs, other.dogs)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.Id);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.surname);
        hash = 89 * hash + Objects.hashCode(this.adress);
        hash = 89 * hash + Objects.hashCode(this.phoneNumber);
        hash = 89 * hash + Objects.hashCode(this.dogs);
        return hash;
    }
}

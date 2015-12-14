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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Martin Penaz
 */
public class CustomerCreateDTO {

    private Long Id;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String surname;

    @NotNull
    @Size(min = 3, max = 50)
    private String adress;
    
    @NotNull
    private String phoneNumber;
    
    private Set<DogDTO> dogs = new HashSet<>();
    
    @NotNull
    @Size(min = 5, max = 10)
    private String password;
    
    @NotNull
    @Size(min = 5, max = 10)
    private String email;
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setId(Long Id){
        this.Id = Id;
    }
    
    public Long getId(){
        return Id;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
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
        final CustomerCreateDTO other = (CustomerCreateDTO) obj;
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
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + Objects.hashCode(this.adress);
        hash = 97 * hash + Objects.hashCode(this.phoneNumber);
        hash = 97 * hash + Objects.hashCode(this.dogs);
        return hash;
    }
}

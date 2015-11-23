package cz.fi.muni.pa165.dogbarber.dto;

import cz.fi.muni.pa165.dogbarber.enums.Color;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Pavel Drobek
 */
public class DogDTO {

    private Long id;
    private String name;
    private String breed;
    private Color color;
    private Calendar bornDate;
    private boolean takenByShop;

    private CustomerDTO customer;
    private Set<ServiceDTO> services = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBornDate(Calendar bornDate) {
        this.bornDate = bornDate;
    }

    public void setTakenByShop(boolean takenByShop) {
        this.takenByShop = takenByShop;
    }

    public void setCustom(CustomerDTO customer){
        this.customer = customer;
    }
    
    public void setServices(Set<ServiceDTO> services) {
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Color getColor() {
        return color;
    }

    public Calendar getBornDate() {
        return bornDate;
    }

    public CustomerDTO getCustomer(){
        return customer;
    }
    
    public Set<ServiceDTO> getServices() {
        return services;
    }

    public boolean getTakenByShop() {
        return takenByShop;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = hash * 29 + (name != null ? name.hashCode() : 0);
        hash = hash * 11 + (bornDate != null ? bornDate.hashCode() : 0);
        hash = hash * 7 + (breed != null ? breed.hashCode() : 0);
        hash = hash * 17 + (color != null ? color.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DogDTO) {
            DogDTO other = (DogDTO) obj;

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

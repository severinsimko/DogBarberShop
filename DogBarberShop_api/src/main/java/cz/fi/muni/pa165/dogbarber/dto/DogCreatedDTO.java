package cz.fi.muni.pa165.dogbarber.dto;

import cz.fi.muni.pa165.dogbarber.enums.Color;
import java.util.Calendar;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pavel Drobek
 */
public class DogCreatedDTO {

    @NotNull
    @Size(min=2, max=20)
    private String name;

    @NotNull
    @Size(min=2, max=20)
    private String breed;

    @NotNull
    private Color color;

    @NotNull
    private Calendar bornDate;

    //@NotNull
    //private CustomerDTO customer;
    
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

    /*public void setCustom(CustomerDTO customer){
     this.customer = customer;
     }*/

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

    /*public CustomerDTO getCustom(){
     return customer;
     }*/
    
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
        if (obj instanceof DogCreatedDTO) {
            DogCreatedDTO other = (DogCreatedDTO) obj;

            return name.equals(other.name)
                    && bornDate.equals(other.bornDate)
                    && breed.equals(other.breed)
                    && color.equals(other.color);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Name: " + name
                + "Breed: " + breed
                + "Age: " + bornDate.toString()
                + "Color " + color;
    }
}

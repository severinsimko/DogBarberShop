/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dto;

import java.math.BigDecimal;
import java.security.Provider.Service;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Severin Simko
 */
public class ServiceDTO {

    private Long id;

    @NotNull
    private String serviceName;

    @NotNull
    private int lengthInMinutes;

    @NotNull
    private BigDecimal price;

    private Set<EmployeeDTO> employees = new HashSet<>();

    //Getters and setters  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public void addEmployee(EmployeeDTO employee) {
        this.employees.add(employee);

    }

    public void removeEmployee(EmployeeDTO employee) {
        this.employees.remove(employee);

    }

    public Set<EmployeeDTO> getAllEmployees() {
        return Collections.unmodifiableSet(employees);

    }

    //hashCode and equals methods 
    @Override
    public int hashCode() {

        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.serviceName);
        return hash;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Service)) {
            return false;
        }
        final ServiceDTO other = (ServiceDTO) obj;

        if (serviceName == null) {
            if (other.getServiceName() != null) {
                return false;
            }
        } else if (!serviceName.equals(other.getServiceName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" + "id=" + id + ", serviceName=" + serviceName + ", lengthInMinutes=" + lengthInMinutes + ", price=" + price + ", employees=" + employees + '}';
    }

}

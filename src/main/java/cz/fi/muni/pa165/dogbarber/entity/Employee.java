/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    //set of services, on which the employee is working    
    @ManyToMany
    private Set <Service> services = new HashSet<>();
    
    public void addService(Service service){
        
        services.add(service);
    
    }
    
    public Set<Service> getServices(){
        return Collections.unmodifiableSet(services);
    }
}

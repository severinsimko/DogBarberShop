/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.Collection;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Severin Simko
 */
public interface ServiceDao {
    
    Service findbyId(Long id);
    void createService(Service service)throws ConstraintViolationException;
    Service updateService(Service service)throws ConstraintViolationException;
    void removeService(Service service);
    List<Service> getAllServices();
    List<Service> findByName(String name);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.Set;

/**
 *
 * @author Severin Simko
 */
public interface ServiceDao {
    
    Service findbyId(Long id);
    void createService(Service service);
    void removeService(Service service);
    Set<Service> getAllServices();
    
}

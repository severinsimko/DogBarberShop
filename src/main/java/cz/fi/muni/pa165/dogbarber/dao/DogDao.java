/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Dog;
import java.util.Set;

/**
 *
 * @author Crumby
 */
public interface DogDao {
    void addDog(Dog dog);
    void removeDog(Dog dog);
    Dog getDogByID(Long Id);
    Set<Dog> getAllDogs();
}

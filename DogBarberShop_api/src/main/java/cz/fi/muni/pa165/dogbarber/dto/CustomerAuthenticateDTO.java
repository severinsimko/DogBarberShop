/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dto;

/**
 *
 * @author Martin Penaz
 */
public class CustomerAuthenticateDTO {
    private Long customerId;
    private String password;
    
    public Long getCustomerId(){
        return customerId;
    }
    
    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}

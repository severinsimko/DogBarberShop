/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.config;

import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Severin Simko
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
public class ServiceConfiguration {

	@Bean
	public Mapper dozer(){
		return new DozerBeanMapper();
	}
}
package cz.fi.muni.pa165.dogbarber.mvc.controllers;

import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import cz.fi.muni.pa165.dogbarber.mvc.exceptions.CustomGenericException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Severin Simko
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handleCustomException(CustomGenericException ex) {

        ModelAndView model = new ModelAndView("/error");
        model.addObject("errCode", ex.getErrCode());
        model.addObject("errMsg", ex.getErrMsg());

        return model;

    }

    @ExceptionHandler(DogBarberException.class)
    public ModelAndView handleDogBarberException(DogBarberException ex) {

        ModelAndView model = new ModelAndView("/error");
        model.addObject("errCode", ex.getMessage());
        model.addObject("errMsg", ex.getMessage());

        return model;

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("/error");
        model.addObject("errMsg", "Any other error happened.");

        return model;

    }

 
}

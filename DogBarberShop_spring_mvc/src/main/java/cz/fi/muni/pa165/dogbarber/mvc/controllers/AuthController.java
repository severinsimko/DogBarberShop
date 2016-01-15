package cz.fi.muni.pa165.dogbarber.mvc.controllers;

import cz.fi.muni.pa165.dogbarber.dto.CustomerAuthenticateDTO;
import cz.fi.muni.pa165.dogbarber.dto.CustomerDTO;
import cz.fi.muni.pa165.dogbarber.dto.EmployeeDTO;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import cz.fi.muni.pa165.dogbarber.facade.CustomerFacade;
import cz.fi.muni.pa165.dogbarber.facade.EmployeeFacade;
import cz.fi.muni.pa165.dogbarber.mvc.utils.Utils;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Severin Simko
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private CustomerFacade customer;
    @Autowired
    private EmployeeFacade employee;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginFormular(Model model, HttpServletRequest req,
            HttpServletResponse res) {
    	return Utils.redirectAuthorized(req.getSession(true), "/service");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String password, @RequestParam String email, Model model, RedirectAttributes red, HttpServletRequest request, HttpServletResponse response) {
    	if (password.isEmpty() || email.isEmpty()) {
            red.addFlashAttribute("alert_danger", "Please insert valid email and password!");
            return "redirect:/auth/login";
        }

        EmployeeDTO employeeDTO;
        try{
        	employeeDTO = employee.findEmployeeByEmail(email);
        }catch(DogBarberException e){
        	employeeDTO = null;
        }

        if (employeeDTO == null) {
            CustomerAuthenticateDTO authCust = new CustomerAuthenticateDTO();
            CustomerDTO customerDTO = customer.getCustomerByEmail(email);

            try {
                authCust.setCustomerId(customerDTO.getId());
            } catch (NullPointerException e) {
                red.addFlashAttribute("alert_danger", "Wrong email or password");
                return "redirect:/auth/login";
            }
            authCust.setPassword(password);
            boolean authenticatedCustomer = customer.authenticate(authCust);
            if (authenticatedCustomer) {
                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", customerDTO);
                request.setAttribute("authUser", customerDTO);
                session.setAttribute("auth", customerDTO);
                request.setAttribute("auth", customerDTO);
                
                double i = customer.getTotalPrice(customerDTO.getId());
                session.setAttribute("total", i);
                request.setAttribute("total", i);
                
                return "redirect:/service";

            } else {
                red.addFlashAttribute("alert_danger", "Wrong email or password");
                return "redirect:/auth/login";
            }
        } else {
            boolean authenticatedEmployee = employee.authenticate(employeeDTO, password);
            if (authenticatedEmployee) {
                HttpSession session = request.getSession(true);
                session.setAttribute("admin", employeeDTO);
                request.setAttribute("admin", employeeDTO);
                session.setAttribute("auth", employeeDTO);
                request.setAttribute("auth", employeeDTO);
                return "redirect:/service";

            } else {
                red.addFlashAttribute("alert_danger", "Wrong email or password");
                return "redirect:/auth/login";
            }
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes red, HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessionLogOut = request.getSession(true);
        sessionLogOut.removeAttribute("authUser");
        sessionLogOut.removeAttribute("admin");
        sessionLogOut.removeAttribute("auth");
        red.addFlashAttribute("alert_success", " Successfully logged out");
        return "redirect:/auth/login";
    }
}

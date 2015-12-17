package cz.fi.muni.pa165.dogbarber.mvc.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import cz.fi.muni.pa165.dogbarber.dto.DogCreatedDTO;
import cz.fi.muni.pa165.dogbarber.facade.DogFacade;

@Controller
@RequestMapping("/dog")
public class DogController {
    final static Logger LOGGER = LoggerFactory.getLogger(DogController.class);


    @Autowired
    private DogFacade dogFacade;

    @RequestMapping(value = {"", "/", "/list"}, method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("dogs", dogFacade.getAllDogs());
        return "dog/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDog(Model model) {
        LOGGER.debug("new()");
        model.addAttribute("dogCreate", new DogCreatedDTO());
        return "dog/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("dogCreate") DogCreatedDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        LOGGER.debug("create(formBean={})", formBean);
        
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                LOGGER.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                LOGGER.trace("FieldError: {}", fe);
            }
            return "dog/new";
        }
        
        dogFacade.createDog(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Dog " + formBean.getName() + " was created");
        return "redirect:" + uriBuilder.path("/dog/list").toString();
    }
}

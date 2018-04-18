package com.khohlov.khohlov.controller.model;

import com.khohlov.khohlov.domain.model.dummies_forms.CountryForm;
import com.khohlov.khohlov.service.model.CountryService;
import com.khohlov.khohlov.validator.CountryCreateFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;
    private final CountryCreateFormValidator countryCreateFormValidator;

    @Autowired
    public CountryController(CountryService countryService, CountryCreateFormValidator countryCreateFormValidator) {
        this.countryService = countryService;
        this.countryCreateFormValidator = countryCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(countryCreateFormValidator);
    }

    @RequestMapping("/countries")
    public ModelAndView getCountriesPage() {
        LOGGER.debug("Getting countries page");
        return new ModelAndView("countries", "countries", countryService.getAllCountries());
    }

    @RequestMapping(value = "/country/create", method = RequestMethod.GET)
    public ModelAndView getCountryCreatePage() {
        LOGGER.debug("Getting country create form");
        return new ModelAndView("country_create", "form", new CountryForm());
    }

    @RequestMapping("/country/remove/{id}")
    public String deleteCountry(@PathVariable Integer id){
        LOGGER.debug("removing country with id={}", id);

        if (!countryService.remove(id))

            return "redirect:/exception";

        return "redirect:/countries";
    }

    @RequestMapping(value = "/country/create", method = RequestMethod.POST)
    public String handleCountryCreateForm(@Valid @ModelAttribute("form") CountryForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing country create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "redirect:country/create";
        }
        try {
            countryService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the country, assuming duplicate name", e);
            bindingResult.reject("name.exists", "Country with such name already exists");
            return "redirect:country/create";
        }
        // ok, redirect
        return "redirect:/countries";
    }
}

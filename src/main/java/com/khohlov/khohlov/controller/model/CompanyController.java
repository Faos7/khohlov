package com.khohlov.khohlov.controller.model;

import com.khohlov.khohlov.domain.model.Country;
import com.khohlov.khohlov.domain.model.dummies_forms.CompanyForm;
import com.khohlov.khohlov.service.model.CompanyService;
import com.khohlov.khohlov.service.model.CountryService;
import com.khohlov.khohlov.validator.CompanyCreateFormValidator;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final CompanyService companyService;
    private final CountryService countryService;
    private final CompanyCreateFormValidator companyCreateFormValidator;

    @Autowired
    public CompanyController(CompanyService companyService, CountryService countryService,
                             CompanyCreateFormValidator companyCreateFormValidator) {
        this.companyService = companyService;
        this.countryService = countryService;
        this.companyCreateFormValidator = companyCreateFormValidator;
    }


    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(companyCreateFormValidator);
    }

    @RequestMapping("/companies")
    public ModelAndView getCompaniesPage() {
        LOGGER.debug("Getting companies page");
        return new ModelAndView("companies", "companies", companyService.getAllCompanies());
    }

    @RequestMapping("/company/remove/{id}")
    public String deleteCompany(@PathVariable Integer id){
        LOGGER.debug("removing company with id={}", id);
        if (!companyService.remove(id)){
            return "redirect:/exception";
        }
        return "redirect:/companies";
    }

    @RequestMapping(value = "/company/create", method = RequestMethod.GET)
    public ModelAndView getCompanyCreatePage() {
        LOGGER.debug("Getting company create form");
        ModelAndView mav = new ModelAndView("company_create", "form", new CompanyForm());
//        .addObject("cities",countryService.getAllCities());
        List<Country> countries = new ArrayList<>();
        countries.addAll(countryService.getAllCountries());
        mav.addObject("countries", countries);

        return mav;
    }

    @RequestMapping(value = "/company/create", method = RequestMethod.POST)
    public String handleCompanyCreateForm(@Valid @ModelAttribute("form") CompanyForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing company create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "redirect:/company/create";
        }
        try {
            companyService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the company, assuming duplicate name", e);
            bindingResult.reject("name.exists", "Company with such name already exists");
            return "company_create";
        }
        // ok, redirect
        return "redirect:/companies";
    }
}
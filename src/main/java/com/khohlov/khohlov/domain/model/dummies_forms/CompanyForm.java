package com.khohlov.khohlov.domain.model.dummies_forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class CompanyForm {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String country = "";

    @NotEmpty
    private String adres = "";

    @NotEmpty
    private String phone = "";

    @NotEmpty
    private String site = "";

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

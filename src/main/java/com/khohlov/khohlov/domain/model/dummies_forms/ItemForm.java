package com.khohlov.khohlov.domain.model.dummies_forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ItemForm {

    @NotEmpty
    private String type = "";

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String country = "";

    @NotNull
    private Double price= -1.0;

    @NotEmpty
    private String company = "";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

package com.khohlov.khohlov.domain.model.dummies_forms;

import org.hibernate.validator.constraints.NotEmpty;

public class CountryForm {

    @NotEmpty
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

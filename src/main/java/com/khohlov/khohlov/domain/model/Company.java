package com.khohlov.khohlov.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @SequenceGenerator(name="company_sequence",sequenceName="company_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="company_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer companyId;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_country")
    private Country country;

    @Column(name = "adres")
    private String adres;

    @Column(name = "phone")
    private String phone;

    @Column(name = "site")
    private String site;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

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

    public Company() {
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name=" + name +
                ", country=" + country +
                ", adres='" + adres + '\'' +
                ", phone='" + phone + '\'' +
                ", site='" + site + '\'' +
                '}';
    }

    public String getInfo() {
        return country.getName() + ", company: " + name + ". " + adres;
    }
}

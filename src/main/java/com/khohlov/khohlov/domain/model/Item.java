package com.khohlov.khohlov.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @SequenceGenerator(name="item_sequence",sequenceName="item_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="item_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Integer itemId;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_country")
    private Country country;

    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_company")
    private Company company;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", price=" + price +
                ", company=" + company +
                '}';
    }
}

package org.example.dto;

import org.example.dto.util.Currency;

public class Product {
    private String name;
    private Currency currency;
    private Double amount;
    private Integer availability;
    private String onSales;
    private Boolean recommended;

    public Product() {};

    public Product(String name, Currency currency, Double amount, Integer availability, String onSales, Boolean recommended) {
        this.name = name;
        this.currency = currency;
        this.amount = amount;
        this.availability = availability;
        this.onSales = onSales;
        this.recommended = recommended;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getOnSales() {
        return onSales;
    }

    public void setOnSales(String onSales) {
        this.onSales = onSales;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", currency=" + currency +
                ", amount=" + amount +
                ", availability=" + availability +
                ", onSales='" + onSales + '\'' +
                ", recommended=" + recommended +
                '}';
    }
}

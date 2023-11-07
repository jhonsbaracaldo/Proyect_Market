package model;
import Services.IProductservices;

import java.util.ArrayList;
import java.util.List;

public class Product {


    private String name;
    private String description;
    private String category;
    private String label;
    private double price;
    private String url;

    private Integer code;

    public Product(Integer code, String name, String description, String category, String label, double price, String url) {
        this.code = code++;
        this.name = name;
        this.description = description;
        this.category = category;
        this.label = label;
        this.price = price;
        this.url = url;
    }


// Getters y setters (opcional, dependiendo de tus necesidades)

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("%-10s|%-60s | %-25s | %-20s | %-20s | %-10.2f | %s", getCode(), getName(), getDescription(), getCategory(), getLabel(), getPrice(), getUrl());
    }
}

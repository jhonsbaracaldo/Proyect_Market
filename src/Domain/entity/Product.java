package Domain.entity;

import java.util.List;

public class Product {

    private List<Product> productList;



    public List<Product> getProductList() {
        return productList;
    }

    private String name;
    private String description;
    private String category;
    private String label;
    private double price;
    private String url;

    private Integer code;

    private  Integer cantidad;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Product(Integer code,String name,Integer cantidad, String description, String category, String label, double price, String url ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.label = label;
        this.price = price;
        this.url = url;
        this.code = code;
        this.cantidad = cantidad;
    }

    public Product() {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return String.format("%-5s|%-60s |%-5s| %-20s | %-30s | %-20s | %-10.2f | %s", getCode(), getName(),getCantidad(), getDescription(), getCategory(), getLabel(), getPrice(), getUrl());
    }
}

package model;
public class RemoveToString extends Product {
    public RemoveToString( Integer code,String name, String description, String category, String label, double price, String url) {
        super(code,name, description, category, label, price, url);
    }



    @Override
    public String toString() {
        return String.format("%-10s|%-60s | %-25s | %-20s | %-20s | %-10.2f | %s" ,getCode(), getName(),getDescription(),getCategory(),getLabel(),getPrice(),getUrl());
    }
}

package Domain.dao.crud;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Domain.entity.Inventory;
import Services.readerMavenCSV;

import Domain.dao.crud.IProductservices;
//import model.RemoveToString;
import Domain.entity.Product;

import static java.util.Arrays.stream;


public class productService  {
    private List<Product> productList;
    private int code = 0;

    public List<Product> getProductList() {
        return productList;
    }

    public productService() {
        productList = new ArrayList<>();
    }

    int incremental;
    Product product = new Product();
    Scanner impresion = new Scanner(System.in);
    readerMavenCSV readerMavenCSV = new readerMavenCSV();
    Inventory inventory = new Inventory();
}


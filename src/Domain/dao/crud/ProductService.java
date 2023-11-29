package Domain.dao.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Domain.entity.Inventory;
import Services.ReaderMavenCSV;

//import model.RemoveToString;
import Domain.entity.Product;

import static java.util.Arrays.stream;


public class ProductService {
    private List<Product> productList;
    private int code = 0;

    public List<Product> getProductList() {
        return productList;
    }

    public ProductService() {
        productList = new ArrayList<>();
    }

    int incremental;
    Product product = new Product();
    Scanner impresion = new Scanner(System.in);
    ReaderMavenCSV readerMavenCSV = new ReaderMavenCSV();
    Inventory inventory = new Inventory();
}


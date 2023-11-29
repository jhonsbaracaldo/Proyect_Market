package Services;

import Domain.dao.crud.ProductService;
import Domain.entity.Product;

import java.util.Optional;
import java.util.Scanner;

public class Search {
    ProductService productService = new ProductService();
    ReaderMavenCSV readerMavenCSV = new ReaderMavenCSV();
    Product product = new Product();
    Scanner impresion = new Scanner(System.in);

    public void Existing(Product product) {
        readerMavenCSV.readerMavenCSV(productService);
        System.out.print("Please enter the product code to check stock: ");
        int codeproduct = impresion.nextInt();

        Optional<Product> StockSearch = productService.getProductList().stream()
                .filter(market -> market.getCode() == codeproduct)
                .findFirst();

        Optional<Integer> stock = productService.getProductList().stream()
                .filter(market -> market.getCode() == codeproduct)
                .map(Product::getCantidad)  //Encontrando Stock
                .findFirst();


            StockSearch.ifPresent(products1 -> System.out.println("Product:\n" + products1));
            stock.ifPresent(existencia -> System.out.println("Units existing:" + existencia));


        }




    }












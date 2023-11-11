package Services;

import Domain.dao.crud.productService;


import Domain.entity.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import java.util.Scanner;

public class readerMavenCSV {
    Scanner reader = new Scanner(System.in);
    Product product = new Product();
     int lastCode =-1;
     public int getLastCode() {
        return lastCode;}
//    public readerMavenCSV() {}


    public void  readerMavenCSV (productService productService){
        //1.Obtener la lista de productos que se encuentra en el objeto productService
        List<Product> productList = productService.getProductList();

        try {
            FileReader fileReader = new FileReader("C:\\Users\\acer\\IdeaProjects\\Market\\resources\\inventory.csv");
            CSVParser readerCSV = new CSVParser(fileReader, CSVFormat.DEFAULT);


            boolean primeraFila = true;

            for (CSVRecord csvRecord : readerCSV) {
                if (primeraFila) {
                    primeraFila = false;
                    continue;
                }

                int codigo = Integer.parseInt(csvRecord.get(0));
                lastCode = Math.max(lastCode, codigo);
                product.setName(csvRecord.get(1));
                product.setDescription(csvRecord.get(2));
                product.setCategory(csvRecord.get(3))  ;
                product.setLabel(csvRecord.get(4));
                product.setPrice(Double.parseDouble(csvRecord.get(5)));
                product.setUrl(csvRecord.get(6));
                Product producto = new Product(codigo, product.getName(), product.getDescription(), product.getCategory(), product.getLabel(), product.getPrice(), product.getUrl());
                productList.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


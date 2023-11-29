package Services;

import Domain.dao.crud.ProductService;


import Domain.entity.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import java.util.Scanner;

public class ReaderMavenCSV {
    Scanner reader = new Scanner(System.in);
    Product product = new Product();
     int lastCode =-1;
     public int getLastCode() {
        return lastCode;}
//    public ReaderMavenCSV() {}


    public void  readerMavenCSV (ProductService productService){
        //1.Obtener la lista de productos que se encuentra en el objeto ProductService
        List<Product> productList = productService.getProductList();

        try {
            FileReader fileReader = new FileReader("C:\\Users\\acer\\IdeaProjects\\Antiguo\\Market\\resources\\inventoryp.csv");
            CSVParser csvParser= new CSVParser(fileReader, CSVFormat.DEFAULT);

            boolean primeraFila = true;

            for (CSVRecord csvRecord : csvParser) {
                if (primeraFila) {
                    primeraFila = false;
                    continue;
                }
                for (CSVRecord csvRecord1 : csvParser) {


//                    int codigo = Integer.parseInt(csvRecord1.get(0));
//                    lastCode = Math.max(lastCode, codigo);
//                    product.setName(csvRecord1.get(1));
//                    product.setCantidad(Integer.parseInt (csvRecord.get(2)));
//                    product.setDescription(csvRecord1.get(3));
//                    product.setCategory(csvRecord1.get(4))  ;
//                    product.setLabel(csvRecord1.get(5));
//                    String pric = (csvRecord1.get(6));
//                    product.setPrice(Integer.parseInt(pric));
//                    product.setUrl(csvRecord1.get(7));

//                    Product producto = new Product(codigo, product.getName(),product.getCantidad(), product.getDescription(), product.getCategory(), product.getLabel(), product.getPrice(), product.getUrl());
//                    productList.add(producto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


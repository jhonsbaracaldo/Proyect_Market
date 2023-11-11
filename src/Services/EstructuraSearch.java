package Services;

import Domain.dao.crud.ProductCrud;
import Domain.dao.crud.productService;
import Domain.entity.Product;
import Services.readerMavenCSV;
import java.util.Optional;
import java.util.Scanner;

public class EstructuraSearch {
    Domain.dao.crud.productService productService= new productService();
    Services.readerMavenCSV readerMavenCSV = new readerMavenCSV();
    Product product = new Product();
    Scanner impresion = new Scanner(System.in);
    public void Existing(Product product)
    {
        readerMavenCSV.readerMavenCSV(productService);
        System.out.println("Ingrese el producto ");
        product.setName(impresion.next());
        String nombreABuscar = product.getName();
        Optional<Product> productoEncontrado = productService.getProductList().stream().filter(produc -> produc.getName().equalsIgnoreCase(nombreABuscar.trim())).findFirst();
        if (productoEncontrado.isPresent()) {
            Product producto = productoEncontrado.get();
            System.out.println("The product you are entering is already in inventory:" + producto.getName());

        } else {
            System.out.println("Your product is not in the current inventory, please register it");

        }

    }







}





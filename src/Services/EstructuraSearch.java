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


   //  Buscando la ultima pocision para asigna el numero siguiente


    public void Existing(Product product)
    {
        readerMavenCSV.readerMavenCSV(productService);
//        readerMavenCSV.readerMavenCSV(productService);
        System.out.println("Ingrese el producto ");
        product.setName(impresion.next());
        String nombreABuscar = product.getName();
        Optional<Product> productoEncontrado = productService.getProductList().stream()
                .filter(produc -> produc.getName().equalsIgnoreCase(nombreABuscar.trim()))
                .findFirst();
        if (productoEncontrado.isPresent()) {
            Product producto = productoEncontrado.get();
            System.out.println("El producto que esta ingresando ya se encuentra en el inventario :" + producto.getName());

        } else {
            System.out.println("Su producto no se encuentra en el inventario actual por favor registrelo");

        }

    }







}





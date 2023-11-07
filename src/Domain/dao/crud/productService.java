package Domain.dao.crud;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import Services.readerMavenCSV;

import Domain.dao.crud.IProductservices;
import model.RemoveToString;
import Domain.entity.Product;


public class productService implements IProductservices {
    private List<Product> productList;
    readerMavenCSV readerMavenCSV = new readerMavenCSV();
    private int code =0;

    public List<Product> getProductList() {
        return productList;
    }

    Scanner impresion = new Scanner(System.in);

    public productService() {
        productList = new ArrayList<>();
    }


    public List<Product> obtenerProductos() {
        return productList;
    }

    @Override
    public void add() {
        readerMavenCSV.readerMavenCSV();


        System.out.print("Ingrese un nuevo producto\nNuevo producto: ");
        String name = impresion.next();
        System.out.print("Asigne una descripcion  ");
        String description = impresion.next();
        System.out.print("Ingrese la categoria ");
        String category = impresion.next();
        System.out.print("Ingrese  la etiqueta: ");
        String label = impresion.next();
        System.out.print("Ingrese el precio: ");
        Double price = impresion.nextDouble();
        System.out.print("Ingrese  la imagen del producto: ");
        String url = impresion.next();



//

        if (readerMavenCSV.getLastCode() >= 0) {
           //auto incremento de la cantidad otal del inventatio y añadiendo producto
            int incremental = readerMavenCSV.getLastCode();
            incremental++;
            productList.add(new Product(incremental, name, description, category, label, price, url));
            productList.stream().forEach(x -> System.out.println(x));

        } else {
            System.out.println("No se encontraron códigos en el archivo CSV.");
        }

    }

//    public void Product(Product product){
//
//        Optional<String> vowelsearch = productList.
//                .filter(market -> market.getName().startsWith(vowel.trim()))
//                .map(Products::getName)  // Mapea el producto encontrado a su nombre
//                .findFirst();
//    }

    @Override
    public void remove() {
        try {
            System.out.print("Para eliminar el producto debe ingresar el codigo:  ");
            code = impresion.nextInt();
            int search = code;

            Optional<Product> RemoveProduct = productList.stream()
                    .filter(persona -> persona.getCode() == search)
                    .findFirst();
            if (RemoveProduct.isPresent()) {
                System.out.println("Se elimino el producto exitosamente");

                productList.removeIf(user -> user.getCode() == (search));
                RemoveProduct.ifPresent(x -> System.out.println(x));
            } else {
                throw new Exception("El codigo " + code + " no exite \n" +
                        "por favor ingrese un producto o valide la opcion 4 para ver los\nproductos existentes");
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }


        //  RemoveProduct.ifPresent(x-> System.out.println(x));
    }
}
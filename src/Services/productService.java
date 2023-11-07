package Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import model.RemoveToString;
import model.Product;

import javax.lang.model.element.NestingKind;
import javax.xml.namespace.QName;


public class productService implements IProductservices {
    private List<Product> productList;
    private int code=1;

    public List<Product> getProductList() {
        return productList;
    }

    Scanner impresion = new Scanner(System.in);
    public productService() {
        productList = new ArrayList<>();
    }

    public void cargarProductosDesdeCSV(String archivoCSV) {
        try {
            File file = new File(archivoCSV);
            Scanner scanner = new Scanner(file);

            // Saltar la primera línea si contiene encabezados
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(",");

                if (campos.length == 7) {
                    int code = Integer.parseInt(campos[0].trim());
                    String nombre = campos[1].trim();
                    String descripcion = campos[2].trim();
                    String categoria = campos[3].trim();
                    String etiqueta = campos[4].trim();
                    double precio = Double.parseDouble(campos[5].trim());
                    String url = campos[6].trim();

                   Product producto = new Product(code,nombre, descripcion, categoria, etiqueta, precio, url);
                  productList.add(producto);


                    RemoveToString remove = new RemoveToString(code,nombre, descripcion, categoria, etiqueta, precio, url);
                    System.out.println(remove);



                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Product> obtenerProductos() {
        return productList;
    }

    @Override
    public void add() {

        System.out.print("Ingrese un nuevo producto\nNuevo producto: ");
        String name= impresion.next();
        System.out.print("Asigne una descripcion  ");
       String description = impresion.next();
        System.out.print("Ingrese la categoria ");
       String category = impresion.next();
        System.out.print("Ingrese  la etiqueta: ");
       String label = impresion.next();
        System.out.print("Ingrese el precio: ");
       Double price = impresion.nextDouble();
        System.out.print("Ingrese  la imagen del producto: ");
       String url= impresion.next();

        // Añadiendo los productos a la etiqueta
        productList.add(new Product(code,name,description,category,label,price,url));

       productList.stream().forEach(x-> System.out.println(x));


    }

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
                RemoveProduct.ifPresent(x-> System.out.println(x));
            } else {
                throw new Exception("El codigo "+code+" no exite \n"+
                        "por favor ingrese un producto o valide la opcion 4 para ver los\nproductos existentes");
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }



      //  RemoveProduct.ifPresent(x-> System.out.println(x));
    }
}
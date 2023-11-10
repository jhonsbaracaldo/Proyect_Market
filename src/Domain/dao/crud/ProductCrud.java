package Domain.dao.crud;



import Domain.entity.Product;
import Services.readerMavenCSV;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductCrud implements IProductservices{
  Scanner impresion = new Scanner(System.in);
    Product product= new Product();
    Services.readerMavenCSV readerMavenCSV = new readerMavenCSV();
    productService productService= new productService();
    int incremental;
    private List<Product> productList;

    public int getIncremental() {
        return incremental;
    }

    @Override
    public void add() {

        System.out.print("Ingrese un nuevo producto\nNuevo producto: ");
        product.setName(impresion.next());
        System.out.print("Asigne una descripcion  ");
        product.setDescription(impresion.next());
        System.out.print("Ingrese la categoria ");
        product.setCategory(impresion.next());
        System.out.print("Ingrese  la etiqueta: ");
        product.setLabel(impresion.next());
        System.out.print("Ingrese el precio: ");
        product.setPrice( impresion.nextDouble());
        System.out.print("Ingrese  la imagen del producto: ");
        product.setUrl(impresion.next());

        if (readerMavenCSV.getLastCode() >= 0) {
            //auto incremento de la cantidad otal del inventatio y añadiendo producto
             incremental = readerMavenCSV.getLastCode();
            incremental++;

            productService.getProductList().add(new Product(incremental,product.getName(), product.getDescription(), product.getCategory(), product.getLabel(), product.getPrice(), product.getUrl()));
            System.out.println("Producto agregado exitosamente");

        } else {
            System.out.println(" retorne a las instruciones ");

        }

    }


    @Override
    public void remove() {
        try {
            System.out.print("Para eliminar el producto debe ingresar el codigo:  ");
            product.setCode(impresion.nextInt());
            int search = product.getCode();

            Optional<Product> RemoveProduct = productService.getProductList().stream().filter(persona -> persona.getCode() == search).findFirst();
            if (RemoveProduct.isPresent()) {
                System.out.println("Se elimino el producto exitosamente");

                productService.getProductList().removeIf(user -> user.getCode() == (search));
                RemoveProduct.ifPresent(x -> System.out.println(x));
            } else {
                throw new Exception("El codigo " + product.getCode() + " no exite \n" +
                        "por favor ingrese un producto o valide la opcion 4 para ver los\nproductos existentes");
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }


    }

    @Override
    public void update() {
        try {
            System.out.println("Ingrese el codigo del producto a modificar");
            product.setCode(impresion.nextInt());
            int altersearch = product.getCode();
            Optional<Product> AlterProduct = productService.getProductList().stream().filter(persona -> persona.getCode() == altersearch).findFirst();

            if (AlterProduct.isPresent()) {
                AlterProduct.ifPresent(product -> System.out.println("Producto:\n " + product));

                System.out.println("Por favor indique que va modificar \n1.Name\n2.Price\n3.Description\n4.Category\5.label");

                int seleccion = impresion.nextInt();
                switch (seleccion) {
                    case 1:
                        System.out.println("Alter Name:");
                        String name = impresion.next();
                        productService.getProductList().stream().filter(alterName -> alterName.getCode() == altersearch).forEach(user -> {
                            user.setName(name);
                        });
                        System.out.println("Name successfully altered");
                        break;
                    case 2:
                        System.out.println(" Alter price: ");
                        Double price = impresion.nextDouble();
                        productService.getProductList().stream().filter(alterPrice -> alterPrice.getCode() == altersearch).forEach(user -> {user.setPrice(price);});
                        Optional<Double> searchPrice = productService.getProductList().stream().filter(market -> market.getCode() == altersearch).map(Product::getPrice).findFirst();

                        if (searchPrice.isPresent() && product.getPrice()<price) {

                            searchPrice.ifPresent(priceproduct-> System.out.println("El precio actual es: "+priceproduct+" El precio ingreso es menor: "+price));
                        } else {
                            searchPrice.ifPresent(priceproduct-> System.out.println("El precio actual es: "+priceproduct+" El precio ingreso es mayor: "+price));
                        }

                        System.out.println("Price successfully altered");
                        break;
                    case 3:
                        System.out.println(" Alter Description:");
                        String description = impresion.next();
                        productService.getProductList().stream().filter(persona -> persona.getCode() == altersearch).forEach(user -> {
                            user.setDescription(description);
                        });
                        System.out.println("Description successfully altered");
                        break;
                    case 4:
                        System.out.println(" Alter Category:");
                        String category = impresion.next();
                        productService.getProductList().stream().filter(alterCategory -> alterCategory.getCode() == altersearch).forEach(user -> {
                            user.setCategory(category);
                        });
                        System.out.println("Category successfully altered");
                        break;
                    case 5:
                        System.out.println(" Alter label:");
                        String label = impresion.next();
                        productService.getProductList().stream().filter(alterlabel -> alterlabel.getCode() == altersearch).forEach(user -> {
                            user.setLabel(label);
                        });
                        System.out.println("label successfully altered");
                        break;
                }

            } else {
                throw new Exception("El codigo " + product.getCode() + " no exite \n" +
                        "por favor ingrese un producto o valide la opcion 4 para ver los\nproductos existentes");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void Searchid() {

    }

    @Override
    public void view()

    {
        if (productService.getProductList().isEmpty())
        {
           readerMavenCSV.readerMavenCSV(productService);
        }
        // Imprime los productos recién agregados
        productService.getProductList().stream().forEach(agregado -> System.out.println(agregado));


    }


}
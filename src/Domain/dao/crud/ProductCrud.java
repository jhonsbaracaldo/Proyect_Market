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

        System.out.print("Inseert new producto\nNew product: ");
        product.setName(impresion.next());
        System.out.println("Insert quantity of units:");
        product.setCantidad(impresion.nextInt());
        System.out.print("Insert a description  ");
        product.setDescription(impresion.next());
        System.out.print("Insert a category ");
        product.setCategory(impresion.next());
        System.out.print("Insert a label: ");
        product.setLabel(impresion.next());
        System.out.print("Insert a price: ");
        product.setPrice( impresion.nextDouble());
        System.out.print("Insert photo a prodcut : ");
        product.setUrl(impresion.next());

        if (readerMavenCSV.getLastCode() >= 0) {
            //auto incremento de la cantidad otal del inventatio y añadiendo producto
            incremental = readerMavenCSV.getLastCode();
            incremental++;

            productService.getProductList().add(new Product(incremental,product.getName(),product.getCantidad(), product.getDescription(), product.getCategory(), product.getLabel(), product.getPrice(), product.getUrl()));
            System.out.println("Product successful ");

        } else {
            System.out.println(" return instruction ");

        }

    }


    @Override
    public void remove() {
        try {
            System.out.print("\nTo delete the product you must insert the code:  ");
            product.setCode(impresion.nextInt());
            int search = product.getCode();

            Optional<Product> RemoveProduct = productService.getProductList().stream().filter(persona -> persona.getCode() == search).findFirst();
            if (RemoveProduct.isPresent()) {
                System.out.println("Product delete successfully ");

                productService.getProductList().removeIf(user -> user.getCode() == (search));
                RemoveProduct.ifPresent(x -> System.out.println(x));
            } else {
                throw new Exception("Code " + product.getCode() + " not exist \n" +
                        "please enter a product or validate to see the existing products");
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }


    }

    @Override
    public void update() {
        try {
            System.out.println("Insert the code of the product to modify");
            product.setCode(impresion.nextInt());
            int altersearch = product.getCode();
            Optional<Product> AlterProduct = productService.getProductList().stream().filter(persona -> persona.getCode() == altersearch).findFirst();

            if (AlterProduct.isPresent()) {
                AlterProduct.ifPresent(product -> System.out.println("Product:\n " + product));

                System.out.println("Please indicate what you are going to modify\n1.Name\n2.Price\n3.Description\n4.Category\n5.label");

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
                        Double priceModi = impresion.nextDouble();
                        Optional<Double> Price = productService.getProductList().stream().filter(market -> market.getCode() == altersearch).map(Product::getPrice).findFirst();
                        productService.getProductList().stream().filter(pric ->pric.getCode() == altersearch).forEach(change -> {
                            change.setPrice(priceModi); });
                        if (Price.isPresent()&& product.getPrice()>priceModi){
                            Price.ifPresent(priceproduct-> System.out.println("El precio actual es: "+priceproduct+" El precio ingreso es menor: "+priceModi));
                        }
                        else {
                            Price.ifPresent(priceproduct-> System.out.println("El precio actual es: "+priceproduct+" El precio ingreso es mayor: "+priceModi));
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
                    case 6:
                        System.out.println(" Alter Quantity:");
                        int Units = impresion.nextInt();
                        productService.getProductList().stream().filter(alterUnits -> alterUnits.getCode() == altersearch).forEach(user -> {
                            user.setCantidad(Units);
                            System.out.println("Quantity successfully altered");
                        });
                        break;
                }

            } else {
                throw new Exception("Coe " + product.getCode() + " not exist \n" +
                        "please enter a product or validate to see the existing products");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void Searchid() {

        System.out.println("Please choose the search method\n1.Code\n2.Name\n");
        int selection = 0;
        selection = impresion.nextInt();
        if (selection == 1) {
            System.out.print("Please insert the product code to check stock: ");

            int codeproduct = impresion.nextInt();
            Optional<Product> product = productService.getProductList().stream()
                    .filter(market -> market.getCode() == codeproduct)
                    .findFirst();

            if (product.isPresent()) {

                product.ifPresent(products1 -> System.out.println("Product:\n" + products1));
            }
        } else {
            System.out.print("Please insert the characteristic word of the product to consult : ");
            String vowel = impresion.next();

            Optional<String> vowelsearch = productService.getProductList().stream().filter(market -> market.getName().startsWith(vowel.trim())).map(Product::getName).findFirst();

            Optional<Product> product = productService.getProductList().stream().filter(market -> market.getName().startsWith(vowel.trim())).findFirst();


            if (vowelsearch.isPresent()) {
                vowelsearch.ifPresent(print -> System.out.println("Product found:\n "+print));
                product.ifPresent(print -> System.out.println("Description:\n"+print));
            }

        }

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
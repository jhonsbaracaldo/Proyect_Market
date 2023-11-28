package Domain.dao.crud;



import Domain.entity.Product;
import Services.Data_base.ConectionBD;
import Services.ReaderMavenCSV;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductCrud implements IProductservices{
  Scanner impresion = new Scanner(System.in);
    Product product= new Product();
//    ReaderMavenCSV readerMavenCSV = new ReaderMavenCSV();
    productService productService= new productService();
    int incremental;
    private List<Product> productList;
    ConectionBD bd = new ConectionBD();
    Connection conexion = bd.conectar();
    UserValidation operaciones = new UserValidation(conexion);

    public ProductCrud() throws SQLException {
    }

    public int getIncremental() {
        return incremental;
    }

    @Override
    public void add() {

        try  {

            System.out.print("Inseert new producto\nNew product: ");
            product.setName(impresion.next());
            System.out.print("Insert quantity of units:");
            product.setCantidad(impresion.nextInt());
            System.out.print("Insert a description:  ");
            product.setDescription(impresion.next());
            System.out.print("Insert a category: ");
            product.setCategory(impresion.next());
            System.out.print("Insert a label: ");
            product.setLabel(impresion.next());
            System.out.print("Insert a price: ");
            product.setPrice(impresion.nextDouble());
            System.out.print("Insert photo a prodcut : ");
            product.setUrl(impresion.next());

            operaciones.Producto(product.getName(), product.getCantidad(), product.getPrice(), product.getDescription(), product.getCategory(), product.getLabel(), product.getUrl());
        }
        catch (Exception e){


        }

//            productService.getProductList().add(new Product(incremental,product.getName(),product.getCantidad(), product.getDescription(), product.getCategory(), product.getLabel(), product.getPrice(), product.getUrl()));
//            System.out.println("Product successful ");



    }


    @Override
    public void remove() throws SQLException {

            System.out.print("\nTo delete the product you must insert the code:  ");
            product.setCode(impresion.nextInt());
            int search = product.getCode();
             operaciones.Remove(search);


//            Optional<Product> RemoveProduct = productService.getProductList().stream().filter(persona -> persona.getCode() == search).findFirst();
//            if (RemoveProduct.isPresent()) {
//
//
//                productService.getProductList().removeIf(user -> user.getCode() == (search));
//                RemoveProduct.ifPresent(x -> System.out.println(x));
//            } else {


    }

    @Override
    public void update() {
        try {
            System.out.println("Insert the code of the product to modify");
            product.setCode(impresion.nextInt());
            int altersearch = product.getCode();
//            Optional<Product> AlterProduct = productService.getProductList().stream().filter(persona -> persona.getCode() == altersearch).findFirst();
//
//            if (AlterProduct.isPresent()) {
//                AlterProduct.ifPresent(product -> System.out.println("Product:\n " + product));

            System.out.println("Please indicate what you are going to modify\n1.Name\n2.Price\n3.Description\n4.Category\n5.label\n6.Quantity");

            int seleccion = impresion.nextInt();
            switch (seleccion) {
                case 1:
                    System.out.println("Alter Name:");
                    String name = impresion.next();
                    operaciones.updateProduct(altersearch, name);
//                        productService.getProductList().stream().filter(alterName -> alterName.getCode() == altersearch).forEach(user -> {
//                            user.setName(name);
//                        });

                    break;
                case 2:
                    System.out.println(" Alter price: ");
                    Double priceModi = impresion.nextDouble();
                    operaciones.updateProduct4(altersearch, priceModi);
//                        Optional<Double> Price = productService.getProductList().stream().filter(market -> market.getCode() == altersearch).map(Product::getPrice).findFirst();
//                        productService.getProductList().stream().filter(pric ->pric.getCode() == altersearch).forEach(change -> {
//                            change.setPrice(priceModi); });
//                        if (Price.isPresent()&& product.getPrice()>priceModi){
//                            Price.ifPresent(priceproduct-> System.out.println("El precio actual es: "+priceproduct+" El precio ingreso es menor: "+priceModi));
//                        }
//                        else {
//                            Price.ifPresent(priceproduct-> System.out.println("El precio actual es: "+priceproduct+" El precio ingreso es mayor: "+priceModi));
//                        }

                    break;
                case 3:
                    System.out.println(" Alter Description:");
                    String description = impresion.next();
                    operaciones.updateProduct1(altersearch, description);
//                        productService.getProductList().stream().filter(persona -> persona.getCode() == altersearch).forEach(user -> {
//                            user.setDescription(description);
//                        });

                    break;
                case 4:
                    System.out.println(" Alter Category:");
                    String category = impresion.next();
                    operaciones.updateProduct2(altersearch, category);
//                        productService.getProductList().stream().filter(alterCategory -> alterCategory.getCode() == altersearch).forEach(user -> {
//                            user.setCategory(category);
//                        });

                    break;
                case 5:
                    System.out.println(" Alter label:");
                    String label = impresion.next();
                    operaciones.updateProduct3(altersearch, label);
//                        productService.getProductList().stream().filter(alterlabel -> alterlabel.getCode() == altersearch).forEach(user -> {
//                            user.setLabel(label);
//                        });

                    break;
                case 6:
                    System.out.println(" Alter Quantity:");
                    int Units = impresion.nextInt();
                    operaciones.updateProduct5(altersearch, Units);
//                        productService.getProductList().stream().filter(alterUnits -> alterUnits.getCode() == altersearch).forEach(user -> {
//                            user.setCantidad(Units);
//                            System.out.println("Quantity successfully altered");
//                        });
                    break;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public void Searchid() {

        System.out.println("Please choose the search method\n1.Code");
        int selection = 0;
        selection = impresion.nextInt();
        if (selection < 1) {
            System.out.print("Please insert the product code to check stock: ");
            int codeproduct = impresion.nextInt();
            String sql = "SELECT * FROM productos.product WHERE id_product =" + codeproduct;
            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    String productName = rs.getString("nameproduct");
                    System.out.println("Nombre del producto: " + productName);
                    String impresion = String.format("%-5s|%-40s |%-5s| %-20s | %-30s | %-20s | %-10s | %s", rs.getInt("id_product"), rs.getString("nameproduct"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("description"), rs.getString("category"),
                            rs.getString("label"), rs.getString("url"));
                    System.out.println(impresion);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);


            }

        }
    }
        @Override
    public void view() throws SQLException {

        String sql = "SELECT * FROM productos.product";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
            String impresion =String.format("%-5s|%-40s |%-5s| %-20s | %-30s | %-20s | %-10s | %s",rs.getInt("id_product"),rs.getString("nameproduct"),rs.getInt("quantity"), rs.getDouble("price"), rs.getString("description"), rs.getString("category"),
                        rs.getString("label"), rs.getString("url"));
                System.out.println(impresion);




            }
        }

    }




}
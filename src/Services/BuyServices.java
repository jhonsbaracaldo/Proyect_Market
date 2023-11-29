package Services;

import Domain.dao.crud.UserValidation;
import Domain.dao.crud.productService;
import Domain.entity.Product;
import Services.Data_base.ConectionBD;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.Optional;
import java.util.Scanner;

public class BuyServices {
     int  altersearch;
       Product product = new Product();
       Domain.dao.crud.productService productService = new productService();
       Scanner reader= new Scanner(System.in);
       ReaderMavenCSV readerMavenCSV =new ReaderMavenCSV();
    ConectionBD bd = new ConectionBD();
    Connection conexion = bd.conectar();
    UserValidation operaciones = new UserValidation(conexion);
    private int units;
Scanner print = new Scanner(System.in);
    public BuyServices() throws SQLException {
    }

    public int getUnits() {
        return units;
    }

    public double getSuma() {
        return suma;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public double getTotal() {
        return total;
    }

    private double suma;
    private double totalIva;
    private double total;



    public void Venta( productService productService){
//      readerMavenCSV.readerMavenCSV(productService);
//        if (productService.getProductList().isEmpty()) {
//            productService.getProductList().add(product);
//        }
        try {


            System.out.println(" Ingrese el codigo del producto a vender");
            product.setCode(reader.nextInt());
//             altersearch = product.getCode();


            String sqlb = "SELECT * FROM productos.product WHERE id_product = "+product.getCode();
            try (Statement stmt = conexion.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlb)) {
                while (rs.next()) {

                    String impresion = String.format("%-6s|%-10s|%-20s|%-20s|%-20s", rs.getInt("id_product"), rs.getString("nameproduct"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("description"));

                    System.out.println(impresion);

//             Optional<Product> MarketProduct = productService.getProductList().stream().filter(buy -> buy.getCode() == altersearch).findFirst();
//             MarketProduct.ifPresent(products1 -> System.out.println(products1));


//            if (MarketProduct.isPresent())
                    System.out.println("Por favor indique las unidades a comprar: ");
                    units = reader.nextInt();
                    double iva = 0.19;

                    suma = (rs.getDouble("price") * units);

//               suma = productService.getProductList().stream()
//                        .filter(producto -> producto.getCode() == altersearch)
//                        .mapToDouble(producto -> units * producto.getPrice()) // Calcular la suma
//                        .sum();

                    totalIva = suma * iva;
                    total = suma + totalIva;
                    java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
                    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = formatter.format(sqlDate);
                    System.out.println("Fecha formateada: " + formattedDate);
                    System.out.println("+-----------------------------------+");
                    System.out.println("|          Market App               |");
                    System.out.println("+-----------------------------------+");
                    System.out.println("|Sale                                ");
                    System.out.println("|" + formattedDate);
                    System.out.println("|Product:" + rs.getString("nameproduct"));
                    System.out.println("|Units:" + units);
                    System.out.println("|Price Units: " + rs.getDouble("price"));
                    System.out.println("|Subtotal:" + suma + "  pesos");
                    System.out.println("|IVA 19% :" + totalIva + " pesos");
                    System.out.println("|Total:" + total);
                    System.out.println("+-----------------------------------+");
                    System.out.println("       Thanks for your buy           ");
                    System.out.println("+-----------------------------------+");

                    operaciones.addBuy(rs.getInt("id_product"), formattedDate,units,total);

                }

            }
                throw new Exception("");

        }catch (Exception e){

            System.out.println(e.getMessage());
        }

    }
    public void BuyNameProductos(productService productService){
        Optional<String> nameProduct = productService.getProductList().stream().filter(market -> market.getCode() == altersearch).map(Product::getName).findFirst();
        nameProduct.ifPresent(print -> System.out.println(print));
    }
    public void BuyPriceProducto(productService productService){
        Optional<Double> Price = productService.getProductList().stream().filter(market -> market.getCode() == altersearch).map(Product::getPrice).findFirst();
        Price.ifPresent(print -> System.out.println(print));
    }


    public void  BuyAll() throws SQLException{

        String sql = "SELECT * FROM ventas.ventas_mas_nombre";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String impresion = String.format("%-5s|%-30s |%-5s| %-20s | %-20s ", rs.getInt("id_ventas"), rs.getString("nameproduct"), rs.getInt("quantity"), rs.getDate("date"), rs.getDouble("total_safe"));
                System.out.println(impresion);


            }
        }
    }


    public void  BuyCategory() throws SQLException{

        String sql = "SELECT * FROM ventas.venta_total";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String impresion = String.format("%-10s|%-15s ", rs.getString("category_name"), rs.getDouble("total_safe"));
                System.out.println(impresion);


            }
        }
    }

    public void  BuyDate() throws SQLException{
        System.out.println("Enter the date to verify sales (yyyy-MM-dd):");
        String date = print.next();
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        String sql = "SELECT * FROM ventas.ventas WHERE date = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setDate(1, sqlDate);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String impresion = String.format(
                        "%-5s|%-5s |%-5s",
                        rs.getDate("date"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_safe")
                );
                System.out.println(impresion);
            }
        }
    }

}

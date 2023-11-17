package Services;

import Domain.dao.crud.productService;
import Domain.entity.Product;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class BuyServices {
     int  altersearch;
       Product product = new Product();
       Domain.dao.crud.productService productService = new productService();
       Scanner reader= new Scanner(System.in);
       ReaderMavenCSV readerMavenCSV =new ReaderMavenCSV();
    private int units;

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
      readerMavenCSV.readerMavenCSV(productService);
        if (productService.getProductList().isEmpty()) {
            productService.getProductList().add(product);
        }
        try {


            System.out.println(" Ingrese el codigo del producto a vender");
            product.setCode(reader.nextInt());
             altersearch = product.getCode();

             Optional<Product> MarketProduct = productService.getProductList().stream().filter(buy -> buy.getCode() == altersearch).findFirst();

            MarketProduct.ifPresent(products1 -> System.out.println(products1));


            if (MarketProduct.isPresent()) {


                System.out.println("Por favor indique las unidades a comprar: ");
                units = reader.nextInt();
                double iva = 0.19;

               suma = productService.getProductList().stream()
                        .filter(producto -> producto.getCode() == altersearch)
                        .mapToDouble(producto -> units * producto.getPrice()) // Calcular la suma
                        .sum();

                totalIva = suma * iva;
                total = suma + totalIva;

                Date date = new Date();
                System.out.println("+-----------------------------------+");
                System.out.println("|          Market App               |");
                System.out.println("+-----------------------------------+");
                System.out.println("|Sale                                ");
                System.out.println("|" + date);
                System.out.print("|Product:");
                BuyNameProductos(productService);
                System.out.println("|Units:" + units);
                System.out.print("|Price Units: ");
                BuyPriceProducto(productService);
                System.out.println("|Subtotal:" + suma + "  pesos");
                System.out.println("|IVA 19% :" + totalIva + " pesos");
                System.out.println("|Total:" + total);
                System.out.println("+-----------------------------------+");
                System.out.println("       Thanks for your buy           ");
                System.out.println("+-----------------------------------+");

            } else {
                throw new Exception("Code  " + product.getCode() + " not exist \n Actual product ");
            }
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

}

package Vista;

import Domain.dao.crud.InventoryCrud;
import Services.BuyServices;
import Services.EstructuraSearch;
import Domain.entity.Product;
import Services.readerMavenCSV;

import Domain.dao.crud.productService;
import Domain.dao.crud.ProductCrud;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        productService productServic = new productService();
        readerMavenCSV readerMavenCSV = new readerMavenCSV();
        Scanner sc= new Scanner(System.in);
        ProductCrud productCrud = new ProductCrud();
        Product product = new Product();
        BuyServices buyServices = new BuyServices();
        InventoryCrud inventoryCrud = new InventoryCrud();
       EstructuraSearch estructuraSearch = new EstructuraSearch();
        // Cargar productos desde un archivo CSV
        int seleccion = showOptionsMenuAndCaptureOptionPrincipal();
        while (seleccion != 11) {

            switch (seleccion) {


                case 1 -> productCrud.view();
                case 2-> productCrud.remove();
                case 3-> productCrud.update();
                case 4-> productCrud.add();
                case 5 -> buyServices.Venta(productServic);
                case 6->inventoryCrud.add();
                case 7->estructuraSearch.Existing(product);
                case 10->readerMavenCSV.readerMavenCSV(productServic);



            }
            seleccion = showOptionsMenuAndCaptureOptionPrincipal();


        }


    }


    static int showOptionsMenuAndCaptureOptionPrincipal() {
        System.out.println("      Bienvenido " +
                "\n" +
                " /////////////\\\\\\\\\\\\\n" +
                "(((((((((((((   \\\\\\\\\\\\\n" +
                "))) ~~      ~~   (((\n" +
                "((( (*)     (*)  )))\n" +
                ")))     <        (((\\s\n" +
                "((( '\\\\______/`   )))\\s\n" +
                ")))\\\\___________/(((\\s\n" +
                " (((   _)  (_    )))\\s\\s\n" +
                "      /\\\\__/\\\\\"\"\" +");
        System.out.println("±----------------------------------------±");
        System.out.println("|   Bienvenido a mi tienda de barrio     |");
        System.out.println("±----------------------------------------±");
        System.out.println("1. ver                                   |");
        System.out.println("2. Añadir                                |");
        System.out.println("3. remover                               |");
        System.out.println("4. Modificar                             |");
        System.out.println("5. Salir                                 |");
        System.out.println("±----------------------------------------±");
        System.out.print("   Ingresa tu opción:    (1 - 4)  ");
        Scanner scanner = new Scanner(System.in);
        int seleccion = 11;
        try {
            seleccion = scanner.nextInt();
            if (seleccion < 1 || seleccion > 11) {
                System.out.println("| The Option selected is not valid. Please try again |");
                showOptionsMenuAndCaptureOptionPrincipal();
            }
        } catch (Exception e) {
            System.out.println("| The Option selected is not valid. Please try again |");
            System.out.println("Usted esta ingresado otro tipo de caracter");
            showOptionsMenuAndCaptureOptionPrincipal();
        }
        return seleccion;
    }

}



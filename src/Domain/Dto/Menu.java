package Domain.Dto;

import Domain.dao.crud.ProductCrud;
import Domain.dao.crud.BdValidation;
import Domain.dao.crud.ProductService;
import Services.BuyServices;
import Domain.dao.crud.InventoryCrud;
import Domain.entity.Product;
import Domain.Data_base.ConectionBD;
import Services.Search;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    InventoryCrud inventoryCrud = new InventoryCrud();
    BuyServices buyServices = new BuyServices();
      Search search = new Search();
    Product product = new Product();

     private String userName ;

    public Menu() throws SQLException {
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    private String password ;
    ProductService productService = new ProductService();
    ProductCrud productCrud = new ProductCrud();

    ConectionBD bd = new ConectionBD();
    Connection conexion = bd.conectar();
   BdValidation operaciones = new BdValidation(conexion);
    public void menuUser() throws SQLException {

        Scanner scanner = new Scanner(System.in);
         userName = "";
         password = "";

        while (true) {
            String[] mainMenu = {
                    "Login",

                    "1. Create Account",
                    "2. Login",
                    "3. Exit",
                    "Choose an option:"

            };
            printMenuWithBorder(mainMenu);

            int selector = scanner.nextInt();
            scanner.nextLine();

            switch (selector) {
                case 1:
                    System.out.print("Please enter username: ");
                    userName = scanner.nextLine();
                    System.out.print("Please enter password: ");
                    password = scanner.nextLine();
                    System.out.println("User created");
                    operaciones.insertarDatos(userName,password);

                    break;
                case 2:
                    System.out.print("Please enter username: ");
                    String inputUserName = scanner.nextLine();
                    System.out.print("Please enter password: ");
                    String inputPassword = scanner.nextLine();

                    if (inputUserName.equals(userName) && inputPassword.equals(password)) {
                        System.out.println("\n Welcome" + " " + userName + "\n");

                        menuInventSaleOptions();
                    } else {
                        System.out.println("Failed login, incorrect username or password.");
                    }
                    break;
                case 3:
                    System.out.println("Exit program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
                    break;

            }
        }
    }

    public static void printMenuWithBorder(String[] mainMenu) {
        int maxLength = 0;
        for (String item : mainMenu) {
            if (item.length() > maxLength) {
                maxLength = item.length();
            }
        }
        // Imprimir el borde superior
        printBorder(maxLength + 4);

        // Imprimir el contenido del menú
        for (int i = 0; i < mainMenu.length; i++) {
            int espaciosAntes = (maxLength - mainMenu[i].length()) / 2;
            int espaciosDespues = maxLength - mainMenu[i].length() - espaciosAntes;
            if (i == 0) {
                System.out.println("*" + addSpaces(espaciosAntes) + " " + mainMenu[i] + addSpaces(espaciosDespues) + "*");
            } else {

                System.out.println("* " + mainMenu[i] + addSpaces(espaciosDespues));
            }
        }

        // Imprimir el borde inferior
        printBorder(maxLength + 4);
    }

    public static void printBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        }
        System.out.println();

    }

    public static String addSpaces(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public void menuInventSaleOptions() throws SQLException {
        Scanner VlInput = new Scanner(System.in);
        int selector = showMenuInventSaleOptions();
        while (selector != 5) {

            switch (selector) {

                case 1 -> menuPrincipalInventoryCrud();
                case 2 -> menuPrincipalCrud(); //dirige a product
                case 3 -> menuPrincipalBuy();
                case 4 -> menuUser();

            }
            selector = showMenuInventSaleOptions();
        }
    }

    static int showMenuInventSaleOptions() {
        String[] menuInventSale = {
                "Commercial Management",
                "1. Inventory",
                "2. Product",
                "3. Sales",
                "4. Exit",
                "Choose an option:"

        };
        printMenuWithBorder(menuInventSale);


        Scanner scanner = new Scanner(System.in);
        int selector = 5;
        try {
            selector = scanner.nextInt();
            if (selector < 1 || selector > 4) {
                System.out.println("| The Option selected is not valid. Please try again |");
                showMenuInventSaleOptions();
            }
        } catch (Exception e) {
            System.out.println("| The Option selected is not valid. Please try again |");
            System.out.println("You are entering a different type of character");
            showMenuInventSaleOptions();
        }
        return selector;

    }

    //Menu principal de acciones en productos
    public void menuPrincipalCrud() throws SQLException {
        Scanner VlInput = new Scanner(System.in);
        int selector = showMenuPrincipalCrud();
        while (selector != 9) {

            switch (selector) {

                case 1 -> operaciones.Stock();
                case 2 -> productCrud.add();
                case 3 -> productCrud.Searchid();
                case 4 -> productCrud.update();
                case 5 -> productCrud.remove();
                case 6 -> productCrud.view();
                case 7 -> search.Existing(product);
                case 8 -> { menuInventSaleOptions();return;}
            }
            selector = showMenuPrincipalCrud();
        }
    }

    static int showMenuPrincipalCrud() {
        String[] menuPrincipalCrud = {
                "Product Modification & Control",
                "1. List products with stock",
                "2. Add",
                "3. Consult",
                "4. Modify",
                "5. Delete",
                "6  View Inventory",
                "7  Existing",
                "8. Exit",
                "Choose an option:"

        };
        printMenuWithBorder(menuPrincipalCrud);


        Scanner scanner = new Scanner(System.in);
        int selector = 9;
        try {
            selector = scanner.nextInt();
            if (selector < 1 || selector > 8) {
                System.out.println("| The Option selected is not valid. Please try again |");
                showMenuPrincipalCrud();
            }
        } catch (Exception e) {
            System.out.println("| The Option selected is not valid. Please try again |");
            System.out.println("You are entering a different type of character");
            showMenuPrincipalCrud();
        }
        return selector;

    }

    //Menu principal acciones inventario
    public void menuPrincipalInventoryCrud() throws SQLException {
        Scanner VlInput = new Scanner(System.in);
        int selector = showMenuPrincipalInventoryCrud();
        while (selector != 9) {

            switch (selector) {


                case 1 -> inventoryCrud.Searchid();
                case 2 -> inventoryCrud.add();
                case 3 -> inventoryCrud.Searchid();
                case 4 -> inventoryCrud.update();
                case 5 -> inventoryCrud.remove();
                case 6->inventoryCrud.view();
                case 7 -> search.Existing(product);
                case 8 -> { menuInventSaleOptions();return;}
            }
            selector = showMenuPrincipalInventoryCrud();
        }
    }

    static int showMenuPrincipalInventoryCrud() {
        String[] menuInventory = {
                "Product Modification & Control",
                "1. Query duplicate code",
                "2. Add",
                "3. Consult",
                "4. Modify",
                "5. Delete",
                "6  View Inventory",
                "7  Existing",
                "8. Exit",
                "Choose an option:"

        };
        printMenuWithBorder(menuInventory);


        Scanner scanner = new Scanner(System.in);
        int selector = 9;
        try {
            selector = scanner.nextInt();
            if (selector < 1 || selector > 8) {
                System.out.println("| The Option selected is not valid. Please try again |");
                showMenuPrincipalInventoryCrud();
            }
        } catch (Exception e) {
            System.out.println("| The Option selected is not valid. Please try again |");
            System.out.println("You are entering a different type of character");
            showMenuPrincipalInventoryCrud();
        }
        return selector;
    }

    public void menuPrincipalBuy() throws SQLException {
        Scanner VlInput = new Scanner(System.in);
        int selector = showMenuPrincipalBuy();
        while (selector != 9) {

            switch (selector) {


                case 1 -> buyServices.Venta(productService);
                case 2 -> buyServices.BuyAll();
                case 3 -> buyServices.BuyCategory();
                case 4 -> buyServices.BuyDate();
                case 5 -> { menuInventSaleOptions();return;}
            }
            selector = showMenuPrincipalBuy();
        }
    }
    static int showMenuPrincipalBuy() {
        String[] menuInventory = {
                "Product Modification & Control",
                "1. Sale",
                "2. Sales history",
                "3. Sales by category",
                "4. Sale for date",
                "5. Exit",
                "Choose an option:"

        };
        printMenuWithBorder(menuInventory);


        Scanner scanner = new Scanner(System.in);
        int selector = 9;
        try {
            selector = scanner.nextInt();
            if (selector < 1 || selector > 8) {
                System.out.println("| The Option selected is not valid. Please try again |");
                showMenuPrincipalInventoryCrud();
            }
        } catch (Exception e) {
            System.out.println("| The Option selected is not valid. Please try again |");
            System.out.println("You are entering a different type of character");
            showMenuPrincipalInventoryCrud();
        }
        return selector;
    }
}


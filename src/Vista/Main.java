package Vista;

import Services.readerMavenCSV;

import Domain.dao.crud.productService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        productService productServic = new productService();
        readerMavenCSV readerMavenCSV = new readerMavenCSV();
        Scanner sc= new Scanner(System.in);
        // Cargar productos desde un archivo CSV
        System.out.println("Seleciona por favor que va realizar \n1.Ver Productos\n2.Añadir\n3.remover \n4.ReaderCSV");

            int seleccion = 0;
            while (seleccion!=4) {
                seleccion = sc.nextInt();
                switch (seleccion) {

                   // case 1->
                    case 2-> productServic.add();
                    case 3-> productServic.remove();
                    case 4-> readerMavenCSV.readerMavenCSV();
                }

            }
        }

    }

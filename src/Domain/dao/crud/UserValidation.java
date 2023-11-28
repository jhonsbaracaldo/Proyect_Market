package Domain.dao.crud;

import Domain.Dto.Menu;

import java.sql.*;
import java.util.Scanner;

public class UserValidation {
    private Connection conexion;

    public UserValidation(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarDatos(String user, String password) throws SQLException {
        String sql = "INSERT INTO clientes.usuarios (user_name,password) VALUES (?,?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            System.out.println("Datos insertados correctamente");
            pstmt.executeUpdate();


        }
    }
    public void Producto(String dato, Integer Cantidad,Double price,String description,String category,String label,String url) throws SQLException {
        String sql = "INSERT INTO productos.product (nameproduct,quantity,price,description,category,label,url) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql))
        {
            pstmt.setString(1, dato);
            pstmt.setInt(2, Cantidad);
            pstmt.setDouble(3, price);
            pstmt.setString(4, description);
            pstmt.setString(5, category);
            pstmt.setString(6, label);
            pstmt.setString(7, url);
            System.out.println( "Datos insertados correctamente");
            pstmt.executeUpdate();

        }
    }

    public void Remove(Integer code) throws SQLException {

        String sql = "DELETE FROM productos.product WHERE  id_product = "+code;
        try (Statement stmt = conexion.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Product delete successfully ");
            } else {
                System.out.println("No se encontró la fila con id_product ");
            }
        }
    }

    public void updateProduct(Integer code ,String nombre) throws SQLException {
        String sql = "UPDATE productos.product SET nameproduct = ? WHERE id_product =" + code;
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, nombre);
        int filasActualizadas = pstmt.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Name successfully altered");
        } else {
            System.out.println("No row found");
        }
    }

    public  void updateProduct1(Integer code ,String descripcion) throws SQLException {
        String sql = "UPDATE productos.product SET description = ? WHERE id_product =" + code;
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, descripcion);
        int filasActualizadas = pstmt.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Description successfully altered");
        } else {
            System.out.println("No row found ");
        }
    }


    public   void updateProduct2(Integer code ,String categoria) throws SQLException {
        String sql2= "UPDATE productos.product SET category = ? WHERE id_product ="+code;
        PreparedStatement pstmt = conexion.prepareStatement(sql2);
        pstmt.setString(1, categoria);
        int filasActualizadas = pstmt.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Category successfully altered");
        } else {
            System.out.println("No row found");
        }
    }

    public void updateProduct3(Integer code ,String etiquetas) throws SQLException {
        String sql3 = "UPDATE productos.product SET label = ? WHERE id_product ="+code;
        PreparedStatement pstmt = conexion.prepareStatement(sql3);
        pstmt.setString(1, etiquetas);
        int filasActualizadas = pstmt.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("label successfully altered");
        } else {
            System.out.println("No row found");
        }
    }
    public  void updateProduct4(Integer code ,Double precio) throws SQLException {
        String sql4 = "UPDATE productos.product SET price = ? WHERE id_product ="+code;
        PreparedStatement pstmt = conexion.prepareStatement(sql4);
        pstmt.setDouble(1, precio);
        int filasActualizadas = pstmt.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Price successfully altered");
        } else {
            System.out.println("No row found ");
        }
    }


    public   void updateProduct5(Integer code ,Integer Quantity) throws SQLException {
        String sql5 = "UPDATE productos.product SET quantity = ? WHERE id_product ="+code;
        PreparedStatement pstmt = conexion.prepareStatement(sql5);
        pstmt.setInt(1, Quantity);
        int filasActualizadas = pstmt.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Quantity successfully altered");
        } else {
            System.out.println("No row found ");
        }
    }


}




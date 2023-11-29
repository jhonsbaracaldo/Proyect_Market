package Domain.dao.crud;

import java.sql.*;
import java.util.Scanner;

public class BdValidation {
    private Connection conexion;
     Scanner scanner = new Scanner(System.in);
    public BdValidation(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarDatos(String user, String password) throws SQLException {
        String sql = "INSERT INTO clientes.usuarios (user_name,password) VALUES (?,?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            pstmt.executeUpdate();


        }
    }
    public void Producto(String dato,Integer Cantidad,Double price,String description, String url,Integer category) throws SQLException {
        String sql = "INSERT INTO productos.product (nameproduct,quantity,price,description,url,id_category) VALUES (?,?,?,?,?,?)";


        try (PreparedStatement pstmt = conexion.prepareStatement(sql))
        {
            pstmt.setString(1, dato);
            pstmt.setInt(2, Cantidad);
            pstmt.setDouble(3, price);
            pstmt.setString(4, description);
            pstmt.setString(5, url);
            pstmt.setInt(6, category);
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


    public  void updateProduct5(Integer code ,Integer Quantity) throws SQLException {
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

    public void category () throws SQLException {

        String sql = "SELECT * FROM  productos.vista_categorias_etiquetas";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
           while (rs.next()){
              Integer idCategory = rs.getInt("id_category");
                String nameCategory = rs.getString("name");
                Integer idLabel = rs.getInt("id_label");
                String nameLabel = rs.getString("label_name");

               System.out.print("Id Category:"+idCategory+"|"+" Name Category "+nameCategory);
               System.out.println("Id Labels:"+idLabel+"| "+"Name Labels "+nameLabel);
               System.out.println("------------");
           }


        }
    }
    public void addCategory(String category) throws SQLException {
        String sql = "INSERT INTO productos.category (name) VALUES (?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql))
        {
            pstmt.setString(1,category);

            System.out.println( "Category entered correctly ");
            pstmt.executeUpdate();

        }
    }
    public void addBuy(Integer id_prodcuto, String fecha,Integer cantidad,Double total) {
        String sql = "INSERT INTO ventas.ventas (id_product,date,quantity,total_safe) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id_prodcuto);
            pstmt.setDate(2, Date.valueOf(fecha));
            pstmt.setInt(3, cantidad);
            pstmt.setDouble(4, total);

            System.out.println("Category entered correctly ");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Stock () throws SQLException {
        System.out.println("insert the number for which you want to verify the quantities");
        Integer Stock = scanner.nextInt();
        String sql = "SELECT * FROM productos.product WHERE quantity < "+Stock;
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                String impresion = String.format("%-5s|%-40s |%-5s| %-20s | %-30s | %-20s ", rs.getInt("id_product"), rs.getString("nameproduct"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("description")
                        ,rs.getString("url"));
                System.out.println(impresion);
            }


        }
    }

}










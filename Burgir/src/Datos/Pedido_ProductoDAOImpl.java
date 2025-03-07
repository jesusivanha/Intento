package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Negocio.Pedido;
import Negocio.Pedido_Productos;
import Negocio.Producto;

public class Pedido_ProductoDAOImpl {
     private ConexionDB conexion;

    // constructor
    public Pedido_ProductoDAOImpl() {
        conexion = new ConexionDB();
        System.out.println("com.mycompany.productoDAO.ProductoDaoImpl.<init>()");
    }


    public Pedido_Productos getPedido_ProductoById(Pedido pedido, Producto producto, int contadorDeProducto){
        String sql = "SELECT * FROM Pedido_Productos WHERE id_pedido = ? AND id_producto = ? AND id_unico_producto = ?";
        Pedido_Productos pedido_Producto = null;

        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setInt(1, pedido.getId());
            consulta.setInt(2, producto.getId());
            consulta.setInt(3, contadorDeProducto);

            // Ejecutar la consulta
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    // Crear y llenar el objeto User
                    pedido_Producto = new Pedido_Productos();
                    pedido_Producto.setIdPedido(resultado.getInt("id_pedido"));
                    pedido_Producto.setIdProducto(resultado.getInt("id_producto"));
                    pedido_Producto.setIdUnicoProducto(resultado.getInt("id_unico_producto"));
                    pedido_Producto.setPrecio(resultado.getInt("precio"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            ex.printStackTrace();
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
        return pedido_Producto;
    }


    public void addProducto(Pedido pedido, Producto producto, int contadorDeProductos){
        String sql = "INSERT INTO Pedido_Productos (id_pedido, id_producto, id_unico_producto, precio) VALUES (?,?,?,?)";
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setInt(1, pedido.getId());
            consulta.setInt(2, producto.getId());
            consulta.setInt(3, contadorDeProductos);
            consulta.setFloat(4, producto.getPrecio());

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto guardado exitosamente.");
            } else {
                System.out.println("No se pudo guardar el Producto.");
            }

        } catch (SQLException ex) {
            System.out.println("Error al guardar el producto.");
            ex.printStackTrace();
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
    }
    
    public void borrarProducto(Pedido_Productos pedido_Producto){
        String sql = "DELETE FROM Pedido_Productos WHERE id_pedido = ? AND id_producto = ? AND id_unico_producto = ?";
    
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setInt(1, pedido_Producto.getIdPedido());
            consulta.setInt(2, pedido_Producto.getIdProducto());
            consulta.setInt(3, pedido_Producto.getIdUnicoProducto());

            // Ejecutar la consulta
            int  rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario borrado exitosamente.");
            } else {
                System.out.println("No se pudo borrar el usuario.");
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada para borrar");
            ex.printStackTrace();
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
    }
}

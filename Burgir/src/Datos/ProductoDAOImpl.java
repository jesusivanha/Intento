package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/

import Negocio.Detalles;
import Negocio.Producto;

public class ProductoDAOImpl {
    private ConexionDB conexion;

    // constructor
    public ProductoDAOImpl() {
        conexion = new ConexionDB();
        System.out.println("com.mycompany.productoDAO.ProductoDaoImpl.<init>()");
    }

    public Producto getProductoById(String id){
        String sql = "SELECT * FROM Producto WHERE id = ?";
        Producto producto = null;

        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setString(1, id);

            // Ejecutar la consulta
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    // Crear y llenar el objeto User
                    producto = new Producto();
                    producto.setId(resultado.getInt("id"));
                    producto.setNombre(resultado.getString("nombre"));

                    producto.setDetalles(resultado.getString("detalles"));
                    producto.setImagen(resultado.getString("imagen"));
                    producto.setPrecio(resultado.getFloat("precio"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
        return producto;
    }
    public Detalles getNumeroDeProductos(){
        String sql = "SELECT COUNT(*) FROM Producto";
        Detalles detalles = null;

        try (Connection objConexion = conexion.obtenerConexion();
            PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            //consulta.setString(1, id);

            // Ejecutar la consulta
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    detalles = new Detalles();
                    detalles.setNumeroDeProductos(resultado.getInt(1));
                }
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }

        return detalles;
    }
}
package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Negocio.Producto_Ingredientes;

public class ProductoIngredienteDAOImpl {
    private ConexionDB conexion;

    public ProductoIngredienteDAOImpl() {
        conexion = new ConexionDB();
        System.out.println("com.mycompany.productoDAO.ProductoDaoImpl.<init>()");
    }

    public Producto_Ingredientes getProductoIngredienteById(String idProducto, String idIngrediente){
        String sql = "SELECT * FROM Producto_Ingredientes WHERE id_producto = ? AND id_ingrediente = ?";
        Producto_Ingredientes producto_Ingredientes = null;

        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setString(1, idProducto);
            consulta.setString(2, idIngrediente);

            // Ejecutar la consulta
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    // Crear y llenar el objeto User
                    producto_Ingredientes = new Producto_Ingredientes();
                    producto_Ingredientes.setIdProducto(resultado.getInt("id_producto"));
                    producto_Ingredientes.setIdIngrediente(resultado.getInt("id_ingrediente"));
                    producto_Ingredientes.setCantidad(resultado.getInt("cantidad"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
        return producto_Ingredientes;
    }

    // constructor
    
}

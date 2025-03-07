package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Negocio.Detalles;
import Negocio.Ingrediente;

public class IngredienteDAOImpl {
    private ConexionDB conexion;

    // constructor
    public IngredienteDAOImpl() {
        conexion = new ConexionDB();
        System.out.println("com.mycompany.productoDAO.ProductoDaoImpl.<init>()");
    }

    public Ingrediente getProductoById(String id_ing){
        String sql = "SELECT * FROM Ingredientes WHERE id_ing = ?";
        Ingrediente ingrediente = null;

        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setString(1, id_ing);

            // Ejecutar la consulta
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    // Crear y llenar el objeto User
                    ingrediente = new Ingrediente();
                    ingrediente.setId_ing(resultado.getInt("id_ing"));
                    ingrediente.setNombre(resultado.getString("nombre"));
                    ingrediente.setPrecio(resultado.getFloat("precio"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
        return ingrediente;
    }
    public Detalles getNumeroDeIngredientes(){
        String sql = "SELECT COUNT(*) FROM Ingredientes";
        Detalles detalles = null;

        try (Connection objConexion = conexion.obtenerConexion();
            PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    detalles = new Detalles();
                    detalles.setNumeroDeIngredientes(resultado.getInt(1));
                }
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }

        return detalles;
    }
}

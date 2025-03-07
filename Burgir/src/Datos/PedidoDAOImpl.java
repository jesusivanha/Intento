package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Negocio.Pedido;

public class PedidoDAOImpl {
     private ConexionDB conexion;

    // constructor
    public PedidoDAOImpl() {
        conexion = new ConexionDB();
        System.out.println("com.mycompany.productoDAO.ProductoDaoImpl.<init>()");
    }

    public Pedido getPedido(){
        String sql = "SELECT * FROM Pedido ORDER BY id_pedido DESC LIMIT 1;";
        Pedido pedido = null;

        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {
            // Ejecutar la consulta
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    // Crear y llenar el objeto User
                    pedido = new Pedido();
                    pedido.setId(resultado.getInt("id_pedido"));
                    pedido.setEstado(resultado.getString("estado"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
        return pedido;
    }




    public void addPedido(Pedido pedido){
        String sql = "INSERT INTO Pedido (estado) VALUES (?)";

        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setString(1, pedido.getEstado());

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido guardado exitosamente.");
            } else {
                System.out.println("No se pudo guardar el Pedido.");
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
    }

    public void borrarPedido(Pedido pedido){
        String sql = "DELETE FROM Pedido WHERE id_pedido = ? ";

        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setInt(1, pedido.getId());

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido borrado exitosamente.");
            } else {
                System.out.println("No se pudo borrar el Pedido.");
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
    }
    
}

package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Negocio.Ingrediente;
import Negocio.IngredientesExtra;
import Negocio.Pedido_Productos;


public class IngredientesExtraDAOImpl {
     private ConexionDB conexion;

    // constructor
    public IngredientesExtraDAOImpl() {
        conexion = new ConexionDB();
        System.out.println("com.mycompany.productoDAO.ProductoDaoImpl.<init>()");
    }


   public IngredientesExtra getIngredientesExtraById(Pedido_Productos pedido_Producto, Ingrediente ingrediente){
        String sql = "SELECT * FROM Ingredientes_Extras WHERE id_pedido = ? AND id_unico_producto = ? AND id_ingrediente = ?";
        IngredientesExtra ingredientesExtra= null;

        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setInt(1, pedido_Producto.getIdPedido());
            consulta.setInt(2, pedido_Producto.getIdUnicoProducto());
            consulta.setInt(3, ingrediente.getId_ing());

            // Ejecutar la consulta
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    // Crear y llenar el objeto User
                    ingredientesExtra = new IngredientesExtra();
                    ingredientesExtra.setId_pedido(resultado.getInt("id_pedido"));
                    ingredientesExtra.setId_unico_producto(resultado.getInt("id_unico_producto"));
                    ingredientesExtra.setId_ingrediente(resultado.getInt("id_ingrediente"));
                    ingredientesExtra.setCantidad(resultado.getInt("cantidad"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("No se encontro nada");
            ex.printStackTrace();
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
        return ingredientesExtra;
    }

    public void addIngredienteExtra(Pedido_Productos pedido_Producto, int id_ingrediente, int cantidad){
        String sql = "INSERT INTO Ingredientes_Extras (id_pedido, id_unico_producto, id_ingrediente, cantidad) VALUES (?,?,?,?)";
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setInt(1, pedido_Producto.getIdPedido());
            consulta.setInt(2, pedido_Producto.getIdUnicoProducto());
            consulta.setInt(3, id_ingrediente);
            consulta.setFloat(4, cantidad);

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto guardado exitosamente.");
            } else {
                System.out.println("No se pudo guardar el Producto.");
            }

        } catch (SQLException ex) {
            System.out.println("Error al guardar el ingrediente.");
            ex.printStackTrace();
            //Logger.getLogger(ProductoDaoImpl.class.getName()).log(Level.SEVERE, "Error al obtener el usuario por ID", ex);
        }
    }
    
    public void updateCantidad(Pedido_Productos pedido_Producto, int id_ingrediente, int cantidad){
        String sql = "UPDATE Ingredientes_Extras SET cantidad = ? WHERE id_pedido = ? AND id_unico_producto = ? AND id_ingrediente = ?";
    
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            // Asignar el valor del parámetro
            consulta.setInt(1, cantidad);
            consulta.setInt(2, pedido_Producto.getIdPedido());
            consulta.setInt(3, pedido_Producto.getIdUnicoProducto());
            consulta.setInt(4, id_ingrediente);

            // Ejecutar la consulta
            int  rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Ingrediente extra agregado exitosamente.");
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

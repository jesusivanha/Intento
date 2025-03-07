package Negocio;

public class Producto_Ingredientes {
    private int idProducto;
    private int idIngrediente;
    private int cantidad;
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public int getIdIngrediente() {
        return idIngrediente;
    }
    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }  
}

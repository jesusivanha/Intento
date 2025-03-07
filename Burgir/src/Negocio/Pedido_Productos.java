package Negocio;

public class Pedido_Productos {
    private int  idPedido;
    private int  idProducto;
    private int idUnicoProducto;
    private float precio;

    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public int getIdUnicoProducto() {
        return idUnicoProducto;
    }

    public void setIdUnicoProducto(int idUnicoProducto) {
        this.idUnicoProducto = idUnicoProducto;
    }

    public float getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
}

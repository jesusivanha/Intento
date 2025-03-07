package Negocio;

public class Producto {
    private int id;
    private String nombre;
    private String detalles;
    private float precio;
    private String imagen;

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDetalles() {
        return detalles;
    }
    public float getPrecio() {
        return precio;
    }
    public String getImagen() {
        return imagen;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }    
}

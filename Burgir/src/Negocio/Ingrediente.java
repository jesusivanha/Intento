package Negocio;

public class Ingrediente {
    private int id_ing;
    private String nombre;
    private float precio;

    public int getId_ing() {
        return id_ing;
    }
    public void setId_ing(int id_ing) {
        this.id_ing = id_ing;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }    
}

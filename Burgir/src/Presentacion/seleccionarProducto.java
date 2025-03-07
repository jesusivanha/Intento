package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Datos.ProductoDAOImpl;
import Negocio.Producto;

public class seleccionarProducto extends JPanel {
    
    public seleccionarProducto(int i) {

        String numProducto = "" + i;

        Producto producto = new Producto();
        ProductoDAOImpl productoDAO = new ProductoDAOImpl();

        producto = productoDAO.getProductoById(numProducto);
         // Etiqueta personalizada para cada botón
        setLayout(new BorderLayout());
        //setPreferredSize(new Dimension(200, 80));
        
        
        JPanel textDerecha = new JPanel();
        JPanel textIngredientes = new JPanel();
        textDerecha.setLayout(new BorderLayout());

        textIngredientes.setBorder(new LineBorder(Color.BLACK,1,true));

        JLabel nombre = new JLabel(producto.getNombre());
        JLabel precio = new JLabel("Precio $" + producto.getPrecio());

        JLabel ingredientes = new JLabel("<html><div style='width:350px;'>"+ "Ingredientes: "+producto.getDetalles()+"</div></html>");
        ingredientes.setHorizontalAlignment(SwingConstants.CENTER);
        // Cargar y redimensionar el icono
        ImageIcon iconoOriginal = new ImageIcon("src\\Presentacion\\rb_2151137700.png");
        Image imagen = iconoOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(90, 90, Image.SCALE_SMOOTH); // Tamaño 40x40
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
       
        
        JLabel image = new JLabel(iconoRedimensionado);

        textDerecha.add(nombre, BorderLayout.CENTER);
        textDerecha.add(precio, BorderLayout.SOUTH);
        textIngredientes.add(ingredientes);
        add(image, BorderLayout.WEST);
        add(textIngredientes);
        add(textDerecha, BorderLayout.EAST);
    }
}

package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Datos.Pedido_ProductoDAOImpl;
import Negocio.Pedido;
import Negocio.Pedido_Productos;
import Negocio.Producto;

public class Panelcarrito extends JPanel implements ActionListener{
    JButton etiquetaImagen2;
    JButton ingrediente;
    JPanel panelP;
    private float[] precioTotal;
    JLabel totalPagar;
    Producto producto;
    Pedido pedido;
    Pedido_Productos pedido_producto;
    Pedido_ProductoDAOImpl pedido_ProductoDAO;
    int contadorDeProductos;
    
    public Panelcarrito(JPanel panelP, Pedido pedido,Producto producto, int contadorDeProductos, JLabel totalPagar, float[] precioTotal){
        this.panelP = panelP;
        this.precioTotal = precioTotal;
        this.totalPagar = totalPagar;
        this.producto = producto;
        //agreacion de productos por pedido
        this.pedido = pedido;
        this.contadorDeProductos = contadorDeProductos;
        pedido_producto = new Pedido_Productos();
        pedido_ProductoDAO = new Pedido_ProductoDAOImpl();
        //se agrega eñ producto la base de datos
        pedido_ProductoDAO.addProducto(pedido, producto, contadorDeProductos);
        //obtenemos el  pedido producto
        pedido_producto = pedido_ProductoDAO.getPedido_ProductoById(pedido, producto, contadorDeProductos);
        
        //setMaximumSize(new Dimension(70, Dimension.MAXIMIZED_BOTH));
        setPreferredSize(new Dimension(100, 100));

        JPanel medio = new JPanel();
        medio.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.BLACK,2,true));
        ImageIcon iconoOriginal = new ImageIcon(producto.getImagen());
        Image imagen = iconoOriginal.getImage();
        Image imagenRedimensionada = imagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Tamaño 40x40
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        ImageIcon iconoOriginal2 = new ImageIcon("src\\Presentacion\\rb_25190.png");
        Image imagen2 = iconoOriginal2.getImage();
        Image imagenRedimensionada2 = imagen2.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Tamaño 40x40
        ImageIcon iconoRedimensionado2 = new ImageIcon(imagenRedimensionada2);

        // Configurar icono
        JLabel etiquetaImagen = new JLabel(iconoRedimensionado);
        etiquetaImagen2 = new JButton(iconoRedimensionado2);

        etiquetaImagen2.setContentAreaFilled(false);
        etiquetaImagen2.setBorderPainted(false);

        JLabel nombreHamburguesa = new JLabel(producto.getNombre());

        ingrediente = new JButton("Ingredientes");

        medio.add(nombreHamburguesa, BorderLayout.WEST);
        medio.add(ingrediente, BorderLayout.EAST);

        // Añadir el JLabel al JFrame
        add(medio, BorderLayout.CENTER);
        add(etiquetaImagen, BorderLayout.WEST);
        add(etiquetaImagen2, BorderLayout.EAST);

        //add(ingrediente, BorderLayout.EAST);
        
        ingrediente.addActionListener(this);
        etiquetaImagen2.addActionListener(this);

        
        totalPagar.setText("Total a Pagar $" + precioTotal);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == etiquetaImagen2){
            //borramos tanto del carrito como de la base de datos
            pedido_ProductoDAO.borrarProducto(pedido_producto);
            panelP.remove(this);
            panelP.revalidate();
            panelP.repaint();
            precioTotal[0] = precioTotal[0] - producto.getPrecio();
            totalPagar.setText("Total a Pagar $" + precioTotal[0]);
        }
        else if(e.getSource() == ingrediente){
            new SeleccionIngrediente(producto, pedido_producto, precioTotal, totalPagar);
        }
    }
}

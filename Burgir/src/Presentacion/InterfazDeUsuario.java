package Presentacion;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Datos.PedidoDAOImpl;
import Datos.ProductoDAOImpl;
import Negocio.Detalles;
import Negocio.Pedido;
import Negocio.Producto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;


public class InterfazDeUsuario extends JFrame implements ActionListener{
    JScrollPane scroll, scroll1;
    JPanel carritoPanel;
    ArrayList<JButton> botonesProductos = new ArrayList<>();
    
    Producto producto;
    ProductoDAOImpl productoDAO;
    int numProducto;
    
    float[] precioTotal = {0.0f};
    JLabel totalPagar;
    
    Pedido pedido;
    PedidoDAOImpl pedidoDAO;
    int contadorDeProducto;
    JButton pagar;
    JButton cancelar;
    
    public InterfazDeUsuario(){
        super("Sistema de venta de hamburguesas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        producto = new Producto();
        productoDAO = new ProductoDAOImpl();
        
        pedido = new Pedido();
        pedidoDAO = new PedidoDAOImpl();
        pedido.setEstado("activo");
        pedidoDAO.addPedido(pedido);
        pedido = pedidoDAO.getPedido();
        
        contadorDeProducto = 0;
        // Panel contenedor para los productos
        JPanel productosPanel = new JPanel();
        productosPanel.setBackground(Color.decode("#F28001"));
        productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
        //productosPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Panel contenedor para el carrito
        carritoPanel = new JPanel();
        carritoPanel.setBackground(Color.decode("#F28001"));
        carritoPanel.setLayout(new GridLayout(botonesProductos.size(), 1, 5, 5));  // Usamos GridLayout para que los elementos tengan un tamaño uniforme
        carritoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        Detalles detalles = new Detalles();
        ProductoDAOImpl productoDAO = new ProductoDAOImpl();
        detalles = productoDAO.getNumeroDeProductos();

        productosPanel.setLayout(new GridLayout(detalles.getNumeroDeProductos(), 1, 10, 10));
        
        // Agregar productos al panel
        for (int i = 1; i <= detalles.getNumeroDeProductos(); i++) {
            //NOTA: Cada boton es independiente, por ende se agrega una lista para generar sus eventos 
            JButton boton = new JButton();
            boton.add(new seleccionarProducto(i));
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
            
            boton.addActionListener(this);
            productosPanel.add(boton);
            botonesProductos.add(boton);
        }
        
        // Crear el JScrollPane con el panel de productos
        scroll = new JScrollPane(productosPanel);
        scroll1 = new JScrollPane(carritoPanel);
        
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(1, 2));
        
        JPanel panelPago = new JPanel();
        totalPagar = new JLabel("Total a pagar $00.00");
        pagar = new JButton("Pagar");
        cancelar = new JButton("Cancelar");
        
        JPanel panelTitulo = new JPanel();
        JLabel titulo = new JLabel("BurguerVend");
        
        titulo.setFont(new Font("Arial", Font.BOLD, 60));
        titulo.setForeground(Color.WHITE);
        panelTitulo.setBackground(Color.decode("#042E46")); 
        panelTitulo.setBorder(new LineBorder(Color.BLACK,4,true));    
        
        totalPagar.setFont(new Font("Arial", Font.BOLD, 50));
        totalPagar.setHorizontalAlignment(SwingConstants.CENTER);
        totalPagar.setForeground(Color.WHITE);
        pagar.setFont(new Font("Arial", Font.BOLD, 50));
        cancelar.setFont(new Font("Arial", Font.BOLD, 50));
        panelPago.setLayout(new BorderLayout());
        panelPago.setBackground(Color.decode("#042E46"));
        panelPago.setBorder(new LineBorder(Color.BLACK,4,true));
        
        panelPago.add(cancelar, BorderLayout.WEST);
        panelPago.add(totalPagar, BorderLayout.CENTER);
        panelPago.add(pagar, BorderLayout.EAST);
        
        panelTitulo.add(titulo);
        
        panelCentral.add(scroll);
        panelCentral.add(scroll1);

        cancelar.addActionListener(this);
        pagar.addActionListener(this);
        
        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelPago, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pagar) {
            new PanelPago(precioTotal);
        } else if (e.getSource() == cancelar){
            Object[] opciones = {"Cancelar pedido", "Continuar con el pedido"};
            int respuesta = JOptionPane.showOptionDialog(
            null,
            "¿Estas seguro que deseas cancelar el pedido?", 
            "Cancelacion",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null,
            opciones,
            opciones[1]);
            
            if(respuesta == 0){
                pedidoDAO.borrarPedido(pedido);
                pedidoDAO.addPedido(pedido);
                pedido = pedidoDAO.getPedido();
                carritoPanel.removeAll(); // Elimina todos los elementos del panel
                carritoPanel.revalidate(); // Recalcula el diseño del panel
                carritoPanel.repaint();  
                precioTotal[0] = 0;    
                totalPagar.setText("Total a Pagar $" + precioTotal[0]);  
            }
    
        } else {
            for(int i = 0; i<botonesProductos.size(); i++){
                //se agrega un producto al carrito cada vez que se presiona un producto
                if(e.getSource() == botonesProductos.get(i)){
                    contadorDeProducto = contadorDeProducto+1;
                    numProducto = i+1;
                    producto = productoDAO.getProductoById(""+ numProducto);
                    precioTotal[0] += producto.getPrecio();
                    carritoPanel.add(new Panelcarrito(carritoPanel, pedido, producto, contadorDeProducto, totalPagar, precioTotal));
                    carritoPanel.revalidate(); // Recalcula el diseño
                    carritoPanel.repaint();   // Redibuja el panel
                    scroll1.repaint();
                    totalPagar.setText("Total a Pagar $" + precioTotal[0]);
                } 
            }
        }
    }
}

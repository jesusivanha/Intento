package Presentacion;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Datos.ProductoDAOImpl;
import Negocio.Detalles;

public class panelProducto extends JPanel implements ActionListener{

    ArrayList<JButton> botonesProductos = new ArrayList<>();

    public panelProducto(){
        setBackground(Color.decode("#7e634e"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        Detalles detalles = new Detalles();
        ProductoDAOImpl productoDAO = new ProductoDAOImpl();
        detalles = productoDAO.getNumeroDeProductos();

        setLayout(new GridLayout(detalles.getNumeroDeProductos(), 1, 10, 10));
        
        // Agregar productos al panel
        for (int i = 1; i <= detalles.getNumeroDeProductos(); i++) {
            //NOTA: Cada boton es independiente, por ende se agrega una lista para generar sus eventos 
            JButton boton = new JButton();
            boton.add(new seleccionarProducto(i));
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);

            boton.addActionListener(this);
            add(boton);
            add(boton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*for(int i = 0; i<botonesProductos.size(); i++){
            //se agrega un producto al carrito cada vez que se presiona un producto
            if(e.getSource() == botonesProductos.get(i)){
                agregarCarrito(i);
            }
        }*/
    }
}

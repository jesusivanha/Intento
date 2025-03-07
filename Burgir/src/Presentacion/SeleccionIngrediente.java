package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Datos.IngredienteDAOImpl;
import Datos.IngredientesExtraDAOImpl;
import Datos.ProductoIngredienteDAOImpl;
import Negocio.Detalles;
import Negocio.Ingrediente;
import Negocio.IngredientesExtra;
import Negocio.Pedido_Productos;
import Negocio.Producto;
import Negocio.Producto_Ingredientes;

public class SeleccionIngrediente extends JFrame implements ActionListener, ItemListener{
    private JCheckBox[] checkBoxes;
    private JButton[] botonMas;
    private JButton[] botonMenos;
    private JLabel[] porcion;
    private float precioBase;
    private JLabel precioLabel;
    private JButton confirmar;
    Detalles detalles;
    Ingrediente ingrediente[];
    IngredienteDAOImpl ingredienteDAO;
    Producto_Ingredientes producto_Ingredientes;

    IngredientesExtra ingredientesExtra[];
    IngredientesExtraDAOImpl ingredientesExtraDAO;
    Pedido_Productos pedido_Productos;

    ProductoIngredienteDAOImpl productoIngredienteDAO;
    Producto producto;
    float[] precio; 
    JLabel totalPagar;
    float total = 0;

    public SeleccionIngrediente(Producto producto, Pedido_Productos pedido_Productos, float[] precio, JLabel totalPagar){
        super("Ingredientes");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.producto = producto;
        this.precio = precio;
        this.totalPagar = totalPagar;
        this.pedido_Productos = pedido_Productos;

        detalles = new Detalles();
        
        ingredienteDAO = new IngredienteDAOImpl();
        producto_Ingredientes = new Producto_Ingredientes();
        productoIngredienteDAO = new ProductoIngredienteDAOImpl();

        detalles = ingredienteDAO.getNumeroDeIngredientes();
        ingredientesExtra = new IngredientesExtra[detalles.getNumeroDeIngredientes()];
        ingredientesExtraDAO = new IngredientesExtraDAOImpl();

        
        
        JPanel panelNorte = new JPanel();
        JLabel nombreHamburguesa = new JLabel(producto.getNombre());
        panelNorte.add(nombreHamburguesa);
        nombreHamburguesa.setFont(new Font("Arial", Font.BOLD, 50));
        nombreHamburguesa.setForeground(Color.WHITE);
        panelNorte.setBorder(new LineBorder(Color.BLACK,4,true));
        panelNorte.setBackground(Color.decode("#042E46"));
        add(panelNorte, BorderLayout.NORTH);

        int num;
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(detalles.getNumeroDeIngredientes(), 4, 10, 15));
        panelCentro.setBackground(Color.decode("#F28001"));
        checkBoxes = new JCheckBox[detalles.getNumeroDeIngredientes()];
        botonMas = new JButton[detalles.getNumeroDeIngredientes()];
        botonMenos = new JButton[detalles.getNumeroDeIngredientes()];
        porcion = new JLabel[detalles.getNumeroDeIngredientes()];
        ingrediente = new Ingrediente[detalles.getNumeroDeIngredientes()];


        for(int i = 0; i < detalles.getNumeroDeIngredientes(); i++){
            
            num = i+1;
            producto_Ingredientes = productoIngredienteDAO.getProductoIngredienteById(Integer.toString(producto.getId()),Integer.toString(num));
            ingrediente[i] = new Ingrediente();
            
            botonMas[i] = new JButton("+");
            botonMenos[i] = new JButton("-");
            botonMas[i].setEnabled(false);
            botonMenos[i].setEnabled(false);

            porcion[i] = new JLabel("");
            if(producto_Ingredientes != null){
                porcion[i] = new JLabel("");
                ingredientesExtraDAO.addIngredienteExtra(pedido_Productos, producto_Ingredientes.getIdIngrediente(),producto_Ingredientes.getCantidad());
            }else {
                ingredientesExtraDAO.addIngredienteExtra(pedido_Productos, i+1, 0);
            }

            porcion[i].setHorizontalAlignment(JLabel.CENTER);
            ingrediente[i] = ingredienteDAO.getProductoById(""+num);
            ingredientesExtra[i] = ingredientesExtraDAO.getIngredientesExtraById(pedido_Productos, ingrediente[i]);
            porcion[i].setText("" + ingredientesExtra[i].getCantidad());


            checkBoxes[i] = new JCheckBox(ingrediente[i].getNombre());
            checkBoxes[i].setText(ingrediente[i].getNombre());
            checkBoxes[i].setForeground(Color.WHITE);
            checkBoxes[i].setFont(new Font("Arial", Font.BOLD, 18));
            porcion[i].setForeground(Color.WHITE);
            porcion[i].setFont(new Font("Arial", Font.BOLD, 18));


            checkBoxes[i].setContentAreaFilled(false);
            checkBoxes[i].setBorderPainted(false);

            checkBoxes[i].addItemListener(this);
            botonMas[i].addActionListener(this);
            botonMenos[i].addActionListener(this);
            panelCentro.add(checkBoxes[i]);
            panelCentro.add(botonMenos[i]);
            panelCentro.add(porcion[i]);
            panelCentro.add(botonMas[i]);
        }

        precioBase = producto.getPrecio();
        confirmar = new JButton("Confirmar");
        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BorderLayout());

        precioLabel = new JLabel("Precio Hambur: $" + precioBase, SwingConstants.CENTER);
        precioLabel.setForeground(Color.WHITE);
        precioLabel.setFont(new Font("Arial", Font.BOLD, 28));


        panelSur.add(precioLabel, BorderLayout.CENTER);
        panelSur.add(confirmar, BorderLayout.EAST);
        panelSur.setBorder(new LineBorder(Color.BLACK,4,true));
        panelSur.setBackground(Color.decode("#042E46"));
        precioLabel.setFont(new Font("Arial", Font.BOLD, 50));



        setLocationRelativeTo(null);

        add(panelSur, BorderLayout.SOUTH);
        add(panelCentro, BorderLayout.CENTER);
        setVisible(true);

        confirmar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmar){
            precio[0] = precio[0] + total;
            totalPagar.setText("Total a Pagar $" + precio[0]);
            dispose();
        }
        for(int i = 0; i < detalles.getNumeroDeIngredientes(); i++){
            if(e.getSource() == botonMas[i]){
                int a = Integer.parseInt(porcion[i].getText()) + 1;
                porcion[i].setText(a+"");

                ingredientesExtraDAO.updateCantidad(pedido_Productos, i+1, a);

                producto.setPrecio(producto.getPrecio()+ingrediente[i].getPrecio());
                total = total + ingrediente[i].getPrecio();
                precioLabel.setText("Precio Hambur: $" + producto.getPrecio());
            }else if(e.getSource() == botonMenos[i]){
                if(Integer.parseInt(porcion[i].getText()) > 0){
                    int a = Integer.parseInt(porcion[i].getText()) - 1;
                    porcion[i].setText(a+"");

                    ingredientesExtraDAO.updateCantidad(pedido_Productos, i+1, a);
                    producto.setPrecio(producto.getPrecio()-ingrediente[i].getPrecio());
                    precioLabel.setText("Precio Hambur: $" + producto.getPrecio());
                    total = total - ingrediente[i].getPrecio();
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        for(int i = 0; i < detalles.getNumeroDeIngredientes(); i++){
            if(checkBoxes[i].isSelected()){
                botonMas[i].setEnabled(true);
                botonMenos[i].setEnabled(true);
            }else if(!checkBoxes[i].isSelected()){
                botonMas[i].setEnabled(false);
                botonMenos[i].setEnabled(false);
            }
        }
    }
}
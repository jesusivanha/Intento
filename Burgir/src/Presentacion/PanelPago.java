package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelPago extends JFrame implements ActionListener {
    float monto, cambio;
    JButton ConfirmarPago, CancelarPago;

    public PanelPago(float[] precioTotal){
        super("Panel pago");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panelFrame = new JPanel();
        panelFrame.setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel();
        JLabel titulo = new JLabel("BurguerVend");
        
        titulo.setFont(new Font("Arial", Font.BOLD, 60)); 
        titulo.setForeground(Color.WHITE);  
        panelTitulo.setBackground(Color.decode("#042E46")); 
        panelTitulo.setBorder(new LineBorder(Color.BLACK,4,true));
        panelTitulo.add(titulo);

        monto = 0;
        cambio = 0;

        JLabel pago = new JLabel("Total a pagar: " + precioTotal[0]);
        JLabel montoIngresasdo = new JLabel("Monto Ingresado " + monto);
        JLabel cambioLabel = new JLabel("Cambio: " + cambio);

        pago.setForeground(Color.WHITE);
        pago.setFont(new Font("Arial", Font.BOLD, 50));
        montoIngresasdo.setForeground(Color.WHITE);
        montoIngresasdo.setFont(new Font("Arial", Font.BOLD, 50));
        cambioLabel.setForeground(Color.WHITE);
        cambioLabel.setFont(new Font("Arial", Font.BOLD, 50));

        JPanel panelCompleto = new JPanel();
        JPanel panelBotones = new JPanel();

        panelCompleto.setLayout(new GridLayout(4, 1, 10, 10));

        ConfirmarPago = new JButton("Confirmar Pago");
        CancelarPago = new JButton("Cancelar Pago");
        panelBotones.add(ConfirmarPago);
        panelBotones.add(CancelarPago);
        

        panelCompleto.setBackground(Color.decode("#F28001"));
        panelBotones.setBackground(Color.decode("#F28001"));

        ConfirmarPago.setFont(new Font("Arial", Font.BOLD, 50));
        CancelarPago.setFont(new Font("Arial", Font.BOLD, 50));

        panelCompleto.add(pago);
        panelCompleto.add(montoIngresasdo);
        panelCompleto.add(cambioLabel);
        panelCompleto.add(panelBotones);

        ConfirmarPago.addActionListener(this);
        CancelarPago.addActionListener(this);

        panelFrame.add(panelTitulo, BorderLayout.NORTH);
        panelFrame.add(panelCompleto, BorderLayout.CENTER);
        add(panelFrame);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CancelarPago) {
            dispose();
        }
        if (e.getSource() == ConfirmarPago) {

        }
    }
}
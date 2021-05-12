package VentanaSwing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * VentanaSwing
 * Nombre_project: DAMJava
 * VentanaConsulta
 * Created by: magua
 * Date : 05/05/2021
 * Description:
 **/


public class VentanaConsulta extends JFrame {
    private JTextField textFieldDNI;
    private JLabel dni_label;
    private JButton calcularNominaButton;
    private JButton consultarTrabajadorButton;
    private JPanel panelMadre;
    private JLabel consulta_label;

    public VentanaConsulta() {
        setLocationRelativeTo(null);
        setResizable(false);
        consultarTrabajadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        calcularNominaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void NewWindowsConsulta() {
        VentanaConsulta vc = new VentanaConsulta();
        vc.setContentPane(new VentanaConsulta().panelMadre);
        vc.pack();
        vc.setVisible(true);
    }
}

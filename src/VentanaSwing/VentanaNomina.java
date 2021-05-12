package VentanaSwing;

import BD.Conexion;
import Tablas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * VentanaSwing
 * Nombre_project: DAMJava
 * VentanaNomina
 * Created by: magua
 * Date : 07/05/2021
 * Description:
 **/
public class VentanaNomina extends JFrame {
    private JPanel panelNomina;
    private JComboBox comboBoxDNI;
    private JLabel CalcularNomina;
    private JLabel DNI;
    private JTextField textFieldHorasExtra;
    private JTextField textFieldHorasExtraFM;
    private JLabel HorasExtra;
    private JLabel HorasExtraFM;
    private JButton calcularButton;

    Connection conn;

    public void selectVentanaNomina(){
        this.comboBoxDNI.removeAllItems();
        try{
            conn = Conexion.getConnection();
            Statement update = conn.createStatement();
            ResultSet r = update.executeQuery("SELECT * FROM Trabajador ");
            while (r.next()){
                this.comboBoxDNI.addItem(r.getString("DNI_Trabajador"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public VentanaNomina() {
        setLocationRelativeTo(null);
        setResizable(false);
        selectVentanaNomina();

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String horas = textFieldHorasExtra.getText();
                String horasfm = textFieldHorasExtraFM.getText();
                String DNI = (String) comboBoxDNI.getSelectedItem();
                Querys q = new Querys();
                int horasParse = Integer.parseInt(horas);
                int horasFMParse = Integer.parseInt(horasfm);
                try {
                    q.insertarNomina(horasParse,horasFMParse,DNI);
                    VentanaVerNomina vvn = new VentanaVerNomina();
                    vvn.NewWindowsVerNomina();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                Nominas n = new Nominas();
                textFieldHorasExtra.setText(null);
                textFieldHorasExtraFM.setText(null);


            }
        });
    }

    public static void NewWindowsNomina() {
        VentanaNomina vn = new VentanaNomina();
        vn.setContentPane(new VentanaNomina().panelNomina);
        vn.pack();
        vn.setVisible(true);
    }
}

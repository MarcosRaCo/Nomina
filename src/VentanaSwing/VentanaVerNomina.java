package VentanaSwing;

import Tablas.Nominas;
import Tablas.Querys;

import javax.swing.*;
import java.awt.*;

/**
 * VentanaSwing
 * Nombre_project: NominasJava-MarcosMiguel
 * VentanaVerNomina
 * Created by: magua
 * Date : 11/05/2021
 * Description:
 **/
public class VentanaVerNomina extends JFrame {
    private JPanel panelVerNomina;
    private JLabel salario_base;
    private JTextField textFieldSalarioBase;
    private JTextField textFieldAntigüedad;
    private JTextField textFieldHExtra;
    private JTextField textFieldHExtraFM;
    private JTextField textFieldContingénciasComunes;
    private JTextField textFieldIRPF;
    private JTextField textFieldHExtraFMD;
    private JTextField textFieldHExtraDeduc;
    private JTextField textFieldFomacion;
    private JTextField textFieldDesempleo;
    private JTextField textFieldTotalMerital;
    private JTextField textFieldSalarioFinal;
    private JLabel plus_antiguedad;
    private JLabel horas_extra;
    private JLabel horas_extra_fm;
    private JLabel contingencias_comunes;
    private JLabel desempleo;
    private JLabel irpf;
    private JLabel total_merital;
    private JLabel deduc_horas_extraFM;
    private JLabel deduc_horas_extra;
    private JLabel formacion;
    private JLabel salario_final;
    private JLabel meritaciones;
    private JLabel deducciones;
    private JButton exportarXMLButton;

    public void ColocarVentana(){
        //Esto te dice la resolución de tu pantalla, lo grande que es
        Toolkit mipantalla=Toolkit.getDefaultToolkit();
        //Almaceno el tamaño de la pantalla en tamanoPantalla
        Dimension tamanoPantalla=mipantalla.getScreenSize();
        //Obtenenmos el alto de la resolucuion y la almacenamo en altoPantalla
        int alturaPantalla=tamanoPantalla.height;
        //Lo mismo pero para el ancho
        int anchoPantalla=tamanoPantalla.width;

        setSize(anchoPantalla/2, alturaPantalla/2);
        setLocation(anchoPantalla/4, alturaPantalla/4);
    }

    public VentanaVerNomina() throws Exception {
        ColocarVentana();
        setResizable(false);
        Querys q = new Querys();
        String ssb = String.valueOf(q.selectSalarioBase());
        String sa = String.valueOf(q.selectAntiguadad());
        String she = String.valueOf(q.selectHorasExtra());
        String shefm = String.valueOf(q.selectHorasExtraFM());
        String stm = String.valueOf(q.selectTotalMerital());

        String scg = String.valueOf(q.selectContingenciasComunes());
        String sd = String.valueOf(q.selectDesempleo());
        String sf = String.valueOf(q.selectFormacion());
        String shed = String.valueOf(q.selectHorasExtraDeducciones());
        String shedfm = String.valueOf(q.selectHorasExtraFMDeducciones());
        String sirpf = String.valueOf(q.selectIrpf());
        String ssf = String.valueOf(q.selectSalarioFinal());

        textFieldSalarioBase.setText(ssb);
        textFieldAntigüedad.setText(sa);
        textFieldHExtra.setText(she);
        textFieldHExtraFM.setText(shefm);
        textFieldTotalMerital.setText(stm);

        textFieldContingénciasComunes.setText(scg);
        textFieldDesempleo.setText(sd);
        textFieldFomacion.setText(sf);
        textFieldHExtraDeduc.setText(shed);

        textFieldHExtraFMD.setText(shedfm);
        textFieldIRPF.setText(sirpf);
        textFieldSalarioFinal.setText(ssf);

    }
    public static void NewWindowsVerNomina() throws Exception {
        VentanaVerNomina vvn = new VentanaVerNomina();
        vvn.setContentPane(new VentanaVerNomina().panelVerNomina);
        vvn.pack();
        vvn.setVisible(true);
    }
    public static void main(String[] args) throws Exception {
        VentanaVerNomina vvn = new VentanaVerNomina();
        vvn.setContentPane(new VentanaVerNomina().panelVerNomina);
        vvn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vvn.pack();
        vvn.setVisible(true);
    }
}

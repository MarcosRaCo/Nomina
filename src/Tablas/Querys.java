package Tablas;
import BD.Conexion;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
/**
 * Tablas
 * Nombre_project: JDBC
 * TrabajadorQuerys
 * Created by: MarcosRa
 * Date : 28/04/2021
 * Description:
 **/
public class Querys {
    private static final String SQL_SELECT = "SELECT * FROM Trabajador";
    private static final String SQL_INSERT = "INSERT INTO Trabajador VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_DNI = "SELECT * FROM Trabajador WHERE DNI_Trabajador = ?";
    Connection conn;

    private void mostrarDatos(String datos) throws IOException {
        System.out.print(datos);
    }
    public List<Trabajador> select() throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement s = null;
        ResultSet r = null;
        Trabajador trabajador = null;

        List<Trabajador> trabajadores = new ArrayList<>();
        conn = Conexion.getConnection();
        s = conn.prepareStatement(SQL_SELECT);
        r = s.executeQuery();

        while(r.next()){
            String DNI_Trabajador = r.getString("DNI_Trabajador");
            String nombre = r.getString("nombre");
            String apellido = r.getString("apellido");
            String apellido2 = r.getString("apellido2");
            Date fechaInicio = r.getDate("fechaInicio");
            int edad = r.getInt("edad");
            String puesto = r.getString("puesto");
            Double irpf = r.getDouble("irpf");
            int idConvenio = r.getInt("idConvenio");
            trabajador = new Trabajador(DNI_Trabajador, nombre, apellido,apellido2, fechaInicio,edad, puesto,irpf, idConvenio);
            trabajadores.add(trabajador);
            mostrarDatos("Trabajador Seleccionado correctamente");
        }
        return trabajadores;
    }


    public int Insert(Trabajador trabajador) throws SQLException {
        Connection conn = null;
        PreparedStatement s = null;
        int registros = 0;
        conn = Conexion.getConnection();
        s = conn.prepareStatement(SQL_INSERT);
        s.setString(1, trabajador.getDNI_Trabajador());
        s.setString(2, trabajador.getNombre());
        s.setString(3, trabajador.getApellido());
        s.setString(4, trabajador.getApellido2());
        s.setDate(5,trabajador.getFechaInicio());
        s.setInt(6, trabajador.getEdad());
        s.setString(7, trabajador.getPuesto());
        s.setDouble(8, trabajador.getIrpf());
        s.setInt(9, trabajador.getIdConvenio());
        registros = s.executeUpdate();
        return registros;
    }
    //PERCEPCIONES

    //Saber idConvenio del trabajador
    public int saberidconTra(String DNITrabajador) throws Exception{
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT idConvenio FROM Trabajador WHERE DNI_Trabajador = '" + DNITrabajador + "'");
        if (r.next()) return (r.getInt(1));
        else return 0;
    }

    //Saber DNI Trabajador
    public String saberDniTra(String DNITrabajador) throws Exception{
       return DNITrabajador;
    }

    //Sabe SalarioBase
    public double saberSalarioBase(String DNITrabajador) throws Exception{
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT salarioBase FROM Convenio WHERE id = '" + saberidconTra(DNITrabajador) + "'");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }


    // Intento Calculo horas extra percepciones
    public double calcularHorasExtra(double horas, String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet rs1 = update.executeQuery("SELECT horasExtra FROM Percepciones_Salariales WHERE idConvenio = '" + saberidconTra(DNITrabajador) + "'");
        if (rs1.next()) return (rs1.getDouble(1) * horas);
        else return 0;
    }

    // Intento Calculo horas extra percepciones
    public double calcularHorasExtrafm(double horas, String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet rs1 = update.executeQuery("SELECT horasExtraFuerzaMayor FROM Percepciones_Salariales WHERE idConvenio = '" + saberidconTra(DNITrabajador) + "'");
        if (rs1.next()) return (rs1.getDouble(1) * horas);
        else return 0;
    }

    //Genera un nuevo id a nomina para la hora de añadirlo
    public int obtenerNuevoIDNomina() throws Exception {
        //Cercar ID maxim Nomina
        Statement cercaMaxId = conn.createStatement();
        ResultSet r = cercaMaxId.executeQuery("SELECT MAX(idNomina) FROM Nominas");
        if (r.next()) return (1 + r.getInt(1));
        else return 1;
    }

    public int saberAñosAntiguedadConvenio(String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet rs1 = update.executeQuery("SELECT años_antiguedad FROM Convenio WHERE id = '" + saberidconTra(DNITrabajador) + "'");
        if (rs1.next()) return (rs1.getInt(1));
        else return 0;
    }
    public String fechaInicioTrabajador(String DNITrabajador) throws Exception{
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT fechaInicio FROM Trabajador WHERE DNI_Trabajador = '" + DNITrabajador + "'");
        if (r.next()) return (r.getString(1));
        else return "Error";
    }

    public double plusAntiguedadPercepcion(String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet rs1 = update.executeQuery("SELECT plus_antiguedad FROM Percepciones_Salariales WHERE idConvenio = '" + saberidconTra(DNITrabajador) + "'");
        if (rs1.next()) return (rs1.getDouble(1));
        else return 0;
    }
    //Calcular dias
    public double calcularAñosAntiguedad(String DNITrabajador) throws Exception {
        String fecha = fechaInicioTrabajador(DNITrabajador);
        LocalDate fechaInicio = LocalDate.parse(fecha);
        LocalDate fechaActual = LocalDate.now();

        long diasEntreFechas = ChronoUnit.DAYS.between(fechaInicio, fechaActual);

        int dias = (int)diasEntreFechas;
        double r = dias / 365;
        r = r / saberAñosAntiguedadConvenio(DNITrabajador);
        r = r * plusAntiguedadPercepcion(DNITrabajador);
        return r;
    }

    //Deducciones
    public double salarioBase(String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet rs1 = update.executeQuery("SELECT salarioBase FROM Convenio WHERE id = '" + saberidconTra(DNITrabajador) + "'");
        if (rs1.next()) return (rs1.getDouble(1));
        else return 0;
    }
    public double salarioBaseMasHorasExtra(double horas ,String DNITrabajador) throws Exception {
        return salarioBase(DNITrabajador) + calcularHorasExtra(horas, DNITrabajador);
    }
    public double contingenciasComunes(String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet rs1 = update.executeQuery("SELECT contingencias_comunes FROM Deducciones WHERE idConvenio = '" + saberidconTra(DNITrabajador) + "'");
        if (rs1.next()) return (rs1.getDouble(1) * salarioBase(DNITrabajador) / 100);
        else return 0;

    }
    public double calcularDesempleo(double horas ,String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet rs1 = update.executeQuery("SELECT Desempleo FROM Deducciones WHERE idConvenio = '" + saberidconTra(DNITrabajador) + "'");
        if (rs1.next()) return (rs1.getDouble(1) * salarioBaseMasHorasExtra(horas, DNITrabajador) / 100);
        else return 0;
    }
    public double calcularFormacion(double horas ,String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet rs1 = update.executeQuery("SELECT formacion FROM Deducciones WHERE idConvenio = '" + saberidconTra(DNITrabajador) + "'");
        if (rs1.next()) return (rs1.getDouble(1) * salarioBaseMasHorasExtra(horas, DNITrabajador) / 100);
        else return 0;
    }

    public double calcularMeritaje(double horas, double horasFM, String DNITrabajador) throws Exception {
        return salarioBase(DNITrabajador) + calcularHorasExtra(horas, DNITrabajador) + calcularHorasExtrafm(horasFM, DNITrabajador) + calcularAñosAntiguedad(DNITrabajador);

    }

    public double calcularIrpf(double horas, double horasFM, String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT irpf FROM Trabajador WHERE DNI_Trabajador = '" + DNITrabajador + "'");
        if (r.next()) return (r.getInt(1) * calcularMeritaje(horas,  horasFM, DNITrabajador) / 100);
        else return 0;
    }
    public double deduccionHorasExtra(double horas, String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT horas_extra FROM Deducciones WHERE idConvenio = '" + saberidconTra(DNITrabajador) + "'");
        if (r.next()) return (r.getInt(1) * calcularHorasExtra(horas, DNITrabajador) / 100);
        else return 0;
    }
    public double deduccionHorasExtraFM(double horas, String DNITrabajador) throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT horas_extra_fm FROM Deducciones WHERE idConvenio = '" + saberidconTra(DNITrabajador) + "'");
        if (r.next()) return (r.getInt(1) * calcularHorasExtrafm(horas, DNITrabajador) / 100);
        else return 0;
    }

    public double aportaciones(double horas,double horasFM, String DNITrabajador) throws Exception {
        return contingenciasComunes(DNITrabajador) + calcularDesempleo(horas, DNITrabajador) + calcularFormacion(horas, DNITrabajador) + deduccionHorasExtraFM(horasFM, DNITrabajador) + deduccionHorasExtra(horas, DNITrabajador);
    }
    public double totalDeducciones(double horas,double horasFM, String DNITrabajador) throws Exception {
        DecimalFormat formato = new DecimalFormat("0.00");
        double r = aportaciones(horas, horasFM, DNITrabajador) + calcularIrpf(horas, horasFM, DNITrabajador);
        double redondeado = Math.round(r * 100.0) / 100.0;
        return redondeado;
    }
    public double importeLiquidoFinal(double horas,double horasFM, String DNITrabajador) throws Exception {
        double r = calcularMeritaje(horas, horasFM, DNITrabajador) - totalDeducciones(horas, horasFM, DNITrabajador);
        double redondeado = Math.round(r * 100.0) / 100.0;
        return redondeado;
    }

    //Insertar en nomina
    public void insertarNomina(double horas, double horasfm, String DNITrabajador) throws Exception{
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        String valors = obtenerNuevoIDNomina()+", '"+calcularHorasExtra(horas,DNITrabajador)+"', '"+calcularHorasExtrafm(horasfm, DNITrabajador)+"','"+ calcularAñosAntiguedad(DNITrabajador) +"','"+
                calcularIrpf(horas, horasfm, DNITrabajador)+"','"+calcularFormacion(horas, DNITrabajador)+"','"+calcularDesempleo(horas, DNITrabajador)+"','"+
                deduccionHorasExtra(horas, DNITrabajador)+"','"+deduccionHorasExtraFM(horasfm, DNITrabajador)+"','"+contingenciasComunes(DNITrabajador)+"','"+
                saberSalarioBase(DNITrabajador)+"','"+calcularMeritaje(horas,horasfm, DNITrabajador)+"','"+importeLiquidoFinal(horas,horasfm, DNITrabajador)+ "','"+saberidconTra(DNITrabajador)+ "','"+saberDniTra(DNITrabajador) + "'";
        update.executeUpdate("INSERT INTO Nominas VALUES(" + valors + ")");
    }

    public double selectSalarioBase() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT salari_base FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }

    public double selectAntiguadad() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT plus_antiguedad FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }

    public double selectHorasExtraFM() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT horas_extra_fm FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }

    public double selectTotalMerital() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT total_merital FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }
    public double selectHorasExtra() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT horas_extra FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }
    public double selectContingenciasComunes() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT contingencias_comunes FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }
    public double selectDesempleo() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT desempleo FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }
    public double selectFormacion() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT formacion FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }
    public double selectHorasExtraDeducciones() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT deduc_horas_extra FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }
    public double selectHorasExtraFMDeducciones() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT deduc_horas_extraFM FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }
    public double selectIrpf() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT irpf FROM Nominas ORDER Bgit Y idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }
    public double selectSalarioFinal() throws Exception {
        conn = Conexion.getConnection();
        Statement update = conn.createStatement();
        ResultSet r = update.executeQuery("SELECT salario_final FROM Nominas ORDER BY idNomina DESC LIMIT 1");
        if (r.next()) return (r.getDouble(1));
        else return 0;
    }

    //

    public static void main(String[] args) throws Exception {
        Querys q = new Querys();

//        System.out.println(q.saberidconTra("43479992X"));
//        System.out.println(q.calcularHorasExtra(15, "43479992X"));
//        System.out.println(q.calcularHorasExtrafm(1, "43479992X"));
//
//
//        System.out.println(q.calcularGratificacionesExtraordinarias(1, "43479992X"));
//
//        System.out.println(q.fechaInicioTrabajador("43479992X"));
//
//        System.out.println(q.calcularAñosAntiguedad("43479992X"));
//        System.out.println(q.saberAñosAntiguedadConvenio("43479992X"));
//        System.out.println(q.plusAntiguedadPercepcion("43479992X"));
//            System.out.println(q.salarioBase("43479992X"));
//            System.out.println(q.salarioBaseMasHorasExtra(2, "43479992X"));

//        System.out.println(q.contingenciasComunes("43479992X"));
//
//        System.out.println(q.calcularFormacion(1, "43479992X"));
//        System.out.println(q.calcularMeritaje(1, 1, "43479992X"));
        //System.out.println(q.calcularIrpf(1,1,"43479992X"));
//        System.out.println(q.aportaciones(1,1,"43479992X"));
//        System.out.println(q.deduccionHorasExtra(1, "43479992X"));
//        System.out.println(q.deduccionHorasExtraFM(1, "43479992X"));
        //System.out.println(q.aportaciones(1,1,"43479992X"));
        //System.out.println(q.totalDeducciones(1,1,"43479992X"));
        //System.out.println(q.importeLiquidoFinal(1,1,"43479992X"));
        //q.insertarNomina(1,1,"43479992X");
        System.out.println(q.selectHorasExtraDeducciones());

    }
}

package Tablas;

/**
 * Tablas
 * Nombre_project: NominasJava-marcos
 * Nominas
 * Created by: MarcosRa
 * Date : 07/05/2021
 * Description:
 **/
public class Nominas {
    private int idNomina;
    private double salarioFinal;
    private double horasExtra;
    private double horasExtraFM;
    private double plusAntiguedad;
    private double gratificacionesExtra;
    private double irpf;
    private double formacion;
    private double desempleo;
    private double deduc_horas_extra;
    private double deduc_horas_extraFM;
    private double contingencias_comunes;
    private double totalMerital;
    private int idConvenio;
    private int dniTrabajador;

    public Nominas() {

    }

    public Nominas(int dniTrabajador) {
        this.dniTrabajador = dniTrabajador;
    }

    public Nominas(int idNomina, double salarioFinal, double horasExtra, double horasExtraFM, double plusAntiguedad, double gratificacionesExtra, double irpf, double formacion, double desempleo, double deduc_horas_extra, double deduc_horas_extraFM, double contingencias_comunes, double totalMerital, int idConvenio, int dniTrabajador) {
        this.idNomina = idNomina;
        this.salarioFinal = salarioFinal;
        this.horasExtra = horasExtra;
        this.horasExtraFM = horasExtraFM;
        this.plusAntiguedad = plusAntiguedad;
        this.gratificacionesExtra = gratificacionesExtra;
        this.irpf = irpf;
        this.formacion = formacion;
        this.desempleo = desempleo;
        this.deduc_horas_extra = deduc_horas_extra;
        this.deduc_horas_extraFM = deduc_horas_extraFM;
        this.contingencias_comunes = contingencias_comunes;
        this.totalMerital = totalMerital;
        this.idConvenio = idConvenio;
        this.dniTrabajador = dniTrabajador;
    }

    public int getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(int idNomina) {
        this.idNomina = idNomina;
    }

    public double getSalarioFinal() {
        return salarioFinal;
    }

    public void setSalarioFinal(double salarioFinal) {
        this.salarioFinal = salarioFinal;
    }

    public double getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(double horasExtra) {
        this.horasExtra = horasExtra;
    }

    public double getHorasExtraFM() {
        return horasExtraFM;
    }

    public void setHorasExtraFM(double horasExtraFM) {
        this.horasExtraFM = horasExtraFM;
    }

    public double getPlusAntiguedad() {
        return plusAntiguedad;
    }

    public void setPlusAntiguedad(double plusAntiguedad) {
        this.plusAntiguedad = plusAntiguedad;
    }

    public double getGratificacionesExtra() {
        return gratificacionesExtra;
    }

    public void setGratificacionesExtra(double gratificacionesExtra) {
        this.gratificacionesExtra = gratificacionesExtra;
    }

    public double getIrpf() {
        return irpf;
    }

    public void setIrpf(double irpf) {
        this.irpf = irpf;
    }

    public double getFormacion() {
        return formacion;
    }

    public void setFormacion(double formacion) {
        this.formacion = formacion;
    }

    public double getDesempleo() {
        return desempleo;
    }

    public void setDesempleo(double desempleo) {
        this.desempleo = desempleo;
    }

    public double getDeduc_horas_extra() {
        return deduc_horas_extra;
    }

    public void setDeduc_horas_extra(double deduc_horas_extra) {
        this.deduc_horas_extra = deduc_horas_extra;
    }

    public double getDeduc_horas_extraFM() {
        return deduc_horas_extraFM;
    }

    public void setDeduc_horas_extraFM(double deduc_horas_extraFM) {
        this.deduc_horas_extraFM = deduc_horas_extraFM;
    }

    public double getContingencias_comunes() {
        return contingencias_comunes;
    }

    public void setContingencias_comunes(double contingencias_comunes) {
        this.contingencias_comunes = contingencias_comunes;
    }

    public double getTotalMerital() {
        return totalMerital;
    }

    public void setTotalMerital(double totalMerital) {
        this.totalMerital = totalMerital;
    }

    public int getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }

    public int getDniTrabajador() {
        return dniTrabajador;
    }

    public void setDniTrabajador(int dniTrabajador) {
        this.dniTrabajador = dniTrabajador;
    }

    @Override
    public String toString() {
        return "Nominas{" +
                "idNomina=" + idNomina +
                ", salarioFinal=" + salarioFinal +
                ", horasExtra=" + horasExtra +
                ", horasExtraFM=" + horasExtraFM +
                ", plusAntiguedad=" + plusAntiguedad +
                ", gratificacionesExtra=" + gratificacionesExtra +
                ", irpf=" + irpf +
                ", formacion=" + formacion +
                ", desempleo=" + desempleo +
                ", deduc_horas_extra=" + deduc_horas_extra +
                ", deduc_horas_extraFM=" + deduc_horas_extraFM +
                ", contingencias_comunes=" + contingencias_comunes +
                ", totalMerital=" + totalMerital +
                ", idConvenio=" + idConvenio +
                ", dniTrabajador=" + dniTrabajador +
                '}';
    }

    public static void main(String[] args) {
        Nominas n = new Nominas();

    }
}

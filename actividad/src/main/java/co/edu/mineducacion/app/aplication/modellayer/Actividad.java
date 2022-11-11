/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.mineducacion.app.aplication.modellayer;

import java.io.Serializable;

/**
 *
 * @author maest
 */
public class Actividad implements Serializable {

    private int idactividad = 0;
    private String descripcion = "";
    private String fechaini = "";
    private String fechapro = "";
    private String finalizado = "";
    private String fechafin = "";
    private int idempleado = 0;
    private Empleado empleado;

    public Actividad() {
    }

    /**
     * @return the idactividad
     */
    public int getIdactividad() {
        return idactividad;
    }

    /**
     * @param idactividad the idactividad to set
     */
    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaini
     */
    public String getFechaini() {
        return fechaini;
    }

    /**
     * @param fechaini the fechaini to set
     */
    public void setFechaini(String fechaini) {
        this.fechaini = fechaini;
    }
    
        /**
     * @return the fechapro
     */
    public String getFechapro() {
        return fechapro;
    }

    /**
     * @param fechapro the fechaini to set
     */
    public void setFechapro(String fechapro) {
        this.fechapro = fechapro;
    }

    /**
     * @return the finalizado
     */
    public String getFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }

    /**
     * @return the fechafin
     */
    public String getFechafin() {
        return fechafin;
    }

    /**
     * @param fechafin the fechafin to set
     */
    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    /**
     * @return the idempleado
     */
    public int getIdempleado() {
        return idempleado;
    }

    /**
     * @param idempleado the idempleado to set
     */
    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
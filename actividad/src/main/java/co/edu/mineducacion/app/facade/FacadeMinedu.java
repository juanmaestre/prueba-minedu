/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.mineducacion.app.facade;

import co.edu.mineducacion.app.aplication.bussineslayer.ActividadBO;
import co.edu.mineducacion.app.aplication.bussineslayer.EmpleadoBO;

/**
 *
 * @author maest
 */
public class FacadeMinedu {
    
    public EmpleadoBO Empleado(){
        return new EmpleadoBO();
    }
    
    public ActividadBO Actividad(){
        return new ActividadBO();
    }    
}

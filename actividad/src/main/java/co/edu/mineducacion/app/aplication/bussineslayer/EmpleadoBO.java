/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.mineducacion.app.aplication.bussineslayer;

import co.edu.mineducacion.app.aplication.datalayer.EmpleadoDAL;
import co.edu.mineducacion.app.aplication.modellayer.Empleado;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maest
 */
public class EmpleadoBO implements Serializable {

    public boolean CreateEmpleado(Empleado obj) throws IOException, SQLException {
        try {
            return EmpleadoDAL.getInstance().CreateEmpleado(obj);
        } catch (Exception ex) {
            throw new SQLException("ERROR:" + ex.getMessage());
        }
    }
    
    public boolean UpdateEmpleado(Empleado obj) throws IOException, SQLException {
        try {
            return EmpleadoDAL.getInstance().UpdateEmpleado(obj);
        } catch (Exception ex) {
            throw new SQLException("ERROR:" + ex.getMessage());
        }
    }

    public boolean DeleteEmpleado(Empleado obj) throws IOException, SQLException {
        try {
            return EmpleadoDAL.getInstance().DeleteEmpleado(obj);
        } catch (Exception ex) {
            throw new SQLException("ERROR:" + ex.getMessage());
        }
    }
    
    public List<Empleado> ListEmpleado(Empleado obj) throws IOException, SQLException {
        try {
            return EmpleadoDAL.getInstance().ListEmpleado(obj);
        } catch (Exception ex) {
            throw new SQLException("ERROR:" + ex.getMessage());
        }
    }    
}

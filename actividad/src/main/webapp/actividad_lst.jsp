<%-- 
    Document   : actividad_lst
    Created on : 11/11/2022, 1:32:39 p. m.
    Author     : maest
--%>

<%@page import="co.edu.mineducacion.app.facade.FactoryMinedu"%>
<%@page import="java.util.List"%>
<%@page import="co.edu.mineducacion.app.aplication.modellayer.Actividad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Avtividad</title>
        <link href="styles/sheet-styles.css" rel="stylesheet" type="text/css" />  
    </head>
    <body>
        <form action="" method="post">
            <table width="60%" border="0" cellspacing="0" cellpadding="0">
                <tr class="verdanaverdeCatorce">
                    <td width="50%" align="left">Descripcion </td>
                    <td width="30%" align="left">ID Empleado</td>
                    <td width="15%" align="left">Fecha Ini.</td>
                    <td width="15%" align="left">Fecha Prop.</td>
                    <td width="15%" align="left">Finalizado</td>
                    <td width="15%" align="left">Fecha Fin.</td>
                    <td width="15%" align="center">Accion</td>
                </tr>
                <%
                    Actividad obj = new Actividad();
                    List<Actividad> lstActividad = FactoryMinedu.FachadaMinedu().Actividad().ListActividad(obj);
                    int contador = 0; 
                    int totalregistros = lstActividad.size();
                %>
                <%while (contador < totalregistros) {%>
                <tr class="normalDiez">
                        <td>&nbsp;<%=lstActividad.get(contador).getDescripcion()%></td>
                        <td>&nbsp;<%=lstActividad.get(contador).getIdempleado()%></td>
                        <td>&nbsp;<%=lstActividad.get(contador).getFechaini()%></td>
                        <td>&nbsp;<%=lstActividad.get(contador).getFechapro()%></td>
                        <td>&nbsp;<%=lstActividad.get(contador).getFinalizado()%></td>
                        <td>&nbsp;<%=lstActividad.get(contador).getFechafin()%></td>                      
                        <td align="center">&nbsp;<img src="images/requerimientos.gif" alt="Editar">&nbsp;<img src="images/delete.gif" alt="Eliminar"> </td>
                    </tr>
                <%contador = contador + 1;}%>
            </table>

        </form>
    </body>
</html>

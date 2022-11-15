<%-- 
    Document   : empleado_lst
    Created on : 11/11/2022, 11:02:24 a. m.
    Author     : maest
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="co.edu.mineducacion.app.facade.FactoryMinedu"%>
<%@page import="co.edu.mineducacion.app.aplication.modellayer.*"%>
<%@page import="co.edu.mineducacion.app.aplication.bussineslayer.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Empleado</title>
        <link href="styles/sheet-styles.css" rel="stylesheet" type="text/css" />  
    </head>
    <body>
        <form action="" method="post">
            <table width="60%" border="0" cellspacing="0" cellpadding="0">
                <tr class="verdanaverdeCatorce">
                    <td width="34%" align="left">Nombre Completo </td>
                    <td width="32%" align="left">Email</td>
                    <td width="15%" align="left">Telefono</td>
                    <td width="19%" align="center">Accion</td>
                </tr>
                <%
                    Empleado obj = new Empleado();
                    List<Empleado> lstEmpleado = FactoryMinedu.FachadaMinedu().Empleado().ListEmpleado(obj);
                    int contador = 0; 
                    int totalregistros = lstEmpleado.size();
                %>
                <%while (contador < totalregistros) {%>
                <tr class="normalDiez">
                        <td>&nbsp;<%=lstEmpleado.get(contador).getNombre()%></td>
                        <td>&nbsp;<%=lstEmpleado.get(contador).getEmail()%></td>
                        <td>&nbsp;<%=lstEmpleado.get(contador).getTelefono()%></td>
                        <td align="center">&nbsp;<img src="images/requerimientos.gif" alt="Editar">&nbsp;<img src="images/delete.gif" alt="Eliminar"> </td>
                    </tr>
                <%contador = contador + 1;}%>
            </table>

        </form>
    </body>
</html>

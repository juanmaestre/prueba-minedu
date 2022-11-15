<%-- 
    Document   : actividad
    Created on : 10/11/2022, 9:27:08 p. m.
    Author     : maest
--%>

<%@page import="co.edu.mineducacion.app.facade.FactoryMinedu"%>
<%@page import="java.util.List"%>
<%@page import="co.edu.mineducacion.app.aplication.modellayer.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="objActividad" class="co.edu.mineducacion.app.aplication.modellayer.Actividad" scope="page">
    <jsp:setProperty name="objActividad" property="*"></jsp:setProperty>
</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Asignacion de Actividades</title>
        <link href="styles/sheet-styles.css" rel="stylesheet" type="text/css" />
    </head>

    <body background="images/fondo.jpg">
        <form id="forma" name="forma" method="post" onSubmit="return validaactividad();" action="salvarActividad"><br><br>
            <table width="500" border="1" align="left" cellpadding="0" cellspacing="0" bordercolor="#000000">
                <tr>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td colspan="2" class="tituloForm"><div align="center">ASIGNACION DE ACTIVIDADES </div>
                                    <hr/></td>
                            </tr>
                            <tr class="normalDiez">
                                <td width="50%" height="57" align="right">Descripci&oacute;n Actividad:&nbsp;</td>
                                <td width="50%"><textarea rows="4" cols=50 name="txtdescripcion" id="txtdescripcion"></textarea></td>
                            </tr>
                            <tr class="normalDiez">
                                <td width="50%" align="right">Empleado:&nbsp;</td>
                                <%
                                    Empleado obj = new Empleado();
                                    List<Empleado> lstEmpleado = FactoryMinedu.FachadaMinedu().Empleado().ListEmpleado(obj);
                                    int contador = 0;
                                    int totalregistros = lstEmpleado.size();
                                %>
                                <td width="50%">
                                    <select id="cmbidempleado" name="cmbidempleado" class="normalDiez">
                                        <option>Escoge una opcion</option>
                                        <%while (contador < totalregistros) {%>                            
                                        <option value="<%=lstEmpleado.get(contador).getIdempleado()%>"><%=lstEmpleado.get(contador).getNombre()%></option>
                                        <%contador = contador + 1;
                                                }%>
                                    </select>       

                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input class="buttonHeaderButtonHover" accesskey="I" type="submit" name="btningresar" id="btningresar" value="Ingresar" /></td>
                            </tr>
                        </table></td>
                </tr>
            </table>
        </form>
    </body>
</html>
<script>
    function validaactividad() {
        if (document.getElementById("txtdescripcion").value.length == 0) {
            alert("Debe escribir la descripción");
            document.getElementById("txtdescripcion").focus();
            return false;
        }
        if (document.getElementById("cmbidempleado.value")!= 0) {
            alert("Debe seleccionar empleado");
            document.getElementById("cmbidempleado").focus();
            return false;
        }        
    }
</script>
<%-- 
    Document   : actividad
    Created on : 10/11/2022, 9:27:08 p. m.
    Author     : maest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>Asignacion de Actividades</title>
    <link href="styles/sheet-styles.css" rel="stylesheet" type="text/css" />
</head>

<body background="images/fondo.jpg">
<form id="forma" name="forma" method="post" onSubmit="return validaactividad();" action=""><br><br>
    <table width="500" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000">
        <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="2" class="tituloForm"><div align="center">ASIGNACION DE ACTIVIDADES </div>
                      <hr/></td>
                </tr>
                <tr class="normalDiez">
                    <td width="50%" height="57" align="right">Descripci&oacute;n Actividad:&nbsp;</td>
                    <td width="50%"><textarea rows="4" cols=50 name="txtactividad" id="txtactividad"></textarea></td>
                </tr>
                <tr class="normalDiez">
                    <td width="50%" align="right">Empleado:&nbsp;</td>
                    <td width="50%"><input type="text" name="txtemail" id="txtemail" /></td>
                </tr>
				<tr class="normalDiez">
                    <td width="50%" align="right">Fecha y Hora Propuesta :&nbsp;</td>
                    <td width="50%"><input type="text" name="txttelefono" id="txttelefono" /></td>
                </tr>
				<tr class="normalDiez">
                    <td width="50%" align="right">Solucionado :&nbsp;</td>
                    <td width="50%"><input type="check" name="chksolucionado" id="chksolucionado" /></td>
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
    function validaactividad(){
        if (document.getElementById("txtactividad").value.length==0){
            alert("Debe escribir la descripción");
            document.getElementById("txtdescripcion").focus();
            return false;
        }		
    }
</script>
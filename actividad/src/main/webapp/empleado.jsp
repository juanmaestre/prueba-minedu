<%-- 
    Document   : empleado
    Created on : 10/11/2022, 4:04:27 p. m.
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
<form id="forma" name="forma" method="post" onSubmit="return validaempleado();" action=""><br><br>
    <table width="500" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000">
        <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="2" class="tituloForm"><div align="center">REGISTRO DE EMPLEADOS </div><hr/></td>
                </tr>
                <tr class="normalDiez">
                    <td width="50%" align="right">Nombre:&nbsp;</td>
                    <td width="50%"><input type="text" name="txtnombre" id="txtnombre" /></td>
                </tr>
                <tr class="normalDiez">
                    <td width="50%" align="right">Email:&nbsp;</td>
                    <td width="50%"><input type="text" name="txtemail" id="txtemail" /></td>
                </tr>
				     <tr class="normalDiez">
                    <td width="50%" align="right">Telefono:&nbsp;</td>
                    <td width="50%"><input type="text" name="txttelefono" id="txttelefono" /></td>
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
    function validaempleado(){
        if (document.getElementById("txtnombre").value.length==0){
            alert("Debe escribir nombre de empleado");
            document.getElementById("txtnombre").focus();
            return false;
        }
        if (document.getElementById("txtemail").value.length==0){
            alert("Debe escribir correo electronico");
            document.getElementById("txtemail").focus();
            return false;
        }
        if (document.getElementById("txttelefono").value.length==0){
            alert("Debe escribir numero telefonico");
            document.getElementById("txttelefono").focus();
            return false;
        }		
    }
</script>
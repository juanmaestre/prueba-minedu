<%-- 
    Document   : signin
    Created on : 6/11/2022, 9:06:54 a. m.
    Author     : maest
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>Login Actividades</title>
    <link href="styles/sheet-styles.css" rel="stylesheet" type="text/css" />
</head>

<body background="images/fondo.jpg">
<form id="forma" name="forma" method="post" onSubmit="return validalogin();" action="ValidaUsuario"><br><br>
    <table width="500" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000">
        <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="2" class="tituloForm"><div align="center">AUTENTICACION DE USUARIO </div><hr/></td>
                </tr>
                <tr class="normalDiez">
                    <td width="50%" align="right">Nombre de Usuario:&nbsp;</td>
                    <td width="50%"><input type="text" name="txtusuario" id="txtusuario" /></td>
                </tr>
                <tr class="normalDiez">
                    <td width="50%" align="right">Contraseña:&nbsp;</td>
                    <td width="50%"><input type="password" name="txtclave" id="txtclave" /></td>
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
    function validalogin(){
        if (document.getElementById("txtusuario").value.length==0){
            alert("Tiene que escribir su nombre de usuario");
            document.getElementById("txtusuario").focus();
            return false;
        }
        if (document.getElementById("txtclave").value.length==0){
            alert("Tiene que escribir su nombre de usuario");
            document.getElementById("txtclave").focus();
            return false;
        }
    }
</script>
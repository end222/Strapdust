<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Resetear contraseña</title>
	  
	  <link rel="icon" type="image/png" href="../favicon.ico"/>

    <!-- Bootstrap core CSS-->
    <link href="control/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="control/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="control/css/sb-admin.css" rel="stylesheet">

  </head>
  <body class="bg-dark">

    <div class="container">
      <div class="card card-login mx-auto mt-5">
        <div class="card-header">Cambio de contraseña para <c:out value="${sessionScope.DATA}"/></div>
        <div class="card-body">
          <div class="text-center mb-4">
            <h4>Reestablecer contraseña</h4>
            <p>Introduce una nueva contraseña para tu cuenta en ecoQUIZ.</p>
            <font size="3" color="red">${errorMessage}</font>
          </div>
          <form method="post" action="ComprobarToken.html">
            <div class="form-group">
              <div class="form-label-group">
                <input type="password" id="inputPass" class="form-control"  required="required" autofocus="autofocus" name="pass">
                <label for="inputEmail">Nueva contraseña</label>
              </div>
            </div>
 <div class="form-group">
              <div class="form-label-group">

                <input type="password" id="inputConfirm" class="form-control" required="required" name="confirm">
                <label for="inputEmail">Confirmar nueva contraseña</label>

	 </div>
			  </div>
            <input type="submit" class="btn btn-primary btn-block" value="Aceptar">
          </form>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="control/vendor/jquery/jquery.min.js"></script>
    <script src="control/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="control/vendor/jquery-easing/jquery.easing.min.js"></script>

  </body>

</html>

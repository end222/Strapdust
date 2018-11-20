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

    <title>Alumno</title>
	  
	  <link rel="icon" type="image/png" href="../favicon.ico"/>

    <!-- Bootstrap core CSS-->
    <link href="control/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="control/vendor/fontawesome-free/css/all.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="control/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="control/css/sb-admin.css" rel="stylesheet">

  </head>

  	
  <body id="page-top" class= "sidebar-toggled">
  	<jsp:include page="/ObtenerAlumnoInfo" />
  	<jsp:useBean id="AlumnoBean" class="Bean.AlumnoBean" scope="session" />

    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

      <a class="navbar-brand mr-1" href="ComprobarSeguridad.do?direccion=/a_alumno.jsp">Alumno</a>
      

      <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#"><em class="fas fa-bars"></em></button>

    

      <!-- Navbar -->
      <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown no-arrow mx-1">
          
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </li>
        <li class="nav-item dropdown no-arrow mx-1">
          
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </li>
        <li class="nav-item dropdown no-arrow">
          <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-user-circle fa-fw"></i>
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">

            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Cerrar sesión</a>
          </div>
        </li>
      </ul>

    </nav>

    <div id="wrapper">

      <!-- Sidebar -->
      <ul class="sidebar navbar-nav toggled">
        <li class="nav-item  active"> <a class="nav-link" href="ComprobarSeguridad.do?direccion=a_alumno.jsp"> <em class="fas fa-fw fa-address-card"></em> <span>Info</span> </a> </li>
        <li class="nav-item">
          <a class="nav-link" href="ComprobarSeguridad.do?direccion=a_upload.jsp">
            <i class="fas fa-fw fa-image"></i>
            <span>Cartel</span></a>
        </li>
      </ul>

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="#">Inicio</a>
            </li>
            <li class="breadcrumb-item active">Información del alumno</li>
          </ol>
<!-- Page Content -->
          <h1>Hola <jsp:getProperty property="nombre" name="AlumnoBean"/></h1>
          <hr>
          
			<div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-users"></i>
              Tu Información</div>
            <div class="card-body">
             <h4>Información</h4>
				<address>
					<strong>Nombre</strong>
					<br><jsp:getProperty property="nombre" name="AlumnoBean"/>
					<br>
					<strong>NIA</strong>
					<br><jsp:getProperty property="NIA" name="AlumnoBean"/>
					<br>
				</address>
				<address>
					<strong>Tu grupo</strong>
					<br>
					Formas parte del grupo: <jsp:getProperty property="grupo" name="AlumnoBean"/>
					<br>
					Los integrantes de ese grupo son:
					

                   		<c:forEach items="${GR_AL.getLista()}" var="Alumno">
							<br>&nbsp&nbsp${Alumno.getNIA()} - ${Alumno.getNombre()}
						</c:forEach>
				
									
					
				</address>
            </div>
				<a class="btn btn-secondary btn-block"  data-toggle="modal" data-target="#change" >Cambiar contraseña</a>
				<p></p>
				 <button type="button" class="btn btn-danger">Solicitar baja</button>
           
          </div>
          
  </div>
        <!-- /.container-fluid -->


        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright © Erios 2018</span>
            </div>
          </div>
        </footer>

      </div>
      <!-- /.content-wrapper -->

  </div>
    <!-- /#wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Vas a cerrar la sesión</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Seleciona salir si deseas finalizar la sesión.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-primary" href="CerrarSesion.do?">Salir</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal cambiar contraseña -->
    <div class="modal" tabindex="-1" role="dialog" id ="change">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Cambiar la contraseña</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Se va a envíar un mensaje de confirmación a tu correo de la unviersidad para procesar el cambio.</p>
      </div>
      <div class="modal-footer">
        <a class="btn btn-primary" href="RegistrarUsuario.do?">Aceptar</a>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>

    <!-- Bootstrap core JavaScript-->
    <script src="control/vendor/jquery/jquery.min.js"></script>
    <script src="control/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="control/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="control/vendor/chart.js/Chart.min.js"></script>
    <script src="control/vendor/datatables/jquery.dataTables.js"></script>
    <script src="control/vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="control/js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="control/js/demo/datatables-demo.js"></script>
    <script src="control/js/demo/chart-area-demo.js"></script>

  </body>

</html>

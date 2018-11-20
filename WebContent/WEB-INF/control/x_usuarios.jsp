<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Usuarios</title>
	  
	  <link rel="icon" type="image/png" href="favicon.ico"/>

    <!-- Bootstrap core CSS-->
    <link href="control/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="control/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="control/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="control/css/sb-admin.css" rel="stylesheet">
    

  </head>

   <body id="page-top" class="sidebar-toggled">

    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

      <a class="navbar-brand mr-1" href="ComprobarSeguridad.do?direccion=x_administrador.jsp">Administración</a>

      <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" ><em class="fas fa-bars"></em></button>

    

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
        <li class="nav-item dropdown"> <a class="nav-link" href="ComprobarSeguridad.do?direccion=x_administrador.jsp"> <em class="fas fa-fw fa-tachometer-alt"></em> <span>Inicio</span> </a> </li>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <em class="fas fa-fw fa-folder"></em> <span>Configuración</span> </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">Configuración:</h6>
			  		<div class="dropdown-divider"></div>
			<a class="dropdown-item" data-toggle="modal" href="#change">Cambiar contraseña</a>
			  <a class="dropdown-item active" href="ComprobarSeguridadTabla.do?direccion=x_usuarios.jsp">Usuarios</a>

            <a class="dropdown-item" href="ComprobarSeguridadTablaGrupos.do?direccion=x_grupos.jsp">Grupos</a>

          </div>
        </li>
        <li class="nav-item"> <a class="nav-link" href="index"> <em class="fas fa-fw fa-images"></em> <span>Carteles</span> </a> </li>
        <li class="nav-item"> <a class="nav-link" href="ComprobarSeguridadStatsel.do"> <em class="fas fa-fw fa-chart-area"></em> <span>Estadísticas</span></a> </li>
      
      </ul>

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="#">Configuración</a>
            </li>
            <li class="breadcrumb-item active">Usuarios</li>
          </ol>
			<div>
			<a class="btn btn-primary btn-block" href="ComprobarSeguridad.do?direccion=x_nuevo_usuario.jsp"><strong>Añadir nuevos alumnos</strong></a>
			<a class="btn btn-secondary btn-block" href="ComprobarSeguridadTabla.do?direccion=x_borrar_usuarios.jsp"><strong>Eliminar alumnos</strong></a>
				
			
			<a class="btn btn-info btn-block" href="ComprobarSeguridad.do?direccion=x_nuevo_admin.jsp">Añadir nuevos administradores</a>
			<a class="btn btn-danger btn-block" href="ComprobarSeguridad.do?direccion=x_borrar_admin.jsp">Eliminar administradores</a>
			</div>
			
          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Tabla de alumnos</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" >
                  <thead>
                    <tr>
					  <th>NIA</th>
                      <th>Nombre</th>
                      <th>Grupo</th>					  
                      <th>Fecha de registro</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
					  <th>NIA</th>
                      <th>Nombre</th>
					  <th>Grupo</th>
                      <th>Fecha de registro</th>
                    </tr>
                  </tfoot>
                  <tbody>
					<c:forEach items="${LISTA_AL}" var="AlumnoBean">
						<tr>
							<td><c:out value="${AlumnoBean.NIA}" /></td>
					    	<td><c:out value="${AlumnoBean.nombre}" /></td>
					    	<td>${AlumnoBean.grupo}</td>
					    	<td>${AlumnoBean.fecha}</td>
					    	
					    </tr>
					</c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
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
		<!-- Change Password Modal-->
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
    <script src="control/vendor/datatables/jquery.dataTables.js"></script>
    <script src="control/vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="control/js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="control/js/demo/datatables-demo.js"></script>

  </body>

</html>

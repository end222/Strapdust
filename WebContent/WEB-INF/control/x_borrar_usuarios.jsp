<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Borrar usuarios</title>
	  
	  <link rel="icon" type="image/png" href="../favicon.ico"/>

    <!-- Bootstrap core CSS-->
    <link href="control/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="control/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="control/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="control/css/sb-admin.css" rel="stylesheet">

  </head>

   <body id="page-top">

    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

      <a class="navbar-brand mr-1" href="ComprobarSeguridad.do?direccion=x_administrador.jsp">Administraci�n</a>

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
            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Cerrar sesi�n</a>
          </div>
        </li>
      </ul>

    </nav>


    <div id="wrapper">

       <!-- Sidebar -->
      <ul class="sidebar navbar-nav">
        <li class="nav-item dropdown"> <a class="nav-link" href="ComprobarSeguridad.do?direccion=x_administrador.jsp"> <em class="fas fa-fw fa-tachometer-alt"></em> <span>Inicio</span> </a> </li>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <em class="fas fa-fw fa-folder"></em> <span>Configuraci�n</span> </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">Configuraci�n:</h6>
			  		<div class="dropdown-divider"></div>
			<a class="dropdown-item" href="forgot-password.html">Cambiar contrase�a</a>
            <a class="dropdown-item active" href="ComprobarSeguridadTabla.do?direccion=x_usuarios.jsp">Usuarios</a>

            <a class="dropdown-item" href="ComprobarSeguridadTablaGrupos.do?direccion=x_grupos.jsp">Grupos</a>

          </div>
        </li>
        <li class="nav-item"> <a class="nav-link" href="ComprobarSeguridad.do?direccion=x_stats.jsp"> <em class="fas fa-fw fa-chart-area"></em> <span>Estad�sticas</span></a> </li>
      
      </ul>

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="#">Configuraci�n</a>
            </li>
		<li class="breadcrumb-item">
			<a href="ComprobarSeguridadTabla.do?direccion=x_usuarios.jsp">Usuarios</a>
		</li>
		<li class="breadcrumb-item active">Borrar alumnos</li>
          </ol>
          <!-- DataTables Example -->
			<div class="form-group">
                 <strong>Introduzca alumnos a borrar*</strong>
                <textarea class="form-control" rows="3" ></textarea>
				  </div>
			<div class="form-group">
				  <p class="card-footer text-muted">*Deben introducirse los NIAs de los usuarios a borrar, separados por espacios o saltos de l�nea. Ejemplo:<br> 123456 222888 121212<br> 987654 789123</p>
              </div>
			
		
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Tabla de alumnos</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
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
                    <tr>
					  <td>123443</td>
					  <td>Tiger Nixon</td>
					  <td>grupo1</td>
                      <td>2011/04/25</td>
                    </tr>
                    <tr>
					  <td>543443</td>
                      <td>Garrett Winters</td>
					  <td>grupo6</td>
                      <td>2011/07/25</td>
                    </tr>
                    <tr>
					  <td>123763</td>
                      <td>Ashton Cox</td>
					  <td>grupo1</td>
                      <td>2009/01/12</td>
                    </tr>
                    <tr>
					  <td>125433</td>
                      <td>Cedric Kelly</td>
					  <td>grupo3</td>
                      <td>2012/03/29</td>
                    </tr>
                    <tr>
					  <td>877443</td>
                      <td>Airi Satou</td>
					  <td>grupo6</td>
                      <td>2008/11/28</td>
                    </tr>
                    <tr>
					  <td>123453</td>
                      <td>Brielle Williamson</td>
					  <td>---</td>
                      <td>2012/12/02</td>
                    </tr>
                    <tr>
					  <td>663443</td>
                      <td>Herrod Chandler</td>
					  <td>grupo1</td>
                      <td>2012/08/06</td>
                    </tr>
                    <tr>
					  <td>123888</td>
                      <td>Rhona Davidson</td>
					  <td>grupo6</td>
                      <td>2010/10/14</td>
                    </tr>
                    <tr>
					  <td>144343</td>
                      <td>Colleen Hurst</td>
					  <td>grupo3</td>
                      <td>2009/09/15</td>
                    </tr>
					<tr>
					  <td>334343</td>
                      <td>Will Smitd</td>
					  <td>---</td>
                      <td>2013/09/15</td>
                    </tr>
					<tr>
					  <td>334343</td>
                      <td>Homer Simpson</td>
					  <td>---</td>
                      <td>2011/10/11</td>
                    </tr>
					<tr>
					  <td>334343</td>
                      <td>Pedro Picapiedra</td>
					  <td>---</td>
                      <td>2008/12/11</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
	<button type="button" class="btn btn-lg btn-danger">Borrar alumnos</button>
		<p class="text-center text-muted my-5">
           Se borrar�n los usuarios seleccionados<br>(los cambios no pueden deshacerse)
          </p>  
			 
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div>

          <p class="small text-center text-muted my-5">
            <em>More table examples coming soon...</em>
          </p>

        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright � Your Website 2018</span>
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
            <h5 class="modal-title" id="exampleModalLabel">Vas a cerrar la sesi�n</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">�</span>
            </button>
          </div>
          <div class="modal-body">Seleciona salir si deseas finalizar la sesi�n.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-primary" href="CerrarSesion.do?">Salir</a>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="es">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cartel</title>

	  <link rel="icon" type="image/png" href="../favicon.ico"/>

    <!-- Bootstrap core CSS-->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">

  </head>

  <body id="page-top">

    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

      <a class="navbar-brand mr-1" href="index.html">Alumno</a>

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
            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Cerrar sesiÃ³n</a>
          </div>
        </li>
      </ul>

    </nav>

    <div id="wrapper">

      <!-- Sidebar -->
      <ul class="sidebar navbar-nav">
        <li class="nav-item"> <a class="nav-link" href="alumno.html"> <em class="fas fa-fw fa-address-card"></em> <span>Info</span> </a> </li>
        <li class="nav-item active">
          <a class="nav-link" href="upload.html">
            <i class="fas fa-fw fa-image"></i>
            <span>Cartel</span></a>
        </li>
      </ul>

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="alumno.html">Inicio</a>
            </li>
            <li class="breadcrumb-item active">Cartel</li>
          </ol>

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-image"></i>
              AdministraciÃ³n del cartel</div>
            <div class="card-body">
              <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                          <div class="row">
                            <div class="col-lg-6">
                                    <form role="form">
                                        <div class="form-group">
                                            <strong>TÃ­tulo</strong>
                                            <input class="form-control">
                                        </div>

                                        <div class="form-group">
                                            <strong>Cartel realizado por el grupo</strong>
										  <input class="form-control" id="disabledInput" type="text" placeholder="Sin Grupo" disabled>

                                        </div>
                                      <div class="form-group">
                                            <strong>Imagen principal</strong>
                                            <input type="file">
											<p class="text-muted">Sube una imagen PNG en alta calidad</p>
                                        </div>
                                        <div class="form-group">
                                            <strong>DescripciÃ³n</strong>
                                            <textarea class="form-control" rows="3"></textarea>
                                        </div>
										<h1>Cuestionario</h1>
										   <div class="form-group">
                                            <h4>Pregunta 1</h4>
                                            <input class="form-control" placeholder="Enunciado">
                                        </div>

                                        <div class="form-group">

													<input class="form-control" placeholder="Respuesta A">
													</div>
											<div class="form-group">


													<input class="form-control" placeholder="Respuesta B">
											</div>
											<div class="form-group">

													<input class="form-control" placeholder="Respuesta C">

                                            <label>Respuestas correctas</label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">A
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">B
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">C
                                            </label>
												<div class="form-group">
                                            <strong>Â¿Por quÃ©?</strong>
                                            <textarea class="form-control" rows="3"></textarea>
                                        </div>

                                        </div>
                                        <div class="form-group">
                                            <h4>Pregunta 2</h4>
                                            <input class="form-control" placeholder="Enunciado">
                                        </div>

                                        <div class="form-group">

													<input class="form-control" placeholder="Respuesta A">
													</div>
											<div class="form-group">


													<input class="form-control" placeholder="Respuesta B">
											</div>
											<div class="form-group">

													<input class="form-control" placeholder="Respuesta C">

                                            <label>Respuestas correctas</label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">A
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">B
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">C
                                            </label>
                                            <div class="form-group">
                                            <strong>Â¿Por quÃ©?</strong>
                                            <textarea class="form-control" rows="3"></textarea>
                                        </div>
                                        </div>
										<div class="form-group">
                                            <h4>Pregunta 3</h4>
                                            <input class="form-control" placeholder="Enunciado">
                                        </div>

                                        <div class="form-group">

													<input class="form-control" placeholder="Respuesta A">
													</div>
											<div class="form-group">


													<input class="form-control" placeholder="Respuesta B">
											</div>
											<div class="form-group">

													<input class="form-control" placeholder="Respuesta C">
                                            <label>Respuestas correctas</label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">A
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">B
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="checkbox">C
                                            </label>
                                        <div class="form-group">
                                            <strong>Â¿Por quÃ©?</strong>
                                            <textarea class="form-control" rows="3"></textarea>
                                        </div>
                                        </div>

					<h1>Pregunta de opiniÃ³n</h1>
					<div class="form-group">
                                            <textarea class="form-control" placeholder="Enunciado" rows="2"></textarea>
                                        </div>
					<h1>Reto</h1>
					<div class="form-group">
                                            <textarea class="form-control" rows="3"></textarea>
                                        </div>
										<div class="btn-group btn-group-toggle" data-toggle="buttons">
										  <label class="btn btn-secondary active">
											<input type="radio" name="options" id="option1" autocomplete="off" checked> Privado
										  </label>
										  <label class="btn btn-secondary">
											<input type="radio" name="options" id="option2" autocomplete="off"> PÃºblico
										  </label>
										</div>
                                        
                                    </form>
								<p></p>
								<button type="submit" class="btn btn-info">Guardar cambios</button>
                                <button type="reset" class="btn btn-danger">Borrar</button>
                              </div>
							  
                              <!-- /.col-lg-6 (nested) -->

                              <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
            </div>
            <div class="card-footer small text-muted">Fecha actual...</div>
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
              <span>ecoQUIZ</span>
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
            <h5 class="modal-title" id="exampleModalLabel">Vas a cerrar la sesiÃ³n</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">Ã</span>
            </button>
          </div>
          <div class="modal-body">Seleciona salir si deseas finalizar la sesiÃ³n.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-primary" href="../access/login.html">Salir</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="js/demo/datatables-demo.js"></script>

  </body>

</html>

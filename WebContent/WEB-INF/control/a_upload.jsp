<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<link href="control/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="control/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link href="control/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="control/css/sb-admin.css" rel="stylesheet">
</head>

<body id="page-top" class="sidebar-toggled">
<jsp:include page="/ObtenerDatosQuiz" />
<nav class="navbar navbar-expand navbar-dark bg-dark static-top"> <a class="navbar-brand mr-1" href="ComprobarSeguridad.do?direccion=a_alumno">Alumno</a>
  <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#"><em class="fas fa-bars"></em></button>
  
  <!-- Navbar -->
  <ul class="navbar-nav ml-auto ml-md-0">
    <li class="nav-item dropdown no-arrow mx-1">
      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown"> <a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#">Something else here</a> </div>
    </li>
    <li class="nav-item dropdown no-arrow mx-1">
      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown"> <a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#">Something else here</a> </div>
    </li>
    <li class="nav-item dropdown no-arrow"> <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="fas fa-user-circle fa-fw"></i> </a>
      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Cerrar sesión</a> </div>
    </li>
  </ul>
</nav>
<div id="wrapper"> 
  
  <!-- Sidebar -->
  <ul class="sidebar navbar-nav toggled">
    <li class="nav-item"> <a class="nav-link" href="ComprobarSeguridad.do?direccion=a_alumno"> <em class="fas fa-fw fa-address-card"></em> <span>Info</span> </a> </li>
    <li class="nav-item active"> <a class="nav-link" href="ComprobarSeguridad.do?direccion=a_upload"> <i class="fas fa-fw fa-image"></i> <span>Cartel</span></a> </li>
  </ul>
  <div id="content-wrapper">
    <div class="container-fluid"> 
      
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item"> <a href="alumno.html">Inicio</a> </li>
        <li class="breadcrumb-item active">Cartel</li>
      </ol>
      
      <!-- DataTables Example -->
      <div class="card mb-3">
        <div class="card-header"> <i class="fas fa-image"></i> Administración del cartel</div>
        <div class="card-body">
          <div class="col-lg-12">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="row">
                  <div class="col-lg-6">
                    <form role="form" action="Upload" method="post" enctype="multipart/form-data">
                      <div class="form-group"> <strong>Título</strong>
                        <input class="form-control" name="titulo" value="${Cartel.titulo}" required>
                      </div>
                      <div class="form-group"> <strong>Cartel realizado por el grupo</strong>
                        <input class="form-control" id="disabledInput" type="text" placeholder="Sin Grupo" value="${AlumnoBean.grupo}" disabled>
                      </div>
                      <div class="form-group"> <strong>Imagen principal</strong>
                        <input type="file" name="file" accept=".png" ${requiredImg}>
                        <p class="text-muted">Sube una imagen PNG en alta calidad</p>
                      </div>
                      <div class="form-group"> <strong>Noticia</strong>
                        <input class="form-control" name="noticia" value="${Cartel.noticia}" required>
                        </input>
                      </div>
                      <div class="form-group"> <strong>Enlace</strong>
                        <input class="form-control" name="enlace" value="${Cartel.enlace}" required>
                        </input>
                      </div>
                      <h1>Cuestionario</h1>
                      <h4>Pregunta 1</h4>
                      <div class="form-group"> <strong>Enunciado</strong>
                        
                        <input class="form-control" name="enunciado1" placeholder="Enunciado" value="${Preg1.enunciado}" required>
                      </div>
                      <div class="form-group"> <strong>Respuestas</strong>
                        <input class="form-control" name="pregunta11" placeholder="Respuesta A" value="${Preg1.respuesta1}" required>
                      </div>
                      <div class="form-group">
                        <input class="form-control" name="pregunta12" placeholder="Respuesta B" value="${Preg1.respuesta2}" required>
                      </div>
                      <div class="form-group">
                        <input class="form-control" name="pregunta13" placeholder="Respuesta C" value="${Preg1.respuesta3}" required>
                      </div>
                      <label>Respuestas correctas</label>
                      <label class="checkbox-inline">
                        <input type="checkbox" name="respuesta11" ${correcta11}>
                        A </label>
                      <label class="checkbox-inline">
                        <input type="checkbox" name="respuesta12" ${correcta12}>
                        B </label>
                      <label class="checkbox-inline">
                        <input type="checkbox" name="respuesta13" ${correcta13}>
                        C </label>
                      <div class="form-group"> <strong>Explicación</strong>
                        <input class="form-control" name="explicacion1" value="${Preg1.explicacion}" required>
                        </input>
                      </div>
                      <h4>Pregunta 2</h4>
                      <div class="form-group"> <strong>Enunciado</strong>
                        
                        <input class="form-control" name="enunciado2" placeholder="Enunciado" value="${Preg2.enunciado}" required>
                      </div>
                      <div class="form-group"> <strong>Respuestas</strong>
                        <input class="form-control" name="pregunta21" placeholder="Respuesta A" value="${Preg2.respuesta1}" required>
                      </div>
                      <div class="form-group">
                        <input class="form-control" name="pregunta22" placeholder="Respuesta B" value="${Preg2.respuesta2}" required>
                      </div>
                      <div class="form-group">
                        <input class="form-control" name="pregunta23" placeholder="Respuesta C" value="${Preg2.respuesta3}">
                      </div>
                      <label>Respuestas correctas</label>
                      <label class="checkbox-inline">
                        <input type="checkbox" name="respuesta21" ${correcta21}>
                        A </label>
                      <label class="checkbox-inline">
                        <input type="checkbox" name="respuesta22" ${correcta22}>
                        B </label>
                      <label class="checkbox-inline">
                        <input type="checkbox" name="respuesta23" ${correcta23}>
                        C </label>
                      <div class="form-group"> <strong>Explicación</strong>
                        <input class="form-control" name="explicacion2" value="${Preg2.explicacion}" required>
                        </input>
                      </div>
                      <h4>Pregunta 3</h4>
                      <div class="form-group"> <strong>Enunciado</strong>
                        
                        <input class="form-control" name="enunciado3" placeholder="Enunciado" value="${Preg3.enunciado}" required>
                      </div>
                      <div class="form-group"> <strong>Respuestas</strong>
                        <input class="form-control" name="pregunta31" placeholder="Respuesta A" value="${Preg3.respuesta1}" required>
                      </div>
                      <div class="form-group">
                        <input class="form-control" name="pregunta32" placeholder="Respuesta B" value="${Preg3.respuesta2}" required>
                      </div>
                      <div class="form-group">
                        <input class="form-control" name="pregunta33" placeholder="Respuesta C" value="${Preg3.respuesta3}" required>
                        <label>Respuestas correctas</label>
                        <label class="checkbox-inline">
                          <input type="checkbox" name="respuesta31" ${correcta31}>
                          A </label>
                        <label class="checkbox-inline">
                          <input type="checkbox" name="respuesta32" ${correcta32}>
                          B </label>
                        <label class="checkbox-inline">
                          <input type="checkbox" name="respuesta33" ${correcta33}>
                          C </label>
                        <div class="form-group"> <strong>Explicación</strong>
                          <input class="form-control" name="explicacion3" value="${Preg3.explicacion}" required>
                          </input>
                        </div>
                      </div>
                      <h1>Pregunta de opinión</h1>
                      <div class="form-group">
                        <input class="form-control" placeholder="Enunciado" name="opinion" value="${Cartel.pregunta}" required>
                        </input>
                      </div>
                      <h1>Reto</h1>
                      <div class="form-group">
                        <input class="form-control" name="reto" value="${Cartel.reto}" required>
                        </input>
                      </div>
                      
                      <button type="submit" ${np} class="btn btn-info">Guardar cambios</button>
                    </form>
                    <p></p>
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
          <p class="small text-center text-muted my-5"> </p>
        </div>
        <!-- /.container-fluid --> 
        
        <!-- Sticky Footer -->

      </div>
      <!-- /.content-wrapper --> 
              <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto"> <span>Copyright © Erios 2018</span> </div>
          </div>
        </footer>
    </div>
  </div>
</div>

<!-- /#wrapper --> 

<!-- Scroll to Top Button--> 
<a class="scroll-to-top rounded" href="#page-top"> <i class="fas fa-angle-up"></i> </a> 

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Vas a cerrar la sesión</h5>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">×</span> </button>
      </div>
      <div class="modal-body">Seleciona salir si deseas finalizar la sesión.</div>
      <div class="modal-footer">
        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
        <a class="btn btn-primary" href="CerrarSesion.do">Salir</a> </div>
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

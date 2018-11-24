<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

	<head>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Cuestionario Estadístico</title>

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
		<div class="card mb-3">
			<div class="card-body">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-6">
									<form role="form" action="enviarRespuestas" method="post">
										<input type="hidden" name="idStat" value="${id}">
										<div class="form-group">
											<div class="card-body">
												<h1>Nos interesa tu opinión</h1>
											</div>
											<div class="form-group">
											    <strong>${pregunta}</strong>
											    <textarea class="form-control" name="respuesta" placeholder="Escribe tu respuesta" rows="3" required></textarea>
											</div> 
										</div>
										<div class="form-group">
											<div class="card-body">
												<h1>Te proponemos un reto</h1>
											</div>
											<div class="form-group">
											    <strong>${reto}</strong>
											</div> 
										</div>
										<div class="form-group">
											<div class="card-body">
												<h1>Cuestionario estadístico</h1>
											</div>
										</div>
										<div class="form-group">
											<h4>Rango de edad:</h4>
											<select required class="custom-select" name="edad">
												<option value="">Seleccionar</option>
												<option value="1">Menor de 18</option>
												<option value="2">De 18 a 25</option>
												<option value="3">De 26 a 39</option>
												<option value="4">De 40 a 52</option>
												<option value="4">De 53 a 65</option>
												<option value="4">Mayor de 65</option>
											</select>
										</div>
										<div class="form-group">
											<h4>¿Pertenece a la Universidad de Zaragoza?</h4>
											<select class="custom-select" name="uni" required>
												<option value="">Seleccionar</option>
												<option value="1">No</option>
												<option value="2">Sí, trabajo en la Universidad de Zaragoza</option>
												<option value="3">Sí, estudio en la Universidad de Zaragoza, 													concretamente en la EINA</option>
												<option value="4">Sí, estudio en la Universidad de Zaragoza, en otra 													facultad</option>

											</select>
										</div>

										<!--<div class="form-group">
											<div class="card-body">
												<h1>¿Quieres saber más?</h1>
											</div>
										    
										    <p>Introduce tu correo electrónico (opcional) si quieres seguir informado sobre nuestras propuestas de concienciación sobre el cuidado del medio ambiente.</p>
										    <input class="form-control" placeholder="Ejemplo: usuario@dominio.com">
										</div>-->
										<button class="btn btn-info">
											Enviar y terminar
										</button>
									</form>
								</div>
								<!-- /.col-lg-6 (nested) -->

								<!-- /.col-lg-6 (nested) -->
							</div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

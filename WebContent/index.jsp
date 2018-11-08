<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Multiverse by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html lang="es">
	<head>
		<title>ecoQUIZ</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="shortcut icon" href="favicon.ico" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
						<h1><a href="index.html"><strong>ecoQUIZ</strong> by HSC</a></h1>
						<nav>
							<ul>
								<li><a href="#footer" class="icon fa-info-circle">Acerca de</a></li>
								<li><a href="ComprobarSesion.do"> Iniciar Sesión</a></li>
							</ul>
						</nav>
					</header>

				<!-- Main -->
					<div id="main">
						<c:forEach items="${LISTA}" var="Cartel">
						    <article class="thumb">
						    	<a href="${Cartel.imagen}" class="image"><img src="${Cartel.imagen}" alt="" /></a>
						    	<h2><a href="quiz.html?cartel=${Cartel.ID}" data-toggle="tooltip" title="Â¡Haz clic para ir al cuestionario!">${Cartel.titulo}</a></h2>
						    	<p>${Cartel.noticia}</p>
						    	<a class="btn btn-secondary" href="${Cartel.enlace}">
						    		Haga click aquí para ir a la noticia original
								</a>
							</article>
						</c:forEach>
					</div>

				<!-- Footer -->
					<footer id="footer" class="panel">
						<div class="inner split">
							<div>
								<section>
									<h2>EcoQUIZ</h2>
									<p>EcoQUIZ es una paǵina de cuestinarios sobre temas de actualidad referentes a la concienciación sobre el medio ambiente con la finalidad de ver cuán informada está la población en este aspecto.</p>
								</section>
								<section>
									<h2>Síguenos en...</h2>
									<ul class="icons">
										<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon fa-github"><span class="label">GitHub</span></a></li>
										<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
										<li><a href="#" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
									</ul>
								</section>
								<p class="copyright">
									&copy; Unttled. Design: <a href="http://html5up.net">HTML5 UP</a>.
								</p>
							</div>
							<div>
								<section>
									<h2>Contacta con nosotros</h2>
									<form method="post" action="#">
										<div class="fields">
											<div class="field half">
												<input type="text" name="name" id="name" placeholder="Nombre" />
											</div>
											<div class="field half">
												<input type="text" name="email" id="email" placeholder="Correo Electrónico" />
											</div>
											<div class="field">
												<textarea name="message" id="message" rows="4" placeholder="Mensaje"></textarea>
											</div>
										</div>
										<ul class="actions">
											<li><input type="submit" value="Enviar" class="primary" /></li>
											<li><input type="reset" value="Resetear" /></li>
										</ul>
									</form>
								</section>
							</div>
						</div>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.poptrox.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>

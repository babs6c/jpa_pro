<!DOCTYPE html>
<head>
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.min.css" >
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/style-responsive.css" rel="stylesheet"/>
<!-- font CSS -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="css/font.css" type="text/css"/>
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<script src="js/jquery2.0.3.min.js"></script>
<script src="js/validate.js"></script>
<script src="js/validations.js"></script>
</head>
<body>
<div class="log-w3">
<div class="w3layouts-main">
	<h2>Se connecter</h2>
	<c:if test="${ !empty problemes }"><span class="erreur">${ problemes }</span></c:if>
		<form action="Login" id="signin" method="post">
			<input type="email" value="<c:choose><c:when test="${ !empty cookie_email }">${ cookie_email }</c:when><c:otherwise><c:out value="${ membre.erreurs.emailvalue }"/></c:otherwise></c:choose>" 
			class="ggg" name="email" placeholder="E-MAIL" >
			<c:if test="${ !empty membre.erreurs.email }">
			<span class="erreur">${ membre.erreurs.email }</span>
			</c:if>
			<input type="password" class="ggg" name="pass" placeholder="MOT DE PASSE" >
			<c:if test="${ !empty membre.erreurs.pass }">
			<span class="erreur">${ membre.erreurs.pass }</span>
			</c:if>
			<!-- <span><input type="checkbox" />Remember Me</span>
			<h6><a href="#">Forgot Password?</a></h6> -->
				<div class="clearfix"></div>
				<input type="submit" value="Connexion" name="login">
				<c:if test="${ !empty membre.erreurs.credentials }">
				<span class="erreur" style="text-align:center;">${ membre.erreurs.credentials }</span>
				</c:if>
		</form>
		
		<p>Pas encore inscrit ?<a href="<c:url value="/Inscription"/>">
		Cr�er un compte</a></p>
</div>
</div>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.dcjqaccordion.2.7.js"></script>
<script src="js/scripts.js"></script>
<script src="js/jquery.slimscroll.js"></script>
<script src="js/jquery.nicescroll.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="js/jquery.scrollTo.js"></script>
</body>
</html>

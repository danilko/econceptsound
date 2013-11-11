<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
<link rel="stylesheet" media="screen" href="css/bootstrap-theme.min.css">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link rel="stylesheet" href="css/main.css">

<script src="js/vendor/jquery-1.10.2.min.js"></script>
<script src="js/vendor/jquery.form.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="' + document.URL + 'js/vendor/jquery-1.10.2.min.js"><\/script>')
</script>

<script src="js/vendor/bootstrap.min.js"></script>
<script>
	var _gaq = [ [ '_setAccount', 'UA-XXXXX-X' ], [ '_trackPageview' ] ];
	(function(d, t) {
		var g = d.createElement(t), s = d.getElementsByTagName(t)[0];
		g.src = ('https:' == location.protocol ? '//ssl' : '//www')
				+ '.google-analytics.com/ga.js';
		s.parentNode.insertBefore(g, s)
	}(document, 'script'));
</script>
<script src="js/services.js"></script>
</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse"></button>
				<a class="navbar-brand" href="#">Econcept Sound</a>
			</div>
			<!--/.nav-collapse -->

		</div>
	</div>
	<div class="container">
		<div id="services">
			
			
		</div>

		<hr>

		<footer>
			<p>ECONCEPT Sound 2013</p>
		</footer>
	</div>
	<!-- /container -->


	 <div class="modal fade" id="divTrackModal" role="dialog"
		aria-labelledby="divUserModalLabel" tabindex="-1"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Sound</h4>
				</div>
				<div class="modal-body">
					<br /> <label class="control-label" for="txtModalLoginUser">User
						Name</label>
					<div class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span> <input type="text"
							class="form-control" id="txtModalLoginUser"
							placeholder="Type your username">
					</div>
					<br />
					<label class="control-label" for="txtModalLoginPassword">Password</label>
					<div class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <input type="password"
							class="form-control" id="txtModalLoginPassword"
							placeholder="Type your password">
					</div>
					<br />
					<label class="control-label" for="txtModalLoginLastName">Last
						Name</label> <input type="text" class="form-control"
						id="txtModalLoginLastName" placeholder="Type your last name">
					<br />
					<label class="control-label" for="txtModalLoginFirstName">First
						Name</label> <input type="text" class="form-control"
						id="txtModalLoginFirstName" placeholder="Type your first name">
					<br />
					<label class="control-label" for="txtModalLoginEmail">Email</label>
					<div class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-envelope"></i></span> <input type="text"
							class="form-control" id="txtModalLoginEmail" type="text"
							placeholder="Type your email">
					</div>
					
				</div>
				
			</div>
		</div>
	</div>


</body>
</html>


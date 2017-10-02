<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>$Title$</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/flat-ui/2.3.0/css/flat-ui.css"
	rel="stylesheet" />
	
	
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/jquery-ui.css" rel="stylesheet" />
<link href="css/font-awesome.css" rel="stylesheet" />



<link href="css/style.css" rel="stylesheet" />
<link href="css/gridstack.css" rel="stylesheet" />
<script src="js/jquery-3.1.1.js" type="application/javascript">
	 
</script>
<script src="js/bootstrap.js" type="application/javascript">
	 
</script>
<script src="js/bootstrap-filestyle.js" type="application/javascript">
	 
</script>
<script src="js/jquery-ui.js" type="application/javascript">
	 
</script>
<script src="js/underscore.js" type="application/javascript">
	 
</script>

<script
	src="https://cdn.jsdelivr.net/lodash/4.17.4/lodash.js"
	type="text/javascript"> </script>
	
<script src="js/gridstack.js" type="application/javascript">
	 
</script>
<script src="js/gridstack.jQueryUI.js" type="application/javascript">
	 
</script>

</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-fixed-top .navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="http://">Poster Maker</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="http://metnetdb.org/pmr">&copy; Iowa State
							University</a></li>
				</ul>
				<ul class=" nav navbar-nav pull-right">
					<li><a href="Login.php">Login</a></li>
					<li><a href="Register.php">Register</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="fileupload-widget">
	    <h1>File Upload</h1>
	    <form method="post" action="UploadServlet"
	        enctype="multipart/form-data">
	        <div class="fileupload-input">
	        	<input type="file" name="file" id="file" class="filestyle" data-buttonName="btn-primary">
	        	
	        </div>
				<button type="submit" class="btn btn-md btn-sample" value="Upload">Upload File </button>
	         

	    </form>
	</div>
</body>
</html>

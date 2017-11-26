<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<base href="<%=basePath%>">
		<title>慧眼代理商登录</title>
		<!-- Loading Bootstrap -->
		<link href="resources/Flat-UI-master/dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<!-- Loading Flat UI -->
	 	<link href="resources/Flat-UI-master/dist/css/flat-ui.min.css" rel="stylesheet">
	    <link href="resources/Flat-UI-master/docs/assets/css/demo.css" rel="stylesheet">
		<link rel="shortcut icon" href="favicon.ico">
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
		<!--[if lt IE 9]>
		<script src="resources/Flat-UI-master/dist/js/vendor/html5shiv.js"></script>
		<script src="resources/Flat-UI-master/dist/js/vendor/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
		<header class="navbar navbar-inverse navbar-static-top" role="banner">
		  	<div class="navbar-header">
		      	<a href="javascript:;" class="navbar-brand">慧眼代理商登录</a>
		    </div>
		</header>
		
		<div class="col-xs-12">
			<form class="login-form"
				action="agent/signinAction" method="post">
				
	            <div class="form-group">
	              <input type="text" id="username" name="username" class="form-control login-field" 
	              	value="" placeholder="username" required autofocus />
	              <label class="login-field-icon fui-user" for="login-name"></label>
	            </div>
	
	            <div class="form-group">
	              <input type="password" id="password" name="password" class="form-control login-field" 
	              	value="" placeholder="password" required/>
	              <label class="login-field-icon fui-lock" for="login-pass"></label>
	            </div>
	
	            <div class="form-group">
                     <input type="submit" class="btn btn-info btn-block" value="登 录"/>
                </div>
          	</form>
		</div>
	
		<script src="resources/Flat-UI-master/dist/js/vendor/jquery.min.js"></script>
	    <script src="resources/Flat-UI-master/dist/js/flat-ui.min.js"></script>
	    <script src="resources/Flat-UI-master/docs/assets/js/application.js"></script>
	    <script src="resources/js/messager.js"></script>
	    <script src="resources/js/jquery.form.min.js"></script>
	    <script src="resources/js/sha1.js"></script>
	    <script type="text/javascript">
	    $(function() {
			$('form').ajaxForm({
				beforeSerialize: function() {
					$('#password').val(CryptoJS.SHA1($('#password').val()));
				},
				dataType: 'json',
			    success: function(data) {
			    	if(data == 0) {
    					messager(' 用户名不存在或密码错误 ', 'danger', 1600);
    				} else {
    					location = 'agent/home';
    				}
			    }
			});
		});
	    </script>
	</body>
</html>

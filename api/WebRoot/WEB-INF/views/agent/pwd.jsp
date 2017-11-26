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
		<title>密码修改</title>
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
		      	<a href="javascript:;" class="navbar-brand">密码修改</a>
		    </div>
		    <nav id="bs-navbar" class="navbar-collapse">
		      	<ul class="nav navbar-nav">
					<c:forEach var="menu" items="${menuList }" varStatus="s">
						<li <c:if test="${s.index == 1 }">class="active"</c:if>>
							<a href="${menu.url }">${menu.title }</a>
						</li>
					</c:forEach>
		      	</ul>
		      	<ul class="nav navbar-nav pull-right">
					<li><a href="javascript:;">你好, ${username }</a></li>
					<li><a href="agent/signout" title="安全退出"><i class="glyphicon glyphicon-off"></i></a></li>
		      	</ul>
		    </nav>
		</header>
		
		<div class="col-xs-12">
			<form class="login-form"
				action="agent/pwdAction" method="post">

	            <div class="form-group">
	            	<label>原始密码</label>
	              	<input type="password" id="passwdOri" name="passwdOri" class="form-control login-field" 
	              		placeholder="请输入原始密码" required autofocus/>
	            </div>
	            
	            <div class="form-group">
	            	<label>输入新密码</label>
	              	<input type="password" id="passwd" name="passwd" class="form-control login-field" 
	              		placeholder="请输入新的密码" required />
	            </div>
	            
	            <div class="form-group">
	            	<label>再次输入新密码</label>
	              	<input type="password" id="passwdAg" name="passwdAg" class="form-control login-field" 
	              		placeholder="请再次输入新的密码" required />
	            </div>
	
	            <div class="form-group">
                     <input type="submit" class="btn btn-info btn-block" value="提交修改" />
                </div>
          	</form>
		</div>
	
		<script src="resources/Flat-UI-master/dist/js/vendor/jquery.min.js"></script>
	    <script src="resources/Flat-UI-master/dist/js/flat-ui.min.js"></script>
	    <script src="resources/Flat-UI-master/docs/assets/js/application.js"></script>
	    <script src="resources/js/jquery.form.min.js"></script>
	    <script src="resources/js/messager.js"></script>
	    <script src="resources/js/sha1.js"></script>
	    <script type="text/javascript">
	    $(function() {
	    	$('form').submit(function() {
	    		var passwdOri = $('#passwdOri').val();
	    		var passwd = $('#passwd').val();
	    		var passwdAg = $('#passwdAg').val();
	    		if(passwd != passwdAg) {
	    			messager(' 两次输入的新的密码不一致 ', 'danger', 1600);
	    		} else if(passwd == passwdOri) {
	    			messager(' 新密码不能重复原始密码 ', 'danger', 1600);
	    		} else {
	    			// submit the form
		    	    $(this).ajaxSubmit({
		    	    	beforeSerialize: function() {
							$('#passwdOri').val(CryptoJS.SHA1(passwdOri));
							$('#passwd').val(CryptoJS.SHA1(passwd));
						},
						dataType: 'json',
					    success: function(data) {
					    	if(data == -2) {
					    		messager(' 原始密码错误,请重新输入 ', 'danger', 1600);
					    		$('form').resetForm();
					    	} else if(data == 0) {
		    					danger();
		    					$('form').resetForm();
		    				} else {
		    					success();
		    				}
					    }
		    	    });
	    		}
	    	    // return false to prevent normal browser submit and page navigation
	    	    return false;
	    	});
	    });
	    </script>
	</body>
</html>

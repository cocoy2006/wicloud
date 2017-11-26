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
		<title>代理商录入</title>
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
			      <a href="javascript:;" class="navbar-brand">慧眼</a>
		    </div>
		    <nav id="bs-navbar" class="navbar-collapse">
			    <ul class="nav navbar-nav">
			        <c:forEach var="menu" items="${menuList }" varStatus="s">
						<li <c:if test="${s.index == 3 }">class="active"</c:if>>
							<a href="${menu.url }">${menu.title }</a>
						</li>
					</c:forEach>
			    </ul>
			    <ul class="nav navbar-nav pull-right">
					<li><a href="javascript:;">你好, ${username }</a></li>
					<li><a href="user/signout" title="安全退出"><i class="glyphicon glyphicon-off"></i></a></li>
		      	</ul>
		    </nav>
		</header>
		
		<div class="col-xs-12">
			<form class="login-form"
				action="user/agent/addAction" method="post">
				
	            <div class="form-group">
	            	<label>登录名</label>
	              	<input type="text" id="userName" name="userName" class="form-control login-field" 
	              		value="" placeholder="登录用户名" required/>
	            </div>
	            
	            <div class="form-group">
	            	<label>登录密码</label>
            		<input type="text" id="passwd" name="passwd" class="form-control login-field" 
	              		value="" placeholder="登录密码, 默认111111" />
		        </div>
	            
	            <div class="form-group">
	            	<label>商铺名称</label>
	              	<input type="text" id="shopName" name="shopName" class="form-control login-field" 
	              		value="" placeholder="商铺名称" />
	            </div>
	            
	            <div class="form-group">
	            	<label>商铺地址</label>
	              	<input type="text" id="shopadd" name="shopadd" class="form-control login-field" 
	              		value="" placeholder="商铺地址" />
	            </div>
	            
				<div class="form-group">
	            	<label>联系方式</label>
				  	<input type="text" id="contact" name="contact" class="form-control login-field" 
				  		value="" placeholder="联系方式">
				</div>
	            
	            <div class="form-group">
                     <input type="submit" class="btn btn-info btn-block" value="提交录入"/>
                </div>
          	</form>        	
		</div>
	
		<script src="resources/Flat-UI-master/dist/js/vendor/jquery.min.js"></script>
	    <script src="resources/Flat-UI-master/dist/js/flat-ui.min.js"></script>
	    <script src="resources/Flat-UI-master/docs/assets/js/application.js"></script>
	    <script src="resources/js/jquery.form.min.js"></script>
	    <script src="resources/js/sha1.js"></script>
	    <script src="resources/js/messager.js"></script>
	    <script type="text/javascript">
	    $(function() {
			$('form').ajaxForm({
				beforeSerialize: function() {
					var passwd = $('#passwd').val();
					if(!passwd) {
						passwd = '111111';
					}
					$('#passwd').val(CryptoJS.SHA1(passwd));
				},
				dataType: 'json',
			    success: function(data) {
			    	if(data == -1) {
    					messager(' 登录名已存在, 请重新输入. ', 'danger', 1600);
    					$('#userName').val('');
    				} else if(data == 0) {
    					danger();
    				} else {
    					success();
    				}
			    }
			});
		});
	    </script>
	</body>
</html>

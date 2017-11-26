<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>我的慧眼</title>
<link rel="stylesheet" href="resources/newui/bootstrap.min.css">
<link rel="stylesheet" href="resources/newui/report.css">
<style>
#items {
	width: 100%;
	height: 100%;
	overflow: hidden;
	background: #32A7B8;
	position: relative;
	z-index: 10;
	min-height: 850px;
}

.cont-area {
	position: absolute;
	left: 0;
	top: 15%;
	padding: 0 20px;
	width: 100%;
	text-align: center;
	height: auto !important;
	z-index: 30;
}

.banner-area {
	text-align: center;
}

.banner-area .slides {
	padding: 0;
	margin: 0;
	list-style: none;
}

.banner-area .slides li {
	padding: 0;
	margin: 0;
	z-index: 30;
}

.cont-area a {
	color: #fff;
}

.banner-btm-img {
	padding: 0;
	margin: 0;
	text-align: center;
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	z-index: 10;
}

.banner-btm-img img {
	width: 100%;
	max-width: 1100px;
}

.large-button {
	border-radius: 5px;
	border: none;
	padding: 10px 28px;
	font-size: 22px;
	background-color: #0A6875;
}

.search-wraper {
	margin-left: auto;
	margin-right: auto;
	max-width: 680px;
	margin-top: 24px;
	margin-bottom: 28px;
}

.search-wraper .search {
	-webkit-box-shadow: none;
	box-shadow: none;
	font-size: 16px;
	padding: 13px 30px;
	border-radius: 0;
	height: auto;
	text-align: center;
	border-color: transparent;
}
</style>
</head>

<body class="index_action">
	<nav class="navbar nav_single_btn">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:;"> 我的慧眼 </a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="index" class="btn btn-default ghost navbar-btn">返回</a></li>
			</ul>

		</div>
	</nav>
	<div class="wrapper">
		<div id="home">
			<div id="items">
				<div class="cont-area">
					<div class="container banner-area">
						<img src="resources/newui/my_logo.jpg" width="100" height="100">
						<ul class="slides clearfix">
							<li>
								<form class="search-wraper" role="search">
									<div class="form-group">
										<input type="text" id="code" name="code" class="form-control search clearable"
											placeholder="我的慧眼口令" autocomplete="off">
									</div>
								</form>
								<div>
									<a class="large-button" href="javascript:;" onclick="whisper()">确定</a>
								</div> <br> <br>
							</li>
						</ul>
					</div>
				</div>

				<div class="banner-btm-img">
					<img
						src="https://cdn.mailblast.io/assets/buildings-0a6a185ee753f3d1ebe524e823d607eeca456157112d59dbef7736bf686e851c.png"
						alt="Buildings">
				</div>
			</div>
		</div>
	</div>
	<footer class="footer">
		<div class="container">
			<div class="text-center">
				<div class="pull-center">
					<span>© 2016-2017 慧眼数据</span><br> <span>Powered by <a
						href="javascript:;" target="_blank">华录北邮</a>
				</div>
			</div>
		</div>
	</footer>
	<script src="resources/newui/main.min.js"></script>
	<script src="resources/js/messager.js"></script>
	<script type="text/javascript">
		    function whisper() {
		    	var code = $('#code').val();
		    	if(!code) {
	    			messager(' 口令不能为空. ', 'danger', 1600);
	    			return false;
	    		}
		    	$.ajax({
		    		url: 'myAction',
		    		data: $('form').serialize(),
		    		dataType: 'json',
		    		type: 'POST',
		    		success: function(data) {
		    			if(data == 1) {
		    				success(' 配对成功，正在准备数据... ', 0);
		    				setTimeout(function() {
		    					location = 'index';
		    				}, 1600);
		    			} else {
		    				messager(' 口令不正确,请重新输入. ', 'danger', 1600);
		    			}
		    		}
		    	});
		    }
	    </script>
</body>
</html>

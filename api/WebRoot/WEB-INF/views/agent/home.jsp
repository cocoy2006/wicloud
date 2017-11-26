<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
						<li <c:if test="${s.index == 0 }">class="active"</c:if>>
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
			<!-- SEARCH -->
			<div class="input-group" style="margin-bottom: 15px;">
			  <input type="text" id="searchString" class="form-control" value="${searchString }"
			  	placeholder="慧眼编号或其他信息" aria-describedby="addon">
			  <span class="input-group-addon" id="addon" onclick="search()">
			  	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
			  </span>
			</div>
			<!-- MAIN -->
			<blockquote>
				正常慧眼&nbsp;<span class="badge">${fn:length(msicList)}</span>
			</blockquote>
			<c:if test="${fn:length(msicList) > 0}">
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			  		<c:forEach var="msic" items="${msicList }" varStatus="s">
			  			<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="heading${msic.mon.monid }">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#accordion" 
								    	href="#collapse${msic.mon.monid }" aria-expanded="true" aria-controls="collapse${msic.mon.monid }">
								        <span class="badge badge-default">${s.count }/${fn:length(msicList)}&nbsp;</span>
								        <span class="label label-success">${fn:toUpperCase(msic.mon.monid)}</span>
									</a>
									<span class="pull-right">
								        <button class="btn btn-link btn-xs" onclick="recover('${msic.mon.monid }')">删除</button>
							        </span>
								</h4>
						    </div>
						    <div id="collapse${msic.mon.monid }" class="panel-collapse collapse <c:if test="${s.index == 0 }">in</c:if>" 
						    	role="tabpanel" aria-labelledby="heading${msic.mon.monid }">
						    	<div class="panel-body">
						    		<p><span class="label label-default">商铺性质</span>&nbsp;${msic.item.name } - ${msic.subItem.name }</p>
						    		<p><span class="label label-default">商铺名称</span>&nbsp;${msic.mon.monname }</p>
						    		<p><span class="label label-default">商铺地址</span>&nbsp;${msic.mon.monAdd }</p>
						    		<p><span class="label label-default">距离</span>&nbsp;${msic.mon.wallDis }米</p>
						    		<p><span class="label label-default">慧眼口令</span>&nbsp;${msic.whisper.code }</p>
									<div class="btn-group btn-group-justified" role="group" aria-label="...">
								  		<a href="javascript:;" class="btn btn-success btn-xs" 
								  			onclick="look('${msic.mon.monid }')" role="button">查看</a>
										<a href="javascript:;" class="btn btn-info btn-xs" 
											onclick="lookAll('${msic.mon.monid }')" role="button">查看大屏</a>
										<a href="javascript:;"  class="btn btn-default btn-xs"
											onclick="edit('${msic.mon.monid }')" role="button">编辑</a>
									</div>
								</div>
						    </div>
					  	</div>
			  		</c:forEach>
				</div>
			</c:if>
			<blockquote>
				不正常慧眼&nbsp;<span class="badge">${fn:length(aMsicList)}</span>
			</blockquote>
			<c:if test="${fn:length(aMsicList) > 0}">
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			  		<c:forEach var="msic" items="${aMsicList }" varStatus="s">
			  			<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="heading${msic.mon.monid }">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#accordion" 
								    	href="#collapse${msic.mon.monid }" aria-expanded="true" aria-controls="collapse${msic.mon.monid }">
								        <span class="badge badge-default">${s.count }/${fn:length(aMsicList)}&nbsp;</span>
								        <span class="label label-danger">${fn:toUpperCase(msic.mon.monid)}</span>
									</a>
									<span class="pull-right">
								        <button class="btn btn-link btn-xs" onclick="recover('${msic.mon.monid }')">删除</button>
							        </span>
								</h4>
						    </div>
						    <div id="collapse${msic.mon.monid }" class="panel-collapse collapse <c:if test="${s.index == 0 }">in</c:if>" 
						    	role="tabpanel" aria-labelledby="heading${msic.mon.monid }">
						    	<div class="panel-body">
						    		<p><span class="label label-default">商铺性质</span>&nbsp;${msic.item.name } - ${msic.subItem.name }</p>
						    		<p><span class="label label-default">商铺名称</span>&nbsp;${msic.mon.monname }</p>
						    		<p><span class="label label-default">商铺地址</span>&nbsp;${msic.mon.monAdd }</p>
						    		<p><span class="label label-default">距离</span>&nbsp;${msic.mon.wallDis }米</p>
						    		<p><span class="label label-default">慧眼口令</span>&nbsp;${msic.whisper.code }</p>
						    		<div class="btn-group btn-group-justified" role="group" aria-label="...">
										<a href="javascript:;"  class="btn btn-default btn-xs"
											onclick="edit('${msic.mon.monid }')" role="button">编辑</a>
									</div>
								</div>
						    </div>
					  	</div>
			  		</c:forEach>
				</div>
			</c:if>
		</div>
	
		<script src="resources/Flat-UI-master/dist/js/vendor/jquery.min.js"></script>
	    <script src="resources/Flat-UI-master/dist/js/flat-ui.min.js"></script>
	    <script src="resources/Flat-UI-master/docs/assets/js/application.js"></script>
	    <script src="resources/js/jquery.form.min.js"></script>
	    <script src="resources/js/messager.js"></script>
	    <script type="text/javascript">
	    function look(monid) {
	    	$.ajax({
	    		url: 'user/look',
	    		async: false,
	    		data: 'monid=' + monid,
	    		dataType: 'json',
	    		success: function(data) {
	    			window.open('index', '_blank');
	    		}
	    	});
	    }
	    
	    function lookAll(monid) {
	    	$.ajax({
	    		url: 'user/lookAll',
	    		async: false,
	    		data: 'monid=' + monid,
	    		dataType: 'json',
	    		success: function(data) {
	    			window.open('all', '_blank');
	    		}
	    	});
	    }
	    
	    function edit(monid) {
	    	location = 'agent/edit?monid=' + monid;
	    }

	    function search() {
	    	location = 'agent/home?searchString=' + $('#searchString').val();
	    }
	    
	    function recover(monid) {
	    	if(confirm('删除后无法恢复,确定删除?')) {
	    		$.ajax({
		    		url: 'user/recoverAction',
		    		data: 'monid=' + monid,
		    		dataType: 'json',
		    		type: 'POST',
		    		success: function(data) {
		    			if(data == 0) {
		    				danger();
	    				} else {
	    					success();
	    				}
		    		}
		    	});
	    	}
	    }
	    </script>
	</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<title>${monid }</title>
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
					<li><a href="user/signout" title="安全退出"><i class="glyphicon glyphicon-off"></i></a></li>
		      	</ul>
		    </nav>
		</header>
		
		<div class="col-xs-12">
			<c:if test="${msi != null}">
				<form class="login-form"
					action="user/editAction" method="post">
		            
		            <div class="form-group">
		            	<label>慧眼编号</label>
		              	<input type="text" id="monid" name="monid" class="form-control login-field" 
		              		value="${fn:toUpperCase(msi.mon.monid)}" readonly="readonly" style="color: #34495e;"/>
		            </div>
		            
		            <div class="form-group">
		            	<label>商铺性质</label>
	            		<select class="form-control" name="itemList" onchange="loadSubItem(this)">
				            <option value="0">请选择行业类别</option>
				            <c:forEach var="item" items="${itemList }" varStatus="s">
				            	<option value="${item.id }" <c:if test="${item.id == msi.item.id }">selected="selected"</c:if>>
				            		${item.name }</option>
				            </c:forEach>
				        </select>
			        </div>
			        <div class="form-group">
	            		<select class="form-control" id="subItem" name="subItem">
				            <option value="0" selected="selected">请选择商铺性质</option>
				        </select>
			        </div>
		            
		            <div class="form-group">
		            	<label>商铺名称</label>
		              	<input type="text" id="monName" name="monName" class="form-control login-field" 
		              		value="${msi.mon.monname }" placeholder="商铺名称" />
		            </div>
		            
		            <div class="form-group">
		            	<label>商铺地址</label>
		              	<input type="text" id="monAdd" name="monAdd" class="form-control login-field" 
		              		value="${msi.mon.monAdd }" placeholder="商铺地址" />
		            </div>
		            
		            <div class="form-group">
		            	<label>慧眼距离(米)</label>
					  	<input type="text" id="wallDis" name="wallDis" class="form-control login-field" 
					  		value="${msi.mon.wallDis }" placeholder="慧眼距离店铺门口的距离">
					</div>
					
					<div class="form-group">
		            	<label>慧眼口令</label>
					  	<input type="text" id="code" name="code" class="form-control login-field" 
					  		value="${msi.whisper.code }" placeholder="慧眼口令">
					</div>
		            
		            <div class="form-group">
	                     <input type="submit" class="btn btn-info btn-block" value="提交修改"/>
	                </div>
	          	</form>
			</c:if>
		</div>
	
		<script src="resources/Flat-UI-master/dist/js/vendor/jquery.min.js"></script>
	    <script src="resources/Flat-UI-master/dist/js/flat-ui.min.js"></script>
	    <script src="resources/Flat-UI-master/docs/assets/js/application.js"></script>
	    <script src="resources/js/jquery.form.min.js"></script>
	    <script src="resources/js/messager.js"></script>
	    <script src="resources/js/map.js"></script>
	    <script src="resources/js/item.js"></script>
	    <script type="text/javascript">
	    var itemArray, subItemMap;
	    $(document).ready(function() {
	    	itemArray = initItemArray("<%=basePath%>user/item");
	    	subItemMap = initSubItemMap("<%=basePath%>user/subItem");
	    	initSubItem();
	    	
	    	$('form').ajaxForm({
				dataType: 'json',
			    success: function(data) {
			    	if(data == -2) {
    					messager(' 慧眼口令已存在. ', 'danger', 1600);
    				} else if(data == 0) {
    					danger();
    				} else {
    					location = 'user/home';
    				}
			    }
			});
	    });
	    
	    function initSubItem() {
	    	var itemId = "${msi.item.id }";
	    	var option = '<option value="0">请选择商铺性质</option>';
	    	if(itemId && itemId != "0") {
	    		var subItemArray = subItemMap.get(itemId);
	    		$.each(subItemArray, function(index, subItem) {
					option += '<option value="' + subItem.id + '" ';
					if(subItem.id == "${msi.subItem.id }") {
						option += 'selected="selected"';
					}
					option += '>' + subItem.name + '</option>';
				});
	    	}
	    	$("#subItem").html(option);
	    }
	    
	    function loadSubItem(that) {
	    	var itemId = $(that).val();
	    	var option = '<option value="0" selected="selected">请选择商铺性质</option>';
	    	if(itemId != 0) {
	    		var subItemArray = subItemMap.get(itemId);
	    		$.each(subItemArray, function(index, subItem) {
					option += '<option value="' + subItem.id + '">' + subItem.name + '</option>';
				});
	    	}
	    	$("#subItem").html(option);
	    }
	    /*
	    function edit() {
    		var monid = $("#monid").val();
    		var monName = $("#monName").val();
    		var monAdd = $("#monAdd").val();
    		var subItem = $("#subItem").val();
    		var wallDis = $("#wallDis").val();
    		if(!monid || !monName || !monAdd || !wallDis || !subItem) {
    			danger(" 信息不能为空.");
    			return false;
    		}
    		if(isNaN(wallDis)) {
    			danger(" 距离应该为正整数.");
    			return false;
    		}
    		$.ajax({
    			url: "user/editAction?subItem=" + subItem,
    			dataType: "json",
    			data: $(".login-form").serialize(),
    			type: "POST",
    			success: function(data) {
    				if(data == 0) {
    					danger();
    				} else {
    					success();
    					setTimeout(function() {
    						location = "user/home";
    					}, 2000);
    				}
    			}
    		});
    	}
	    */
	    </script>
	</body>
</html>

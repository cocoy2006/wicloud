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
		<title>代理商管理</title>
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
						<li <c:if test="${s.index == 2 }">class="active"</c:if>>
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
			<!-- MAIN -->
			<c:if test="${fn:length(agentList) > 0}">
				<blockquote>
					代理商列表
				</blockquote>
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			  		<c:forEach var="agent" items="${agentList }" varStatus="s">
			  			<div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="heading${agent.id }">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#accordion" 
								    	href="#collapse${agent.id }" aria-expanded="true" aria-controls="collapse${agent.id }">
								        <span class="badge badge-default">${s.count }/${fn:length(agentList)}&nbsp;</span>
								        <span class="label label-success">${agent.shopName }</span>
									</a>
								</h4>
						    </div>
						    <div id="collapse${agent.id }" class="panel-collapse collapse <c:if test="${s.index == 0 }">in</c:if>" 
						    	role="tabpanel" aria-labelledby="heading${agent.id }">
						    	<div class="panel-body">
						    		<p><span class="label label-default">代理商地址</span>&nbsp;${agent.shopadd }</p>
						    		<p><span class="label label-default">联系方式</span>&nbsp;${agent.contract }</p>
									<div class="btn-group btn-group-justified" role="group" aria-label="...">
								  		<a href="javascript:;" class="btn btn-success btn-xs"
											onclick="openAssign('${agent.userName }')" role="button">分配慧眼</a>
										<a href="javascript:;" class="btn btn-default btn-xs"
											onclick="edit('${agent.userName }')" role="button">编辑代理商</a>
										<a href="javascript:;" class="btn btn-danger btn-xs"
											onclick="remove('${agent.userName }')" role="button">删除代理商</a>
									</div>
								</div>
						    </div>
					  	</div>
			  		</c:forEach>
				</div>
			</c:if>
			<c:if test="${fn:length(agentList) == 0}">
				<blockquote>
					暂无代理商
				</blockquote>
			</c:if>
		</div>
	
		<!-- 选择未分配慧眼的模态框 --> 
	    <div id="monSelectionModal" class="modal fade" role="dialog" aria-hidden="true">
	         <div class="modal-dialog modal-lg">
	              <div class="modal-content">
	                   <div class="modal-header">
	                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                        <h4 class="modal-title">请选择慧眼</h4>
	                   </div>
	                   <div class="modal-body">
	                        <form action="user/assignMonAction" method="post">
	                        	<input type="hidden" id="username" name="username">
	                        	<table class="table table-striped table-bordered" cellspacing="0" width="100%">
					                <thead>
					                    <tr>
					                    	<th>
					                    		<input type="checkbox" id="checkboxAll"
				            						onclick="checkAll(this, 'ids')">
					                    	</th>
					                    	<th>慧眼编号</th>
					                    	<th>商铺名称</th>
					                        <th>商铺地址</th>
					                        <th>慧眼距离</th>
					                    </tr>
					                </thead>
					                <tbody>
					                	<c:if test="${fn:length(unassignedList) == 0}">
					                		<tr>
					                			<td colspan="6" class="text-center">无可用慧眼</td>
					                		</tr>
					                	</c:if>
					                	<c:forEach var="monindex" items="${unassignedList }" varStatus="s">
					                		<tr>
						                		<td>
						                			<input type="checkbox" name="ids" value="${monindex.monid}"
		                								onclick="cancelCheckAll('checkboxAll', 'ids')"/>
						                		</td>
						                		<td>${monindex.monid }</td>
						                		<td>${monindex.monname }</td>
						                		<td>${monindex.monAdd }</td>
						                		<td>${monindex.wallDis }米</td>
						                	</tr>
					                	</c:forEach>
					                </tbody>
					           </table>
					           <c:if test="${fn:length(unassignedList) > 0}">
					           		<div class="form-group">
				                   		<input type="submit" class="btn btn-info btn-block" value="提交分配" />
				               		</div>
		                	   </c:if>
	                        </form>
	                   </div>
	              </div><!-- /.modal-content -->
	         </div><!-- /.modal-dialog -->
	    </div><!-- /.modal -->
    
		<script src="resources/Flat-UI-master/dist/js/vendor/jquery.min.js"></script>
	    <script src="resources/Flat-UI-master/dist/js/flat-ui.min.js"></script>
	    <script src="resources/Flat-UI-master/docs/assets/js/application.js"></script>
	    <script src="resources/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>
    	<script src="resources/DataTables-1.10.13/media/js/dataTables.bootstrap.min.js"></script>
    	<script src="resources/js/jquery.form.min.js"></script>
	    <script src="resources/js/messager.js"></script>
	    <script src="resources/js/checkbox.js"></script>
	    <script type="text/javascript">
	    $(function() {
			$('table').DataTable({
	    		"language": {
	                "url": "resources/DataTables-1.10.13/chinese.json"
	            },
	            "ordering": false
	    	});
			
			$('form').ajaxForm({
				dataType: 'json',
			    success: function(data) {
			    	if(data == 0) {
			    		danger();
    				} else {
    					success();
    				}
			    }
			});
		});
	    
	    function openAssign(username) {
	    	$('#username').val(username);
	    	$('#monSelectionModal').modal();
	    }
	    
	    function edit(userName) {
	    	location = 'user/agent/edit?userName=' + userName;
	    }
	    
	    function remove(username) {
	    	if(confirm('删除后无法恢复,确定删除?')) {
	    		$.ajax({
	    			url: 'user/agent/remove',
	    			dataType: 'json',
	    			data: 'userName=' + username,
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

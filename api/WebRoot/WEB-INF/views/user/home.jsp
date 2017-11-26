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
		<title>慧眼管理</title>
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
								        <c:if test="${msic.mon.userName == 'huimei'}">
								        	<button class="btn btn-link btn-xs" onclick="openAssign('${msic.mon.monid }')">分配</button>
								        </c:if>
								        <c:if test="${msic.mon.userName != 'huimei'}">
								        	已分配&nbsp;${msic.mon.userName }&nbsp;
								        	<button class="btn btn-link btn-xs" onclick="recover('${msic.mon.monid }')">回收</button>
								        </c:if>
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
								  		<a href="javascript:;"  class="btn btn-success btn-xs" 
								  			onclick="look('${msic.mon.monid }')" role="button">查看</a>
										<a href="javascript:;"  class="btn btn-info btn-xs" 
											onclick="lookAll('${msic.mon.monid }')" role="button">查看大屏</a>
										<!-- <a href="javascript:;"  class="btn btn-default btn-xs"
											onclick="edit('${msic.mon.monid }')" role="button">编辑</a> -->
										<a href="javascript:;"  class="btn btn-danger btn-xs"
											onclick="remove('${msic.mon.monid }')" role="button">删除</a>
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
								        <c:if test="${msic.mon.userName == 'huimei'}">
								        	<button class="btn btn-link btn-xs" onclick="openAssign('${msic.mon.monid }')">分配</button>
								        </c:if>
								        <c:if test="${msic.mon.userName != 'huimei'}">
								        	已分配&nbsp;${msic.mon.userName }&nbsp;
								        	<button class="btn btn-link btn-xs" onclick="recover('${msic.mon.monid }')">回收</button>
								        </c:if>
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
								</div>
						    </div>
					  	</div>
			  		</c:forEach>
				</div>
			</c:if>
		</div>
		
		<!-- 选择代理商的模态框 --> 
	    <div id="agentSelectionModal" class="modal fade" role="dialog" aria-hidden="true">
	         <div class="modal-dialog modal-lg">
	              <div class="modal-content">
	                   <div class="modal-header">
	                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                        <h4 class="modal-title">请选择代理商</h4>
	                   </div>
	                   <div class="modal-body">
	                        <form action="user/assignAgentAction" method="post">
	                        	<input type="hidden" id="monid" name="monid">
	                        	<table class="table table-striped table-bordered" cellspacing="0" width="100%">
					                <thead>
					                    <tr>
					                    	<th></th>
					                    	<th>商铺名称</th>
					                    	<th>商铺地址</th>
					                        <th>联系方式</th>
					                    </tr>
					                </thead>
					                <tbody>
					                	<c:if test="${fn:length(agentList) == 0}">
					                		<tr>
					                			<td colspan="4" class="text-center">无可用代理商</td>
					                		</tr>
					                	</c:if>
					                	<c:forEach var="agent" items="${agentList }" varStatus="s">
					                		<tr>
						                		<td>
						                			<input type="radio" name="username" value="${agent.userName }" />
						                		</td>
						                		<td>${agent.shopName }</td>
						                		<td>${agent.shopadd }</td>
						                		<td>${agent.contract }</td>
						                	</tr>
					                	</c:forEach>
					                </tbody>
					           </table>
					           <c:if test="${fn:length(agentList) > 0}">
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
	    
	    function openAssign(monid) {
	    	$('#monid').val(monid);
	    	$('#agentSelectionModal').modal();
	    }
	    
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
	    	location = 'user/edit?monid=' + monid;
	    }
	    
	    function remove(monid) {
	    	if(confirm('删除后无法恢复,确定删除?')) {
	    		$.ajax({
	    			url: 'user/remove',
	    			dataType: 'json',
	    			data: 'monid=' + monid,
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
	    
	    function search() {
	    	location = 'user/home?searchString=' + $('#searchString').val();
	    }
	    
	    function recover(monid) {
	    	if(confirm('回收后该慧眼将对代理商不可见,确定回收?')) {
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

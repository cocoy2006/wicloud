<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<meta name="viewport" content="height=device-height, initial-scale=1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<base href="<%=basePath%>">
		<title>${monname } 数据统计</title>
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
		<style type="text/css">
			body {
				background: url('resources/image/bg.jpg') top center no-repeat; 
				background-size: cover; color: whitesmoke; width: 100%; height: 540pt; text-align: center;}
			.panel {color: #34495e; margin: 0;}
			.chart {padding: 0;}
		</style>
	</head>
	
	<body>
		<!-- 实时报表 -->
		<div class="col-xs-12">
			<span class="pull-left" style="font-size: 12px;">实时数据</span>
		</div>
		<div class="col-xs-4">
			<div class="panel panel-default" style="height: 100px;">
		  		<!-- <div class="panel-heading">${monname }</div> -->
		  		<div class="panel-body">
					<div class="col-xs-5">
						<span style="font-size: 8px;">入店顾客数</span><br>
						<span id="total">${total }</span>
					</div>
					<div class="col-xs-7">
						<span style="font-size: 8px;">平均停留时间(分钟)</span><br>
						<span id="stay">${stay }</span>
					</div>
					<div class="col-xs-12">
						<span id="time" class="pull-right text-muted" style="font-size: 5px;"></span>
					</div>
		  		</div>
		  	</div>
		</div>
		<div class="col-xs-2">
			<div id="rateChart" class="chart"></div>
		</div>
		<div class="col-xs-6">
			<div id="trafficChart" class="chart"></div>
		</div>
		<!-- 周报表 -->
		<div class="col-xs-12">
			<span class="pull-left" style="font-size: 12px;">近7日数据</span>
		</div>
		<div class="col-xs-4">
			<div id="weekTotalListChart" class="chart"></div>
		</div>
		<div class="col-xs-4">
			<div id="rateListChart" class="chart"></div>
		</div>
		<div class="col-xs-4">
			<div id="stayListChart" class="chart"></div>
		</div>
		<!-- 月报表 -->
		<div class="col-xs-12">
			<span class="pull-left" style="font-size: 12px;">近30日数据</span>
		</div>
		<div class="col-xs-2" style="padding-bottom: 5px;">
			<div id="percentChart" class="chart"></div>
		</div>
		<div class="col-xs-10" style="padding-bottom: 5px;">
			<div id="monthTotalListChart" class="chart"></div>
		</div>
		<div class="col-xs-4">
			<div id="wChart" class="chart"></div>
		</div>
		<div class="col-xs-5">
			<div id="hChart" class="chart"></div>
		</div>
		<div class="col-xs-3">
			<div id="abilityChart" class="chart"></div>
		</div>
		<script src="resources/Flat-UI-master/dist/js/vendor/jquery.min.js"></script>
	    <script src="resources/Flat-UI-master/dist/js/flat-ui.min.js"></script>
	    <script src="resources/Flat-UI-master/docs/assets/js/application.js"></script>
	    <script src="resources/Highcharts-4.2.5/js/highcharts.js"></script>
	    <script src="resources/js/date.js"></script>
	    <script type="text/javascript">
	    $(function () {
	    	setInterval(function() {
	    		$.ajax({
	    			url: 'day/total/${monid }',
	    			dataType: 'json',
	    			success: function(data) {
	    				$('#total').html(data.total);
	    			}
	    		});
	    		$.ajax({
	    			url: 'day/stay/${monid }',
	    			dataType: 'json',
	    			success: function(data) {
	    				$('#stay').html(data.stay);
	    			}
	    		});
	    		$('#time').html('更新时间:' + new Date().toLocaleTimeString());
	    	}, 300000);
	    	// 实时报表
	    	$('#rateChart').highcharts({
		        chart: {
		        	height: 100,
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            type: 'pie'
		        },
		        credits: {enabled: false},
		        title: {
		        	align: 'left',
		            text: '入店率',
		            style: {fontSize: '5px'},
		            verticalAlign: 'top'
		        },
		        subtitle: {
		        	align: 'center',
		            text: '<fmt:formatNumber value="${rate * 100 }" pattern="###.##"/>%',
		            style: {fontSize: '5px'},
		            verticalAlign: 'middle'
		        },
		        tooltip: {
		        	pointFormat: '<b>{point.percentage:.2f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                startAngle: -90,
		                endAngle: 270
		            }
		        },
		        series: [{
		            name: '',
		            innerSize: '80%',
		            colorByPoint: true,
		            data: [{
		            	color: '#5A9BD5',
		                name: '入店率',
		                y: ${rate },
		                selected: true,
		                sliced: true
		            }, {
		            	color: '#ED7C30',
		            	name: null,
		                y: ${1 - rate }
		            }],
		            dataLabels: {
		                enabled: false
		            }
		        }]
		    });
	    	//
	        $('#trafficChart').highcharts({
	        	chart: {height: 100},
	            credits: {enabled: false},
	            legend: {
	            	align: 'left',
	                verticalAlign: 'center',
	                layout: 'vertical',
	            	itemStyle: {fontSize: '5px'}
	            },
	            series: [{
	            	color: '#5A9BD5',
	                name: '入店客流',
	                data: [
						<c:forEach var="traffic" items="${trafficList }" varStatus="s">
							${traffic.in },
						</c:forEach>
	                ]
	            }, {
	            	color: '#ED7C30',
	                name: '周边客流',
	                data: [
						<c:forEach var="traffic" items="${trafficList }" varStatus="s">
							${traffic.around },
						</c:forEach>       
	                ]
	            }],
	            title: {
	            	style: {fontSize: '8px'},
	            	text: '前一天的小时客流量'
	            },
	            xAxis: {
	                categories: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
	                labels: {
	                	style: {fontSize: '5px'}
	                }
	            },
	            yAxis: {
	            	labels: {
	                	style: {fontSize: '5px'}
	                },
	            	title: {
	            		text: null
	            	}
	           	}
	        });
	    	// 周报表
	    	var today = new Date();
	    	var weekDateArray = new Array();
	    	for(var i = 0; i < 7; i++) {
	    		weekDateArray[i] = new Date(today.valueOf() - (7 - i) * 86400000).format("MMdd");
	    	}
	    	// 近7日入店顾客数
	        $('#weekTotalListChart').highcharts({
	            chart: {height: 100,type: 'column'},
	            credits: {enabled: false},
	            legend: {enabled: false},
	            title: {
	            	style: {fontSize: '5px'},
	            	text: null
	            },
	            xAxis: {
	                categories: weekDateArray,
	                labels: {
	                	style: {fontSize: '5px'}
	                }
	            },
	            yAxis: {
	            	labels: {
	                	style: {fontSize: '5px'}
	                },
	                title: {
	                	style: {fontSize: '8px'},
	                	text: '入店顾客数'
	                }
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
	            series: [{
	            	color: '#5A9BD5',
	            	name: '入店顾客数',
	                data: [
						<c:forEach var="total" items="${weekTotalList }" varStatus="s">
						${total.total },
						</c:forEach>  
	                ]
	            }]
	        });
	        // 近7日入店率
	        $('#rateListChart').highcharts({
	            chart: {height: 100, type: 'column'},
	            credits: {enabled: false},
	            legend: {enabled: false},
	            title: {
	            	style: {fontSize: '5px'},
	            	text: null
	            },
	            xAxis: {
	                categories: weekDateArray,
	                labels: {
	                	style: {fontSize: '5px'}
	                }
	            },
	            yAxis: {
	            	labels: {
	                	style: {fontSize: '5px'}
	                },
	                title: {
	                	style: {fontSize: '8px'},
	                	text: '入店率(%)'
	                }
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
	            series: [{
	            	color: '#ED7C30',
	            	name: '入店率',
	                data: [
						<c:forEach var="rate" items="${rateList }" varStatus="s">
						${rate.rate * 100 },
						</c:forEach>
	                ]
	            }]
	        });
	        // 近7日顾客平均停留时间
	        $('#stayListChart').highcharts({
	            chart: {height: 100,
	                type: 'column'
	            },
	            credits: {enabled: false},
	            legend: {enabled: false},
	            title: {
	            	style: {fontSize: '5px'},
	            	text: null
	            },
	            xAxis: {
	                categories: weekDateArray,
	                labels: {
	                	style: {fontSize: '5px'}
	                }
	            },
	            yAxis: {
	            	labels: {
	                	style: {fontSize: '5px'}
	                },
	                title: {
	                	style: {fontSize: '8px'},
	                    text: '平均停留时间(分钟)'
	                }
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
	            series: [{
	            	color: '#5A9BD5',
	            	name: '平均停留时间(分钟)',
	                data: [
						<c:forEach var="stay" items="${stayList }" varStatus="s">
						${stay.stay },
						</c:forEach>  
	                ]
	            }]
	        });
	        // 月报表
	        var monthDateArray = new Array();
	    	for(var i = 0; i < 30; i++) {
	    		monthDateArray[i] = new Date(today.valueOf() - (30 - i) * 86400000).format("MMdd");
	    	}
	    	// 近30日入店顾客数
	        $('#monthTotalListChart').highcharts({
	            chart: {height: 100,
	                type: 'column'
	            },
	            credits: {enabled: false},
	            legend: {enabled: false},
	            title: {
	            	style: {fontSize: '5px'},
	            	text: null
	            },
	            xAxis: {
	                categories: monthDateArray,
	                labels: {
	                	style: {fontSize: '5px'}
	                }
	            },
	            yAxis: {
	                min: 0,
	                labels: {
	                	style: {fontSize: '5px'}
	                },
	                title: {
	                	style: {fontSize: '8px'},
	                    text: '入店顾客数'
	                }
	            },
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
	            series: [{
	            	color: '#5A9BD5',
	            	name: '入店顾客数',
	                data: [
						<c:forEach var="total" items="${monthTotalList }" varStatus="s">
						${total.total },
						</c:forEach>  
	                ]
	            }]
	        });
	     	// 近30日新老顾客占比
//	     	if(${percent.percent } != 0) {
	     		$('#percentChart').highcharts({
			        chart: {
			        	height: 100,
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            type: 'pie'
			        },
			        title: {
			        	align: 'left',
			        	style: {fontSize: '5px'},
			            text: '新老顾客比',
			            verticalAlign: 'top'
			        },
			        subtitle: {
			        	align: 'center',
			        	style: {fontSize: '5px'},
			            text: '<fmt:formatNumber value="${(1 - percent.percent) * 100 }" pattern="###.##"/>%',
			            verticalAlign: 'middle'
			        },
			        credits: {enabled: false},
			        tooltip: {
			            pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
			        },
			        plotOptions: {
			        	pie: {
			                startAngle: -90,
			                endAngle: 270
			            }
			        },
			        series: [{
			            name: '',
			            innerSize: '80%',
			            colorByPoint: true,
			            data: [{
			            	color: '#5A9BD5',
			                name: '新顾客',
			                y: ${1 - percent.percent },
			                selected: true
			            }, {
			            	color: '#ED7C30',
			                name: '老顾客',
			                y: ${percent.percent }
			            }],
			            dataLabels: {
			                enabled: false
			            }
			        }]
			    });
//	     	} else {
//	     		$('#percentChart').remove();
//	     	}
			// 近30日周内客流信息
			$('#wChart').highcharts({
				chart: {height: 100},
				credits: {enabled: false},
	            title: {
	            	style: {fontSize: '8px'},
	            	text: '周内客流量'
	            },
	            xAxis: {
	                categories: ['周日','周一','周二','周三','周四','周五','周六'],
	                labels: {
	                	style: {fontSize: '5px'}
	                }
	            },
	            yAxis: {
	            	labels: {
	                	style: {fontSize: '5px'}
	                },
	                title: {
	                	style: {fontSize: '8px'},
	                    text: '客流量'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            legend: {
	            	align: 'left',
	                verticalAlign: 'center',
	                layout: 'vertical',
            		itemStyle: {fontSize: '5px'}
	            },
	            series: [{
	            	color: '#5A9BD5',
	                name: '入店客流',
	                data: [
						<c:forEach var="w" items="${wList }" varStatus="s">
							${w.in },
						</c:forEach>
	                ]
	            }, {
	            	color: '#ED7C30',
	                name: '周边客流',
	                data: [
						<c:forEach var="w" items="${wList }" varStatus="s">
							${w.around },
						</c:forEach>       
	                ]
	            }]
	        });
			// 近30日日内客流信息
			$('#hChart').highcharts({
				chart: {height: 100},
				credits: {enabled: false},
	            title: {
	            	style: {fontSize: '8px'},
	            	text: '日内客流量'
	            },
	            xAxis: {
	                categories: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24],
	                labels: {
	                	style: {fontSize: '5px'}
	                }
	            },
	            yAxis: {
	            	labels: {
	                	style: {fontSize: '5px'}
	                },
	                title: {
	                	style: {fontSize: '8px'},
	                    text: '客流量'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            legend: {
	            	align: 'left',
	                verticalAlign: 'center',
	                layout: 'vertical',
            		itemStyle: {fontSize: '5px'}
	            },
	            series: [{
	            	color: '#5A9BD5',
	                name: '入店客流',
	                data: [
						<c:forEach var="h" items="${hList }" varStatus="s">
							${h.in },
						</c:forEach>
	                ]
	            }, {
	            	color: '#ED7C30',
	                name: '周边客流',
	                data: [
						<c:forEach var="h" items="${hList }" varStatus="s">
							${h.around },
						</c:forEach>       
	                ]
	            }]
	        });
			// 近30日顾客消费能力指数
//			if(${!noBrand }) {
	     		$('#abilityChart').highcharts({
			        chart: {
			        	height: 100,
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            type: 'pie'
			        },
			        colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4', '#262626', '#CCE8CF'],
			        credits: {enabled: false},
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.2f} %',
			                    style: {
			                    	colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4', '#262626', '#F3F5F6'],
			                    	fontSize: '5px'
			                    }
			                },
			                startAngle: -90,
			                endAngle: 270
			            }
			        },
			        series: [{
			            name: null,
			            innerSize: '80%',
			            colorByPoint: true,
			            data: [
							<c:forEach var="brand" items="${brandList }" varStatus="s">
							{name: '${brand.brand }', y: ${brand.value }},
							</c:forEach>
			            ]
			        }],
			        title: {
			        	align: 'right',
			        	style: {fontSize: '5px'},
			            text: '消费指数',
			            verticalAlign: 'top'
			        },
			        subtitle: {
			        	align: 'center',
			            text: '${rank}',
			            style: {fontSize: '5px'},
			            verticalAlign: 'middle'
			        },
			        tooltip: {
			            pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
			        }
			    });
//	     	} else {
//	     		$('#abilityChart').remove();
//	     	}
	    });
	    </script>
	</body>
</html>

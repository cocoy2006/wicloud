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
<title>${monname }- 实时数据</title>
<link rel="stylesheet" href="resources/newui/report.css" type="text/css">
<link rel="stylesheet" href="resources/newui/jquery-ui.min.css">
</head>

<body class="is-loading afternoon" style="overflow-x: hidden">

	<nav class="navbar nav_single_btn">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:;"> 慧眼实时数据 </a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="index"
					class="btn btn-default ghost navbar-btn">返回</a></li>
			</ul>

		</div>
	</nav>


	<main class="clearfix interactive_workflow with_navbar">
		<article class="intro" style="min-height:1700px">
			<header class="container text-center" id="intro">
				<h1
					class="color-white text-center margin-top margin-bottom_half text-biggest">
					实时<strong>动态</strong>监控 <strong>商业数据</strong>
				</h1>
				<h2 class="color-white text-center text-big margin-bottom">从海量数据中挖掘商业先机</h2>
			</header>
			<section id="how"
				class="wp1 bg-white arrow-white padding-top_double padding-bottom_double animated">
				<div class="container_1680">
					<div class="row">
						<div class="tech-prov col-md-4 padding-left padding-right">
							<header class="text-center">
								<img src="resources/newui/icon-conversions.svg"
									alt="software developers icon" width="72" height="72">
								<h2 class="text-bigger margin-bottom_half">
									加快 <strong>销售周期</strong>
								</h2>
							</header>
							<div class="description">
								<p class="text-center" style="height: 50px;">
									立即将您的产品置于潜在客户手中</p>
							</div>
						</div>

						<div class="tech-training col-md-4 padding-left padding-right">
							<header class="text-center">
								<img src="resources/newui/icon-guide.svg"
									alt="software developers icon" width="72" height="72">
								<h2 class="text-bigger margin-bottom_half">
									拓展 <strong>商业网络</strong>
								</h2>
							</header>
							<div class="description">
								<p class="text-center" style="height: 50px;">通过实时数据监控发现最优投资点</p>
							</div>
						</div>

						<div class="software-dev col-md-4 padding-left padding-right">
							<header class="text-center">
								<img src="resources/newui/icon-insights.svg"
									alt="software developers icon" width="72" height="72">
								<h2 class="text-bigger margin-bottom_half">
									先进的 <strong>在线分析</strong>
								</h2>
							</header>
							<div class="description">
								<p class="text-center" style="height: 50px;">实现对产品销售效能的即时了解</p>
							</div>
						</div>
					</div>
				</div>
			</section>

			<section>
				<div class="col-xs-12" style="padding: 0px 20px;">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="col-xs-6">
								<table>
									<tr>
										<td class="text-right" style="padding-right: 5px;"><span
											class="glyphicon glyphicon-user"></span></td>
										<td class="text-left" style="padding-left: 5px;">${total }&nbsp;人<br>
											<small class="text-muted" style="font-size: 80%;">入店顾客数</small>
										</td>
									</tr>
								</table>
							</div>
							<div class="col-xs-6">
								<table>
									<tr>
										<td class="text-right" style="padding-right: 5px;"><span
											class="glyphicon glyphicon-time"></span></td>
										<td class="text-left" style="padding-left: 5px;">${stay }&nbsp;分钟<br>
											<small class="text-muted" style="font-size: 75%;">平均停留时间</small>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
			<section id="tutorials"
				class="bg-gray_light arrow-gray_light padding-top_double padding-bottom_double">
				<div class="container_1680 margin-top padding-bottom">
					<div class="row">
						<div class="row-height-equal">
							<div id="rateChart"
								class="col-md-12 col-height-equal wp2 animate-images fadein"
								style="height: 500px"></div>
						</div>
					</div>
				</div>
			</section>

			<section id="editor"
				class="bg-white arrow-white padding-top_double padding-bottom_double">
				<div class="container_1680 margin-top padding-bottom">
					<div class="row">
						<div class="row-height-equal">
							<div id="trafficChart"
								class="col-md-12 col-height-equal wp2 animate-images fadein"
								style="height: 500px"></div>
						</div>
					</div>
				</div>
			</section>
		</article>
	</main>
	<script src="resources/newui/jquery.min.js"></script>
	<script src="resources/newui/bootstrap.min.js"></script>
	<script src="resources/newui/highcharts.js"></script>
	<script>
    $(function () {
        //
        $('#rateChart').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                type: 'pie'
            },
            credits: {enabled: false},
            title: {
                align: 'center',
                text: '入店率<br><fmt:formatNumber value="${rate * 100 }" pattern="###.##"/>%',
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
                name: null,
                innerSize: '60%',
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
            credits: {enabled: false},
            legend: {layout: 'vertical'},
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
        title: {text: '前一天的小时客流量'},
        xAxis: {
            categories: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
        },
        yAxis: {title: null}
    });
    });
</script>
</body>
</html>

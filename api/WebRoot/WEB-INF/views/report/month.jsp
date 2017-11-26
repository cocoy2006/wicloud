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
<title>${monname }- 月报表</title>
<link rel="stylesheet" href="resources/newui/report.css" type="text/css">
<link rel="stylesheet" href="resources/newui/jquery-ui.min.css">
</head>

<body class="is-loading afternoon" style="overflow-x: hidden">

	<nav class="navbar nav_single_btn">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:;"> 慧眼月报表 </a>
			</div>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="index"
					class="btn btn-default ghost navbar-btn">返回</a></li>
			</ul>

		</div>
	</nav>


	<main class="clearfix interactive_workflow with_navbar">
		<article class="intro" style="min-height:2000px">
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

			<section id="tutorials"
				class="bg-gray_light arrow-gray_light padding-top_double padding-bottom_double">
				<div class="container_1680 margin-top padding-bottom">
					<div class="row">
						<div class="row-height-equal">
							<div id="totalChart"
								class="col-md-7 col-height-equal wp2 animate-images fadein"
								style="height: 500px"></div>
							<div
								class="col-md-5 col-height-equal wp2-1 padding-left_double padding-right_double text animate-images fadein">
								<h1 class="text-bigger">
									实时 <strong>入店顾客数</strong>
								</h1>
								<p class="margin-bottom">通过实时监控入店顾客数，是您获得一天之内客户最多的时间点。</p>
							</div>
						</div>
					</div>
				</div>
			</section>

			<section id="editor"
				class="bg-white arrow-white padding-top_double padding-bottom_double">
				<div class="container_1680 margin-top padding-bottom">
					<div class="row">
						<div class="row-height-equal">
							<div
								class="col-md-5 col-height-equal wp3-1 padding-left_double padding-right_double text inverse animate-images fadein">
								<h1 class="text-bigger">
									<strong>为您</strong> 寻找最优资源
								</h1>
								<p class="margin-bottom">通过硬件检测和软件分析实时提供最佳客流量地点。</p>
							</div>
							<div id="percentChart"
								class="col-md-7 col-height-equal wp2 animate-images fadein"
								style="height: 500px"></div>
						</div>
					</div>
				</div>
			</section>

			<section id="books"
				class="bg-gray_light arrow-gray_light padding-top_double padding-bottom_double">
				<div class="container_1680 margin-top padding-bottom">
					<div class="row">
						<div class="row-height-equal">
							<div id="wChart"
								class="col-md-7 col-height-equal wp2 animate-images fadein"
								style="height: 500px"></div>
							<div
								class="col-md-5 col-height-equal wp4-1 padding-left_double padding-right_double text animate-images fadein">
								<h1 class="text-bigger">
									一周 <strong>客流量</strong>
								</h1>
								<p class="margin-bottom">使您一目了然目前地点的一周之内的客流量。</p>
							</div>
						</div>
					</div>
				</div>
			</section>


			<section id="editor"
				class="bg-white arrow-white padding-top_double padding-bottom_double">
				<div class="container_1680 margin-top padding-bottom">
					<div class="row">
						<div class="row-height-equal">
							<div
								class="col-md-5 col-height-equal wp3-1 padding-left_double padding-right_double text inverse animate-images fadein">
								<h1 class="text-bigger">
									<strong>一日</strong> 客流量
								</h1>
								<p class="margin-bottom">使您一目了然目前地点的一日之内的客流量。</p>
							</div>
							<div id="hChart"
								class="col-md-7 col-height-equal wp2 animate-images fadein"
								style="height: 500px"></div>
						</div>
					</div>
				</div>
			</section>

			<section id="books"
				class="bg-gray_light arrow-gray_light padding-top_double padding-bottom_double">
				<div class="container_1680 margin-top padding-bottom">
					<div class="row">
						<div class="row-height-equal">
							<div id="abilityChart"
								class="col-md-7 col-height-equal wp2 animate-images fadein"
								style="height: 500px"></div>
							<div
								class="col-md-5 col-height-equal wp4-1 padding-left_double padding-right_double text animate-images fadein">
								<h1 class="text-bigger">
									综合 <strong>消费指数</strong>
								</h1>
								<p class="margin-bottom">动态分析目前客流量的消费能力。</p>
							</div>
						</div>
					</div>
				</div>
			</section>
		</article>
	</main>
	<script src="resources/newui/jquery.min.js"></script>
	<script src="resources/newui/bootstrap.min.js"></script>
	<script src="resources/newui/highcharts.js"></script>
	<script src="resources/js/date.js"></script>
	<script>
    $(function () {
        var today = new Date();
        var dateArray = new Array();
        for(var i = 0; i < 30; i++) {
            dateArray[i] = new Date(today.valueOf() - (30 - i) * 86400000).format("MM月dd日");
        }
        // 近30日入店顾客数
        $('#totalChart').highcharts({
            chart: {
                type: 'column'
            },
            credits: {enabled: false},
            title: {text: null},
            xAxis: {
                categories: dateArray
            },
            yAxis: {
                min: 0,
                title: {
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
                <c:forEach var="total" items="${totalList }" varStatus="s">
            ${total.total },
        </c:forEach>
        ]
    }]
    });
        // 近30日新老顾客占比
        if(${percent.percent } != 0) {
            $('#percentChart').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    type: 'pie'
                },
                title: {
                    align: 'center',
                    text: '新老顾客比<br><fmt:formatNumber value="${(1 - percent.percent) * 100 }" pattern="###.##"/>%',
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
                    type: 'pie',
                    name: '',
                    innerSize: '60%',
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
                    }]
                }]
            });
        } else {
            $('#percentChart').remove();
        }
        // 近30日周内客流信息
        $('#wChart').highcharts({
            credits: {enabled: false},
            title: {text: '周内客流量'},
            xAxis: {
                categories: ['周日','周一','周二','周三','周四','周五','周六']
            },
            yAxis: {
                title: {
                    text: '客流量'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            legend: {layout: 'vertical'},
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
            credits: {enabled: false},
            title: {text: '日内客流量'},
            xAxis: {
                categories: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]
            },
            yAxis: {
                title: {
                    text: '客流量'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            legend: {layout: 'vertical'},
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
        if(${!noBrand }) {
            $('#abilityChart').highcharts({
                chart: {
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
                                colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4', '#262626', '#F3F5F6']
                            }
                        },
                        startAngle: -90,
                        endAngle: 270
                    }
                },
                series: [{
                    name: null,
                    innerSize: '60%',
                    colorByPoint: true,
                    data: [
                    <c:forEach var="brand" items="${brandList }" varStatus="s">
                {name: '${brand.brand }', y: ${brand.value }},
        </c:forEach>
        ]
        }],
            title: {
                align: 'center',
                    text: '消费指数'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
            }
        });
        } else {
            $('#abilityChart').remove();
        }

    });
</script>
</body>
</html>

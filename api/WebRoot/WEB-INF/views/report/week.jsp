<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>${monname }- 周报表</title>
<link rel="stylesheet" href="resources/newui/report.css" type="text/css">
<link rel="stylesheet" href="resources/newui/jquery-ui.min.css">
</head>

<body class="is-loading afternoon" style="overflow-x: hidden">

	<nav class="navbar nav_single_btn">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:;"> 慧眼周报表 </a>
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
									入店 <strong>顾客数</strong>
								</h1>
								<p class="margin-bottom">通过实时监控统计入店顾客数，使您获得一天之内入店光顾的客户数量。</p>
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
									<strong>入</strong> 店率
								</h1>
								<p class="margin-bottom">通过硬件检测和软件分析实时提供入店顾客率。</p>
							</div>
							<div id="rateChart"
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
							<div id="stayChart"
								class="col-md-7 col-height-equal wp2 animate-images fadein"
								style="height: 500px"></div>
							<div
								class="col-md-5 col-height-equal wp4-1 padding-left_double padding-right_double text animate-images fadein">
								<h1 class="text-bigger">
									平均 <strong>停留时间</strong>
								</h1>
								<p class="margin-bottom">使您一目了然目前地点的顾客停留时间。</p>
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
        for(var i = 0; i < 7; i++) {
            dateArray[i] = new Date(today.valueOf() - (7 - i) * 86400000).format("MM月dd日");
        }
        // 近7日入店顾客数
        $('#totalChart').highcharts({
            chart: {type: 'column'},
            credits: {enabled: false},
            title: {text: null},
            xAxis: {
                categories: dateArray
            },
            yAxis: {
                title: {text: '入店顾客数'}
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
        // 近7日入店率
        $('#rateChart').highcharts({
            chart: {type: 'column'},
            credits: {enabled: false},
            title: {text: null},
            xAxis: {
                categories: dateArray
            },
            yAxis: {
                title: {text: '入店率'}
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
            ${rate.rate },
        </c:forEach>
        ]
    }]
    });
        // 近7日顾客平均停留时间
        $('#stayChart').highcharts({
            chart: {
                type: 'column'
            },
            credits: {enabled: false},
            title: {text: null},
            xAxis: {
                categories: dateArray
            },
            yAxis: {
                title: {
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
    });
</script>
</body>
</html>

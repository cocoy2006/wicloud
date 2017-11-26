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
<title>${monname }</title>
<link rel="stylesheet" href="resources/newui/bootstrap.min.css">
<link rel="stylesheet" href="resources/newui/material-kit.min.css">
<link rel="stylesheet" href="resources/newui/main.css">
<link rel="stylesheet" href="resources/newui/template.css">
<link rel="shortcut icon" href="favicon.ico">
</head>

<body>
	<nav
		class="navbar navbar-fixed-top navbar-color-on-scroll navbar-info navbar-transparent">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle collapsed" type="button"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span><span
						class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="javascript:;">慧眼</a>
			</div>
			<div class="navbar-collapse collapse" id="navbar"
				aria-expanded="false" style="height: 0px;">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown" role="presentation">
						<a id="dLabel"
							data-target="#" href="#" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false"> 慧眼数据
								<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" aria-labelledby="dLabel">
							<li><a href="day">实时数据</a></li>
							<li><a href="week">周报表</a></li>
							<li><a href="month">月报表</a></li>
						</ul>
					</li>
					<li><a href="my">我的慧眼</a></li>
					<li><a href="tel:400-900-8828" data-toggle="tooltip" data-placement="bottom"
						title="400-900-8828">客服中心</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="hero__box">
					<div class="hero__image text-left hidden-xs col-sm-6 col-md-5">
						<img src="resources/newui/rocket.svg"
							alt="rocket flying out of the monitor">
					</div>
					<div class="hero__text col-xs-12 col-sm-6 col-md-7">
						<h1 class="text-center">慧眼数据</h1>
						<p class="hero__lead text-center">流量大数据</p>
						<p class="hero__lead text-center">数据分析，实时反馈</p>
						<p class="hero__lead text-center">24小时全天候检测，数据说话</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<main>
		<div class="main">
			<section class="hg_section bg-white ptop-80 pbottom-80">
				<div class="container">
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<!-- Section title -->
							<div
								class="kl-title-block clearfix tbk--text-default tbk--center text-center tbk-symbol--line tbk-icon-pos--after-title">
								<!-- Title -->
								<h3 class="tbk__title ">我们的业务</h3>
								<!--/ Title -->

								<!-- Title bottom symbol -->
								<div class="tbk__symbol ">
									<span></span>
								</div>
								<!--/ Title bottom symbol -->
							</div>
							<!--/ Section title -->

							<!-- Grid icon boxes element -->
							<div
								class="grid-ibx grid-ibx--cols-3 grid-ibx--style-lined-center grid-ibx--hover-shadow">
								<div class="grid-ibx__inner">
									<div class="grid-ibx__row clearfix">
										<!-- Item - height 260px -->
										<a href="day">
											<div class="grid-ibx__item h-260">
												<div class="grid-ibx__item-inner">
													<!-- Title -->
													<div class="grid-ibx__title-wrp">
														
															<h4 class="grid-ibx__title montserrat fs-16">实时数据</h4>
														
													</div>
													<!--/ Title -->
	
													<!-- Icon - .glyphicon-list-alt -->
													<div class="grid-ibx__icon-wrp">
														<img src="resources/newui/bg_day.png" width="100"
															height="100">
													</div>
													<!--/ Icon - .glyphicon-list-alt -->
												</div>
												<!--/ .grid-ibx__item-inner -->
											</div>
										</a>
										<!--/ Item - height 260px - .grid-ibx__item -->

										<!-- Item - height 260px -->
										<a href="week">
											<div class="grid-ibx__item h-260">
												<div class="grid-ibx__item-inner">
													<!-- Title -->
													<div class="grid-ibx__title-wrp">
														
															<h4 class="grid-ibx__title montserrat fs-16">周报表</h4>
														
													</div>
													<!--/ Title -->
	
													<!-- Icon - .glyphicon-list-alt -->
													<div class="grid-ibx__icon-wrp">
														<img src="resources/newui/bg_week.png" width="100"
															height="100">
													</div>
													<!--/ Icon - .glyphicon-list-alt -->
												</div>
												<!--/ .grid-ibx__item-inner -->
											</div>
										</a>
										<!--/ Item - height 260px - .grid-ibx__item -->

										<!-- Item - height 260px -->
										<a href="month">
											<div class="grid-ibx__item h-260">
												<div class="grid-ibx__item-inner">
													<!-- Title -->
													<div class="grid-ibx__title-wrp">
														
															<h4 class="grid-ibx__title montserrat fs-16">月报表</h4>
														
													</div>
													<!--/ Title -->
	
													<!-- Icon - .glyphicon-list-alt -->
													<div class="grid-ibx__icon-wrp">
														<img src="resources/newui/bg_month.png" width="100"
															height="100">
													</div>
													<!--/ Icon - .glyphicon-list-alt -->
												</div>
												<!--/ .grid-ibx__item-inner -->
											</div>
										</a>
										<!--/ Item - height 260px - .grid-ibx__item -->
									</div>
									<!--/ .grid-ibx__row -->


									<!--/ .grid-ibx__row -->
								</div>
								<!--/ .grid-ibx__inner -->
							</div>
							<!--/ Grid icon boxes element - .grid-ibx-->
						</div>
						<!--/ col-md-12 col-sm-12 -->
					</div>
					<!--/ row -->
				</div>
				<!--/ container -->
			</section>

			<section>
				<div class="container">
					<div class="row indexjigou">
						<div class="col-md-6" data-ride="carousel">
							<h3>战略伙伴</h3>
							<div class="carousel-inner" id="demo" style="overflow:hidden">
								<div class="item active">
									<div class="col-md-4 m15px">
										<div class="media">
											<a class="pull-left" target="_blank"
												href="http://hm2019.com/"> <img class="img-circle"
												src="resources/newui/huimeishenghuo.png" alt="" width="80"
												height="80">
											</a>
											<div class="media-body justify">
												<h6 class="media-heading grey18 m16px">
													<a target="_blank" href="http://hm2019.com/">慧美生活</a>
												</h6>
												<span class="grey12">提供商业解决方案</span>
											</div>
										</div>
									</div>
									<div class="col-md-4 m15px">
										<div class="media">
											<a class="pull-left" target="_blank"
												href="http://huipay.goldepay.cn/appd/appd.html"> <img
												class="img-circle" src="resources/newui/huifu.jpg" alt=""
												width="80" height="80">
											</a>
											<div class="media-body justify">
												<h6 class="media-heading grey18 m16px">
													<a target="_blank"
														href="http://huipay.goldepay.cn/appd/appd.html">慧付</a>
												</h6>
												<span class="grey12">更高效的支付手段</span>
											</div>
										</div>
									</div>
									<div class="col-md-4 m15px">
										<div class="media">
											<a class="pull-left" target="_blank"
												href="http://7xryng.com2.z0.glb.qiniucdn.com/hc.html"> <img
												class="img-circle" src="resources/newui/huichuan.png" alt=""
												width="80" height="80">
											</a>
											<div class="media-body justify">
												<h6 class="media-heading grey18 m16px">
													<a target="_blank"
														href="http://7xryng.com2.z0.glb.qiniucdn.com/hc.html">慧传</a>
												</h6>
												<span class="grey12">高效推广企业品牌</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-6" data-ride="carousel">
							<h3 class="">合作机构</h3>
							<div class="carousel-inner" id="demo" style="overflow:hidden">
								<div class="item active">
									<div class="col-md-4 m15px">
										<div class="media">
											<a class="text-center" target="_blank"
												href="http://www.bupt.edu.cn/"> <img class="img-rounded"
												src="resources/newui/bupt.png" alt="" width="100"
												height="100">
											</a>
										</div>
									</div>
									<div class="col-md-4 m15px">
										<div class="media">
											<a class="text-center" target="_blank"
												href="http://www.asiabrand.cn/"> <img
												class="img-rounded" src="resources/newui/asiabrand.png"
												alt="" width="100" height="100">
											</a>
										</div>
									</div>
									<div class="col-md-4 m15px">
										<div class="media">
											<a class="text-center" target="_blank"
												href="http://wap.cmbc.com.cn/"> <img class="img-rounded"
												src="resources/newui/cmb.png" alt="" width="100"
												height="100">
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</main>
	<footer class="footer">
		<div class="container">
			<div class="text-center">
				<div class="pull-center">
					<span>© 2016-2017 慧眼数据</span><br>
					<span>Powered by <a href="javascript:;" target="_blank">华录北邮</a>
				</div>
			</div>
		</div>
	</footer>
	<script src="resources/newui/main.min.js"></script>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<!--[if lt IE 7]>      <![endif]-->
<!--[if IE 7]>          <![endif]-->
<!--[if IE 8]>          <![endif]-->
<!--[if gt IE 8]><!-->  <!--<![endif]-->
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>${title}</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/flexboxgrid.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>
<link href='http://fonts.googleapis.com/css?family=Lato:400,300' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway:400,300,500' rel='stylesheet' type='text/css'>
<body>

<%--HEADER--%>
<section id="header">
    <div class="row center-lg center-md center-sm center-xs">
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
            <div class="row">
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                    <a href="/">
                        <img src="${pageContext.request.contextPath}/static/images/logo.png" alt="" >
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 menu">
            <div class="row end-lg end-md end-sm end-xs">
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                    <a href="/home"><h4 class="menu_btn">${home}</h4></a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                    <a href="#services"><h4 class="menu_btn">${services}</h4></a>
                </div>
                <c:if test="${sessionScope.loginedUser != null}">
                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                        <a href="/logout"><h4 class="menu_btn">${logout}</h4></a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.loginedUser == null}">
                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                        <a href="/login"><h4 class="menu_btn">${login}</h4></a>
                    </div>
                </c:if>
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                    <a href="/home?language=en_US">
                        <img src="${pageContext.request.contextPath}/static/images/english.png" alt="" >
                    </a>
                </div>
                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                    <a href="/home?language=ru_RU">
                        <img src="${pageContext.request.contextPath}/static/images/russian.png" alt="" >
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/in.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="${ctx}/script/ext/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/script/ext/example/desktop/css/desktop.css" />
    <!-- GC -->
    <script type="text/javascript" src="${ctx}/script/ext/ext.js"></script>
    <script type="text/javascript" src="${ctx}/script/ext/locale/ext-lang-zh_CN.js"></script>

    <!-- GC -->
    <script type="text/javascript" src="${ctx}/script/ext/example/desktop/all-classes.js"></script>

    <script type="text/javascript">
        Ext.Loader.setPath({
            'Ext.ux.desktop': '${ctx}/script/ext/example/desktop/js',
            MyDesktop: ''
        });

        Ext.require('MyDesktop.App');

        var myDesktopApp;
        Ext.onReady(function () {
            myDesktopApp = new MyDesktop.App();
        });
    </script>
</head>

<body>

    <a href="http://www.sencha.com" target="_blank" alt="Powered by Ext JS"
       id="poweredby"><div></div></a>

</body>
</html>

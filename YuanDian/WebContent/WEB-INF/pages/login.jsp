<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/combos.css" />
<script type="text/javascript" src="${ctx}/script/login.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
    var ds = new Ext.data.Store({
    	proxy:new Ext.data.HttpProxy({url: '${ctx}/admin/getLoginUserList.do'}),
   		reader:new Ext.data.JsonReader({
   		}, [
            {name: 'loginName', mapping: 'loginName'},
            {name: 'job', mapping: 'job'
        ])
    });
    
    // Custom rendering Template
    var resultTpl = new Ext.XTemplate(
    		'<tpl for="."><table class="search-item">',
    		'<tr>',
            '<td style="width:100px">&nbsp;{loginName}</td>',
            '<td style="width:100px;">&nbsp;{job}</td>',
    		'</tr>',
        '</table></tpl>'
    );
    
    var search = new Ext.form.ComboBox({
        store: ds,
        typeAhead: false,
        loadingText: 'Searching...',
        hideTrigger:true,
        width:172,
        queryParam:'loginName',
        tpl: resultTpl,
        minChars : 1,
        valueField : 'loginName',
        displayField:'loginName',
        applyTo: 'loginName',
        itemSelector: 'table.search-item'
    });
});
</script>

</head>

<body class="login_bg">
	<div class="login">
		<form id="frm" action="userLogin.do" method="post">
			<table width="300" border="0" cellspacing="0" cellpadding="3">
				<tr>
					<td width="49" height="50">登录名：</td>
					<td width="200"><input name="user.loginName" id="loginName"
						type="text"  class="text text_1"/>
					</td>
				</tr>
				<tr>
					<td>密 &nbsp;码：</td>
					<td><input name="user.password" id="password" type="password" class="text text_1"/></td>
				</tr>
				<tr>
					<td colspan="2" id="divPrompt" style="color: red;" height="10">&nbsp;
						<s:if test="actionMessages.size()!=0">
							<s:property value="actionMessages[0]" />
						</s:if></td>
				</tr>
				<tr>
					<td height="29">&nbsp;</td>
					<td><input name="" type="reset" class="dl01"
						onmouseover="this.className='dl02'"
						onmouseout="this.className='dl01'" id="" value=""
						onclick="userLogin()" />&nbsp;&nbsp;&nbsp; <input name=""
						type="reset" class="cz01" onmouseover="this.className='cz02'"
						onmouseout="this.className='cz01'" id="" value=""
						onclick="toReset()" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

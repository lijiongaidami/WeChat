<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="bootstrap.min.css" rel="stylesheet" media="screen">
<script src="./jquery-1.11.3.min.js"></script>
<script src="./bootstrap.min.js"></script>
<title></title>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" role="form" action="#" method="post">
			<div class="form-group ">
				<div class="col-sm-offset-2 col-sm-10">
					<label for="firstname" class="col-sm-2" style="padding: 0"><h2>用户注册</h2></label>
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">手机号码</label>
				<div class="col-sm-10">
					<input type="tel" class="form-control" id="firstname"
						placeholder="请输入手机号码进行注册">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-info btn-xs form-control">获取验证码</button>
				</div>
			</div>

			<div class="form-group">
				<label for="yzm" class="col-sm-2 control-label">输入验证码</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="yzm"
						placeholder="输入验证码">
				</div>
			</div>
			<div class="form-group">
				<label for="psw" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="psw"
						placeholder="输入6位密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success btn-xs form-control">注册</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
<%@ page contentType="text/html;charset=GBK" import="java.lang.*"%>
<%@ include file="/common.jsp"%>

<html>
	<head>
		<title>框架集top页面</title>
		<script language="javascript">
		    /**
		     * 修改自己的登陆密码
		     */
			

		    //获取当前时间
			function clock() {
				var date = new Date();
				date.setTime(date.getTime());
				document.getElementById("sysDate").innerHTML= date.toLocaleTimeString();
				setTimeout("clock()", 1000);

			}
			
			$(function(){
				//当前时间 每一秒刷新一次
				clock();
				//----------------------退出系统方法开始--------------
				//点击退出按钮时调用的退出方法。
				$('#logout1').click(function(){
					var vaild = window.confirm("您确定要退出吗？");
					if(vaild==true){
						//logout_useframes();
					    top.location="/user/logout.action";
				    }else{
			           return false;
				    }
				});
				//----------------------退出系统方法结束---------------//
			
			})
		</script>

	</head>
	<body>
		<div class="container" style="width: 100%">
			<%-- <nav class="navbar navbar-inverse style="margin-bottom:0px;"">
				<div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>
      					<ul class="nav navbar-nav" style="float: right;padding-right: 50px;">
      						
        					<li class="dropdown">
          						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${CurrentUser.name }<span class="caret"></span></a>
          					<ul class="dropdown-menu">
            					<li><a href="#">我的信息</a></li>
            					<li><a href="#">Something else here</a></li>
            					<li role="separator" class="divider"></li>
           						<li><a href="#">Separated link</a></li>
            					<li role="separator" class="divider"></li>
            					<li><a href="#">One more separated link</a></li>
          					</ul>
        					</li>
        					<li><a href="#" id="sysDate"></a></li>
      					</ul>
      		</nav> --%>
				<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>	
		</div>
	</body>
</html>

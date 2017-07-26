<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp" %>
<%@ include file="/public/message.jsp" %>
<!DOCTYPE html>
<html>
<head>
<c:if test="${days == 1 }">
<script>
    alert('您现在使用的是初始密码，建议您修改密码');
</script>
</c:if>
<script type="text/javascript">



//获取当前时间
function clock() {
	var date = new Date();
	date.setTime(date.getTime());
	document.getElementById("sysDate").innerHTML= date.toLocaleTimeString();
	setTimeout("clock()", 1000);

}


	$(function() {
		//当前时间 每一秒刷新一次
		clock();
		//----------------------退出系统方法开始--------------
		//点击退出按钮时调用的退出方法。
		$('#logout').click(function(){
			var vaild = window.confirm("您确定要退出吗？");
			if(vaild==true){
				//logout_useframes();
			    top.location="${path}/user/logout.action";
		    }else{
	           return false;
		    }
		});
		
		
		//----------------------退出系统方法结束---------------//
		
		
		
		$.ajax({
			async : false, //请勿改成异步，下面有些程序依赖此请数据
			type : "POST",
			url : '${path }/resources/resources.action',
			dataType : 'json',
			success : function(data) {
				var ul = $("#menu");
				ul.html('');
				var li ='';
				$.each(data, function(i) {
					li+='<li class="level1"><a href="javascript:void(0)">'+data[i].name+'</a>';
					li+='<ul class="level2" id="level2_'+i+'">';
					var jdata = data[i].children;
					$.each(jdata, function(j) {/* <img src="${pageContext.request.contextPath}/public/img/t1.png" id="level2_img"> */
						li+='<li></img><a href="${pageContext.request.contextPath}'+jdata[j].resUrl+'" id="level2_'+jdata[j].resKey+'" target="main_frame" name="'+jdata[j].name+'">'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+jdata[j].name+'</a></li>';
					});
					li+='</ul></li>';
				});
				ul.html(li);
			}
		});
		
		$("#checkIn").click(function(){
			$.ajax({
				url:'${path}/attendance/checkIn.action',
				type:'post',
				success:function(data){
					alert(data);
				}
			})
		})
		
		$("#checkOut").click(function(){
			if(confirm("您确定要签退吗？")){
				$.ajax({
					url:'${path}/attendance/checkOut.action',
					type:'post',
					success:function(data){
						alert(data);
					}
				})
			}
			
		})
		////////////////////密码修改////////////////
		$("#changePwd").click(function(){
			$('#myModal').modal('show');
			$("#oldPwd").val('');
			$("#password").val('');
		})
		
		
		
		$("#change_button").click(function(){
			
			
			var oldPwd = $("#oldPwd").val();
			var password = $("#password").val();
			if(oldPwd.lemgth == 0){
				alert("请输入原密码！");
				return false;
			}else if(password.length <6){
				alert("请输入至少6位新密码！");
				return false;
			}
			if(confirm("您确定要修改吗？")){
				$.ajax({
					url:'${path}/user/changePwd.action',
					type:'post',
					data:{"oldPwd":oldPwd,"password":password},
					success:function(data){
						alert(data);
						$('#myModal').modal('hide');
						//parent.location.reload();///重新加载父页面
					}
				})
			}
		})

	});
	
	
</script>

</head>
	<body >
		
		<div class="container" style="width: 100%;height: 950px;border: 0px solid red;">
			<div class="row">
				<nav class="navbar navbar-inverse" style="margin-bottom:0px;">
				<div class="navbar-header">
      				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        				<span class="sr-only">Toggle navigation</span>
        				<span class="icon-bar"></span>
        				<span class="icon-bar"></span>
        				<span class="icon-bar"></span>
      				</button>
      				<a class="navbar-brand" href="#">人力资源管理系统</a>
    			</div>
      					<ul class="nav navbar-nav" style="float: right;padding-right: 50px;">
      						
        					<li class="dropdown">
          						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${CurrentUser.name }<span class="caret"></span></a>
          						
        					</li>
        					
        					<li><a href="#" id="sysDate"></a></li>
        					
        					<li><a href="#" id="checkIn">签到</a></li>
        					
        					<li><a href="#" id="checkOut">签退</a></li>
        					
        					<li><a href="#" id="changePwd">密码修改</a></li>
        					
        					<li><a href="#" id="logout">退出</a></li>
      					</ul>
      		</nav>
			</div>
	
			<div class="row" style="z-index: -1;">
			  <div class="col-md-2" style="margin:0px;padding:0px;background-color: #222222;height: 900px;" id="menu_left">
			  		<jsp:include page="/frame/menu.jsp"></jsp:include>
			  </div>
			  <div class="col-md-10" style="padding: 0px;margin: 0px;height: 900px;">
			  	
			  		<iframe id="main" name="main_frame" src=""  frameborder="0" scrolling="no" height="100%" width="100%"></iframe>
			  		
			  </div>
			</div>
			
			
			<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static"> 
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">密码修改</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" action="" method="post" id="change_form">
  			<div class="form-group">
    			<label for="username" class="col-sm-2 control-label">用户名</label>
    			<div class="col-sm-10">
     				 <input type="email" class="form-control" id="username" name="username" readonly="readonly" value="${CurrentUser.name}">
    			</div>
  			</div>
  			<div class="form-group">
    			<label for="oldPwd" class="col-sm-2 control-label">原密码</label>
    			<div class="col-sm-10">
      				<input type="text" class="form-control" id="oldPwd" name="oldPwd">
    			</div>
  			</div>
  			
  			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label">新密码</label>
    			<div class="col-sm-10">
      				<input type="text" class="form-control" id="password" name="password">
    			</div>
  			</div>
  		
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="change_button">修改</button>
      </div>
    </div>
  </div>
</div>
		</div>
	</body>
</html>
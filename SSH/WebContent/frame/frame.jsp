<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common.jsp"%>
<html>
<head>
	<style>
		#header{height:100px;border:1px solid #f00;}
		.anniu_kuang{float:right; width:150px; height:65px;padding:5px 0 0 0;}
		.framebottom{height:800px;border:1px solid #f00;}
		.message{margin-top:5px;}
		.check_input{color:#f00;text-align: center;font-size: 16px;}
	</style>
	<script language="javascript">
		    /**
		     * 修改自己的登陆密码
		     */

		    //获取当前时间
			function clock() {
				var now = new Date();
				var hh = now.getHours();      //时
				var mm = now.getMinutes();     //分
				var ms = now.getSeconds();     //秒
				var clock = '';
				clock += hh + ":";
				if (mm < 10) clock += '0';
				clock += mm;
				if (ms < 10) clock += '0';
				clock +=":"+ms;
				document.getElementById("sysDate").innerHTML= clock;
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
				
				//----------------------密码修改---------------------//
				$("#updatePwd").bootstrapValidator({
					 message: 'This value is not valid',
				        feedbackIcons: {
				            valid: 'glyphicon glyphicon-ok',
				            invalid: 'glyphicon glyphicon-remove',
				            validating: 'glyphicon glyphicon-refresh'
				        },
				        fields:{
				        	oldPwd:{
				        		validators:{
				        			notEmpty:{
				        				message:"请输入密码"
				        			},
				        			stringLength:{
				        				min:6,
				        				max:20,
				        				message:"密码至少有6位"
				        			}
				        		}
				        	},
				        	password:{
				        		validators:{
				        			notEmpty:{
				        				message:"请输入密码"
				        			},
				        			stringLength:{
				        				min:6,
				        				max:20,
				        				message:"密码至少有6位"
				        			}
				        			
				        		}
				        	},
				        	surePassword:{
				        		validators:{
				        			notEmpty:{
				        				message:"请确认密码"
				        			},
				        			identical: {
						        		field: 'password',
						        		message: '密码不相符'
						        	},
				        		}
				        	}
				        	
				        
				        }
				 });
				
				$("#save").click(function(){
					var oldPwd = $("#oldPwd").val();
					var password = $("#password").val();
					if(confirm("您确定要操作吗？")){
						$.ajax({
							url:'${path }/user/updatePwd.action',
							type:'post',
							data:{"oldPwd":oldPwd,"password":password},
							success:function(data){
								alert(data);
							}
						})
					}
				})
			
			})
		</script>
</head>
	
<body>
	<div id="header">
			<form name=frm id=frm>
				<div class="logo"> 
					<div class="anniu_kuang" >
					    <div style="display: block;height: 35px">	
									<a type="button" href="#" id="logout1" class="btn btn-primary btn-sm" >
										<img src="${path }/public/img/logout.png"  width="25px" height="25px"  border="0" >
									</a>
									<input type="hidden" id="logout" value="logout">
							
									<a type="button" href="#" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
										<img src="${path }/public/img/pwd.png" width="25px" height="25px"  border="0" >
									</a>
							
						</div>
						<div  class="message">
									<span id="userInfoSpan">管理员：${CurrentUser.name }</span>&nbsp;
									<font id="sysDate"></font>
						</div>
					</div>
				</div>
			</form>
		</div>
	<div class="framebottom">
		
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">密码修改</h4>
		      </div>
		      <div class="modal-body">
			       <form class="form-horizontal" id="updatePwd" action="" method="post">
					  <div class="form-group">
					    <label for="userId" class="col-sm-3 control-label">用户代码</label>
					    <div class="col-sm-9">
					      <input type="text" class="form-control" id="userId" name="userId" value="${CurrentUser.userId }" readonly="readonly">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="name" class="col-sm-3 control-label">用户名称</label>
					    <div class="col-sm-9">
					      <input type="text" class="form-control" id="name" name="name" value="${CurrentUser.name }" readonly="readonly">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="oldPwd" class="col-sm-3 control-label">原密码</label>
					    <div class="col-sm-8">
					      <input type="password" class="form-control" name="oldPwd" id="oldPwd" >
					    </div>
					    <span class="check_input">*</span>
					  </div>
					  <div class="form-group">
					    <label for="password" class="col-sm-3 control-label">新密码</label>
					    <div class="col-sm-8">
					      <input type="password" class="form-control" id="password" name="password">
					    </div>
					    <span class="check_input">*</span>
					  </div>
					  <div class="form-group">
					    <label for="surePassword" class="col-sm-3 control-label">确认新密码</label>
					    <div class="col-sm-8">
					      <input type="password" class="form-control" id="surePassword" name="surePassword">
					    </div>
					    <span class="check_input">*</span>
					  </div>
					  
			</form>
		      </div>
		      <div class="modal-footer">
		      	 <button type="submit" class="btn btn-primary" id="save">保存</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		       
		      </div>
		    </div>
		  </div>
		</div>
</body>
</html>
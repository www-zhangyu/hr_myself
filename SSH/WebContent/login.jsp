<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="common.jsp" %>
<%@ include file="/public/message.jsp" %>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
		span{
			color:red;
			font-size:10px;
		}
	
</style>



<script type="text/javascript">
	$(function(){
		$("#userId").focus();///userId获得焦点
		////验证表单
		$("#login_form").bootstrapValidator({
			 message: 'This value is not valid',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields:{
		        	username:{
		        		validators:{
		        			notEmpty:{
		        				message:"请填写用户名"
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
		        	randNumInput:{
		        		validators:{
		        			notEmpty:{
		        				message:"请填写验证码"
		        			}
		        		}
		        	}
		        	
		        
		        }
		 });	 
		 ////看不清  换一张
		$("#changeImg").click(function(){
			var imgCode=document.getElementById('imgCode');
			imgCode.src="${path}/jsp/logoncheckimage.jsp?" + Math.random();
		});	
	})
	
	
	//按enter键时
		function keyEnter(event)
		{
			if ( event.keyCode == 13 )
			{
				check();
			}
		}
		var submit=0;
		//提交表单
		function check(){    
		    if(++submit>1){
		    	return false;}
		    else{
		    	frmChk();
		        return true;}
		}
		function frmChk()
		{
			frm.submit();	
		}
		
</script>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" action="${path }/user/login.action" id="login_form" role="form" method="post" name="frm">
		  <div class="form-group">
		    <label for="username" id="username" class="col-sm-2 control-label">用户名</label>		   
		    <div class="col-sm-3">
		      <input type="text" name="username" class="form-control" id="username" value="${usernameS}" onkeydown="keyEnter();">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="password" class="col-sm-2 control-label">密码</label>
		    <div class="col-sm-3">
		      <input type="password" name="password" class="form-control" id="password"  onkeydown="keyEnter();">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="randNumInput" class="col-sm-2 control-label">验证码</label>
		    <div class="col-sm-3">
		      <input type="text" name="randNumInput" class="form-control" id="randNumInput">
		      <img src="${path }/jsp/logoncheckimage.jsp?,Math.random();" id="imgCode"/>
		      <a id="changeImg">看不清,换一张</a>
		    </div>
		  </div>
		  <div>
		  	
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" id="login" class="btn btn-default active" onclick="check();" onkeydown="keyEnter();">登录</button>
		      <a href="login.jsp" class="btn btn-default active" ">重置</a>
		    </div>
		  </div>
		</form>
	</div>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp" %>
<%@ include file="/public/message.jsp" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<script type="text/javascript">

resources = '';///全局变量  保存当前用户的所有权限


//////页面加载时  默认当前页是第一页  页面大小是1 
	$(function(){
		var pageCount = $('#pageCount option:selected').text();///每页大小
		/////根据每页大小 当前页  查询条件获取当页数据
		loadData(1,pageCount,'');
		
		$("#pageCount").change(function(){
			var pageSize = $('#pageCount option:selected').text();///每页大小
			var name = $("#name").val();
			$("#curPage").html(1);
			loadData(1,pageSize,name);
		})
		
		/* 获取当前用户的所有权限 */
			$.ajax({
				url:'${path}/user/getUserResources.action',
				type:'post',
				success:function(data){
					resources = data;
					
				}
			})
		
		
		//确定增加
		$("#save_add").click(function(){
			
			///验证表单信息
			
			var name = $("#add_userName").val();
			var age = $("#add_userAge").val();
			var sex = $("#add_userSex").val();
			var phone = $("#add_userPhone").val();
			var mail = $("#add_userMail").val();
			var hireDate = $("#add_hireDate").val();
			var bankAccount = $("#add_bankAccount").val();
			var shouldWage = $("#add_shouldWage").val();
			var depName = $("#depName").val();
			if(name.length == 0){
				alert("请填写用户名称！");
				return false;
			}else if(depName == '全部'){
				alert("请选择所在部门");
				return false;
			}
			else if(age.length == 0){
				alert("请填写用户年龄");
				return false;
			}else if(phone.length < 0){
				alert("请填写11位手机号");
				return false;
			}else if(!mail.match(/^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/)){
				alert("邮箱格式不正确！请重新输入");
				return false;
			}else if(shouldWage.length == 0){
				alert("请填写入职工资");
				return false;
			}else if(hireDate.length == 0){
				alert("请填写入职日期");
				return false;
			}else{
				if(luhmCheck(bankAccount) == false) {////验证银行卡号
					return false;
				};
			}

			$("#add_form").submit();
		})
		
		//修改
		$("#saveChange").click(function(){
			///验证邮箱和电话
			var phone = $("#edit_userPhone").val();
			var mail = $("#edit_userMail").val();
			if(phone.length < 11){
				alert("请输入11位手机号码");
				return false;
			}
			if(!mail.match(/^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/)){
				alert("邮箱格式不正确！请重新输入");
				return false;
			}
			$("#edit_form").submit();
		})
		
		
		///查询数据库中所有的部门名称
		var option ='<option>全部</option>';
		$.ajax({
			url:'${path}/department/selectAllDepartment.action',
			type:'post',
			dataType:'json',
			success:function(data){
				for(var i = 0; i < data.length; i++){
					
					option += '<option value='+data[i].depId+'>'+data[i].depName+'</option>';
					//alert(option);
					$("#depName").html(option);
				}
			}
		})

		
	})
	

	/* 加载数据 */
	function loadData(curPage,pageCount,key){
		var tbody = $("#users").find("tbody");
		tbody.empty();	//清空原有数据
		$("#pagination").empty();
		$.ajax({
			url:'${path}/user/listAllUser.action',
			type:'post',
			data:{"curPage":curPage,"pageCount":pageCount,"key":key},
			dataType:'json',
			success:function(jsonData){
				var data = jsonData.data;	//请求得到的数据
				var totalPage = jsonData.totalPage;	//总页数
				$("#totalPage").html(totalPage);
				
				var li = "<li><a href='javascript:loadPerPage()' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
				for(var j=1;j<=totalPage;j++){
					li += "<li><a id=cur_"+j+" href='javascript:loadCurPage("+j+");'>"+j+"</a></li>";
				}
				li += "<li><a href='javascript:loadNextPage()' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
				$("#pagination").append(li);
				
				for(var i=0;i<data.length;i++){
					var trs = "<tr id=tr_"+data[i].userId+"><td><input type='checkbox' value='"+data[i].userId+"' name='checkbox'></td>"+
					    "<td>"+data[i].userId+"</td><td>"+data[i].name+"</td><td>"+data[i].depName+"</td><td>"+data[i].roleName+"</td>"+
					    "<td>"+data[i].userAge+"</td><td>"+data[i].userSex+"</td><td>"+data[i].userPhone+"</td>"+
					    "<td>"+data[i].userMail+"</td><td>"+data[i].hireDate+"</td>"+
					    "<td><a href='#' onclick=deleteUser('"+data[i].userId+"')><span  style='color:red;' class='glyphicon glyphicon-trash' aria-hidden='true'></span></a>"+
					    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#'  onclick=updateUser('"+data[i].userId+"')><span style='color:#286090;' class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a></td></tr>";
					tbody.append(trs);
				}
			}
		})
	}
	
	
	/* 加载指定页面的内容 */
	function loadCurPage(curPage){
		var pageCount = $('#pageCount option:selected').text();///每页大小
		var name = $("#name").val();
		$("#curPage").html(curPage);
		loadData(curPage,pageCount,name);
	}
	
	/* 加载上一页数据 */
	function loadPerPage(){
		///当前页
		var curPage = $("#curPage").html()-1;
		if(curPage == 0){
			curPage = 1;
		}
		
		var pageCount = $('#pageCount option:selected').text();///每页大小
		
		var name = $("#name").val();
		
		$("#curPage").html(curPage);
		
		loadData(curPage,pageCount,name);
	}
	
	
	/* 加载下一页数据 */
	function loadNextPage(){
		///当前页
		var curPage = $("#curPage").html()*1+1*1;
		var total = $("#totalPage").html();
		
		if(curPage > total){
			curPage -= 1; 
		}
		var pageCount = $('#pageCount option:selected').text();///每页大小
		
		var name = $("#name").val();
		
		$("#curPage").html(curPage);
		
		loadData(curPage,pageCount,name);
	}
	
	/* 加载模糊查询的结果 */
	function loadSeachData(){
		var pageCount = $('#pageCount option:selected').text();///每页大小
		var name = $("#name").val();
		loadData(1,pageCount,name);
	}
	
	/* 改变复选框的状态 */
	function changeStatus(){
		   if($("#all_status").prop("checked")){
				 $("[name='checkbox']").prop("checked",true);//全选  attr只能实现一次全选
			}else{
				 $("[name='checkbox']").prop("checked", false);
			}
	   }
	
	/* 增加用户，判断有无权限 */
	function addUser(){
		if (resources.indexOf("addUser") > 0) {
			$('#addModal').modal('show');
		}else {
			alert("您没有权限");
			return false;
		}
	 	
	}
	
	
	/* 批量删除，确定有无权限 */
	function deleteSelected(){
		if(resources.indexOf("deleteUser")<0){
			alert("您没有权限");
			return false;
		}
		var selectedCheckbox='';

		$("input[name=checkbox]").each(function(){
			if($(this).prop("checked")){
				selectedCheckbox += $(this).val() + ",";
			}	
		})
		if(selectedCheckbox < 1){
			alert("请选中至少一个资源");
			return false;
		}
		if(confirm("您确定要删除所选资源吗")){
			$.ajax({
				url:'${path}/user/deleteSelectedUsers.action',
				type:'post',
				data:{"ids":selectedCheckbox},
				success:function(data){
					alert(data);
					document.frames('main_frame').location.reload();///重新加载父页面
				}
			})
		}
	}
	
	/* 删除指定用户 */
	function deleteUser(id){
		 if(confirm("您确定要删除该用户吗？")){
			   $.ajax({
				   url:'${path}/user/deleteSelectedUsers.action',
				   type:'post',
				   data:{"ids":id},
				   success:function(data){
					   alert(data);
					   document.frames('main_frame').location.reload();///重新加载父页面
				   }
			   })
		   }
	}
	
	function updateUser(id){
			 
		if (resources.indexOf("updateUser") < 0) {
			alert("您没有权限");
			return false;
		}
		
		var table = $("#users");
		var tr = table.find('tbody').find('#tr_' + id);
		var tds = tr.find('td');    //取tr下的所有td
		
		/* 向模态框中填充信息 */
	   var id = tds.eq(1).text();///资源id
	   $("#edit_userId").val(id);
	   var name = tds.eq(2).text();///名称
	   $("#edit_name").val(name);
	   var age = tds.eq(5).text();///年龄
	   $("#edit_userAge").val(age);
	   
	   var sex = tds.eq(7).text();///性别
	   if(sex == "女"){
		   $("#edit_userSex option").eq(0).attr("selected",true);
	   }else{
		   $("#edit_userSex option").eq(1).attr("selected",true);
	   }
	  
	   
	   var phone = tds.eq(8).text();///电话
	   $("#edit_userPhone").val(phone);
	   
	   var mail = tds.eq(9).text();///邮箱
	   $("#edit_userMail").val(mail);
		
		
		$('#updateModal').modal('show');
		
	}
	
	
</script>
</head>
<body>
	<div class="container" style="width: 100%;">
		
		<!-- 导航条 -->
			<ol class="breadcrumb" style="margin-bottom: 0px;">
			  <li><a href="#">Home</a></li>
			  <li class="active">用户管理</li>
			</ol>
			<nav class="navbar navbar-default" style="margin-bottom: 3px;margin-top: 3px;">
	  			<div class="container-fluid">
	    			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	    				
	      				<form class="navbar-form navbar-left" >
	      					<span>用户名称</span>
	        				<div class="form-group">
	          					<input type="text" class="form-control" name="name" id="name">
	        				</div>
	        				<button type="button" class="btn btn-default" id="seach" onclick="loadSeachData()">查询</button>
	      				</form>
	    			</div>
	  			</div>
			</nav>
			
			<div style="margin-bottom: 5px;margin-top: 5px;margin-left: 15px;">
				<button type="button" class="btn btn-primary" id="add" onclick="addUser()">增加</button>
				<button type="button" class="btn btn-danger" id="delete_selected" onclick="deleteSelected()">删除</button>
			</div>
			
			<table class="table table-hover" id="users" style="height:80%;border: 0px solid red;">
				<thead>
					<tr style="width: 200px;">
						<th><input type="checkbox" id="all_status" name="checkbox" onchange=changeStatus()></th>
						<th >id</th>
						<th>用户名</th>
						<th>部门</th>
						<th>角色</th>
						<th>年龄</th>
						<th >性别</th>
						<th>电话</th>
						<th>邮箱</th>
						<th>入职日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			
			
			<div id="paging" style="position: fixed;bottom: 0;border: 0px solid red;width: 100%;height: 50px;">
				<div style="width: 250px;float: left;margin: 16px;">
					<span>每页</span>
					<select id="pageCount">
						<option >1</option>
						<option selected="selected">5</option>
						<option>10</option>
					</select>
					<span>条</span>
					<span>一共</span>
					<span id="totalPage">${totalPage }</span>
					<span>页</span>
					<span>当前第</span>
					<span id="curPage">1</span>
					<span>页</span>
				</div>
				<nav aria-label="Page navigation" style="display:inline;">
				  <ul class="pagination" style="margin: 8px;" id="pagination">

				  </ul>
				</nav>
			</div> 
				
		<!-- 添加用户的模态框 -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title" id="myModalLabel">增加用户</h4>
      				</div>
      				<div class="modal-body">
        				<form class="form-horizontal" action="${path }/user/addUser.action" method="post" id="add_form">
        				
        					
  							
  							<div class="form-group">
    							<label for="add_userName" class="col-sm-2 control-label">用户名</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_userName" name="add_userName">
    							</div>
  							</div>
  							
  							
  							<div class="form-group">
    							<label for="add_userDep" class="col-sm-2 control-label">所在部门</label>
    							<div class="col-sm-10">
      								<select class="form-control" name="depName" id="depName">
							    		<option>--请选择--</option>
							    	</select>
    							</div>
  							</div>
  							
  							<div class="form-group" >
    							<label for="add_userAge" class="col-sm-2 control-label">年龄</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_userAge" name="add_userAge">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_userSex" class="col-sm-2 control-label">性别</label>
    							<div class="col-sm-10">
    								<select class="form-control" id="add_userSex" name="add_userSex">
    									<option selected="selected">女</option>
    									<option>男</option>
    								</select>
      								
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_userPhone" class="col-sm-2 control-label">电话</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_userPhone" name="add_userPhone">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_userMail" class="col-sm-2 control-label">邮箱</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_userMail" name="add_userMail">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_bankAccount" class="col-sm-2 control-label">银行帐号</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_bankAccount" name="add_bankAccount">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_shouldWage" class="col-sm-2 control-label">月工资</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_shouldWage" name="add_shouldWage">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_hireDate" class="col-sm-2 control-label">入职日期</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_hireDate" name="add_hireDate" onClick="WdatePicker()">
    							</div>
  							</div>
						</form>
      				</div>
      				<div class="modal-footer">
        				<button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
        				<button type="button" class="btn btn-primary" id="save_add">保存</button>
      				</div>
    			</div>
  			</div>
		</div>
		
		
		<!-- 修改用户的模态框 -->
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title" id="myModalLabel">修改用户信息</h4>
      				</div>
      				<div class="modal-body">
        				<form class="form-horizontal" action="${path }/user/updateUser.action" method="post" id="edit_form">
        				
        				
        					<div class="form-group" >
    							<label for="edit_userId" class="col-sm-2 control-label">用户编号</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_userId" name="edit_userId" readonly="true">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_name" class="col-sm-2 control-label">用户名称</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_name" name="edit_name" readonly="readonly">
    							</div>
  							</div>
  							
  							
  							
  							<div class="form-group">
    							<label for="edit_userAge" class="col-sm-2 control-label">年龄</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_userAge" name="edit_userAge">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_userSex" class="col-sm-2 control-label">性别</label>
    							<div class="col-sm-10">
      								<select class="form-control" id="edit_userSex" name="edit_userSex">
    									<option>女</option>
    									<option>男</option>
    								</select>
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_userPhone" class="col-sm-2 control-label">联系方式</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_userPhone" name="edit_userPhone">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_userMail" class="col-sm-2 control-label">电子邮箱</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_userMail" name="edit_userMail">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_bankAccount" class="col-sm-2 control-label">银行帐号</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_bankAccount" name="edit_bankAccount" value="${CurrentUser.bankAccount }">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_shouldWage" class="col-sm-2 control-label">月工资</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_shouldWage" name="edit_shouldWage">
    							</div>
  							</div>
 
						</form>
      				</div>
      				<div class="modal-footer">
        				<button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
        				<button type="button" class="btn btn-primary" id="saveChange">保存</button>
      				</div>
    			</div>
  			</div>
		</div>		
		
	</div>

</body>
</html>
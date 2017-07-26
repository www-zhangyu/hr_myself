<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp" %>
<%@ include file="/public/message.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	
	.table tbody tr td{
		padding-top: 10px;
		padding-bottom: 10px;
	}
</style>
<script type="text/javascript">
   $(function(){
		$.ajax({
			type : "POST",
			url : '${path }/resources/resources.action',
			dataType : 'json',
			success : function(data) {
				var tr = $("#resources");
				var content = '';
				var td ='';
				$.each(data, function(i) {/*  */
					content += "<tr id='tr_"+data[i].id+"'><td><input type='checkbox' value='"+data[i].id+"' name='checkbox'></td><td >"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].parentName+"</td><td>"+data[i].resKey+"</td><td>"+data[i].resUrl+"</td><td>"+data[i].level+"</td><td>"+data[i].description+"</td>"+
								"<td><a href='#' onclick=deleteResources('"+data[i].id+"')><span  style='color:red;' class='glyphicon glyphicon-trash' aria-hidden='true'></span></a>"+
								"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#'  onclick=updateResources('"+data[i].id+"')><span style='color:#286090;' class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a></td></tr>";
					var jdata = data[i].children;
					$.each(jdata, function(j) {
						content += "<tr  id='tr_"+jdata[j].id+"'><td><input type='checkbox' value='"+jdata[j].id+"' name='checkbox'></td><td >"+jdata[j].id+"</td><td style='padding-left: 20px;'>"+jdata[j].name+"</td><td>"+jdata[j].parentName+"</td><td>"+jdata[j].resKey+"</td><td>"+jdata[j].resUrl+"</td><td>"+jdata[j].level+"</td><td>"+jdata[j].description+"</td>"+
								"<td><a href='#' onclick=deleteResources('"+jdata[j].id+"')><span style='color:red;' class='glyphicon glyphicon-trash ' aria-hidden='true'></span></a>"+
								"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#'  onclick=updateResources('"+jdata[j].id+"')><span style='color:#286090;' class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a></td></tr>"
					});
				
				});
				tr.append(content);
				
				
			}
		});
		
		/////批量删除
		$("#delete_selected").click(function(){
			var selectedCheckbox='';

			$("input[name=checkbox]").each(function(){
				if($(this).prop("checked")){
					selectedCheckbSox += $(this).val() + ",";
				}	
			})
			if(selectedCheckbox < 1){
				alert("请选中至少一个资源");
				return false;
			}
			if(confirm("您确定要删除所选资源吗")){
				$.ajax({
					url:'${path}/resources/deleteSelectedResources.action',
					type:'post',
					data:{"ids":selectedCheckbox},
					success:function(data){
						alert(data);
						parent.location.reload();///重新加载父页面
					}
				})
			}
		})
		
		
		//修改
		$("#saveChange").click(function(){
			$("#edit_form").submit();
		})
		
		//增加
		$("#add").click(function(){
			//add_resId add_resName add_parentId add_resUrl add_resKey add_resType
			
			$('#addModal').modal('show');
		})
		//确定增加
		$("#save_add").click(function(){
			
			///验证表单信息
			var id = $("#add_resId").val();
			var name = $("#add_resName").val();
			var pid = $("#add_parentId").val();
			var url = $("#add_resUrl").val();
			var key = $("#add_resKey").val();
			var type = $("#add_resType").val();
			if(id.length==0){
				alert("请填写资源ID！");
				return false;
			}else if(name.length==0){
				alert("请填写资源名称！");
				return false;
			}else if(pid.length==0){
				alert("请指定该资源的父类资源！");
				return false;
			}else if(url.length==0){
				alert("请填写资源对应的url!");
				return false;
			}else if(key.length==0){
				alert("请填写资源对应的关键字");
				return false;
			}else if(type.length == 0 ||type >3 || type < 0){
				alert("请填写该资源的类型：1为一级菜单2为二级菜单3为按钮");
				return false;
			}	

			$("#add_form").submit();
		})
		
})    

	/////////////删除单个资源
	function deleteResources(id){
	   if(confirm("您确定要删除该权限吗？")){
		   $.ajax({
			   url:'${path}/resources/deleteOneResources.action',
			   type:'post',
			   data:{"resId":id},
			   success:function(data){
				   alert(data);
				   parent.location.reload();///重新加载父页面
			   }
		   })
	   }
   }
   

   /////////////向模态框中添加信息
   function updateResources(id){
	   var table = $("#resources");
	   var tr = table.find('tbody').find('#tr_' + id);
	   var tds = tr.find('td');    //取tr下的所有td
	   
	   var id = tds.eq(1).text();///资源id
	   $("#edit_resId").val(id);
	   var name = tds.eq(2).text();///资源名称
	   $("#edit_resName").val(name);
	   var resKey = tds.eq(4).text();///资源Key
	   $("#edit_resKey").val(resKey);
	   var resUrl = tds.eq(5).text();///资源url
	   $("#edit_resUrl").val(resUrl);
	   
	   var level = tds.eq(6).text();///资源序号
	   $("#edit_level").val(level);
	   
	   var des = tds.eq(7).text();///资源描述
	   $("#edit_resdes").val(des);
	   $('#myModal').modal('show');
   }
   
   
   function changeStatus(){
	   if($("#all_status").prop("checked")){
			 $("[name='checkbox']").prop("checked",true);//全选  attr只能实现一次全选
		}else{
			 $("[name='checkbox']").prop("checked", false);
		}
   }
	
</script>
</head>
<body>
	
			
	<div class="container" style="width: 100%;">
			<!-- 导航条 -->
			<ol class="breadcrumb" style="margin-bottom: 0px;">
			  <li><a href="#">Home</a></li>
			  <li><a href="active">权限设置</a></li>
			</ol>
		<!-- 模糊查询 -->
		<!-- <nav class="navbar navbar-default" style="margin-bottom: 10px;">
  			<div class="container-fluid">
    			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    				
      				<form class="navbar-form navbar-left" action="/resources/getResourcesByName.action" method="post">
      					<span>资源名称</span>
        				<div class="form-group">
          					<input type="text" class="form-control" name="resName">
        				</div>
        				<button type="submit" class="btn btn-default">查询</button>
      				</form>
    			</div>
  			</div>
		</nav> -->
		<div style="margin: 5px;">
			<button type="button" class="btn btn-primary" id="add">增加</button>
			<button type="button" class="btn btn-danger" id="delete_selected">删除</button>
		</div>
		<table class="table table-hover" id="resources" style="height:80%;border: 0px solid red;">
			<tr style="width: 200px;">
				
				<th><input type="checkbox" id="all_status" name="checkbox" onchange="changeStatus();"></th>
				<td >id</td>
				<th>name</th>
				<th>父类权限</th>
				<th >resKey</th>
				<th style="width: 350px;">resUrl</th>
				<th>level</th>
				<th>description</th>
				<th>操作</th>
			</tr>
		</table>
		
		
		<!-- -->
		
		<!-- 修改资源的模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title" id="myModalLabel">修改资源信息</h4>
      				</div>
      				<div class="modal-body">
        				<form class="form-horizontal" action="${path }/resources/updateResources.action" method="post" id="edit_form">
        				
        				
        					<div class="form-group" >
    							<label for="edit_resId" class="col-sm-2 control-label">资源id</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_resId" name="edit_resId" readonly="true">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_resName" class="col-sm-2 control-label">资源名称</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_resName" name="edit_resName">
    							</div>
  							</div>
  							
  							
  							
  							<div class="form-group">
    							<label for="edit_resUrl" class="col-sm-2 control-label">资源URL</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_resUrl" name="edit_resUrl">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_resKey" class="col-sm-2 control-label">资源Key</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_resKey" name="edit_resKey">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_level" class="col-sm-2 control-label">资源序号</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_level" name="edit_level">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="edit_des" class="col-sm-2 control-label">资源描述</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="edit_des" name="edit_des">
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
		
		<!-- 添加资源的模态框 -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title" id="myModalLabel">增加资源信息</h4>
      				</div>
      				<div class="modal-body">
        				<form class="form-horizontal" action="${path }/resources/addResources.action" method="post" id="add_form">
        				
        					<div class="form-group" >
    							<label for="add_resId" class="col-sm-2 control-label">资源id</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_resId" name="add_resId">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_resName" class="col-sm-2 control-label">资源名称</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_resName" name="add_resName">
    							</div>
  							</div>
  							
  							<div class="form-group" >
    							<label for="add_parentId" class="col-sm-2 control-label">父类资源</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_parentId" name="add_parentId">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_resUrl" class="col-sm-2 control-label">资源URL</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_resUrl" name="add_resUrl">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_resKey" class="col-sm-2 control-label">资源Key</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_resKey" name="add_resKey">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_resType" class="col-sm-2 control-label">资源类型</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_resType" name="add_resType">
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="add_des" class="col-sm-2 control-label">资源描述</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_des" name="add_des">
    							</div>
  							</div>
  							
  							
  							
  							<div class="form-group">
    							<label for="add_level" class="col-sm-2 control-label">资源序号</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="add_level" name="add_level">
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
		
	</div>
</body>
</html>
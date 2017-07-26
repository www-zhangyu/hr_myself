<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp" %>
<%@ include file="/public/message.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

resources = '';///全局变量  保存当前用户的所有权限
	$(function(){
		
		/* 获取当前用户的所有权限 */
		$.ajax({
			url:'${path}/user/getUserResources.action',
			type:'post',
			success:function(data){
				resources = data;
				
			}
		})
		
		var pageSize = $('#pageCount option:selected').text();///每页大小
		/////根据每页大小 当前页  查询条件获取当页数据
		loadData(1,pageSize,'','','');
		
		$("#pageCount").change(function(){
			var pageSize = $('#pageCount option:selected').text();///每页大小
			var userName = $("#userName").val();
			var depName = $("#depName").val();
			 if(depName == "全部"){
					depName = '';
				} 
			var date = $("#date").val();
			loadData(1,pageSize,userName,depName,date)
		})
		
		
			///查询数据库中所有的部门名称
			var option ='<option>全部</option>';
			$.ajax({
				url:'${path}/department/selectAllDepartment.action',
				type:'post',
				dataType:'json',
				success:function(data){
					for(var i = 0; i < data.length; i++){
						
						option += '<option>'+data[i].depName+'</option>';
						//alert(option);
						$("#depName").html(option);
					}
				}
			})
	
	})
	
	/////根据条件加载数据
	function loadData(curPage,pageSize,userName,depName,date){
		var tbody = $("#attendances").find("tbody");
		tbody.empty();	//清空原有数据
		$("#pagination").empty();
		$.ajax({
			url:'${path}/attendance/listAttendance.action',
			data:{"curPage":curPage,"pageSize":pageSize,"userName":userName,"depName":depName,"date":date},
			type:'post',
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
					var trs = "<tr id=tr_"+data[i].id+"><td>"+data[i].id+"</td>"+
					    "<td>"+data[i].userId+"</td><td>"+data[i].userName+"</td><td>"+data[i].depName+"</td><td>"+data[i].checkDate+"</td><td>"+data[i].checkInTime+"</td>"+
					    "<td>"+data[i].checkInStatus+"</td><td>"+data[i].checkOutTime+"</td><td>"+data[i].checkOutStatus+"</td>"+
					    "<td><a href='#' onclick=deleteUser('"+data[i].id+"')><span  style='color:red;' class='glyphicon glyphicon-trash' aria-hidden='true'></span></a>"+
					    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#'  onclick=updateUser('"+data[i].id+"')><span style='color:#286090;' class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a></td></tr>";
					tbody.append(trs);
				}
			}
		})
	}
	
	////模糊查询
	function loadSeachData(){
		$("#curPage").html(1);
		var pageSize = $('#pageCount option:selected').text();///每页大小
		var userName = $("#userName").val();
		var depName = $("#depName").val();
		 if(depName == "全部"){
				depName = '';
			} 
		var date = $("#date").val();
		loadData(1,pageSize,userName,depName,date);
	}
	
	/////加载指定页面
	function loadCurPage(curPage){
		var pageSize = $('#pageCount option:selected').text();///每页大小
		
		var userName = $("#userName").val();
		var depName = $("#depName").val();
		 if(depName == "全部"){
			depName = '';
		} 
		
		var date = $("#date").val();
		$("#curPage").html(curPage);
		loadData(curPage,pageSize,userName,depName,date);
	}
	
	////加载上一页数据
	function loadPerPage(){
		var curPage = $("#curPage").html()-1;
		if(curPage == 0){
			curPage = 1;
		}
		var pageSize = $('#pageCount option:selected').text();///每页大小
		var userName = $("#userName").val();
		var depName = $("#depName").val();
		 if(depName == "全部"){
				depName = '';
			} 
		var date = $("#date").val();
		$("#curPage").html(curPage);
		loadData(curPage,pageSize,userName,depName,date);
	}
	
	////加载下一页数据
	function loadNextPage(){
		var curPage = $("#curPage").html()*1+1*1;
		var total = $("#totalPage").html();
		
		if(curPage > total){
			curPage -= 1; 
		}
		var pageSize = $('#pageCount option:selected').text();///每页大小
		var userName = $("#userName").val();
		var depName = $("#depName").val();
		 if(depName == "全部"){
				depName = '';
			} 
		var date = $("#date").val();
		$("#curPage").html(curPage);
		loadData(curPage,pageSize,userName,depName,date);
	}
	
	function exportExcel(){
		//alert(resources);
		if (resources.indexOf("exportExcel") > 0) {
			if(confirm("你要导出当月的考勤表吗？")){
				$.ajax({
					url:'${path}/attendance/exportExcel.action',
					type:'post',
					success:function(data){
						//alert("导出成功");
					}
				})
			}
		}else{
			alert("您没有该权限！");
			return false;
			
		}
		
	}
</script>
</head>
<body>
	<div class="container" style="width: 100%;">
	
		<!-- 导航条 -->
			<ol class="breadcrumb" style="margin-bottom: 0px;">
			  <li><a href="#">Home</a></li>
			  <li class="active">考勤管理</li>
			</ol>
			
			<nav class="navbar navbar-default" style="margin-bottom: 3px;margin-top: 3px;">
	  			<div class="container-fluid">
	    			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	    				<form class="form-inline" style="padding-top: 7px;">
						  <div class="form-group">
						    <label for="userName">用户名称</label>
						    <input type="text" class="form-control" name="userName" id="userName">
						  </div>
						  <div class="form-group">
						    <label for="depName">部门</label>
						    <select class="form-control" name="depName" id="depName">
						    	<option>--请选择--</option>
						    </select>
 						  </div>
						  <div class="form-group">
						    <label for="date">考勤日期</label>
						    <input type="text" class="form-control" name="date" id="date" onClick="WdatePicker()">
						  </div>
						  <button type="button" class="btn btn-default" id="seach" onclick="loadSeachData()">查询</button>
						  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default" id="export" onclick="exportExcel()">导出当月考勤数据</button>

						</form>
	      				
	    			</div>
	  			</div>
			</nav>
			
		<table class="table table-hover" id="attendances">
  			<thead>
  				<tr>
  					<th>考勤编号</th>
  					<th>员工编号</th>
  					<th>员工姓名</th>
  					<th>所在部门</th>
  					<th>日期</th>
  					<th>签到时间</th>
  					<th>签到状态</th>
  					<th>签退时间</th>
  					<th>签退状态</th>
  					<th>操作</th>
  				</tr>
  			</thead>
  			<tbody>
  				
  			</tbody>
		</table>
		
		<div id="paging" style="position: fixed;bottom: 0;border: 0px solid red;width: 100%;height: 50px;">
				<div style="width: 250px;float: left;margin: 16px;">
					<span>每页</span>
					<select id="pageCount" name="pageSize">
						<option >1</option>
						<option selected="selected">5</option>
						<option>10</option>
					</select>
					<span>条</span>
					<span>一共</span>
					<span id="totalPage"></span>
					<span>页</span>
					<span>当前第</span>
					<span id="curPage">1</span>
					<span>页</span>
				</div>
				<nav aria-label="Page navigation" style="display:inline;">
				  <ul class="pagination" style="margin: 8px;" id="pagination">

				  </ul>
				</nav>
				<div style="float: right;color: red;padding-top: 10px;padding-right: 15px;">签到状态：1为正常;2为迟到;3为旷工;4为请假  签退状态：-2为早退;0为正常</div>
			</div> 
	</div>
</body>
</html>
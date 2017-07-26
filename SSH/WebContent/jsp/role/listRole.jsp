<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp" %>
<%@ include file="/public/message.jsp" %>
<html>
<head>
	<script type="text/javascript">
		$(function(){
			var pageCount = $('#pageCount option:selected').text();///每页大小
			/////根据每页大小 当前页  查询条件获取当页数据
			loadData(1,pageCount,'');
			

			///改变每页大小时
			$("#pageCount").change(function(){
				var pageSize = $('#pageCount option:selected').text();///每页大小
				var name = $("#name").val();
				$("#curPage").html(1);
				loadData(1,pageSize,name);
			});
			
			$("#save_update").click(function(){
				$("#addRoleToUser_form").submit();
			})
			
		});
		
		///加载数据
		function loadData(curPage,pageCount,key){
			var tbody = $("#user_role").find("tbody");
			tbody.empty();	//清空原有数据
			$("#pagination").empty();
			$.ajax({
				type:'post',
				url:'${path}/user/listAllUser.action',
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
						var trs = "<tr id=tr_"+data[i].userId+">"+
						    "<td>"+data[i].userId+"</td><td>"+data[i].name+"</td><td>"+data[i].depName+"</td><td>"+data[i].roleName+"</td>"+
						    "<td><button type='button' class='btn btn-info btn-sm' onclick=addRoleToUser('"+data[i].userId+"')>授权</button>"+
						    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
						tbody.append(trs);
					}
				}
			});
			
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
		
		/////授权
		function addRoleToUser(id){
			
			$("#userId").val(id);
			$("#addRoleToUser").modal('show');
			
		}
		
	</script>
</head>
<body>
	<div class="container" style="width: 100%;">
		<!-- 导航条 -->
		<ol class="breadcrumb" style="margin-bottom: 0px;">
			<li><a href="#">Home</a></li>
			<li class="active">角色管理</li>
		</ol>
		<!-- 快速查询 -->
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
			
			<table class="table table-hover" id="user_role" style="height:80%;">
				<thead>
					<tr style="width: 200px;">
						<th>用户编号</th>
						<th>用户名</th>
						<th>部门</th>
						<th>角色</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			
			<div id="paging" style="position: fixed;bottom: 0;width: 100%;height: 50px;">
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
			
	</div>
	
	
	<div class="modal fade" id="addRoleToUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title" id="myModalLabel">授权</h4>
      				</div>
      				<div class="modal-body">
        				<form class="form-horizontal" action="${path }/role/addRoleToUser.action" method="post" id="addRoleToUser_form">
        				
        					<div class="form-group" >
    							<label for="userId" class="col-sm-2 control-label">用户编号</label>
    							<div class="col-sm-10">
      								<input type="text" class="form-control" id="userId" name="userId" readonly="readonly"/>
    							</div>
  							</div>
  							
  							<div class="form-group">
    							<label for="user_role" class="col-sm-2 control-label">角色</label>
    							<div class="col-sm-10">
    								<select class="form-control" id="user_role" name="user_role">
    									<option selected="selected">超级管理员</option>
    									<option>管理员</option>
    									<option>普通员工</option>
    								</select>
      								
    							</div>
  							</div>
  							
  							
						</form>
      				</div>
      				<div class="modal-footer">
        				<button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
        				<button type="button" class="btn btn-primary" id="save_update">保存</button>
      				</div>
    			</div>
  			</div>
		</div>
</body>
</html>
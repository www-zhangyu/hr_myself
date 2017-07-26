<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp" %>
<head>
<script type="text/javascript">

	var totalCount = ${totalCount};
	var everyCount = 0;
	
	$(function(){
		
		sureData(totalCount);
		$("#count").change(function(){
			sureData(totalCount);
		})
		//加载第一页的数据
		goPage(1);
	})
	
	
	///确定每页的记录数和总页数
	function sureData(totalCount){

		var totalPage = 0;
		everyCount = $("#count").find("option:selected").text();///每页的记录数
		if(totalCount%everyCount == 0){/////计算总页数
			totalPage = parseInt(totalCount/everyCount);
		}else{
			totalPage = parseInt(totalCount/everyCount)+1;
		}
		$("#totalPage").html(totalPage);
		var li = "<li><a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
		for(var i=1;i<=totalPage;i++){
			li += "<li><a href='#' onclick=goPage("+i+")>"+i+"</a></li>"
		}
		li +="<li><a href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>"
		$("#pageView").html(li);
		
	}
	
	
	function goPage(i){
		$.ajax({
			url:'${path }/resources/resources.action',
			type:'post',
			data:{"currentPage":i,"everyCount":everyCount},
			dataType:'json',
			success:function(data){
				
			}
		})
	}
	
</script>
</head>
<body>
	<div class="container">
		<div style="width: 50%;float: left;margin-top: 16px;">
			<span>每页</span>
				<select id="count">
					<option selected="selected">2</option>
					<option>3</option>
					<option>5</option>
				</select>
			<span>条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总共</span>
			<span id="totalPage">0</span>
			<span>页</span>
		</div>
		<div style="display: inline;float: left;">	
			<nav aria-label="Page navigation" >
	  				<ul class="pagination" style="margin-top: 8px;" id="pageView">
	    				
	    				
	      			</ul>
				</nav>
			
		</div>
	</div>
</body>
</html>
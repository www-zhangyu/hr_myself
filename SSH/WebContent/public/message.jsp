<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 获取返回信息 -->
<c:if test="${not empty ReturnValue.info }">
	<script>
        alert('${ReturnValue.info}');
    </script>
</c:if>

<% request.removeAttribute("ReturnValue.info"); %>
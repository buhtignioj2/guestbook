<%@page import="com.cafe24.guestbook.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@page import="com.cafe24.guestbook.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	GuestbookDao dao = new GuestbookDao();
	List<GuestbookVo> list = dao.getList();
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="/guestbook/add.jsp" method="post">
		<table border="1" width="500">
			<tr>
				<td>이름</td><td><input type="text" name="name"></td>
				<td>비밀번호</td><td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<br>
	<%
	if( list != null ) {
	%>
	<%
	for( GuestbookVo vo : list ){
	%>
	<table width="510" border="1">
		<tr>
			<td><%=vo.getNo() %></td>
			<td><%=vo.getName() %></td>
			<td><%=vo.getRegDate() %></td>
			<td><a href="/guestbook/deleteform.jsp?no=<%=vo.getNo() %>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4">
			<%=vo.getContent().replace( "\n", "<br>" ) %>
			</td>
		</tr>
	</table>
	<br>
	<%
	}
	%>
	<%
	} else {
	%>
	<h3> 방명록없음 </h3>
	<%
	}
	%>
	
</body>
</html>
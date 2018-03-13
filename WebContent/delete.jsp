<%@page import="com.cafe24.guestbook.dao.GuestbookDao"%>
<%@page import="com.cafe24.guestbook.vo.GuestbookVo"%>
<%
	request.setCharacterEncoding( "UTF-8" );
	Long no = Long.parseLong( request.getParameter( "no" ));
	String password = request.getParameter( "password" );
	
	GuestbookVo vo = new GuestbookVo();
	vo.setNo( no );
	vo.setPassword( password );
	
	GuestbookDao dao = new GuestbookDao();
	dao.delete( vo );
	
	response.sendRedirect( "/guestbook" );
%>
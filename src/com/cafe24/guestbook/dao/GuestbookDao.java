package com.cafe24.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.guestbook.vo.GuestbookVo;

public class GuestbookDao {

    public boolean delete(GuestbookVo vo) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();

	    String sql = "delete from guestbook where no = ? and password = password(?)";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setLong( 1, vo.getNo() );
	    pstmt.setString( 2, vo.getPassword() );

	    int count = pstmt.executeUpdate();
	    result = ( count == 1 );

	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) {
		    pstmt.close();
		}
		if ( conn != null ) {
		    conn.close();
		}
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}
	return result;
    }
    
    public boolean insert(GuestbookVo vo) {
	boolean result = false;

	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();

	    String sql = "insert into guestbook values(null, ?, password(?), ?, now())";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setString( 1, vo.getName() );
	    pstmt.setString( 2, vo.getPassword() );
	    pstmt.setString( 3, vo.getContent() );

	    int count = pstmt.executeUpdate();
	    result = ( count == 1 );

	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) {
		    pstmt.close();
		}
		if ( conn != null ) {
		    conn.close();
		}
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }

    public List<GuestbookVo> getList() {
	List<GuestbookVo> list = new ArrayList<GuestbookVo>();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();

	    String sql = "select no, name, content, DATE_FORMAT(reg_date, '%Y-%m-%d %h:%i:%s') from guestbook order by no desc";
	    pstmt = conn.prepareStatement( sql );

	    rs = pstmt.executeQuery();
	    while ( rs.next() ) {
		Long no = rs.getLong( 1 );
		String name = rs.getString( 2 );
		String content = rs.getString( 3 );
		String regDate = rs.getString( 4 );

		GuestbookVo vo = new GuestbookVo();
		vo.setNo( no );
		vo.setName( name );
		vo.setContent( content );
		vo.setRegDate( regDate );

		list.add( vo );
	    }
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( rs != null ) {
		    rs.close();
		}
		if ( pstmt != null ) {
		    pstmt.close();
		}
		if ( conn != null ) {
		    conn.close();
		}
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return list;
    }

    private Connection getConnection() throws SQLException {
	Connection conn = null;
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );

	    String url = "jdbc:mysql://localhost/webdb";
	    conn = DriverManager.getConnection( url, "webdb", "webdb" );
	} catch ( ClassNotFoundException e ) {
	    System.out.println( "드라이버 로딩 실패: " + e );
	}

	return conn;
    }
}

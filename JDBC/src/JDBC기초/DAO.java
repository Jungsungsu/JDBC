package JDBC기초;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	// DAO -> Data Access Object
	// 데이터베이스와 관련하여 필요한 기능(insert, select, delete...)들만 따로 정리해 두는 파일
	// DAO를 사용하는 이유? 자바와 데이터베이스의 연결에 있어 효율적인 관리를 위하여 사용!
	
	// DTO -> Data Transfer Object = VO(Value Object)
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int result = 0;
	
	//sql문을 저장하는 변수
	String sql;
	
	// 회원 정보 insert 메소드
	public void getconn() {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	//2.DB연결
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user ="hr";
		String password ="hr";
		conn = DriverManager.getConnection(url, user, password);
		
		}catch (Exception e) {
			// Exception 클래스는 어떠한 오류라도 발생했을시 catch해 주는 역할!
			System.out.println("연결 오류 발생");
			e.printStackTrace(); 
		    
		
		}
	}
	// 데이터베이스를 종료하는 메소드
	public void close() {
		try {
			//5.DB연결종료(사용한 객체를 역순으로 종료)
			if(rs != null) { rs.close(); }
			if(psmt != null) {psmt.close(); }
			if(conn != null) {conn.close(); }
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}
	public int insert(String id, String pw, String nick) {
		// 하나의 기능이 시작되기 전에 꼭! 데이터베이스 연결 메소드 호출하기
		
		getconn();
		
		//3.실행할 SQL문 작성
		sql = "insert into member values(?,?,?)";
		
		try {
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, id);
		psmt.setString(2, pw);
		psmt.setString(3, nick);
		result = psmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return result;
		
	}
	// 회원 정보 select 메소드
	public void select() {
		
		// 1. 데이터베이스 연결
		getconn();
		
		try {
		// 2. sql문 전송 단계
			sql = "select * from member order by id asc";
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
		    while(rs.next()) {
				String getId = rs.getString(1);
				String getPw = rs.getString(2);
				String getNick = rs.getString(3);
				
				System.out.println(getId + " / " + getPw + " / " + getNick);
				
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//3. 데이터베이스 닫아주기
			close();
		}
		
		
	}
	
	// 회원 정보 update 메소드
	public int update(String id, String pw) {
		
		getconn();
		//수정할 컬럼이 한개 이상일 경우 ==> 
		//update 테이블명 set 컬럼1 = 데이터1, 컬럼2=데이터2,...where 기준컬럼=기준데이터
		sql = "update member set pw=? where id=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, id);
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	// 회원 정보 delete 메소드
        public int delete(String id, String pw) {
		
		getconn();
		
	//수정할 컬럼이 한개 이상일 경우 ==> 
	//Delete from 테이블명 where 컬럼1 = 데이터1, and 컬럼2=데이터2
		sql = "Delete from member where id = ? and pw = ? ";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
}
}

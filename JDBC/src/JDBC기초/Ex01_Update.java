package JDBC기초;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex01_Update {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			//1.오라클 드라이버 동적로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB연결
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			String user ="hr";
			String password ="hr";
			conn = DriverManager.getConnection(url, user, password);
			
			//DB연결확인
			if(conn != null) {
				System.out.println("DB연결성공!");
			}else {
				System.out.println("DB연결실패...");
			}
			
			//3.실행할 SQL문 작성 -> id컬럼을 기준으로 내림차순 정렬하는 sql문 작성해보기!
			//3.1 id컬럼을 기준으로 내림차순 정렬하는 sql문 작성해보기!
			//3.2 특정데이터만 조회해보기!(id컬럼기준)
			
			// 아이디가 "smart"인 회원의 비밀번호를 "4321"로 수정하는 sql문 작성하기!
			String sql = "Update member set pw = '4321' where id = 'smart'";
			
			psmt = conn.prepareStatement(sql);
			
			//4.SQL실행!
			//실행할 때 2가지 메소드
			//executeUpdate() : insert, update, delete와 같이 DB에 변화를 주는 SQL문장을 실행할 때!
			//executeQuery() : select문을 실행할 때!
			psmt.executeUpdate();
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("클래스 경로 오타 또는 ojdbc6.jar파일 설정 다시 확인!");
			
			//ClassNotFoundException : 클래스를 찾을 수 없을 때 발생하는 오류
			//1.클래스 이름이 오타나거나 경로가 잘못되었을 때
			//2.Java Project에 ojdbc6.jar파일 설정이 안되어 있을 때
			
			//오류메시지를 출력해주는 메소드
			e.printStackTrace(); 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
			//5.DB연결종료(사용한 객체를 역순으로 종료)
			if(psmt != null) {psmt.close(); }
			if(conn != null) {conn.close(); }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

}

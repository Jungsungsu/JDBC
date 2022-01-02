package JDBC기초;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex01_Insert {

	public static void main(String[] args) {
		
		//실습때 사용할 SQL문: insert(삽입), select(조회), update(수정), delete(삭제)
		// ==> CRUD
		
		//JDBC(Java Database Connectivity)
		// : Java와 DB를 연결하여 데이터를 읽거나 쓸 때 사용하는 API
		
		//데이터베이스 연결순서
		//1.오라클 드라이버 로드
		//2.DB연결
		//3.실행할 SQL문 준비
		//4.SQL문 실행
		//5.DB연결종료
		
		//0.ojdbc6.jar 파일을 Java Project에 설정!
		// -> 프로젝트 오른쪽 클릭 -> Build Path -> Configure Build Path 클릭
		// -> Libraries 탭 이동 -> ModulePath클릭 후 'Add JARs'버튼 클릭
		// -> ojdbc6.jar 파일의 위치를 설정
		
		//try~catch문: 예외처리를 수행하는 구문
		//try문 : 실행할 로직을 작성
		//catch문 : try문에서 오류가 발생한 부분을 잡아내는 구문
		//finally문 : try문에서 오류가 난 후 catch문으로 가더라도 마지막에 무조건 실행하는 구문
		
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
			
			//3.실행할 SQL문 작성
			String sql = "insert into member values(?,?,?)";
			
			psmt = conn.prepareStatement(sql);
			
			//?에 들어갈 데이터 설정 --> ?의 첫 시작인덱스는 1부터!
			psmt.setString(1, "smart1");
			psmt.setString(2, "1234");
			psmt.setString(3, "smhrd");
			
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

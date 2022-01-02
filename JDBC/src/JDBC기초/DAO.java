package JDBC����;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	// DAO -> Data Access Object
	// �����ͺ��̽��� �����Ͽ� �ʿ��� ���(insert, select, delete...)�鸸 ���� ������ �δ� ����
	// DAO�� ����ϴ� ����? �ڹٿ� �����ͺ��̽��� ���ῡ �־� ȿ������ ������ ���Ͽ� ���!
	
	// DTO -> Data Transfer Object = VO(Value Object)
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int result = 0;
	
	//sql���� �����ϴ� ����
	String sql;
	
	// ȸ�� ���� insert �޼ҵ�
	public void getconn() {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	//2.DB����
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user ="hr";
		String password ="hr";
		conn = DriverManager.getConnection(url, user, password);
		
		}catch (Exception e) {
			// Exception Ŭ������ ��� ������ �߻������� catch�� �ִ� ����!
			System.out.println("���� ���� �߻�");
			e.printStackTrace(); 
		    
		
		}
	}
	// �����ͺ��̽��� �����ϴ� �޼ҵ�
	public void close() {
		try {
			//5.DB��������(����� ��ü�� �������� ����)
			if(rs != null) { rs.close(); }
			if(psmt != null) {psmt.close(); }
			if(conn != null) {conn.close(); }
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}
	public int insert(String id, String pw, String nick) {
		// �ϳ��� ����� ���۵Ǳ� ���� ��! �����ͺ��̽� ���� �޼ҵ� ȣ���ϱ�
		
		getconn();
		
		//3.������ SQL�� �ۼ�
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
	// ȸ�� ���� select �޼ҵ�
	public void select() {
		
		// 1. �����ͺ��̽� ����
		getconn();
		
		try {
		// 2. sql�� ���� �ܰ�
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
			//3. �����ͺ��̽� �ݾ��ֱ�
			close();
		}
		
		
	}
	
	// ȸ�� ���� update �޼ҵ�
	public int update(String id, String pw) {
		
		getconn();
		//������ �÷��� �Ѱ� �̻��� ��� ==> 
		//update ���̺�� set �÷�1 = ������1, �÷�2=������2,...where �����÷�=���ص�����
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
	
	// ȸ�� ���� delete �޼ҵ�
        public int delete(String id, String pw) {
		
		getconn();
		
	//������ �÷��� �Ѱ� �̻��� ��� ==> 
	//Delete from ���̺�� where �÷�1 = ������1, and �÷�2=������2
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

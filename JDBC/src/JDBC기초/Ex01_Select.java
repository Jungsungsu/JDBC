package JDBC����;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex01_Select {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			//1.����Ŭ ����̹� �����ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB����
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			String user ="hr";
			String password ="hr";
			conn = DriverManager.getConnection(url, user, password);
			
			//DB����Ȯ��
			if(conn != null) {
				System.out.println("DB���Ἲ��!");
			}else {
				System.out.println("DB�������...");
			}
			
			//3.������ SQL�� �ۼ� -> id�÷��� �������� �������� �����ϴ� sql�� �ۼ��غ���!
			//3.1 id�÷��� �������� �������� �����ϴ� sql�� �ۼ��غ���!
			//3.2 Ư�������͸� ��ȸ�غ���!(id�÷�����)
			String sql = "select * from member where id = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,  "smart1");
			
			
			//4.SQL����!
			//������ �� 2���� �޼ҵ�
			//executeUpdate() : insert, update, delete�� ���� DB�� ��ȭ�� �ִ� SQL������ ������ ��!
			//executeQuery() : select���� ������ ��!
			rs = psmt.executeQuery();
			
			
			//4.1 ResultSet��ü�� �����Ͱ� �ִ��� ������ üũ
			// next() : ���� ������ �Ѿ �� �����Ͱ� �����ϴ��� Ȯ���ϴ� �޼ҵ� -> ��ȯ : True : False
			
			while(rs.next()) {
				String getId = rs.getString(1);
				String getPw = rs.getString(2);
				String getNick = rs.getString(3);
				
				System.out.println(getId + " / " + getPw + " / " + getNick);
				
				
				
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("Ŭ���� ��� ��Ÿ �Ǵ� ojdbc6.jar���� ���� �ٽ� Ȯ��!");
			
			//ClassNotFoundException : Ŭ������ ã�� �� ���� �� �߻��ϴ� ����
			//1.Ŭ���� �̸��� ��Ÿ���ų� ��ΰ� �߸��Ǿ��� ��
			//2.Java Project�� ojdbc6.jar���� ������ �ȵǾ� ���� ��
			
			//�����޽����� ������ִ� �޼ҵ�
			e.printStackTrace(); 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
			//5.DB��������(����� ��ü�� �������� ����)
			if(rs != null) { rs.close(); }
			if(psmt != null) {psmt.close(); }
			if(conn != null) {conn.close(); }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

}

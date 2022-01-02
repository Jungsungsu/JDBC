package JDBC����;

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
			
			// ���̵� "smart"�� ȸ���� ��й�ȣ�� "4321"�� �����ϴ� sql�� �ۼ��ϱ�!
			String sql = "Update member set pw = '4321' where id = 'smart'";
			
			psmt = conn.prepareStatement(sql);
			
			//4.SQL����!
			//������ �� 2���� �޼ҵ�
			//executeUpdate() : insert, update, delete�� ���� DB�� ��ȭ�� �ִ� SQL������ ������ ��!
			//executeQuery() : select���� ������ ��!
			psmt.executeUpdate();
			
			
			
			
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
			if(psmt != null) {psmt.close(); }
			if(conn != null) {conn.close(); }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

}

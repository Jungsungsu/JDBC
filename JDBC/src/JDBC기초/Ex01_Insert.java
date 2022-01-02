package JDBC����;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex01_Insert {

	public static void main(String[] args) {
		
		//�ǽ��� ����� SQL��: insert(����), select(��ȸ), update(����), delete(����)
		// ==> CRUD
		
		//JDBC(Java Database Connectivity)
		// : Java�� DB�� �����Ͽ� �����͸� �аų� �� �� ����ϴ� API
		
		//�����ͺ��̽� �������
		//1.����Ŭ ����̹� �ε�
		//2.DB����
		//3.������ SQL�� �غ�
		//4.SQL�� ����
		//5.DB��������
		
		//0.ojdbc6.jar ������ Java Project�� ����!
		// -> ������Ʈ ������ Ŭ�� -> Build Path -> Configure Build Path Ŭ��
		// -> Libraries �� �̵� -> ModulePathŬ�� �� 'Add JARs'��ư Ŭ��
		// -> ojdbc6.jar ������ ��ġ�� ����
		
		//try~catch��: ����ó���� �����ϴ� ����
		//try�� : ������ ������ �ۼ�
		//catch�� : try������ ������ �߻��� �κ��� ��Ƴ��� ����
		//finally�� : try������ ������ �� �� catch������ ������ �������� ������ �����ϴ� ����
		
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
			
			//3.������ SQL�� �ۼ�
			String sql = "insert into member values(?,?,?)";
			
			psmt = conn.prepareStatement(sql);
			
			//?�� �� ������ ���� --> ?�� ù �����ε����� 1����!
			psmt.setString(1, "smart1");
			psmt.setString(2, "1234");
			psmt.setString(3, "smhrd");
			
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

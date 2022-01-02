package JDBC����;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//� ����� ���� �ϴ��� ����ڰ� ����� �� �ֵ���
		//DAO ��ü �����ϱ�
		DAO dao = new DAO();
		
		while(true) {
		System.out.print("[1]���� [2]��ȸ [3]���� [4]���� [5]���� >> ");
		
		  int menu = sc.nextInt();
		  
		  if(menu == 1) {
			  
			  System.out.print("���� ���̵� : ");
			  String id = sc.next();
			  
			  System.out.print("���� ��й�ȣ : ");
			  String pw = sc.next();
			  
			  System.out.print("���� �г��� : ");
			  String nick = sc.next();
				
			  
			  // DAO Ŭ������ �ִ� insert()�� ������ �� �ִ� ��ü �����ϱ�(new)
			  int cnt =dao.insert(id, pw, nick);
			  
			  if(cnt>0) {
				  System.out.println("ȸ�� ���� ����");
			  }else {
				  System.out.println("ȸ�� ���� ����..");
			  }
			  
					
		 }else if (menu == 2) {
			  
			dao.select();	
				
		  }else if (menu == 3) {
			  
			  // ���� ��� ����� ����
			  // � ���̵� ���ؼ� -> �Է¹ޱ�
			  // ��� ��й�ȣ�� ������ ������ -> �Է¹ޱ�
			  
			  // DAO Ŭ������ update() ���� �����ϱ�!
			  System.out.print("���̵� �Է�>> ");
			  String id = sc.next();
			  
			  System.out.print("��й�ȣ �Է� >> ");
			  String pw = sc.next();
			  
			  int cnt = dao.update(id, pw);
			  
			  if(cnt>0) {
				  System.out.println("ȸ�� ���� ����!");
			  }else {
				  System.out.println("ȸ������ ��������..");
			  }
				
			  
		  }else if (menu == 4) {
			  
			  //ȸ�� ���� ����
			  //� ���̵� ����? => �Է¹ޱ�
			  //�ش� ���̵� ��й�ȣ => �Է¹ޱ�
			  
			  
			  
			  System.out.print("������ ���̵� : ");
			  String id = sc.next();
			  
			  System.out.print("��й�ȣ �Է� : ");
			  String pw = sc.next();
			  
			  int cnt = dao.delete(id, pw);
			  
			  if(cnt>0) {
				  System.out.println("ȸ�� ���� �����Ϸ�!");
			  }else {
				  System.out.println("ȸ������ ��������..");
			  }
			  
			  
		  }else {
			  System.out.println("���α׷� ����");
			  break;
		  }
		
		}
	}

}

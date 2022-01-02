package JDBC기초;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//어떤 기능을 선택 하더라도 사용자가 사용할 수 있도록
		//DAO 객체 생성하기
		DAO dao = new DAO();
		
		while(true) {
		System.out.print("[1]가입 [2]조회 [3]수정 [4]삭제 [5]종료 >> ");
		
		  int menu = sc.nextInt();
		  
		  if(menu == 1) {
			  
			  System.out.print("가입 아이디 : ");
			  String id = sc.next();
			  
			  System.out.print("가입 비밀번호 : ");
			  String pw = sc.next();
			  
			  System.out.print("가입 닉네임 : ");
			  String nick = sc.next();
				
			  
			  // DAO 클래스에 있는 insert()에 접근할 수 있는 객체 생성하기(new)
			  int cnt =dao.insert(id, pw, nick);
			  
			  if(cnt>0) {
				  System.out.println("회원 가입 성공");
			  }else {
				  System.out.println("회원 가입 실패..");
			  }
			  
					
		 }else if (menu == 2) {
			  
			dao.select();	
				
		  }else if (menu == 3) {
			  
			  // 수정 기능 요약해 보기
			  // 어떤 아이디에 대해서 -> 입력받기
			  // 어떻게 비밀번호를 수정할 것인지 -> 입력받기
			  
			  // DAO 클래스의 update() 만들어서 정리하기!
			  System.out.print("아이디 입력>> ");
			  String id = sc.next();
			  
			  System.out.print("비밀번호 입력 >> ");
			  String pw = sc.next();
			  
			  int cnt = dao.update(id, pw);
			  
			  if(cnt>0) {
				  System.out.println("회원 정보 수정!");
			  }else {
				  System.out.println("회원정보 수정실패..");
			  }
				
			  
		  }else if (menu == 4) {
			  
			  //회원 정보 삭제
			  //어떤 아이디를 삭제? => 입력받기
			  //해당 아이디에 비밀번호 => 입력받기
			  
			  
			  
			  System.out.print("삭제할 아이디 : ");
			  String id = sc.next();
			  
			  System.out.print("비밀번호 입력 : ");
			  String pw = sc.next();
			  
			  int cnt = dao.delete(id, pw);
			  
			  if(cnt>0) {
				  System.out.println("회원 정보 삭제완료!");
			  }else {
				  System.out.println("회원정보 삭제실패..");
			  }
			  
			  
		  }else {
			  System.out.println("프로그램 종료");
			  break;
		  }
		
		}
	}

}

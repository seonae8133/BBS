package co.seonae.board.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	private String driver = driver = "oracle.jdbc.driver.OracleDriver";
	private String  url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "seonae"; //자신 계정
	private String password = "1234"; //이 계정의 패스워드
	//private는 상속이 안되기 때문에 connect 객체를 만들어줌
	
	public Connection conn; //db연결 객체
	
	public DAO() { //데이터 베이스를 다룰때는 항상 try catch를 해줘야함
		try {
			//Class.forName(driver); //driver load
			//conn = DriverManager.getConnection(url, user, password);
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
			
			System.out.println("db 연결 성공");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	




}

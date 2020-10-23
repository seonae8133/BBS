package co.seonae.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.seonae.board.vo.MemberVO;

public class MemberDao extends DAO {
	private PreparedStatement psmt; //sql명령문 작성할때 사용
	private ResultSet rs; //select 결과 담을 결과셋 (전체에서 부분집합을 가져 오는것.java에서는resultset(원랜recordSet)
	private MemberVO vo;
	
	private final String SELECT_ALL = "SELECT * FROM MEMBER"; //ctrl+shift+x = 대문자 자동변환
	private final String SELECT = "SELECT * FROM MEMBER WHERE ID=? AND PASSWORD=?";
	//final 로 만들어서 상수로 만듬(쿼리를 누가 바깥에서 수정하지 못하게 상수로 만듦)
	private final String INSERT = "INSERT INTO MEMBER(ID,NAME,PASSWORD,ADDRESS,TEL,ENTERDATE)"
														+ " VALUES(?,?,?,?,?,?)";
	
	public List<MemberVO> selectAll() { //멤버list전체를 불러오기 위한 함수
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			psmt = conn.prepareStatement(SELECT_ALL);//상위 dao가 conn을 갖고있어서 여기도 사용가능
			//conn객체에 내가 실행해야할 sql문 담음 : dbms에 sql문 전달한것임
			rs = psmt.executeQuery(); //executeQuery는 resultSet을 반환함 - resultSet변수에 받기
			while(rs.next()) {
				vo = new MemberVO(); //가지고 온 결과 담기위한 vo객체 생성
				vo.setId(rs.getString("id")); //"컬럼네임" (동일하게 작성) - getxxx = get다음엔 type임.
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setAuthor(rs.getString("author"));
				vo.setEnterdate(rs.getDate("enterdate"));
				//다 담고나면 list 에 add시킴
				list.add(vo); 
				//위 while문에 rs가 있는동안에 계속 담아줌
			}
		}catch(SQLException e) {//
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	public MemberVO select(MemberVO vo) {//한 행을 검색할때
		try {
			psmt = conn.prepareStatement(SELECT);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPassword());
			//select 는무조건 resultset이들어옴
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setAuthor(rs.getString("author"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	public int insert(MemberVO vo) {//memberVO에 insert 하는것
		int n =0;
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getTel());
			psmt.setDate(6, vo.getEnterdate());
			n = psmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return  n; //정수를 반환해줌 ex)n 행이 반환되었습니다
	} 
	
	public int update(MemberVO vo) {//memberVO에 update 하는것
		int n =0;
		return  n;
	} 
	
	public int delete(MemberVO vo) {//memberVO에 delete 하는것
		int n =0;
		return  n;
	}
	
	//db연결닫기
	private void close() {
		try {
			//커넥션연결 - preparestatement연결 - result set 순서 : 열어준 순서 반대로 닫아주기
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
	}
}

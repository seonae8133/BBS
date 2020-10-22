package co.seonae.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import co.seonae.board.vo.MemberVO;

public class MemberDao extends DAO {
	private PreparedStatement psmt; //sql명령문 작성할때 사용
	private ResultSet rs; //select 후 결과셋 (전체에서 부분집합을 가져 오는것.java에서는resultset(원랜recordSet)
	private MemberVO vo;
	
	private final String SELECT_ALL = "SELECT * FROM MEMBER"; //ctrl+shift+x = 대문자 자동변환
	//final 로 만들어서 상수로 만듬
	
	public List<MemberVO> selectAll() { //멤버list전체를 불러오기 위한 함수
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			psmt = conn.prepareStatement("SELECT_ALL");//상위 dao가 conn을 갖고있어서 여기도 사용가능
			//conn객체에 내가 실행해야할 sql문 담음 : dbms에 sql문 전달한것임
			rs = psmt.executeQuery(); //executeQuery는 resultSet을 반환함 - resultSet변수에 받기
			while(rs.next()) {
				vo = new MemberVO(); //가지고 온 결과 담기위한 vo객체 생성
				vo.setId(rs.getString("id")); //"컬럼네임" (동일하게 작성) - getxxx = get다음엔 type임.
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password"));
				vo.setAddress(rs.getString("addredd"));
				vo.setTel(rs.getString("tel"));
				vo.setAuthor(rs.getString("author"));
				vo.setEnterdate(rs.getDate("enterdate"));
				//다 담고나면 list 에 add시킴
				list.add(vo); 
				//위 while문에 rs가 있는동안에 계속 담아줌
			}
		}catch(SQLException e) {//
			e.printStackTrace();
		}
		return list;
	}
	
	public MemberVO select(MemberVO vo) {//한 행을 검색할때
		
		return vo;
	}
	
	public int insert(MemberVO vo) {//memberVO에 insert 하는것
		int n =0;
		return  n;
	} 
	
	public int update(MemberVO vo) {//memberVO에 update 하는것
		int n =0;
		return  n;
	} 
	
	public int delete(MemberVO vo) {//memberVO에 delete 하는것
		int n =0;
		return  n;
	} 
}

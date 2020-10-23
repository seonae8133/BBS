package co.seonae.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.seonae.board.common.Action;
import co.seonae.board.dao.MemberDao;
import co.seonae.board.vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원 리스트 보기 구현
		MemberDao dao = new MemberDao();
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		list =  dao.selectAll();
		request.setAttribute("members", list); //members변수는 jsp 에서 사용함
		
		return "jsp/member/memberList.jsp";
	}

}

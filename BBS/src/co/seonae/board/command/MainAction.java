package co.seonae.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.seonae.board.common.Action;

public class MainAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "jsp/menu/mainMenu.jsp"; // jsp페이지 뿌려주기 위해서 리턴값 지정
	}

}

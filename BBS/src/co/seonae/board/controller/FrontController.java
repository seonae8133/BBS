package co.seonae.board.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.seonae.board.command.LoginAction;
import co.seonae.board.command.MainAction;
import co.seonae.board.common.Action;


@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //사용하기 위한 해쉬맵 변수 선언
	 //액션이라는 인터페이스를 통해서 맵이라는 이름으로 하나 생성함
	private HashMap<String,Action> map = new HashMap<String,Action>();
	
	
	
    public FrontController() {
        super();
       
    }


	public void init(ServletConfig config) throws ServletException {
		// 요청들을 정의함
		map.put("/main.do",new MainAction());//처음 브라우저 접근할때 사용 (index.jsp): index.jsp가 들어오면 처리할거
		map.put("/login.do",new LoginAction()); //로그인 메뉴를 처리하는곳에 사용
		
		
		//url에서 컨텐츠 패스가 /login.do가들어오면 new LoginAction 을 수행하라
		//~.do는 어떤 명령어를 태워라
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수행할 명령을 정리
		request.setCharacterEncoding("utf-8"); //한글깨짐 방지를 위해서 작성
		//http://localhost/BBS/main.do
		String uri = request.getRequestURI(); // uri = /BBS/main.do 를 String uri 변수에 담음
		String contextPath = request.getContextPath(); // contextPath : /BBS
		String path = uri.substring(contextPath.length()); //실제 들어오는 요청 페이지를 찾음 // path=/main.do
		//path까지 찾았으니 Action
		Action command = map.get(path); // map에 path를 던지면 MainAction()이 날아옴 (Action.java 보면String으로 받아야됨) //메인액션을 초기화시키고
		String viewPage = command.exec(request, response); //실제 명령어가 수행될 부분, 1.command를 실행시켜서 문자열로 돌려줌 ,보여줄 페이지(viewPage)를 선택
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); // 2.dispatcher에 viewpage 를 실어주고 - 선택한 페이지로 가기 
		//RequestDispatcher객체 생성 (request객체를 전달해줄 페이지)
		dispatcher.forward(request, response); //3.그페이지로 가라~
		
		
	}

}

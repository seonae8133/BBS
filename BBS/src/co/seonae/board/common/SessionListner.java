package co.seonae.board.common;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class SessionListner implements HttpSessionListener, HttpSessionAttributeListener {

	public static Set<String> sessionSet = new HashSet<>();
	

    public SessionListner() {
        // TODO Auto-generated constructor stub
    }

	//세션 생성될때실행될 메서드
    public void sessionCreated(HttpSessionEvent event)  { 
    		System.out.println("세션생성" + event.getSession().getId());
    		
    }

    //세션 제거될때 실행될 메서드
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
     
    }

//세션에 값 추가 될때
    public void attributeAdded(HttpSessionBindingEvent event)  { 
		System.out.println("세션추가" + event.getName() + ":" + event.getValue());
		if(event.getName().equals("id")) {
			sessionSet.add((String)event.getValue());
		}
    }


    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	if(event.getName().equals("id")) {
			sessionSet.remove((String)event.getValue());
		}

    }

	
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
        
    }
	
}

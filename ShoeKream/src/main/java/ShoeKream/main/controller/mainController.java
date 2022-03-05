package ShoeKream.main.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {
	
	//메인페이지
	@GetMapping("/ShoeKream")
	public String main() throws Exception{
		return "main/mainPage";
	}


	//회원가입
	@GetMapping("ShoeKream/join")
	public String JoinPage() throws Exception{
		
		return "main/joinPage";
	}
	
	//로그인 + 쿠키확인 220304 추가
	@GetMapping("ShoeKream/login")
	public String LoginPage(HttpServletRequest req) throws Exception{
		
		String memberCookieid = new String(); // cookieValue담는 객체
		
		Cookie[] allCookies = req.getCookies();
		
		for(int i = 0; i < allCookies.length; i++) {
			
			if(allCookies[i].getName().equals("memberid")) {
				memberCookieid = allCookies[i].getValue();
			}
		}
		
		if(!memberCookieid.equals("")) {
			req.setAttribute("memberCookieid", memberCookieid);
		}
		
		return "main/loginPage";
	}
	
	//고객센터
	@GetMapping("ShoeKream/customer")
	public String CustomerPage() throws Exception{
		
		return "main/customerPage";
	}
	
}

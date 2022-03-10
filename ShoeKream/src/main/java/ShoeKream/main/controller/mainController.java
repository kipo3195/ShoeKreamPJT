package ShoeKream.main.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import ShoeKream.communityBoard.VO.communityBoardVO;
import ShoeKream.communityBoard.paging.Criteria;
import ShoeKream.communityBoard.paging.PageMaker;
import ShoeKream.communityBoard.service.communityBoardService;

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
	
	
	
	@Autowired
	private communityBoardService cbs;
	//고객센터 페이지 이동
	@GetMapping("ShoeKream/community")
	public ModelAndView CustomerPage(HttpServletRequest req,@RequestParam(value="page",required = false)String page) throws Exception{
		
		ModelAndView mv = new ModelAndView("community/communityPage");
		String string = "notice";
		mv.addObject("string", string);
		
		int startPage;
		if(page == null) {
			// 고객센터 메인페이지 처음 접근시
			 startPage = 1;
		}else {
			// 페이지 요청
			 startPage = Integer.parseInt(page);
		}
		
		
		
		//페이징 처리 
		int totalCount = cbs.totalCommunityBoardCount(); //현재의 모든 게시물의 수 파악

		Criteria cri = new Criteria(startPage,10); // startPage 변경시 list를 가져올 쿼리의 Limit값을 변경시키기위함
		
		PageMaker pm = new PageMaker(cri,totalCount); 
				
		mv.addObject("pm",pm);
		
		//2를 입력받았다면 10번째 부터, 10개를 넣어줘야함. limit 할것 = cri.getPageStart
		List<communityBoardVO> list = cbs.selectboardList(cri.getPageStart());
		
		mv.addObject("list", list);
		

		return mv;
	
	}
	
	
}

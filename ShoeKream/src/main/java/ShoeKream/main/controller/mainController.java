package ShoeKream.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.admin.service.adminService;
import ShoeKream.communityBoard.VO.communityBoardVO;
import ShoeKream.communityBoard.paging.Criteria;
import ShoeKream.communityBoard.paging.PageMaker;
import ShoeKream.communityBoard.service.communityBoardService;
import ShoeKream.main.service.mainService;
import ShoeKream.user.VO.joinVO;

@Controller
public class mainController {
	
	@Autowired
	private mainService ms;
	
	//메인페이지
	@GetMapping("/ShoeKream")
	public ModelAndView main() throws Exception{
		ModelAndView mv = new ModelAndView("main/mainPage");
		
		List<luxBoardVO> list = ms.selectMain();

		int pageFlage = 1;
		mv.addObject("pageFlag",pageFlage);
		mv.addObject("list", list);
		return mv;
	}
	//메인 페이지에서 더보기 클릭시 페이지 추가
	
	@GetMapping("/ShoeKream/addLuxBoard")
	public ResponseEntity<Object> addLuxBoard(@RequestParam("pageFlag")int pageFlag) throws Exception{
		ResponseEntity<Object> entity;


		System.out.println(pageFlag);
		
		List<luxBoardVO> list = ms.addLuxBoard(pageFlag);
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("list", list);
		map.put("pageFlag", ++pageFlag);
		
		try {
		
			entity = new ResponseEntity<>(map,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
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
		
		if(allCookies != null) {
			
			for(int i = 0; i < allCookies.length; i++) {
				
				if(allCookies[i].getName().equals("memberid")) {
					memberCookieid = allCookies[i].getValue();
				}
			}
			
			if(!memberCookieid.equals("")) {
				req.setAttribute("memberCookieid", memberCookieid);
			}
			
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
	
	//메인에있는 게시물 상세페이지로 이동
	@GetMapping("ShoeKream/luxBoardDetail/{luxbNo}")
	public ModelAndView luxBoardDetail(@PathVariable("luxbNo")int luxbNo)throws Exception{
		ModelAndView mv= new ModelAndView("main/luxBoardDetail");
		
		luxBoardVO lbvo = ms.selectLuxBoard(luxbNo);
		System.out.println(lbvo);
		mv.addObject("lbvo", lbvo);		
		return mv;
	}
	
	//메인에있는 게시물 삭제 요청
	@GetMapping("ShoeKream/deleteMainBoard/{luxbNo}")
	public String deleteMainBoard(@PathVariable("luxbNo")int luxbNo, RedirectAttributes rttr)throws Exception{
		
		
		String msg = ms.deleteBoard(luxbNo);
		
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/ShoeKream";
	}
	
	//메인에있는 게시물 수정 페이지
	@GetMapping("ShoeKream/updateMainBoard/{luxbNo}")
	public ModelAndView updateMainBoard(@PathVariable("luxbNo")int luxbNo)throws Exception{
		ModelAndView mv = new ModelAndView("main/updateMainBoard");
		luxBoardVO lbvo = ms.selectLuxBoard(luxbNo);
		
		mv.addObject("lbvo", lbvo);
		return mv;
	}
	
	//메인 게시물 수정 페이지 수정요청
	@PostMapping("/ShoeKream/updateMainBoardRequest")
	public String updateMainBoardRequest(luxBoardVO lbvo, RedirectAttributes rttr)throws Exception{
		
		String msg = ms.updateBoard(lbvo);
		
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/ShoeKream";
	}
	
	//0323 추가
	@GetMapping("/ShoeKream/myPage")
	public String myPage()throws Exception {
		
		
		return "member/myPage";
	}
	

	
	
}

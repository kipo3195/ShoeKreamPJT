package ShoeKream.user.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.main.service.mainService;
import ShoeKream.user.VO.joinVO;
import ShoeKream.user.VO.loginVO;
import ShoeKream.user.VO.memberVO;
import ShoeKream.user.service.userService;


@Controller
public class userController {

	@Autowired
	private userService userservice;
	@Autowired
	private mainService ms;
	
	//회원가입 신청
	@RequestMapping("/joinRequest")
	public ModelAndView joinRequest(joinVO joinVo) throws Exception{
		
		if(!joinVo.getUserpass().equals(joinVo.getUserpass1())) { 
			ModelAndView mv = new ModelAndView("/main/joinPage");
			mv.addObject("msg", "비밀번호와 비밀번호확인이 일치하지 않습니다. 다시입력해주세요.");
			return  mv;
		}
		ModelAndView mv = new ModelAndView("/main/loginPage");
		userservice.insertUser(joinVo);
		mv.addObject("msg", "회원가입이 완료되었습니다.");
		return mv;
	}
	
	//로그인 요청
	@RequestMapping("/loginRequest")
	public String loginRequest(loginVO loginvo, HttpServletRequest req, RedirectAttributes rttr, HttpServletResponse res)throws Exception{
		
		HttpSession session = req.getSession();
		
		
		memberVO member = userservice.selectUser(loginvo);
		
		
		//세션 삽입 코드
		if(member == null) {// 로그인 계정 불일치
			
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg","일치하는 회원이 존재하지 않습니다. 아이디와 비밀번호를 확인하세요."); 
			return "redirect:/ShoeKream/login";
			}else if(member.getDeleteyn().equals("y")) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", "탈퇴한 회원 입니다.");
			return "redirect:/ShoeKream/login";
			}
			else {
			
			// 쿠키 등록
			if(loginvo.getIdcookie() != null) { // 문자열 비교는 equals...
				Cookie idCookie = new Cookie("memberid", String.valueOf(member.getUserid()));
				idCookie.setMaxAge(60);
				res.addCookie(idCookie);
			}
		session.setAttribute("member", member);
			
		}
	
		return "redirect:/ShoeKream";
	}
	
	//로그 아웃 요청
	@RequestMapping("/logoutRequest")
	public String logoutRequest(HttpServletRequest req)throws Exception{
		HttpSession session = req.getSession();
		
		session.removeAttribute("member");
		
		return "redirect:/ShoeKream";
	}
	
	//계정 찾기 페이지
	@RequestMapping("/finduserid")
	public String finduserid() throws Exception{
		
		return "member/finduserid";
	}
	
	//계정 찾기 요청
	@RequestMapping("/finduseridRequest")
	public ModelAndView finduseridRequest(
			@RequestParam(value = "userphone")String userphone,
			@RequestParam(value = "username" )String username
			) throws Exception {
	
		memberVO member = userservice.findUserid(username, userphone);
		
		
		ModelAndView mv = new ModelAndView("/member/founduserid");
		
		if(member != null) {
			mv.addObject("userid", member.getUserid());			
		}else {
			mv.addObject("userid", null);
		}
		return mv;
		
	}
	
	//비밀번호 찾기 페이지
	@RequestMapping("/finduserpass")
	public String finduserpass() throws Exception{
		
		return "member/finduserpass";
	}
	
	//비밀번호 찾기 요청
	@RequestMapping("/finduserpassRequest")
	public ModelAndView finduserpassRequest(joinVO joinvo,HttpServletRequest request) throws Exception{
		
		memberVO member = userservice.findUserpass(joinvo);
		
		
		//입력한 정보에 맞는 계정이 존재하지 않으면
		if(member == null) {
			ModelAndView mv = new ModelAndView("redirect:/finduserpass");
			
			//경고창에 출력할 메시지 추가해야함.!
			
			return mv;
		}
			//일치하는 계정이 존재하면 view등록 및 비밀 번호 코드 전송
			ModelAndView mv = new ModelAndView("member/insertpasscode");
			int rst = userservice.SendaPasswordCode(joinvo,request);
			
			if(rst == 0) {
				//성공적으로 전송한거임 Userid 보내줘야함.
				mv.addObject("userId", member.getUserid());			
				
			}
			else {
				// 전송 실패 이기 때문에 메시지 보내줘야함.
			}
			
	
			
		return mv;
	}
	
	//패스 코드 입력 후 확인
	@RequestMapping("/changeMyPassword")
	public ModelAndView changeMyPassword(@RequestParam("userId") String userId,@RequestParam("passCode") String passCode) throws Exception {
	
		ModelAndView mv;
		
		System.out.println(userId+" : "+passCode);
		
		int rst = userservice.checkPassCode(userId,passCode);
		//rst가 1이면 패스코드가 정상적으로 확인된것
		System.out.println("패스코드 확인 작업 count : "+rst);
		
		if(rst == 1) {
			mv = new ModelAndView("member/changeMyPass");
			//성공적으로 비밀번호 변경
			System.out.println("변경창 이동");
			mv.addObject("userId", userId);			
			
			return mv;
		}else {
			
			mv = new ModelAndView("member/finduserpass");
			System.out.println("오류발생");
			//메시지 추가해주기
			return mv;
			
		}
		
	}
	
	@RequestMapping("/updateMyPass")
	public ModelAndView updateMyPass(joinVO joinvo) throws Exception{
		
		ModelAndView mv;
		System.out.println("변경 정보 확인!"+joinvo);
		
		int rst = userservice.updatePass(joinvo);
		
		if(rst == 1) {
			//성공
			mv = new ModelAndView("/main/loginPage");
			mv.addObject("msg","변경된 비밀번호로 로그인 해주세요");
			
			//패스 코드를 삭제하고 result 1을 받으면 삭제성공 = 비밀번호 변경완료 
		     int result = userservice.deletePassCode(joinvo);
		     System.out.println("임시 패스코드 삭제 확인 :"+result);
		     
			return mv;
			
		}else {
			//실패
			mv = new ModelAndView("redirect:/finduserpass");
			System.out.println("실패!");
			return mv;
			
		}
		
	}
	
	
    //0323 추가
	
	@PostMapping("/ShoeKream/myInfo")
	public ResponseEntity<Object> myInfo(@RequestParam("userId")String userId)throws Exception{
		
		ResponseEntity<Object> entity;
		
		joinVO myInfo = userservice.findMyInfo(userId);
		System.out.println(myInfo);
		
		try {
			entity = new ResponseEntity<>(myInfo,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@PostMapping("/ShoeKream/changePass")
	public ResponseEntity<Object> changePass()throws Exception{
		
		ResponseEntity<Object> entity;
		
		
		try {
			entity = new ResponseEntity<>(null,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@PostMapping("/ShoeKream/insertPass")
	public ResponseEntity<Object> insertPass(@RequestParam("userId")String userId)throws Exception{
		
		ResponseEntity<Object> entity;
		
		loginVO myPass = userservice.findMyPass(userId);
		System.out.println(myPass);
		
		try {
			entity = new ResponseEntity<>(myPass.getUserpass(),HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping("/ShoeKream/ChangePassRequest")
	public String changePassRequest(joinVO joinvo,RedirectAttributes rttr,HttpServletRequest req)throws Exception{
		
		if(joinvo.getUserpass().equals(joinvo.getUserpass1())) {
			//비밀 번호변경
			int result = userservice.changePassRequest(joinvo);
			
				if(result == 1) {
					rttr.addFlashAttribute("msg", "비밀번호가 성공적으로 변경 되었습니다. 변경된 비밀번호로 다시 로그인 하세요.");
					HttpSession session = req.getSession();
					session.removeAttribute("member");
					return "redirect:/ShoeKream";
				}
			
		}else {
			rttr.addFlashAttribute("msg", "입력하신 비밀번호가 서로 일치하지 않습니다. 다시 시도해주세요.");
		}
		return "redirect:/ShoeKream/myPage";
		
	}
	
	@PostMapping("/ShoeKream/withdraw")
	public ResponseEntity<Object> withdraw()throws Exception{
		
		ResponseEntity<Object> entity;
	
		
		try {
			entity = new ResponseEntity<>(null,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@PostMapping("/ShoeKream/withDrawRequest")
	public ResponseEntity<Object> withDrawRequest(@RequestParam("myId")String myId,@RequestParam("myPass")String myPass)throws Exception{
		
		ResponseEntity<Object> entity;
		
		int result = userservice.withDrawRequest(myId,myPass);

		
		try {
			entity = new ResponseEntity<>(result,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PostMapping("/ShoeKream/addMyCart")
	public ResponseEntity<Object> addMyCart(@RequestParam("userId")String userId,@RequestParam("luxbNo")String luxbNo)throws Exception{
		
		ResponseEntity<Object> entity;
		
		int result = userservice.addMyCart(userId, luxbNo);
		
		try {
			entity = new ResponseEntity<>(result,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@PostMapping("/ShoeKream/myCart")
	public ResponseEntity<Object> myCart(@RequestParam("userId")String userId)throws Exception{
		
		ResponseEntity<Object> entity;
		
		 List<luxBoardVO> luxblist = userservice.selectLuxList(userId);
		
		
		try {
			entity = new ResponseEntity<>(luxblist,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@PostMapping("/ShoeKream/deleteMyCart")
	public ResponseEntity<Object> deleteMyCart(@RequestParam("userId")String userId,@RequestParam("luxbNo")String luxbNo)throws Exception{
		
		ResponseEntity<Object> entity;
		

		int result  = userservice.deleteMyCart(userId,luxbNo);
		
		
		try {
			entity = new ResponseEntity<>(result,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	
	
	
}

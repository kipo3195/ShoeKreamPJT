package ShoeKream.communityBoard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ShoeKream.communityBoard.VO.bulletinBoardVO;
import ShoeKream.communityBoard.VO.communityBoardVO;
import ShoeKream.communityBoard.paging.Criteria;
import ShoeKream.communityBoard.paging.PageMaker;
import ShoeKream.communityBoard.service.communityBoardService;

@Controller
public class communityBoardController {
	
	@Autowired
	private communityBoardService cbs;
	
	
	// 자유 게시판
	@GetMapping("/ShoeKream/bulletinBoardPage")
	public ModelAndView bulletinBoardPage(@RequestParam(value="page",required = false)String page) throws Exception{
		ModelAndView mv = new ModelAndView("community/bulletinBoardPage");
		
		String string = "bulletin";
		mv.addObject("string", string);
		
		int totalCnt = cbs.totalBulletinBoardCount();
		int startPage;
		
		if(page == null) {
			startPage = 1;
		}else {
			startPage = Integer.parseInt(page);
		}
		
		Criteria cri = new Criteria(startPage,10); // 요청받는 페이지로 변경해주기 page변수 
		
		PageMaker pm = new PageMaker(cri, totalCnt);

		List<bulletinBoardVO> list = cbs.selectBulletinList(cri.getPageStart());
		
		mv.addObject("list", list);
		
		mv.addObject("pm", pm);
		
		
		return mv;
	}
	//  자유 게시판 글쓰기 페이지 
	@GetMapping("/ShoeKream/createBulletinPage")
	public String createBulletinPage()throws Exception{
		
		return "community/createBulletinPage";
	}
	
	//자유 게시판 글쓰기 요청 
	@PostMapping("/ShoeKream/createBulletinRequest")
	public String createBulletinRequest(RedirectAttributes rttr, bulletinBoardVO bbvo) throws Exception{

		System.out.println("bbvo : "+bbvo);
		Integer result = cbs.createBulletin(bbvo);
		System.out.println(result);
		String msg = "";
	
		if(result != 1) {
			msg = "알수 없는 이유로 게시글 등록에 실패했습니다.";
			rttr.addFlashAttribute("msg", msg);
			return "redirect:/ShoeKream/createBulletPage";
		}else {
			//작성 성공
			msg = "게시글 등록에 성공하였습니다.";
			rttr.addFlashAttribute("msg", msg);
			return "redirect:/ShoeKream/bulletinBoardPage";
			
		}

	}
	
	//자유게시판 상세페이지
	@GetMapping("/ShoeKream/bulletinDetail")
	public ModelAndView bulletinDetail(@RequestParam("bbNo")int bbNo) throws Exception {
		ModelAndView mv = new ModelAndView("community/bulletinDetailPage");
		
		cbs.addBulletinHitCnt(bbNo);
		
		bulletinBoardVO bbvo = cbs.bulletinDetailRequest(bbNo);
		
		mv.addObject("bbvo", bbvo);
		
		return mv;
		
	}
	// 자유게시판 삭제
	@GetMapping("/ShoeKream/deleteBulletin")
	public ModelAndView deleteBulletinRequest(@RequestParam int bbNo)throws Exception{
		
		Integer result = cbs.deleteBulletin(bbNo);
		
		ModelAndView mv = new ModelAndView("redirect:/ShoeKream/bulletinBoardPage");
		
		return mv;
		
	
	}
	
	//자유게시판 수정
	@GetMapping("/ShoeKream/updateBulletin")
	public ModelAndView updateBulletinRequest(@RequestParam int bbNo)throws Exception{
		ModelAndView mv = new ModelAndView("community/bulletinUpdatePage");
		
		bulletinBoardVO bbvo = cbs.bulletinDetailRequest(bbNo);
		mv.addObject("bbvo", bbvo);
		
		
		return mv;
	}
	
	//자유게시판 게시물 수정 요청
	@PostMapping("/ShoeKream/updateBulletinRequest")
	public String updateBulletinRequest(bulletinBoardVO bbvo,RedirectAttributes rttr) throws Exception{
		
		
		Integer result = cbs.updateBulletin(bbvo);
		
		String msg ;
		
		if(result == 1) {
			msg = "정상적으로 수정이 완료 되었습니다.";
			rttr.addFlashAttribute("msg",msg);
		}else {
			msg = "비 정상적인 이유로 수정이 불가 합니다.";
		}
		
		return "redirect:/ShoeKream/bulletinBoardPage";
	}
	
	//첫 비동기통신 성공!! 0311
	@GetMapping("/ShoeKream/clickLikeCnt/{bbno}")
	public ResponseEntity<Object> clickLikeCnt(
			@PathVariable("bbno")String bbno,
			@RequestParam("userid")String userid) throws Exception{
		
		ResponseEntity<Object> entity;
		
		System.out.println("bbno : "+bbno);
		System.out.println("userid : "+userid);
		
		
		try {
			entity = new ResponseEntity<>(null,HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	//묻고 답하기 게시판
	@GetMapping("/ShoeKream/qnaBoardPage")
	public ModelAndView qnaBoardPage() throws Exception{
		ModelAndView mv = new ModelAndView("community/qnaBoardPage");
		String string ="qna";
		mv.addObject("string", string);

			
		return mv;
	}
	
	//공지사항 글 작성
	@GetMapping("/ShoeKream/createNoticePage")
	public String createNoticePage() throws Exception{
		
		return "community/createNoticePage";
	}
	
	// 공지글 작성 요청
	@PostMapping("/ShoeKream/createNoticeRequest")
	public String createNoticeRequest(communityBoardVO cbvo,RedirectAttributes rttr)throws Exception{
		

		Integer result = cbs.createNotice(cbvo);
		String msg = new String();

		
		if(result == 1) {
			msg = "게시물이 성공적으로 등록 되었습니다.";
			rttr.addFlashAttribute("msg", msg);
			
		}else {
			msg = "알수 없는 이유로 게시물이 등록되지 않았습니다.";
			return "redirect:/ShoeKream/createNoticePage";
		}
	
		return "redirect:/ShoeKream/community";
	}
	
	//공지글 상세보기
	@GetMapping("/ShoeKream/noticeDetail")
	public ModelAndView noticeDetailPage(@RequestParam int cbNo)throws Exception {

		ModelAndView mv = new ModelAndView("community/noticeDetailPage");
		
		//공지 글 select
		communityBoardVO cbvo= cbs.noticeDetailRequest(cbNo);
		mv.addObject("cbvo",cbvo);
		
		//공지 글 조회수 update
		cbs.addnoticeHitCnt(cbNo);
		
		
		
		
		return mv;
	}
	
	// 공지글 삭제
	@GetMapping("/ShoeKream/deleteNotice")
	public ModelAndView deleteNoticeRequest(@RequestParam int cbNo)throws Exception{
		
		cbs.deleteNotice(cbNo);
		
		ModelAndView mv = new ModelAndView("redirect:/ShoeKream/community");
		
		return mv;
		
	
	}
	
	//공지 수정 페이지
	@GetMapping("/ShoeKream/updateNotice")
	public ModelAndView updateNotice(@RequestParam int cbNo)throws Exception{
		
		System.out.println("cbNo : " +  cbNo);
		
		communityBoardVO cbvo = cbs.noticeDetailRequest(cbNo);
		
		ModelAndView mv = new ModelAndView("community/noticeUpdatePage");
		
		mv.addObject("cbvo", cbvo);
		
		return mv;	
		
	}
	// 공지 수정 요청
	@PostMapping("/ShoeKream/updateNoticeRequest")
	public String updateNoticeRequest(communityBoardVO cbv) throws Exception{
		
		
		System.out.println(cbv);
		
		Integer rst = cbs.updateNotice(cbv);
		
		System.out.println("rst : "+ rst);
		
		return "redirect:/ShoeKream/community";
	}
	
}

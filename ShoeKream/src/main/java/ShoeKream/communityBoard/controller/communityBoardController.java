package ShoeKream.communityBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class communityBoardController {
	
	
	
	// 자유 게시판
	@GetMapping("/ShoeKream/bulletinBoardPage")
	public String bulletinBoardPage() throws Exception{
		
		return "community/bulletinBoardPage";
	}
	
	
	//묻고 답하기 게시판
	@GetMapping("/ShoeKream/qnaBoardPage")
	public String qnaBoardPage() throws Exception{
		
		return "community/qnaBoardPage";
	}
	

}

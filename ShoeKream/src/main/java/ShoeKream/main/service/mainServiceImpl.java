package ShoeKream.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.main.mapper.mainMapper;
import ShoeKream.user.VO.joinVO;

@Service
public class mainServiceImpl implements mainService{

	@Autowired
	private mainMapper mm;
	
	@Override
	public List<luxBoardVO>  selectMain() throws Exception {
		
		List<luxBoardVO> list = mm.selectMain();
		
		return list;
	}

	@Override
	public luxBoardVO selectLuxBoard(int luxbNo) throws Exception {
		
		return mm.selectLuxBoard(luxbNo);
	}

	@Override
	public String deleteBoard(int luxbNo) throws Exception {
		
		int result = mm.deleteBoard(luxbNo);
		
		String msg;
		if(result == 1) {
			msg = "정상적으로 삭제 되었습니다.";
		}else {
			msg = "예기치 못한 이유로 삭제 되지 않았습니다.";
		}
		
		return msg;
	}

	@Override
	public String updateBoard(luxBoardVO lbvo) throws Exception {
		
		int result = mm.updateBoard(lbvo);
		
//		String thumb = lbvo.getLuxbThumbnailImg();
//		String img1 = lbvo.getLuxbImg1();
//		String img2 = lbvo.getLuxbImg2();
//		int lbvoNo = lbvo.getLuxbNo();
//		if(thumb == null) {
//			thumb= mm.selectThumbNail(lbvoNo);
//			
//		}
//		if(img1 == null) {
//			img1 = mm.selectimg1(lbvoNo);
//			
//		}if(img1 == null) {
//			img2 = mm.selectimg2(lbvoNo);
//		}
//		
//		
		String msg;
		if(result == 1) {
			msg = "정상적으로 수정 되었습니다.";
		}else {
			msg = "예기치 못한 이유로 수 되지 않았습니다.";
		}
		
		return msg;
	
	
	}

	@Override
	public int totalCountBoard() throws Exception {
		
		return mm.totalBoardCount();
	}

	
	
	@Override
	public List<luxBoardVO> addLuxBoard(int pageFlag) throws Exception {
		
		List<luxBoardVO> lbvo= mm.addLuxBoard(pageFlag*4);
		
		return lbvo;
	}


	
	
	
	
	

}

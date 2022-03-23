package ShoeKream.main.service;

import java.util.List;

import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.user.VO.joinVO;

public interface mainService {

	List<luxBoardVO> selectMain()throws Exception;

	luxBoardVO selectLuxBoard(int luxbNo)throws Exception;

	String deleteBoard(int luxbNo)throws Exception;

	String updateBoard(luxBoardVO lbvo)throws Exception;

	int totalCountBoard()throws Exception;

	List<luxBoardVO> addLuxBoard(int pageFlag)throws Exception;

	
	

}

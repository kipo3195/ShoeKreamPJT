package ShoeKream.user.service;



import java.util.List;

import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.user.VO.joinVO;
import ShoeKream.user.VO.loginVO;
import ShoeKream.user.VO.memberVO;

 
public interface userService {

	//회원가입
	void insertUser(joinVO joinvo) throws Exception;

	//로그인
	memberVO selectUser(loginVO loginvo) throws Exception;
	
	//계정찾기
	memberVO findUserid(String username,String userphone) throws Exception;
	
	//비밀번호 찾기 - 일치하는 정보 확인.
	memberVO findUserpass(joinVO joinvo) throws Exception;

	// 임시 코드 생성 -> 비밀번호로 변경, 이메일로 임시 코드 전송.
	void SendaPasswordCode(joinVO joinvo) throws Exception;

	joinVO findMyInfo(String userId)throws Exception;

	loginVO findMyPass(String userId)throws Exception;

	int changePassRequest(joinVO joinvo)throws Exception;

	int withDrawRequest(String myId, String myPass)throws Exception;

	int addMyCart(String userId, String luxbNo)throws Exception;

	List<luxBoardVO> selectLuxList(String userId)throws Exception;

	int deleteMyCart(String userId, String luxbNo)throws Exception;

	
	
}

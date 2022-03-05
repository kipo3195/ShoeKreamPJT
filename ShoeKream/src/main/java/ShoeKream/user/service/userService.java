package ShoeKream.user.service;



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
	
	
}

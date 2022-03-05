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
	
	
}

package ShoeKream.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ShoeKream.user.VO.joinVO;
import ShoeKream.user.VO.loginVO;
import ShoeKream.user.VO.memberVO;
import ShoeKream.user.mapper.userMapper;

@Service
public class userServiceImpl implements userService{

	@Autowired
	private userMapper usermapper;
	
	//회원가입
	@Override
	public void insertUser(joinVO joinvo) throws Exception {
		
		usermapper.insertUser(joinvo);
		
	}

	//로그인
	@Override
	public memberVO selectUser(loginVO loginvo) throws Exception {
		
		memberVO member= usermapper.selectUser(loginvo);
		return member;
		
	}

	//계정 찾기
	@Override
	public memberVO findUserid(String username, String userphone) throws Exception {

		memberVO member = usermapper.findUserid(username,userphone);
		
		return member;
	}

}

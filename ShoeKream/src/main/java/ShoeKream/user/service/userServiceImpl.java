package ShoeKream.user.service;

import java.util.Random;

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

	//비밀번호 찾기 시 계정이 존재하는지 확인.
	@Override
	public memberVO findUserpass(joinVO joinvo) throws Exception {
		memberVO member = usermapper.findUserpass(joinvo);
		
		return member;
	}

	
	//비밀번호 난수 생성 및 메일로 코드 전송. DB를 조작하여 전송한 코드를 비밀번호로 변경
	@Override
	public void SendaPasswordCode(joinVO joinvo) throws Exception {

		Random random = new Random();
		//난수 생성을 위한 StringBuffer
		StringBuffer sb = new StringBuffer();
		
		
		for(int i = 0; i < 4; i++) {
			int randomCode = (random.nextInt(9) +1);
			System.out.println(i+"번째 passCode : "+randomCode);
			sb.append(randomCode+"");
		}
		// 비밀번호를 난수로 변경
		String passwordCode = sb.toString();
		usermapper.changePassword(joinvo.getUserid(), passwordCode);
		
		// TO DO 이메일 전송 코드 들어갈 부분.
		
		
		
	
		
		
		

	}


	
	
	
	
	
	

}

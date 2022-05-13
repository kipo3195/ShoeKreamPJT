package ShoeKream.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import ShoeKream.admin.VO.luxBoardVO;
import ShoeKream.user.VO.joinVO;
import ShoeKream.user.VO.loginVO;
import ShoeKream.user.VO.memberVO;
import ShoeKream.user.mapper.userMapper;
import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

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
	public int SendaPasswordCode(joinVO joinvo, HttpServletRequest request) throws Exception {

		Random random = new Random();
		//난수 생성을 위한 StringBuffer
		StringBuffer sb = new StringBuffer();
		
		
		for(int i = 0; i < 6; i++) {
			int randomCode = (random.nextInt(9) +1);
			sb.append(randomCode+"");
		
		}
		// 비밀번호를 난수로 변경
		String passwordCode = sb.toString();
		//usermapper.changePassword(joinvo.getUserid(), passwordCode);
		
		//  전송 코드 DB삽입
		 usermapper.PassCodeInsert(joinvo.getUserid(), passwordCode);
		System.out.println(passwordCode);
		
		//사용자 전화번호
		String phoneNumber = joinvo.getUserphone();
		
		//코드전송
	    String api_key = "NCSYM12AV5FZDO4F";
        String api_secret = "KIY7AINDZCJHQHPN5S3NCPI2APRCOZGP";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("from", "010-7743-4521");    // 발신전화번호
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("type", "SMS");
        params.put("text", "[슈크림(본인)] 본인확인 인증번호["+passwordCode+"]" + "입니다. \n\"타인노출금지\"");
        params.put("app_version", "test app 1.2"); // application name and version

        int flag = 0;
        
        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            flag = 1;
        }
		
        System.out.println("문자 발신 성공!");
	
        return flag;
	}
	
	@Override
	public joinVO findMyInfo(String userId) throws Exception {
		
		joinVO myInfo = usermapper.selectMyInfo(userId);
		
		return myInfo;
	}

	@Override
	public loginVO findMyPass(String userId) throws Exception {
		
		loginVO myPass = usermapper.selectMyPass(userId);
		
		return myPass;
	}

	@Override
	public int changePassRequest(joinVO joinvo) throws Exception {
		
		int result = usermapper.changePassRequets(joinvo);
		return result;
	}

	@Override
	public int withDrawRequest(String myId, String myPass) throws Exception {
		
		int result = usermapper.withDrawRequest(myId,myPass);
		return result;
	}

	@Override
	public int addMyCart(String userId, String luxbNo) throws Exception {
		int result ;
		int rst = usermapper.selectMyCart(userId,luxbNo);
		
		if(rst == 1) {
			return 0;
		}else {
			result = usermapper.addMyCart(userId, luxbNo);
		}
		
		
		return result;
	}

	@Override
	public List<luxBoardVO> selectLuxList(String userId) throws Exception {
		
		List<String> userCart = usermapper.selectUserCart(userId);
		
		List<luxBoardVO> luxboardList = new ArrayList<luxBoardVO>();
		
		for(int i=0; i< userCart.size();i++) {
		String luxbNo = userCart.get(i)+"";
		luxBoardVO lbvo = usermapper.selectUserCartList(luxbNo);
		luxboardList.add(lbvo);
		}
		

		return luxboardList;
	}

	@Override
	public int deleteMyCart(String userId, String luxbNo) throws Exception {
		
		return usermapper.deleteMyCart(userId,luxbNo);
	}

	@Override
	public int deletePassCode(joinVO joinvo) throws Exception {
		
		String passwordCode = usermapper.selectPassCode(joinvo.getUserid());
		
		int rst = usermapper.PassCodeDelete(joinvo.getUserid(), passwordCode);		
		
		return rst;
	}

	@Override
	public int checkPassCode(String userId, String passCode) throws Exception {

		int rst = usermapper.checkPassCode(userId,passCode);
		
		return rst;
	}

	@Override
	public int updatePass(joinVO joinvo) throws Exception {
		System.out.println(joinvo.getUserid());
		int rst = usermapper.updatePass(joinvo.getUserid(),joinvo.getUserpass(),joinvo.getUserpass1());
		
		return rst;
	}
	
	
	
	
	


	
	
	
	
	
	

}
